package GUI;

import Database.DatabaseConnector;
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
import java.time.LocalDate;

public class AddEnrollment {
    private Stage stage;
    private ChoiceBox<String> studentEmailChoiceBox;
    private ChoiceBox<String> courseNameChoiceBox;
    private ChoiceBox<String> certificateIdChoiceBox; // Added
    private DatePicker enrollmentDatePicker;
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

        // Initialize choice boxes
        studentEmailChoiceBox = new ChoiceBox<>();
        courseNameChoiceBox = new ChoiceBox<>();
        certificateIdChoiceBox = new ChoiceBox<>(); // Added

        // Populate choice boxes with data
        populateStudentEmails();
        populateCourseNames();
        populateCertificateIds(); // Added

        enrollmentDatePicker = new DatePicker();
        gradeField = new TextField();
        employeeNameField = new TextField();

        Button addButton = new Button("Add Enrollment");
        addButton.setOnAction(e -> addEnrollment());

        gridPane.add(new Label("Student Email:"), 0, 0);
        gridPane.add(studentEmailChoiceBox, 1, 0);
        gridPane.add(new Label("Course Name:"), 0, 1);
        gridPane.add(courseNameChoiceBox, 1, 1);
        gridPane.add(new Label("Certificate ID:"), 0, 2);
        gridPane.add(certificateIdChoiceBox, 1, 2);
        gridPane.add(new Label("Enrollment Date:"), 0, 3);
        gridPane.add(enrollmentDatePicker, 1, 3);
        gridPane.add(new Label("Grade:"), 0, 4);
        gridPane.add(gradeField, 1, 4);
        gridPane.add(new Label("Employee Name:"), 0, 5);
        gridPane.add(employeeNameField, 1, 5);
        gridPane.add(addButton, 0, 6);

        root.getChildren().add(gridPane);

        return new Scene(root, 800, 600);
    }

    private void addEnrollment() {
        String studentEmail = studentEmailChoiceBox.getValue();
        String courseName = courseNameChoiceBox.getValue();
        String certificateIdText = certificateIdChoiceBox.getValue();
        LocalDate enrollmentDate = enrollmentDatePicker.getValue();
        String gradeText = gradeField.getText();
        String employeeName = employeeNameField.getText();

        // Validate input fields
        if (!validateGrade(gradeText)) {
            // Show an alert for invalid grade
            showAlert("Please enter a grade between 1 and 10, or leave it empty.");
            return;
        }

        int grade = 0;
        if (!gradeText.isEmpty()) {
            grade = Integer.parseInt(gradeText);
        }

        // Insert the enrollment into the database
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Enrollment (StudentEmail, CourseName, CertificateId, EnrollmentDate, Grade, EmployeeName) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, studentEmail);
                statement.setString(2, courseName);
                statement.setString(3, certificateIdText);
                statement.setDate(4, java.sql.Date.valueOf(enrollmentDate));
                statement.setInt(5, grade);
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

    private boolean validateGrade(String gradeText) {
        if (gradeText.isEmpty()) {
            return true; // Allow empty grade field
        }
        try {
            double grade = Double.parseDouble(gradeText);
            return (grade >= 1 && grade <= 10); // Add more decimal grades as needed
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void populateStudentEmails() {
        ObservableList<String> emails = FXCollections.observableArrayList();
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "SELECT StudentEmail FROM Student";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String email = resultSet.getString("StudentEmail");
                    emails.add(email);
                }
                studentEmailChoiceBox.setItems(emails);
            }
        } catch (SQLException ex) {
            System.err.println("Error populating student emails:");
            ex.printStackTrace();
        }
    }

    private void populateCourseNames() {
        ObservableList<String> courseNames = FXCollections.observableArrayList();
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "SELECT CourseName FROM Course";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String courseName = resultSet.getString("CourseName");
                    courseNames.add(courseName);
                }
                courseNameChoiceBox.setItems(courseNames);
            }
        } catch (SQLException ex) {
            System.err.println("Error populating course names:");
            ex.printStackTrace();
        }
    }

    private void populateCertificateIds() {
        ObservableList<String> certificateIds = FXCollections.observableArrayList();
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "SELECT CertificateId FROM Certificate";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String certificateId = resultSet.getString("CertificateId");
                    certificateIds.add(certificateId);
                }
                certificateIdChoiceBox.setItems(certificateIds);
            }
        } catch (SQLException ex) {
            System.err.println("Error populating certificate IDs:");
            ex.printStackTrace();
        }
    }
}