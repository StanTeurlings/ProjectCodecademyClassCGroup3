package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Database.DatabaseConnector;
import Domain.Class.Content;
import Domain.Class.Course;
import Domain.Enummeration.Difficulty;
import Domain.Enummeration.Status;

public class AddContent {
    private Stage stage;
    private TextField titleField;
    private TextArea descriptionArea;
    private DatePicker publicationDatePicker;
    private ChoiceBox<Status> statusChoiceBox;
    private ChoiceBox<Course> courseNameChoiceBox;

    public AddContent(Stage stage) {
        this.stage = stage;
    }

    public Scene getAddContentScene() {
        NavigationBar navigationBar = new NavigationBar(stage);
        VBox root = new VBox();
        root.getChildren().add(navigationBar.getMenuBar());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label titleLabel = new Label("Title:");
        titleField = new TextField();

        Label descriptionLabel = new Label("Description:");
        descriptionArea = new TextArea();

        Label publicationDateLabel = new Label("Publication Date:");
        publicationDatePicker = new DatePicker();

        Label statusLabel = new Label("Status:");
        statusChoiceBox = new ChoiceBox<>();
        statusChoiceBox.getItems().addAll(Status.values());

        Label courseLabel = new Label("Course:");
        courseNameChoiceBox = new ChoiceBox<>();
        populateCourses();

        Button addButton = new Button("Add Content");
        addButton.setOnAction(e -> addContent());

        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleField, 1, 0);
        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionArea, 1, 1);
        gridPane.add(publicationDateLabel, 0, 2);
        gridPane.add(publicationDatePicker, 1, 2);
        gridPane.add(statusLabel, 0, 3);
        gridPane.add(statusChoiceBox, 1, 3);
        gridPane.add(courseLabel, 0, 4);
        gridPane.add(courseNameChoiceBox, 1, 4);
        gridPane.add(addButton, 0, 5);

        root.getChildren().add(gridPane);

        return new Scene(root, 800, 600);
    }

    private void populateCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "SELECT * FROM Course";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String courseName = resultSet.getString("CourseName");
                    String courseSubject = resultSet.getString("CourseSubject");
                    String introductionText = resultSet.getString("IntroductionText");
                    Difficulty difficulty = Difficulty.valueOf(resultSet.getString("Difficulty"));

                    Course course = new Course(courseName, courseSubject, introductionText, difficulty);
                    courses.add(course);
                }
                courseNameChoiceBox.setItems(courses);
            }
        } catch (SQLException ex) {
            System.err.println("Error populating courses:");
            ex.printStackTrace();
        }
    }

    private void addContent() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        Date publicationDate = java.sql.Date.valueOf(publicationDatePicker.getValue());
        Course course = courseNameChoiceBox.getValue();

        if (title.isEmpty() || description.isEmpty() || publicationDate == null || course == null) {
            System.err.println("All fields must be filled.");
            return;
        }

        Status status = statusChoiceBox.getValue();

        Content content = new Content(0, publicationDate, status.name(), title, description, course);

        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Content (Title, Description, PublicationDate, Status, CourseName) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, content.getTitle());
                statement.setString(2, content.getDescription());
                statement.setDate(3, new java.sql.Date(content.getPublicationDate().getTime()));
                statement.setString(4, content.getStatus());
                statement.setString(5, content.getCourse().getCourseName());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("New content added successfully!");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting content:");
            ex.printStackTrace();
        }
    }
}
