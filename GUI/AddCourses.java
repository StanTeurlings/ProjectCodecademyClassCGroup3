package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.DatabaseConnector;
import Domain.Class.Course;
import Domain.Enummeration.Difficulty;

public class AddCourses {
    private Stage stage;
    private TextField nameField;
    private TextField subjectField;
    private TextField introductionTextField;
    private ChoiceBox<String> difficultyChoiceBox;

    public AddCourses(Stage stage) {
        this.stage = stage;
    }

    public Scene getAddCourseScene() {
        // Create navigation bar
        NavigationBar navigationBar = new NavigationBar(stage);
        VBox root = new VBox();
        root.getChildren().add(navigationBar.getMenuBar());

        // Create grid pane for course details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label nameLabel = new Label("Course Name:");
        nameField = new TextField();

        Label subjectLabel = new Label("Course Subject:");
        subjectField = new TextField();

        Label introductionTextLabel = new Label("Introduction Text:");
        introductionTextField = new TextField();

        Label difficultyLabel = new Label("Difficulty:");
        difficultyChoiceBox = new ChoiceBox<>();
        difficultyChoiceBox.getItems().addAll("Beginner", "Intermediate", "Expert");

        Button addButton = new Button("Add Course");
        addButton.setOnAction(e -> addCourse());

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(subjectLabel, 0, 1);
        gridPane.add(subjectField, 1, 1);
        gridPane.add(introductionTextLabel, 0, 2);
        gridPane.add(introductionTextField, 1, 2);
        gridPane.add(difficultyLabel, 0, 3);
        gridPane.add(difficultyChoiceBox, 1, 3);
        gridPane.add(addButton, 0, 7);

        root.getChildren().add(gridPane);

        return new Scene(root, 800, 600);
    }

    private Difficulty parseDifficulty(String difficultyString) {
        switch (difficultyString) {
            case "Beginner":
                return Difficulty.BEGINNER;
            case "Intermediate":
                return Difficulty.INTERMEDIATE;
            case "Expert":
                return Difficulty.EXPERT;
            default:
                return null;
        }
    }

    private void addCourse() {
        String name = nameField.getText();
        String subject = subjectField.getText();
        String introductionText = introductionTextField.getText();
        String difficultyString = difficultyChoiceBox.getValue();

        // Check if any field is empty or if difficulty is not selected
        if (name.isEmpty() || subject.isEmpty() || introductionText.isEmpty() || difficultyString == null) {
            System.err.println("All fields must be filled, and a difficulty must be selected.");
            return;
        }

        Difficulty difficulty = parseDifficulty(difficultyString);

        // Create a new Course object
        Course course = new Course(name, subject, introductionText, difficulty);

        // Insert the course into the database
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Course (CourseName, CourseSubject, IntroductionText, Difficulty) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, course.getCourseName());
                statement.setString(2, course.getCourseSubject());
                statement.setString(3, course.getIntroductionText());
                statement.setString(4, difficulty.name()); // Convert enum to string

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new course inserted successfully!");
                    // Redirect to the Courses page
                    Courses coursesPage = new Courses(stage);
                    stage.setScene(coursesPage.getCoursesLayout());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting course:");
            ex.printStackTrace();
        }
    }
}
