package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Homepage extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Codecademy");

        Button students = new Button("Students");
        Button cursusses = new Button("Cursusses");
        HBox buttons1 = new HBox();
        buttons1.getChildren().addAll(
                students,
                cursusses);

        Label Homepage = new Label("Homepage");
        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(
                Homepage,
                buttons1);

        Scene mainView = new Scene(mainBox);
        stage.setScene(mainView);
        stage.show();
    }
}