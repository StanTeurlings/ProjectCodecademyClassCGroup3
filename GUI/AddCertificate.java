package GUI;

import Database.DatabaseConnector;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCertificate {
    private Stage stage;
    private TextField certificateNameField;

    public AddCertificate(Stage stage) {
        this.stage = stage;
    }

    public Scene getAddCertificateScene() {
        // Create navigation bar
        NavigationBar navigationBar = new NavigationBar(stage);
        VBox root = new VBox();
        root.getChildren().add(navigationBar.getMenuBar());

        // Create grid pane for certificate details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label certificateNameLabel = new Label("Certificate Name:");
        certificateNameField = new TextField();

        Button addButton = new Button("Add Certificate");
        addButton.setOnAction(e -> addCertificate());

        gridPane.add(certificateNameLabel, 0, 0);
        gridPane.add(certificateNameField, 1, 0);
        gridPane.add(addButton, 0, 1);

        root.getChildren().add(gridPane);

        return new Scene(root, 400, 300);
    }

    private void addCertificate() {
        String certificateName = certificateNameField.getText();

        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                String sql = "INSERT INTO Certificate (CertificateName) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, certificateName);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new certificate inserted successfully!");
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Generated Certificate ID: " + generatedId);
                    }
                    // Direct back to the Certificates.java page
                    Certificates certificatesPage = new Certificates(stage);
                    stage.setScene(certificatesPage.getCertificatesLayout());
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error inserting certificate:");
            ex.printStackTrace();
        }
    }
}