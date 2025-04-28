package controller;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenuController {
    private Stage stage;

    public MainMenuController(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Label label = new Label("Welcome to the Student Management System!");

        Button studentBtn = new Button("Students");
        studentBtn.setOnAction(e -> new StudentController(stage).show());

        Button courseBtn = new Button("Courses");
        courseBtn.setOnAction(e -> new CourseController(stage).show());

        Button enrollBtn = new Button("Enrollments");
        enrollBtn.setOnAction(e -> new EnrollmentController(stage).show());

        Button gradesBtn = new Button("Grades");
        gradesBtn.setOnAction(e -> new GradesController(stage).show());

        VBox layout = new VBox(15, label, studentBtn, courseBtn, enrollBtn, gradesBtn);
        layout.setStyle("-fx-padding: 20px");

        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Main Menu");
        stage.show();
    }
}