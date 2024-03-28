package GUI;

import Database.DatabaseConnector;
import Domain.Class.Course;
import Domain.Enummeration.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Courses {
    private final TableView<Course> table = new TableView<>();
    private final ObservableList<Course> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Courses(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    @SuppressWarnings("unchecked")
    private void setupTableColumns() {
        TableColumn<Course, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Course, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("courseSubject"));

        TableColumn<Course, String> introColumn = new TableColumn<>("Introduction Text");
        introColumn.setCellValueFactory(new PropertyValueFactory<>("introductionText"));

        TableColumn<Course, Difficulty> difficultyColumn = new TableColumn<>("Difficulty");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        TableColumn<Course, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setCellFactory(col -> new TableCell<Course, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox hbox = new HBox(5, editButton, deleteButton);

            {
                editButton.setOnAction(e -> {
                    Course course = getTableView().getItems().get(getIndex());
                    editCourse(course);
                });
                deleteButton.setOnAction(e -> {
                    Course course = getTableView().getItems().get(getIndex());
                    deleteCourse(course);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });

        table.getColumns().addAll(nameColumn, subjectColumn, introColumn, difficultyColumn, actionColumn);
        table.setItems(data);
    }

    private void loadDataFromDatabase() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to make database connection!");
                return;
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Course")) {

                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found.");
                    return;
                }

                while (rs.next()) {
                    String difficultyStr = rs.getString("Difficulty");
                    Difficulty difficulty = Difficulty.valueOf(difficultyStr.toUpperCase()); // Assuming the difficulty
                    // values in the database
                    // match the enum names
                    Course course = new Course(
                            rs.getString("CourseName"),
                            rs.getString("CourseSubject"),
                            rs.getString("IntroductionText"),
                            difficulty);
                    data.add(course);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }

    public Scene getCoursesLayout() {
        NavigationBar navigationBar = new NavigationBar(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navigationBar.getMenuBar());
        borderPane.setCenter(table);

        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Courses");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        return scene;
    }

    private void editCourse(Course course) {
        try {
            // Create a dialog for editing the course
            Dialog<Course> dialog = new Dialog<>();
            dialog.setTitle("Edit Course");
            dialog.setHeaderText("Edit Course Details");

            // Define the buttons for saving and canceling the edit
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            // Create a grid pane to organize the input fields
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            // Add text fields and other input controls for editing course details
            TextField nameField = new TextField(course.getCourseName());
            TextField subjectField = new TextField(course.getCourseSubject());
            TextArea introTextArea = new TextArea(course.getIntroductionText());
            ComboBox<Difficulty> difficultyComboBox = new ComboBox<>();
            difficultyComboBox.getItems().addAll(Difficulty.values());
            difficultyComboBox.setValue(course.getDifficulty());

            // Add labels and input fields to the grid pane
            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Subject:"), 0, 1);
            grid.add(subjectField, 1, 1);
            grid.add(new Label("Introduction Text:"), 0, 2);
            grid.add(introTextArea, 1, 2);
            grid.add(new Label("Difficulty:"), 0, 3);
            grid.add(difficultyComboBox, 1, 3);

            // Set the content of the dialog pane to the grid pane
            dialog.getDialogPane().setContent(grid);

            // Set the result converter to handle saving changes when the save button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    // Update the course object with the new values from the input fields
                    course.setCourseName(nameField.getText());
                    course.setCourseSubject(subjectField.getText());
                    course.setIntroductionText(introTextArea.getText());
                    course.setDifficulty(difficultyComboBox.getValue());
                    return course;
                }
                return null; // Return null if cancel button is clicked
            });

            // Show the dialog and wait for the user to close it
            Optional<Course> result = dialog.showAndWait();

            // If the user clicked save, update the course in the database and refresh the table
            result.ifPresent(updatedCourse -> {
                updateCourseInDatabase(updatedCourse);
                refreshTableData();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCourseInDatabase(Course course) {
        String sql = "UPDATE Course SET CourseName = ?, CourseSubject = ?, IntroductionText = ?, Difficulty = ? WHERE CourseName = ?";
    
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseSubject());
            stmt.setString(3, course.getIntroductionText());
            stmt.setString(4, course.getDifficulty().toString());
            stmt.setString(5, course.getCourseName()); // Assuming CourseName is the primary key
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    private void deleteCourse(Course course) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Course");
        alert.setContentText("Are you sure you want to delete the course: " + course.getCourseName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (deleteCourseFromDatabase(course.getCourseName())) {
                data.remove(course);
                System.out.println("Deleted course: " + course.getCourseName());
            } else {
                System.out.println("Failed to delete course: " + course.getCourseName());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private boolean deleteCourseFromDatabase(String courseName) {
        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Course WHERE CourseName = ?")) {
            stmt.setString(1, courseName);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    private void refreshTableData() {
        data.clear();
        loadDataFromDatabase(); // Assuming you have this method to load data
    }
}
