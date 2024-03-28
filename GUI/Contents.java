package GUI;

import Database.DatabaseConnector;
import Domain.Class.Content;
import Domain.Class.Course;
import Domain.Enummeration.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class Contents {
    private final TableView<Content> table = new TableView<>();
    private final ObservableList<Content> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Contents(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    @SuppressWarnings("unchecked")
    private void setupTableColumns() {
        TableColumn<Content, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Content, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Content, Date> publicationDateColumn = new TableColumn<>("Publication Date");
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));

        TableColumn<Content, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Content, Course> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        TableColumn<Content, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setCellFactory(col -> new TableCell<Content, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox hbox = new HBox(5, editButton, deleteButton);

            {
                editButton.setOnAction(e -> {
                    Content content = getTableView().getItems().get(getIndex());
                    editContent(content);
                });
                deleteButton.setOnAction(e -> {
                    Content content = getTableView().getItems().get(getIndex());
                    deleteContent(content);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });

        table.getColumns().addAll(titleColumn, descriptionColumn, publicationDateColumn, statusColumn, courseColumn, actionColumn);
        table.setItems(data);
    }

    private void loadDataFromDatabase() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to make database connection!");
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Content JOIN Course ON Content.CourseID = Course.CourseID")) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Course course = new Course(rs.getString("CourseName"), rs.getString("CourseSubject"), rs.getString("IntroductionText"), Difficulty.valueOf(rs.getString("Difficulty")));
                    Content content = new Content(
                        rs.getInt("ContentID"),
                        rs.getDate("PublicationDate"),
                        rs.getString("Status"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        course);
                    data.add(content);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }

    public Scene getContentsLayout() {
        NavigationBar navigationBar = new NavigationBar(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navigationBar.getMenuBar());
        borderPane.setCenter(table);

        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Contents");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        return scene;
    }

    private void editContent(Content content) {
        // Similar to editCourse, but for editing Content
    }

    private void deleteContent(Content content) {
        // Similar to deleteCourse, but for deleting Content
    }

    // Additional methods (updateContentInDatabase, deleteContentFromDatabase, refreshTableData, etc.)
    // Implement these similar to their counterparts in Courses.java
}
