package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroupC3;trustServerCertificate=true";
    private static final String USER = "userTJES";
    private static final String PASSWORD = "GroupC3";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace(); // Add stack trace for more details
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace(); // Add stack trace for more details
            return null;
        }        
    }
}
