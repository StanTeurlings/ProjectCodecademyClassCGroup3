package GUI;

import Database.DatabaseConnector;
import Domain.Class.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().studentEmailProperty());

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());

        TableColumn<Student, String> birthdateColumn = new TableColumn<>("Birthdate");
        birthdateColumn.setCellValueFactory(cellData -> cellData.getValue().birthdateProperty());

        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        TableColumn<Student, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        TableColumn<Student, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());

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
        // Create a MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus (eerste laag)
        Menu homeMenu = new Menu("Home");
        Menu studentsMenu = new Menu("Students");

        // Create studentsMenu items (tweede laag)
        MenuItem addStudent = new MenuItem("New Student");
        MenuItem editStudent = new MenuItem("Edit Student");
        MenuItem deleteStudent = new MenuItem("Delete Student");
        studentsMenu.getItems().addAll(addStudent, editStudent, deleteStudent);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(homeMenu, studentsMenu);

        // Link back to homepage
        homeMenu.setOnAction(e -> {
            Homepage homepage = new Homepage();
            homepage.start(primaryStage); // This will set the primary stage's scene to the homepage scene
        });

        // Creating the VBox and adding the table to it was in your original code
        VBox vBox = new VBox(menuBar, table); // Now the VBox includes the menuBar

        // Create a BorderPane and add the VBox to the center
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar); // Setting the MenuBar at the top
        borderPane.setCenter(vBox); // Adding VBox to the center which includes the table

        // Create and return the scene with the BorderPane
        Scene scene = new Scene(borderPane);
        return scene;
    }
}
