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

        Button studentsButton = new Button("Students");
        studentsButton.setOnAction(e -> {
            Students studentsView = new Students(primaryStage);
            Scene studentLayout = studentsView.getStudentLayout(); // Change the type to Scene
            primaryStage.setScene(studentLayout); // Remove the 'new Scene' constructor
        });

        VBox layout = new VBox(10, studentsButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
