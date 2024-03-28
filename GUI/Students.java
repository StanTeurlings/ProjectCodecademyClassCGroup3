package GUI;

import Database.DatabaseConnector;
import Domain.Class.Student;
import Domain.Enummeration.Gender;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class Students {
    private final TableView<Student> table = new TableView<>();
    private final ObservableList<Student> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Students(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    @SuppressWarnings("unchecked")
    private void setupTableColumns() {
        TableColumn<Student, Boolean> checkBoxColumn = new TableColumn<>();
        checkBoxColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue() != null));
        checkBoxColumn.setCellFactory(col -> new TableCell<Student, Boolean>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(checkBox);
                    checkBox.setSelected(item);
                }
            }
        });

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn<Student, Date> birthdateColumn = new TableColumn<>("Birthdate");
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        birthdateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter("dd-MM-yyyy")));

        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Student, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Student, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Student, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setCellFactory(col -> new TableCell<Student, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox hbox = new HBox(5, editButton, deleteButton);

            {
                editButton.setOnAction(e -> {
                    Student student = getTableView().getItems().get(getIndex());
                    editStudent(student);
                });
                deleteButton.setOnAction(e -> {
                    Student student = getTableView().getItems().get(getIndex());
                    deleteStudent(student);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });

        table.getColumns().addAll(checkBoxColumn, emailColumn, nameColumn, birthdateColumn, genderColumn, addressColumn, cityColumn, countryColumn, actionColumn);
        table.setItems(data);
    }

    private void loadDataFromDatabase() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("StudentEmail"),
                        rs.getString("StudentName"),
                        rs.getDate("Birthdate"),
                        Gender.valueOf(rs.getString("Gender")),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("Country"));
                data.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Scene getStudentLayout() {
        NavigationBar navigationBar = new NavigationBar(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navigationBar.getMenuBar());
        borderPane.setCenter(table);

        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Students");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        return scene;
    }

    private void editStudent(Student student) {
        try {
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("Edit Student");
            dialog.setHeaderText("Edit Student Details");
    
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
    
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
    
            TextField nameField = new TextField(student.getStudentName());
    
            // Convert java.sql.Date to LocalDate for DatePicker
            java.util.Date birthDateUtil = new java.util.Date(student.getBirthDate().getTime());
            LocalDate birthDateLocal = birthDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DatePicker birthdatePicker = new DatePicker(birthDateLocal);
    
            // Assuming genderField is a ComboBox<Gender> containing gender options as enum values
            ComboBox<Gender> genderField = new ComboBox<>();
            genderField.getItems().addAll(Gender.values());

            // Set the default value to the current gender of the student
            genderField.setValue(student.getGender());


    
            TextField addressField = new TextField(student.getAddress());
            TextField cityField = new TextField(student.getCity());
            TextField countryField = new TextField(student.getCountry());
    
            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Birthdate:"), 0, 1);
            grid.add(birthdatePicker, 1, 1);
            grid.add(new Label("Gender:"), 0, 2);
            grid.add(genderField, 1, 2);
            grid.add(new Label("Address:"), 0, 3);
            grid.add(addressField, 1, 3);
            grid.add(new Label("City:"), 0, 4);
            grid.add(cityField, 1, 4);
            grid.add(new Label("Country:"), 0, 5);
            grid.add(countryField, 1, 5);
    
            dialog.getDialogPane().setContent(grid);
    
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    student.setStudentName(nameField.getText());
                    student.setBirthDate(Date.from(birthdatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    // Convert the selected gender string to the corresponding Gender enum value
                    Gender gender = Gender.valueOf(genderField.getValue().toString());
                    student.setGender(gender);
                    student.setAddress(addressField.getText());
                    student.setCity(cityField.getText());
                    student.setCountry(countryField.getText());
                    return student;
                }
                return null;
            });
    
            Optional<Student> result = dialog.showAndWait();
    
            result.ifPresent(updatedStudent -> {
                updateStudentInDatabase(updatedStudent);
                refreshTableData();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }       

    private void updateStudentInDatabase(Student student) {
        String sql = "UPDATE Student SET StudentName = ?, Birthdate = ?, Gender = ?, Address = ?, City = ?, Country = ? WHERE StudentEmail = ?";
    
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getStudentName());
            stmt.setDate(2, new java.sql.Date(student.getBirthDate().getTime()));
            stmt.setString(3, student.getGender().toString());
            stmt.setString(4, student.getAddress());
            stmt.setString(5, student.getCity());
            stmt.setString(6, student.getCountry());
            stmt.setString(7, student.getStudentEmail());
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }    

    private void deleteStudent(Student student) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Student");
        alert.setContentText("Are you sure you want to delete the student with email: " + student.getStudentEmail() + "?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (deleteStudentFromDatabase(student.getStudentEmail())) {
                data.remove(student);
                System.out.println("Deleted student: " + student.getStudentEmail());
            } else {
                System.out.println("Failed to delete student: " + student.getStudentEmail());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    

    private boolean deleteStudentFromDatabase(String studentEmail) {
        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Student WHERE StudentEmail = ?")) {
            stmt.setString(1, studentEmail);
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
