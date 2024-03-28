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

public class AddStudent {
    private Stage stage;
    private TextField emailField;
    private TextField nameField;
    private DatePicker birthdatePicker;
    private ChoiceBox<String> genderChoiceBox; // Updated to ChoiceBox
    private TextField addressField;
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
        genderChoiceBox.getItems().addAll("Male", "Female", "Other"); // Add options

        Label addressLabel = new Label("Address:");
        addressField = new TextField();

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
        gridPane.add(genderChoiceBox, 1, 3); // Add ChoiceBox to gridPane
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressField, 1, 4);
        gridPane.add(cityLabel, 0, 5);
        gridPane.add(cityField, 1, 5);
        gridPane.add(countryLabel, 0, 6);
        gridPane.add(countryField, 1, 6);
        gridPane.add(addButton, 0, 7);

        root.getChildren().add(gridPane);

        return new Scene(root, 400, 300);
    }

    private void addStudent() {
        String email = emailField.getText();
        String name = nameField.getText();
        Date birthdate = java.sql.Date.valueOf(birthdatePicker.getValue()); // Retrieve selected date from DatePicker
        String gender = genderChoiceBox.getValue(); // Retrieve selected value from ChoiceBox
        String address = addressField.getText();
        String city = cityField.getText();
        String country = countryField.getText();

        // Create a new Student object
        Student student = new Student(email, name, birthdate, gender, address, city, country);

        // Insert the student into the database
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Student (StudentEmail, StudentName, Birthdate, Gender, Address, City, Country) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, student.getStudentEmail());
                statement.setString(2, student.getStudentName());
                statement.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
                statement.setString(4, student.getGender());
                statement.setString(5, student.getAddress());
                statement.setString(6, student.getCity());
                statement.setString(7, student.getCountry());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new student inserted successfully!");
                    // direct me back to the Students.java page
                    Students studentsPage = new Students(stage);
                    stage.setScene(studentsPage.getStudentLayout());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting student:");
            ex.printStackTrace();
        }
    }
}
