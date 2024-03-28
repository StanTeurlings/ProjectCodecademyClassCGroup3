package GUI;

import Domain.Class.Enrollment;
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

import Database.DatabaseConnector;

public class Enrollments {
    private final TableView<Enrollment> table = new TableView<>();
    private final ObservableList<Enrollment> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Enrollments(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    @SuppressWarnings("unchecked")
    private void setupTableColumns() {
        TableColumn<Enrollment, String> studentEmailColumn = new TableColumn<>("Student Email");
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));

        TableColumn<Enrollment, String> courseNameColumn = new TableColumn<>("Course Name");
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Enrollment, String> enrollmentDateColumn = new TableColumn<>("Enrollment Date");
        enrollmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));

        TableColumn<Enrollment, Integer> certificateIdColumn = new TableColumn<>("Certificate ID");
        certificateIdColumn.setCellValueFactory(new PropertyValueFactory<>("certificateId"));

        TableColumn<Enrollment, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Enrollment, String> employeeNameColumn = new TableColumn<>("Employee Name");
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        table.getColumns().addAll(studentEmailColumn, courseNameColumn,
                enrollmentDateColumn, certificateIdColumn, gradeColumn, employeeNameColumn);
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
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Enrollment")) {

                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found.");
                    return;
                }

                while (rs.next()) {
                    Enrollment enrollment = new Enrollment(
                            rs.getString("StudentEmail"),
                            rs.getString("CourseName"),
                            rs.getString("EnrollmentDate"),
                            rs.getInt("CertificateId"),
                            rs.getString("Grade"),
                            rs.getString("EmployeeName"));
                    data.add(enrollment);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }

    public Scene getEnrollmentsLayout() {
        NavigationBar navigationBar = new NavigationBar(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navigationBar.getMenuBar());
        borderPane.setCenter(table);

        Scene scene = new Scene(borderPane);
        return scene;
    }
}
