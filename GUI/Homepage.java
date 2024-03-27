package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Homepage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        // Buttons for different options
        Button studentsButton = new Button("Students");
        Button coursesButton = new Button("Courses");
        Button enrollmentsButton = new Button("Enrollments");
        Button certificatesButton = new Button("Certificates");

        studentsButton.setOnAction(e -> {
            Students studentsView = new Students(primaryStage);
            Scene studentLayout = studentsView.getStudentLayout(); // Change the type to Scene
            primaryStage.setScene(studentLayout); // Remove the 'new Scene' constructor
        });
        coursesButton.setOnAction(e -> {
            Students studentsView = new Students(primaryStage);
            Scene studentLayout = studentsView.getStudentLayout(); // Change the type to Scene
            primaryStage.setScene(studentLayout); // Remove the 'new Scene' constructor
        });
        enrollmentsButton.setOnAction(e -> {
            Students studentsView = new Students(primaryStage);
            Scene studentLayout = studentsView.getStudentLayout(); // Change the type to Scene
            primaryStage.setScene(studentLayout); // Remove the 'new Scene' constructor
        });
        certificatesButton.setOnAction(e -> {
            Students studentsView = new Students(primaryStage);
            Scene studentLayout = studentsView.getStudentLayout(); // Change the type to Scene
            primaryStage.setScene(studentLayout); // Remove the 'new Scene' constructor
        });

        VBox layout = new VBox(10, studentsButton, coursesButton, enrollmentsButton, certificatesButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
