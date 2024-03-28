package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import Database.DatabaseConnector;
import Domain.Class.Student;
import Domain.Enummeration.Gender;

public class AddStudent {
    private Stage stage;
    private TextField emailField;
    private TextField nameField;
    private DatePicker birthdatePicker;
    private ChoiceBox<String> genderChoiceBox;
    private TextField addressField;
    private TextField postcodeField;
    private TextField cityField;
    private TextField countryField;

    public AddStudent(Stage stage) {
        this.stage = stage;
    }

    public Scene getAddStudentScene() {
        // Create navigation bar
        NavigationBar navigationBar = new NavigationBar(stage);
        VBox root = new VBox();
        root.getChildren().add(navigationBar.getMenuBar());

        // Create grid pane for student details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        birthdatePicker = new DatePicker(); // DatePicker for birthdate

        Label genderLabel = new Label("Gender:");
        genderChoiceBox = new ChoiceBox<>(); // Initialize ChoiceBox
        // Add options from the Gender enum
        for (Gender gender : Gender.values()) {
            genderChoiceBox.getItems().add(gender.toString());
        }

        Label addressLabel = new Label("Address:");
        addressField = new TextField();

        Label postcodeLabel = new Label("Postcode:");
        postcodeField = new TextField();

        Label cityLabel = new Label("City:");
        cityField = new TextField();

        Label countryLabel = new Label("Country:");
        countryField = new TextField();

        Button addButton = new Button("Add Student");
        addButton.setOnAction(e -> addStudent());

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(new Label("Birthdate:"), 0, 2);
        gridPane.add(birthdatePicker, 1, 2);
        gridPane.add(genderLabel, 0, 3);
        gridPane.add(genderChoiceBox, 1, 3);
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressField, 1, 4);
        gridPane.add(postcodeLabel, 0, 5);
        gridPane.add(postcodeField, 1, 5);
        gridPane.add(cityLabel, 0, 6);
        gridPane.add(cityField, 1, 6);
        gridPane.add(countryLabel, 0, 7);
        gridPane.add(countryField, 1, 7);
        gridPane.add(addButton, 0, 8);

        root.getChildren().add(gridPane);

        return new Scene(root, 400, 300);
    }

    private void addStudent() {
        // Validate fields before adding the student
        if (!validateFields()) {
            return;
        }

        String email = emailField.getText();
        String name = nameField.getText();
        Date birthdate = java.sql.Date.valueOf(birthdatePicker.getValue());
        Gender gender = Gender.valueOf(genderChoiceBox.getValue().toUpperCase());
        String address = addressField.getText();
        String postcode = postcodeField.getText();
        String city = cityField.getText();
        String country = countryField.getText();

        Student student = new Student(email, name, birthdate, gender, address, postcode, city, country);

        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Student (StudentEmail, StudentName, Birthdate, Gender, Address, Postcode, City, Country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, student.getStudentEmail());
                statement.setString(2, student.getStudentName());
                statement.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
                statement.setString(4, student.getGender().toString());
                statement.setString(5, student.getAddress());
                statement.setString(6, student.getPostcode());
                statement.setString(7, student.getCity());
                statement.setString(8, student.getCountry());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new student inserted successfully!");
                    Students studentsPage = new Students(stage);
                    stage.setScene(studentsPage.getStudentLayout());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting student:");
            ex.printStackTrace();
        }
    }

    private boolean validateFields() {
        // Validate email format
        String email = emailField.getText();
        if (email.isEmpty() || !isValidEmail(email)) {
            // Show an alert for invalid email format
            showAlert("Please enter a valid email address.");
            return false;
        }
        
        // Validate postcode format
        String postcode = postcodeField.getText();
        if (postcode.isEmpty() || !isValidPostcode(postcode)) {
            // Show an alert for invalid postcode format
            showAlert("Please enter a valid postcode (e.g., 1234 AB).");
            return false;
        }

        // Check other fields
        if (nameField.getText().isEmpty() || birthdatePicker.getValue() == null
                || genderChoiceBox.getValue() == null || addressField.getText().isEmpty()
                || cityField.getText().isEmpty() || countryField.getText().isEmpty()) {
            // Show an alert for incomplete fields
            showAlert("Please fill in all fields.");
            return false;
        }
        
        return true;
    }
    
    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    
    private boolean isValidPostcode(String postcode) {
        // Regular expression for postcode validation
        String postcodeRegex = "^\\d{4}\\s[A-Z]{2}$";
        return postcode.matches(postcodeRegex);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
