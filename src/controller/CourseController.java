package controller;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import data_access_objects.CourseDAO;
import javafx.collections.*;
import javafx.geometry.Orientation;

public class CourseController {
    private Stage stage;
    private ObservableList<CourseDAO> courses = FXCollections.observableArrayList(CourseDAO.fetchAllCourses());

    public CourseController(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20px");
        
        // make the table for courses
        TableView<CourseDAO> table = new TableView<>(courses);
        table.setPrefHeight(150);
        
        TableColumn<CourseDAO, Integer> idCourse = new TableColumn<>("Course ID");
        idCourse.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<CourseDAO, String> courseNameCol = new TableColumn<>("Course Name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        TableColumn<CourseDAO, String> teacherNameCol = new TableColumn<>("Teacher Name");
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        TableColumn<CourseDAO, Integer> semesterYear = new TableColumn<>("Semester");
        semesterYear.setCellValueFactory(new PropertyValueFactory<>("semester"));
        table.getColumns().addAll(idCourse, courseNameCol, teacherNameCol, semesterYear);

        // --- ADD COURSE ---
        TextField name = new TextField();
        name.setPromptText("Course Name");
        TextField teacher = new TextField();
        teacher.setPromptText("Teacher Name");
        TextField semester = new TextField();
        semester.setPromptText("Semester");

        Button addBtn = new Button("Add Course");
        
        VBox addBox = new VBox(10, new Label("Add Course"), name, teacher, semester, addBtn);
        
        // --- DELETE COURSE ---
        TextField deleteByID = new TextField();
        deleteByID.setPromptText("CourseID to delete Course");

        Button deleteBtn = new Button("Delete Course");
        

        VBox deleteBox = new VBox(10, new Label("Delete Course"), deleteByID, deleteBtn);
        
        // --- UPDATE COURSE ---
        TextField updateByID = new TextField();
        updateByID.setPromptText("CourseID (required)");
        TextField updateFirst = new TextField();
        updateFirst.setPromptText("Updated Course Name (if needed)");
        TextField updateLast = new TextField();
        updateLast.setPromptText("Updated Teacher Name (if needed)");
        TextField updateSemester = new TextField();
        updateSemester.setPromptText("Updated Semester (if needed)");
        
        Button updateBtn = new Button("Update Course Records");

        VBox updateBox = new VBox(10, new Label("Update Course"), updateByID, updateFirst, updateLast, updateSemester, updateBtn);
        
        // --- SEARCH/FILTER Courses ---
        Label columnLabel = new Label("Select Columns:");
        CheckBox colCourseID = new CheckBox("courseId");
        CheckBox colCourseName = new CheckBox("Course Name");
        CheckBox colTeacherName = new CheckBox("Teacher Name");
        CheckBox colSemesterYear = new CheckBox("Semester and year");

        VBox columnSelectionBox = new VBox(5, columnLabel, colCourseID, colCourseName, colTeacherName, colSemesterYear);
        
        // separate cols and rows for filtering - looks better
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        // Conditions - filter through rows
        
        Label rowLabel = new Label("Select Rows:");
        // filter for courseID
        ComboBox<String> courseIDOperatorCombo = new ComboBox<>();
        courseIDOperatorCombo.getItems().addAll("Select", "<", "≤", "=", "≥", ">");
        courseIDOperatorCombo.setValue("Select"); // placeholder-style default
        TextField courseIDChoose = new TextField();
        courseIDChoose.setPromptText("courseID to filter");
        HBox courseIDRow = new HBox(5, courseIDOperatorCombo, courseIDChoose);
        
        // filter for course name
        ComboBox<String> courseNameOperatorCombo = new ComboBox<>();
        courseNameOperatorCombo.getItems().addAll("Select", "="); // course name is string, so can only be operator only = for now
        courseNameOperatorCombo.setValue("Select"); // placeholder-style default
        TextField courseNameChoose = new TextField();
        courseNameChoose.setPromptText("Course Name to filter");
        HBox courseNameRow = new HBox(5, courseNameOperatorCombo, courseNameChoose);
        
        // filter for teacher name
        ComboBox<String> teacherNameOperatorCombo = new ComboBox<>(); // teacher name is string, so can only be operator only = for now
        teacherNameOperatorCombo.getItems().addAll("Select", "=");
        teacherNameOperatorCombo.setValue("Select"); // placeholder-style default
        TextField teacherNameChoose = new TextField();
        teacherNameChoose.setPromptText("Teacher Name to filter");
        HBox teacherRow = new HBox(5, teacherNameOperatorCombo, teacherNameChoose);
        
        // filter for semester year
        ComboBox<String> semesterOperatorCombo = new ComboBox<>();
        semesterOperatorCombo.getItems().addAll("Select", "="); // teacher name is string, so can only be operator only = for now
        semesterOperatorCombo.setValue("Select"); // placeholder-style default
        TextField semesterChoose = new TextField();
        semesterChoose.setPromptText("Semester year (e.g. Spring 2025) to filter");
        HBox semesterRow = new HBox(5, semesterOperatorCombo, semesterChoose);
        
        VBox rowSelectionBox = new VBox(5, rowLabel, courseIDRow, courseNameRow, teacherRow, semesterRow);
        
        HBox selectionsBox = new HBox(5, columnSelectionBox, separator, rowSelectionBox);


        Button searchBtn = new Button("Filter Courses");
        
        VBox searchBox = new VBox(10, new Label("Search/Filter Grade With Conditions"), selectionsBox, searchBtn);
        
        // --- Place Add and Delete and Update and Filter Side by Side ---
        HBox formSection = new HBox(30, addBox, deleteBox, updateBox, searchBox);
        
        // add actions to add, delete, update, search buttons
        addBtn.setOnAction(e -> {
            CourseDAO course = new CourseDAO(-1, name.getText(), teacher.getText(), semester.getText());
            course.createCourseRecord(name.getText(), teacher.getText(), semester.getText());
            courses.setAll(CourseDAO.fetchAllCourses());
            
            // make sure all columns visible in case filter button was used
            idCourse.setVisible(true);
            courseNameCol.setVisible(true);
            teacherNameCol.setVisible(true);
            semesterYear.setVisible(true);
        });
        
        deleteBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(deleteByID.getText());
                CourseDAO course = new CourseDAO(id);
                course.deleteCourseRecord(id);
                courses.setAll(CourseDAO.fetchAllCourses());
                
             // make sure all columns visible in case filter button was used
                idCourse.setVisible(true);
                courseNameCol.setVisible(true);
                teacherNameCol.setVisible(true);
                semesterYear.setVisible(true);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid ID format.");
            }
        });
        
        updateBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(updateByID.getText());
                CourseDAO existing = courses.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);
                if (existing == null) {
                    //showAlert("Course with ID " + id + " not found.");
                    return;
                }

                String courseName  = updateFirst.getText().isEmpty()  ? existing.getCourseName() : updateFirst.getText();
                String teacherName = updateLast.getText().isEmpty()   ? existing.getInstructor(): updateLast.getText();
                String semesterVal = updateSemester.getText().isEmpty() ? existing.getSemester()   : updateSemester.getText();

                CourseDAO c = new CourseDAO(id, courseName, teacherName, semesterVal);
                c.updateCourseRecord(id, courseName, teacherName, semesterVal);
                
                // make sure all columns visible in case filter button was used
                idCourse.setVisible(true);
                courseNameCol.setVisible(true);
                teacherNameCol.setVisible(true);
                semesterYear.setVisible(true);

            } catch (NumberFormatException ex) {
                //showAlert("Invalid ID format.");
            }
        });
        
        searchBtn.setOnAction(e -> {
        	List<String> selectedCols = new ArrayList<>();
            if (colCourseID.isSelected()) selectedCols.add("courseID");
            if (colCourseName.isSelected()) selectedCols.add("course_name");
            if (colTeacherName.isSelected()) selectedCols.add("instructor");
            if (colSemesterYear.isSelected()) selectedCols.add("semester");
            String colsString = selectedCols.isEmpty() ? "*" : String.join(", ", selectedCols);
            if (!selectedCols.contains("courseID")) {
                colsString = "courseID, " + colsString;  // ensure it's always included for object mapping
            }
            
            // Convert selected columns to string for search

            List<String> conditions = new ArrayList<>();
            
            // Create conditions for each filter
            String eidOp = courseIDOperatorCombo.getValue();
            String eidVal = courseIDChoose.getText().trim();
            if (!"Select".equals(eidOp) && !eidVal.isEmpty()) {
                conditions.add("courseID " + convertOperator(eidOp) + " " + eidVal);
            }

            String sidOp = courseNameOperatorCombo.getValue();
            String sidVal = courseNameChoose.getText().trim();
            if (!"Select".equals(sidOp) && !sidVal.isEmpty()) {
                conditions.add("course_name " + convertOperator(sidOp) + " " + sidVal);
            }

            String cidOp = teacherNameOperatorCombo.getValue();
            String cidVal = teacherNameChoose.getText().trim();
            if (!"Select".equals(cidOp) && !cidVal.isEmpty()) {
                conditions.add("instructor " + convertOperator(cidOp) + " " + cidVal);
            }

            String yearOp = semesterOperatorCombo.getValue();
            String yearVal = semesterChoose.getText().trim();
            if (!"Select".equals(yearOp) && !yearVal.isEmpty()) {
                conditions.add("semester " + convertOperator(yearOp) + " " + yearVal);
            }
            
            // Build condition string for query
            String conditionString = String.join(" AND ", conditions);
            
            // Query the DAO to get the filtered courses
            CourseDAO dao = new CourseDAO(0);
            List<CourseDAO> validCourses = dao.searchCourseRecord(colsString, conditionString);
            validCourses.removeIf(course -> course.getId() == -1); // Exclude invalid records
            
            // Set the filtered list to the ObservableList
            courses.setAll(validCourses);
            idCourse.setVisible(colCourseID.isSelected());
            courseNameCol.setVisible(colCourseName.isSelected());
            teacherNameCol.setVisible(colTeacherName.isSelected());
            semesterYear.setVisible(colSemesterYear.isSelected());
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