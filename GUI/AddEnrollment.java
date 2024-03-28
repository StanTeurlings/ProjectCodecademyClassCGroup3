package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Database.DatabaseConnector;

public class AddEnrollment {
    private Stage stage;
    private ChoiceBox<String> studentEmailChoiceBox;
    private ChoiceBox<String> courseNameChoiceBox;
    private DatePicker enrollmentDatePicker;
    private TextField certificateIdField;
    private TextField gradeField;
    private TextField employeeNameField;

    public AddEnrollment(Stage stage) {
        this.stage = stage;
    }

    public Scene getAddEnrollmentScene() {
        // Create navigation bar
        NavigationBar navigationBar = new NavigationBar(stage);
        VBox root = new VBox();
        root.getChildren().add(navigationBar.getMenuBar());

        // Create grid pane for enrollment details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        enrollmentDatePicker = new DatePicker();
        certificateIdField = new TextField();
        gradeField = new TextField();
        employeeNameField = new TextField();

        Button addButton = new Button("Add Enrollment");
        addButton.setOnAction(e -> addEnrollment());

        gridPane.add(new Label("Student Email:"), 0, 0);
        gridPane.add(studentEmailChoiceBox, 1, 0);
        gridPane.add(new Label("Course Name:"), 0, 1);
        gridPane.add(courseNameChoiceBox, 1, 1);
        gridPane.add(new Label("Enrollment Date:"), 0, 2);
        gridPane.add(enrollmentDatePicker, 1, 2);
        gridPane.add(new Label("Certificate ID:"), 0, 3);
        gridPane.add(certificateIdField, 1, 3);
        gridPane.add(new Label("Grade:"), 0, 4);
        gridPane.add(gradeField, 1, 4);
        gridPane.add(new Label("Employee Name:"), 0, 5);
        gridPane.add(employeeNameField, 1, 5);
        gridPane.add(addButton, 0, 6);

        root.getChildren().add(gridPane);

        return new Scene(root, 400, 300);
    }

    private void addEnrollment() {
        String studentEmail = studentEmailChoiceBox.getValue();
        String courseName = courseNameChoiceBox.getValue();
        LocalDate enrollmentDate = enrollmentDatePicker.getValue();
        int certificateId = Integer.parseInt(certificateIdField.getText());
        String grade = gradeField.getText();
        String employeeName = employeeNameField.getText();

        // Validate input fields here...

        // Insert the enrollment into the database
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Enrollment (StudentEmail, CourseName, EnrollmentDate, CertificateId, Grade, EmployeeName) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, studentEmail);
                statement.setString(2, courseName);
                statement.setDate(3, java.sql.Date.valueOf(enrollmentDate));
                statement.setInt(4, certificateId);
                statement.setString(5, grade);
                statement.setString(6, employeeName);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new enrollment inserted successfully!");
                    // Redirect to the Enrollments page
                    Enrollments enrollmentsPage = new Enrollments(stage);
                    stage.setScene(enrollmentsPage.getEnrollmentsLayout());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting enrollment:");
            ex.printStackTrace();
        }
    }
}
