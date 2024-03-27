package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroupC3;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "userTJES";
    private static final String PASSWORD = "GroupC3";

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnector.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.log(Level.INFO, "Connected to the database.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "JDBC Driver not found: " + e.getMessage(), e);
        }
        return connection;
    }
}
