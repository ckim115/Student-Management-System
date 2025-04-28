package controller;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import data_access_objects.EnrollmentDAO;
import data_structures.Enrollment;
import javafx.collections.*;
import javafx.geometry.Orientation;

public class EnrollmentController {
    private Stage stage;
    private ObservableList<Enrollment> enrollments = FXCollections.observableArrayList(EnrollmentDAO.fetchAllEnrollments());
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    
    public EnrollmentController(Stage stage) {
        this.stage = stage;
    }

    public void show() {
    	
    	//make table for enrollment
    	
        TableView<Enrollment> table = new TableView<>(enrollments);
        table.setPrefHeight(150);

        TableColumn<Enrollment, Integer> idCol = new TableColumn<>("Enrollment ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("enrollmentID"));

        TableColumn<Enrollment, Integer> courseCol = new TableColumn<>("Course ID");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        TableColumn<Enrollment, Integer> studentCol = new TableColumn<>("Student ID");
        studentCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<Enrollment, Integer> enrollYear = new TableColumn<>("Enrollment Year");
        enrollYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.getColumns().addAll(idCol, studentCol, courseCol, enrollYear);
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20px");

        // --- ENROLL STUDENT ---
        TextField courseID = new TextField();
        courseID.setPromptText("Course ID");

        TextField studentID = new TextField();
        studentID.setPromptText("Student ID");

        TextField year = new TextField();
        year.setPromptText("Enrollment Year");

        Button enrollBtn = new Button("Enroll");
        VBox addBox = new VBox(10, new Label("Enroll Student To Course"), courseID, studentID, year, enrollBtn);

        // --- DELETE STUDENT FROM COURSE ---
        TextField deleteEnrollmentID = new TextField();
        deleteEnrollmentID.setPromptText("Delete EnrollmentID");

        Button deleteBtn = new Button("Delete Student From Course");
        VBox deleteBox = new VBox(10, new Label("Delete Student"), deleteEnrollmentID, deleteBtn);

        // --- UPDATE STUDENT FROM COURSE ---
        TextField updateEnrollmentID = new TextField();
        updateEnrollmentID.setPromptText("EnrollmentID (required)");
        TextField updateCourseID = new TextField();
        updateCourseID.setPromptText("CourseID (optional)");
        TextField updateStudentID = new TextField();
        updateStudentID.setPromptText("StudentID (optional)");
        TextField updateYear = new TextField();
        updateYear.setPromptText("Update Year (optional)");

        Button updateBtn = new Button("Update");

        VBox updateBox = new VBox(10, new Label("Update Student/Course Enrollment"), updateEnrollmentID, updateStudentID, updateCourseID, updateYear, updateBtn);

        // --- SEARCH/FILTER ROWS ---
        // conditions - filter through columns
        Label columnLabel = new Label("Select Columns:");
        CheckBox colEnrollmentID = new CheckBox("EnrollmentID");
        CheckBox colCourse = new CheckBox("courseID");
        CheckBox colStudent = new CheckBox("StudentID");
        CheckBox colEnrollmentYear = new CheckBox("EnrollmentYear");


        VBox columnSelectionBox = new VBox(5, columnLabel, colEnrollmentID, colStudent, colCourse, colEnrollmentYear);

        // separate cols and rows for filtering - looks better
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Conditions - filter through rows
        Label rowLabel = new Label("Select Rows:");

        // filter for EnrollmentID
        ComboBox<String> EnrollmentIDOperatorCombo = new ComboBox<>();
        EnrollmentIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        EnrollmentIDOperatorCombo.setValue("Select"); // placeholder-style default

        TextField enrollmentIDChoose = new TextField();
        enrollmentIDChoose.setPromptText("enrollmentID to filter");
        HBox enrollmentIDRow = new HBox(5, EnrollmentIDOperatorCombo, enrollmentIDChoose);
        
        // filter for studentID
        ComboBox<String> studentIDOperatorCombo1 = new ComboBox<>();
        studentIDOperatorCombo1.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        studentIDOperatorCombo1.setValue("Select"); // placeholder-style default

        TextField studentIDChoose1 = new TextField();
        studentIDChoose1.setPromptText("studentID to filter");
        HBox studentIDRow = new HBox(5, studentIDOperatorCombo1, studentIDChoose1);

        // filter for courseID
        ComboBox<String> courseIDOperatorCombo = new ComboBox<>();
        courseIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        courseIDOperatorCombo.setValue("Select"); // placeholder-style default

        TextField courseIDChoose = new TextField();
        courseIDChoose.setPromptText("courseID to filter");
        HBox courseIDRow1 = new HBox(5, courseIDOperatorCombo, courseIDChoose);

        // filter for EnrollmentYear (e.g. 2025)
        ComboBox<String> enrollmentYearOperatorCombo = new ComboBox<>();
        enrollmentYearOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        enrollmentYearOperatorCombo.setValue("Select"); // placeholder-style default

        TextField enrollmentChoose = new TextField();
        enrollmentChoose.setPromptText("enrollment year (e.g. 2025) to filter");
        HBox enrollmentYearRow = new HBox(5, enrollmentYearOperatorCombo, enrollmentChoose);

        VBox rowSelectionBox = new VBox(5, rowLabel, enrollmentIDRow, studentIDRow, courseIDRow1, enrollmentYearRow);

        HBox selectionsBox = new HBox(5, columnSelectionBox, separator, rowSelectionBox);

        Button searchBtn = new Button("Search/Filter Grade");



        VBox searchBox = new VBox(10, new Label("Search/Filter Grade With Conditions"), selectionsBox, searchBtn);

        // --- Place Add, Delete, Update, Search Side by Side ---
        HBox formSection = new HBox(30, addBox, deleteBox, updateBox, searchBox);
        
        // give actions to Enroll, Delete, Update, and Search buttons
        enrollBtn.setOnAction(e -> {
            try {
                int cID = Integer.parseInt(courseID.getText());
                int sID = Integer.parseInt(studentID.getText());
                int y = Integer.parseInt(year.getText());

                Enrollment enrollment = new Enrollment(-1, cID, sID, y); // Adjust constructor as needed
                enrollmentDAO.createEnrollmentRecord(cID, sID, y); // Instance method call

                // Refresh the list to show all enrollments
                enrollments.setAll(EnrollmentDAO.fetchAllEnrollments());

                idCol.setVisible(true);
                courseCol.setVisible(true);
                studentCol.setVisible(true);
                enrollYear.setVisible(true);

            } catch (NumberFormatException ex) {
                System.err.println("Please enter valid integers.");
            }
        });
        
        deleteBtn.setOnAction(e -> {
            int id = Integer.parseInt(deleteEnrollmentID.getText());
            Enrollment deleteGrade = new Enrollment(id);
            enrollmentDAO.deleteEnrollmentRecord(id);
            // Refresh the list to show all enrollments
            enrollments.setAll(EnrollmentDAO.fetchAllEnrollments());
            enrollments.setAll(enrollments);
            
            // Refresh the list to show all enrollments
            enrollments.setAll(EnrollmentDAO.fetchAllEnrollments());

            idCol.setVisible(true);
            courseCol.setVisible(true);
            studentCol.setVisible(true);
            enrollYear.setVisible(true);
        });
        
        updateBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(updateEnrollmentID.getText());

                Enrollment existingEnrollment = enrollments.stream()
                    .filter(s -> s.getEnrollmentID() == id)
                    .findFirst()
                    .orElse(null);

                if (existingEnrollment == null) {
                    System.err.println("Enrollment with ID " + id + " not found.");
                    return;
                }

                int newStudentID = updateStudentID.getText().isEmpty() ? existingEnrollment.getStudentID() : Integer.parseInt(updateStudentID.getText());
                int newCourseID = updateCourseID.getText().isEmpty() ? existingEnrollment.getCourseID() : Integer.parseInt(updateCourseID.getText());
                int newYear = updateYear.getText().isEmpty() ? existingEnrollment.getYear() : Integer.parseInt(updateYear.getText());

                Enrollment updated = new Enrollment(id, newCourseID, newStudentID, newYear);
                enrollmentDAO.updateEnrollmentRecord(id, newStudentID, newCourseID, newYear);

                // Refresh the list to show all enrollments
                enrollments.setAll(EnrollmentDAO.fetchAllEnrollments());
                enrollments.setAll(enrollments);
                // Refresh the list to show all enrollments
                enrollments.setAll(EnrollmentDAO.fetchAllEnrollments());

                idCol.setVisible(true);
                courseCol.setVisible(true);
                studentCol.setVisible(true);
                enrollYear.setVisible(true);
                
                

            } catch (NumberFormatException ex) {
                System.err.println("Please enter valid numeric values.");
            }
        });
        
        searchBtn.setOnAction(e -> {
            // List of selected columns
            List<String> selectedCols = new ArrayList<>();
            if (colEnrollmentID.isSelected()) selectedCols.add("enrollmentID");
            if (colCourse.isSelected()) selectedCols.add("courseID");
            if (colStudent.isSelected()) selectedCols.add("studentID");
            if (colEnrollmentYear.isSelected()) selectedCols.add("year");
            
            String colsString = selectedCols.isEmpty() ? "*" : String.join(", ", selectedCols);
            if (!selectedCols.contains("enrollmentID")) {
                colsString = "enrollmentID, " + colsString;  // ensure it's always included for object mapping
            }
            if (!selectedCols.contains("studentID")) {
                colsString = "studentID, " + colsString;  // ensure it's always included for object mapping
            }
            if (!selectedCols.contains("courseID")) {
                colsString = "courseID, " + colsString;  // ensure it's always included for object mapping
            }

            List<String> conditions = new ArrayList<>();

            // Create conditions for each filter
            String eidOp = EnrollmentIDOperatorCombo.getValue();
            String eidVal = enrollmentIDChoose.getText().trim();
            if (!"Select".equals(eidOp) && !eidVal.isEmpty()) {
                conditions.add("enrollmentID " + convertOperator(eidOp) + " " + eidVal);
            }

            String sidOp = courseIDOperatorCombo.getValue();
            String sidVal = courseIDChoose.getText().trim();
            if (!"Select".equals(sidOp) && !sidVal.isEmpty()) {
                conditions.add("studentID " + convertOperator(sidOp) + " " + sidVal);
            }

            String cidOp = studentIDOperatorCombo1.getValue();
            String cidVal = studentIDChoose1.getText().trim();
            if (!"Select".equals(cidOp) && !cidVal.isEmpty()) {
                conditions.add("courseID " + convertOperator(cidOp) + " " + cidVal);
            }

            String yearOp = enrollmentYearOperatorCombo.getValue();
            String yearVal = enrollmentChoose.getText().trim();
            if (!"Select".equals(yearOp) && !yearVal.isEmpty()) {
                conditions.add("year " + convertOperator(yearOp) + " " + yearVal);
            }

            // Build condition string for query
            String conditionString = String.join(" AND ", conditions);

            // Query the DAO to get the filtered enrollments
            List<Enrollment> validEnrollments = enrollmentDAO.searchEnrollmentRecord(colsString, conditionString);
            validEnrollments.removeIf(enrollment -> enrollment.getEnrollmentID() == -1); // Exclude invalid records

            // Set the filtered list to the ObservableList
            enrollments.setAll(validEnrollments);
            idCol.setVisible(colEnrollmentID.isSelected());
            courseCol.setVisible(colCourse.isSelected());
            studentCol.setVisible(colStudent.isSelected());
            enrollYear.setVisible(colEnrollmentYear.isSelected());
        });
        
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
