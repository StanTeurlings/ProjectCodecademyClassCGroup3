package GUI;

import Database.DatabaseConnector;
import Domain.Class.Course;
import Domain.Enummeration.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course Name");
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Course, String> courseSubjectColumn = new TableColumn<>("Course Subject");
        courseSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("courseSubject"));

        TableColumn<Course, String> introductionTextColumn = new TableColumn<>("Introduction Text");
        introductionTextColumn.setCellValueFactory(new PropertyValueFactory<>("introductionText"));

        TableColumn<Course, String> difficultyColumn = new TableColumn<>("Difficulty");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        // Add additional columns if needed

        table.getColumns().addAll(courseNameColumn, courseSubjectColumn, introductionTextColumn, difficultyColumn);
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
        return scene;
    }
}
