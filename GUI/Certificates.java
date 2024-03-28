package GUI;

import Database.DatabaseConnector;
import Domain.Class.Certificate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Certificates {
    private final TableView<Certificate> table = new TableView<>();
    private final ObservableList<Certificate> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Certificates(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    @SuppressWarnings("unchecked")
    private void setupTableColumns() {
        TableColumn<Certificate, Integer> certificateIdColumn = new TableColumn<>("Certificate ID");
        certificateIdColumn.setCellValueFactory(new PropertyValueFactory<>("certificateId"));

        TableColumn<Certificate, String> certificateNameColumn = new TableColumn<>("Certificate Name");
        certificateNameColumn.setCellValueFactory(new PropertyValueFactory<>("certificateName"));

        TableColumn<Certificate, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setCellFactory(col -> new TableCell<Certificate, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox hbox = new HBox(5, editButton, deleteButton);

            {
                editButton.setOnAction(e -> {
                    Certificate certificate = getTableView().getItems().get(getIndex());
                    editCertificate(certificate);
                });
                deleteButton.setOnAction(e -> {
                    Certificate certificate = getTableView().getItems().get(getIndex());
                    deleteCertificate(certificate);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });

        table.getColumns().addAll(certificateIdColumn, certificateNameColumn, actionColumn);
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
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Certificate")) {

                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found.");
                    return;
                }

                while (rs.next()) {
                    Certificate certificate = new Certificate(rs.getInt("CertificateId"), rs.getString("CertificateName"));
                    data.add(certificate);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }

    public Scene getCertificatesLayout() {
        NavigationBar navigationBar = new NavigationBar(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navigationBar.getMenuBar());
        borderPane.setCenter(table);

        return new Scene(borderPane);
    }

    private void editCertificate(Certificate certificate) {
        try {
            Dialog<Certificate> dialog = new Dialog<>();
            dialog.setTitle("Edit Certificate");
            dialog.setHeaderText("Edit Certificate Details");

            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField nameField = new TextField(certificate.getCertificateName());

            grid.add(new Label("Certificate Name:"), 0, 0);
            grid.add(nameField, 1, 0);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    certificate.setCertificateName(nameField.getText());
                    return certificate;
                }
                return null;
            });

            Optional<Certificate> result = dialog.showAndWait();

            result.ifPresent(updatedCertificate -> {
                updateCertificateInDatabase(updatedCertificate);
                refreshTableData();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCertificateInDatabase(Certificate certificate) {
        String sql = "UPDATE Certificate SET CertificateName = ? WHERE CertificateId = ?";

        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, certificate.getCertificateName());
            stmt.setInt(2, certificate.getCertificateId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    private void deleteCertificate(Certificate certificate) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Certificate");
        alert.setContentText("Are you sure you want to delete the certificate with ID: " + certificate.getCertificateId() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (deleteCertificateFromDatabase(certificate.getCertificateId())) {
                data.remove(certificate);
                System.out.println("Deleted certificate with ID: " + certificate.getCertificateId());
            } else {
                System.out.println("Failed to delete certificate with ID: " + certificate.getCertificateId());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private boolean deleteCertificateFromDatabase(int certificateId) {
        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Certificate WHERE CertificateId = ?")) {
            stmt.setInt(1, certificateId);
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

