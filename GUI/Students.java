package GUI;

import Database.DatabaseConnector;
import Domain.Class.Student;
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

public class Students {
    private final TableView<Student> table = new TableView<>();
    private final ObservableList<Student> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Students(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    private void setupTableColumns() {
        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn<Student, String> birthdateColumn = new TableColumn<>("Birthdate");
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Student, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Student, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        table.getColumns().addAll(emailColumn, nameColumn, birthdateColumn, genderColumn, addressColumn, cityColumn,
                countryColumn);
        table.setItems(data);
    }

    private void loadDataFromDatabase() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection()) {
            if (conn == null) {
                System.out.println("Failed to make database connection!");
                return;
            }

            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Student")) {

                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found.");
                    return;
                }

                while (rs.next()) {
                    Student student = new Student(
                            rs.getString("StudentEmail"),
                            rs.getString("StudentName"),
                            rs.getDate("Birthdate").toString(),
                            rs.getString("Gender"),
                            rs.getString("Address"),
                            rs.getString("City"),
                            rs.getString("Country"));
                    data.add(student);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }

    public Scene getStudentLayout() {
        NavigationBar navigationBar = new NavigationBar(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navigationBar.getMenuBar());
        borderPane.setCenter(table);

        Scene scene = new Scene(borderPane);
        return scene;
    }
}
