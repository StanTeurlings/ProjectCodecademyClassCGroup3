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
            Courses coursesView = new Courses(primaryStage); // Create an instance of the Courses view
            Scene coursesLayout = coursesView.getCoursesLayout(); // Get the Courses scene
            primaryStage.setScene(coursesLayout); // Set the Courses scene to the primary stage
        });
        enrollmentsButton.setOnAction(e -> {
            Enrollments enrollmentsView = new Enrollments(primaryStage); // Assuming Enrollments class is defined
            Scene enrollmentsLayout = enrollmentsView.getEnrollmentsLayout(); // Change the method name accordingly
            primaryStage.setScene(enrollmentsLayout);
        });
        certificatesButton.setOnAction(e -> {
            Certificates certificatesView = new Certificates(primaryStage); // Assuming you have a Certificates class
            Scene certificatesLayout = certificatesView.getCertificatesLayout(); // Change the method name if needed
            primaryStage.setScene(certificatesLayout); // Set the scene to the certificates layout
        });

        VBox layout = new VBox(10, studentsButton, coursesButton, enrollmentsButton, certificatesButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
