package GUI;

import Database.DatabaseConnector;
import Domain.Class.Certificate;
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

public class Certificates {
    private final TableView<Certificate> table = new TableView<>();
    private final ObservableList<Certificate> data = FXCollections.observableArrayList();
    private Stage primaryStage;

    public Certificates(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupTableColumns();
        loadDataFromDatabase();
    }

    private void setupTableColumns() {
        TableColumn<Certificate, String> certificateNameColumn = new TableColumn<>("Certificate Name");
        certificateNameColumn.setCellValueFactory(new PropertyValueFactory<>("certificateName"));
        table.getColumns().add(certificateNameColumn);
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
                    ResultSet rs = stmt.executeQuery("SELECT CertificateName FROM Certificate")) {

                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found.");
                    return;
                }

                while (rs.next()) {
                    Certificate certificate = new Certificate(rs.getString("CertificateName"));
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
}
