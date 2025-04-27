package controller;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import data_access_objects.StudentDAO;
import data_structures.Student;
import javafx.collections.*;
import javafx.geometry.Orientation;

public class StudentController {
    private Stage stage;
    private ObservableList<Student> students = FXCollections.observableArrayList(StudentDAO.fetchAllStudents());
    StudentDAO studentDAO = new StudentDAO();

    public StudentController(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20px");
        
        // --- Student Table ---
        TableView<Student> table = new TableView<>(students);
        table.setPrefHeight(150);
        TableColumn<Student, Integer> studentIDCol = new TableColumn<>("Student ID");
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Student, String> studentFirstCol = new TableColumn<>("First Name");
        studentFirstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Student, String> studentLastCol = new TableColumn<>("Last Name");
        studentLastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Student, Date> studentBirthCol = new TableColumn<>("Birth Date");
        studentBirthCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        table.getColumns().addAll(studentIDCol, studentFirstCol, studentLastCol, studentBirthCol);
        
        

        // --- Add Student Section ---
        TextField firstName = new TextField();
        firstName.setPromptText("First Name");
        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");
        DatePicker birthDate = new DatePicker();
        birthDate.setPromptText("Student Birthday");

        Button addBtn = new Button("Add Student");

        VBox addBox = new VBox(10, new Label("Add Student"), firstName, lastName, birthDate, addBtn);

        // --- Delete Student Section ---
        TextField deleteByID = new TextField();
        deleteByID.setPromptText("StudentID to delete Student");

        Button deleteBtn = new Button("Delete Student");

        VBox deleteBox = new VBox(10, new Label("Delete Student"), deleteByID, deleteBtn);
        
        // --- Update Student Section ---
        TextField updateByID = new TextField();
        updateByID.setPromptText("StudentID (required)");
        TextField updateFirst = new TextField();
        updateFirst.setPromptText("Updated First Name (if needed)");
        TextField updateLast = new TextField();
        updateLast.setPromptText("Updated Last Name (if needed)");
        DatePicker updateDate = new DatePicker();
        updateDate.setPromptText("Updated Birthdate (if needed)");
        
        Button updateBtn = new Button("Update Student Records");

        
        VBox updateBox = new VBox(10, new Label("Update Student"), updateByID, updateFirst, updateLast, updateDate, updateBtn);
        
        // --- Filter/Search Rows ---
        // conditions - filter through columns
        Label columnLabel = new Label("Select Columns:");
        CheckBox colstudentID = new CheckBox("studentID");
        CheckBox colFirstName = new CheckBox("First Name");
        CheckBox colLastName = new CheckBox("Last Name");
        CheckBox colBirthDate = new CheckBox("Birth Date");

        VBox columnSelectionBox = new VBox(5, columnLabel, colstudentID, colFirstName, colLastName, colBirthDate);
        
        
        // separate cols and rows for filtering - looks better
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        // Conditions - filter through rows
        
        Label rowLabel = new Label("Select Rows:");
        // filter for ID
        ComboBox<String> studentIDOperatorCombo = new ComboBox<>();
        studentIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        studentIDOperatorCombo.setValue("Select"); // placeholder-style default
        TextField studentIDChoose = new TextField();
        studentIDChoose.setPromptText("studentID to filter");
        HBox studentIDRow = new HBox(5, studentIDOperatorCombo, studentIDChoose);
        
        // filter for First Name
        ComboBox<String> studentFirstNameOperatorCombo = new ComboBox<>();
        studentFirstNameOperatorCombo.getItems().addAll("Select", "="); // first name is string, so can only be operator only = for now
        studentFirstNameOperatorCombo.setValue("Select"); // placeholder-style default
        TextField studentFirstChoose = new TextField();
        studentFirstChoose.setPromptText("first name to filter");
        HBox studentFirstRow = new HBox(5, studentFirstNameOperatorCombo, studentFirstChoose);
        
        // filter for Last Name
        ComboBox<String> studentLastNameOperatorCombo = new ComboBox<>();
        studentLastNameOperatorCombo.getItems().addAll("Select", "="); // last name is string, so can only be operator only = for now
        studentLastNameOperatorCombo.setValue("Select"); // placeholder-style default
        TextField studentLastChoose = new TextField();
        studentLastChoose.setPromptText("Last Name to filter");
        HBox studentLastRow = new HBox(5, studentLastNameOperatorCombo, studentLastChoose);
        
        // filter for Birthdate
        ComboBox<String> studentBirthDateOperatorCombo = new ComboBox<>();
        studentBirthDateOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        studentBirthDateOperatorCombo.setValue("Select"); // placeholder-style default
        TextField studentBirthDateChoose = new TextField();
        studentBirthDateChoose.setPromptText("birth date to filter");
        HBox studentBirthDateRow = new HBox(5, studentBirthDateOperatorCombo, studentBirthDateChoose);
        
        VBox rowSelectionBox = new VBox(5, rowLabel, studentIDRow, studentFirstRow, studentLastRow, studentBirthDateRow);
        
        HBox selectionsBox = new HBox(5, columnSelectionBox, separator, rowSelectionBox);


        Button searchBtn = new Button("Filter Students");
        
        VBox searchBox = new VBox(10, new Label("Search/Filter Grade With Conditions"), selectionsBox, searchBtn);

        // --- Place Add, Delete, Update, Filter/Search Side by Side ---
        HBox formSection = new HBox(30, addBox, deleteBox, updateBox, searchBox);
        
        // --- Make Action for Add, Delete, Update, Filter/Search Buttons ---
        addBtn.setOnAction(e -> {
        	studentDAO.createStudentRecord(firstName.getText(), lastName.getText(), birthDate.getValue().toString());
            students.setAll(StudentDAO.fetchAllStudents());
            
            studentIDCol.setVisible(true);
            studentFirstCol.setVisible(true);
            studentLastCol.setVisible(true);
            studentBirthCol.setVisible(true);
        });
        
        deleteBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(deleteByID.getText());
                Student student = new Student(id);
                studentDAO.deleteStudentRecord(id);
                students.setAll(StudentDAO.fetchAllStudents());
                studentIDCol.setVisible(true);
                studentFirstCol.setVisible(true);
                studentLastCol.setVisible(true);
                studentBirthCol.setVisible(true);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid ID format.");
                
            }
        });
        
        updateBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(updateByID.getText());
                // Find the existing student
                Student existingStudent = students.stream()
                    .filter(s -> s.getId() == id)
                    .findFirst()
                    .orElse(null);

                if (existingStudent == null) {
                    //showAlert("Student with ID " + id + " not found.");
                    return;
                }

                String newFirst = updateFirst.getText().isEmpty() ? existingStudent.getFirstName() : updateFirst.getText();
                String newLast = updateLast.getText().isEmpty() ? existingStudent.getLastName() : updateLast.getText();
                String newDate = (updateDate.getValue() == null) ? existingStudent.getBirthDate() : updateDate.getValue().toString();

                Student student = new Student(id, newFirst, newLast, newDate);
                studentDAO.updateStudentRecord(id, newFirst, newLast, newDate);
                students.setAll(StudentDAO.fetchAllStudents());
                studentIDCol.setVisible(true);
                studentFirstCol.setVisible(true);
                studentLastCol.setVisible(true);
                studentBirthCol.setVisible(true);

            } catch (NumberFormatException ex) {
                //showAlert("Invalid ID format.");
            }
        });
        
        searchBtn.setOnAction(e -> {
            List<String> selectedCols = new ArrayList<>();
            if (colstudentID.isSelected()) selectedCols.add("studentID");
            if (colFirstName.isSelected()) selectedCols.add("first_name");  // fixed column name
            if (colLastName.isSelected()) selectedCols.add("last_name");
            if (colBirthDate.isSelected()) selectedCols.add("birthdate");
            
            String colsString = selectedCols.isEmpty() ? "*" : String.join(", ", selectedCols);
            if (!selectedCols.contains("studentID")) {
                colsString = "studentID, " + colsString;  // ensure it's always included for object mapping
            }

            List<String> conditions = new ArrayList<>();

            String eidOp = studentIDOperatorCombo.getValue();
            String eidVal = studentIDChoose.getText().trim();
            if (!"Select".equals(eidOp) && !eidVal.isEmpty()) {
                conditions.add("studentID " + convertOperator(eidOp) + " " + eidVal); // numeric
            }

            String sidOp = studentFirstNameOperatorCombo.getValue();
            String sidVal = studentFirstChoose.getText().trim();
            if (!"Select".equals(sidOp) && !sidVal.isEmpty()) {
                conditions.add("first_name " + convertOperator(sidOp) + " '" + sidVal + "'"); // string
            }

            String cidOp = studentLastNameOperatorCombo.getValue();
            String cidVal = studentLastChoose.getText().trim();
            if (!"Select".equals(cidOp) && !cidVal.isEmpty()) {
                conditions.add("last_name " + convertOperator(cidOp) + " '" + cidVal + "'");
            }

            String yearOp = studentBirthDateOperatorCombo.getValue();
            String yearVal = studentBirthDateChoose.getText().trim();
            if (!"Select".equals(yearOp) && !yearVal.isEmpty()) {
                conditions.add("birthdate " + convertOperator(yearOp) + " '" + yearVal + "'");
            }

            String conditionString = String.join(" AND ", conditions);

            StudentDAO dao = new StudentDAO();
            List<Student> validStudents = dao.searchStudentRecord(colsString, conditionString);
            validStudents.removeIf(student -> student.getId() == -1);

            students.setAll(validStudents);
            studentIDCol.setVisible(colstudentID.isSelected());
            studentFirstCol.setVisible(colFirstName.isSelected());
            studentLastCol.setVisible(colLastName.isSelected());
            studentBirthCol.setVisible(colBirthDate.isSelected());

        });


        // --- Back Button ---
        Button back = new Button("Back");
        back.setOnAction(e -> new MainMenuController(stage).show());

        layout.getChildren().addAll(formSection, table, back);
        stage.setScene(new Scene(layout, 1000, 400));
    }
    
    private String convertOperator(String op) {
        return switch (op) {
            case "≤" -> "<=";
            case "≥" -> ">=";
            default -> op;
        };
    }

}