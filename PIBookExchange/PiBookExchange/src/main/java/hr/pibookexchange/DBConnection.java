package hr.pibookexchange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Connection details for SQL Server
    private static final String URL = "jdbc:sqlserver://DESKTOP-H0UGAVT\\SQLEXPRESS99;databaseName=PIBookExchange;encrypt=false";
    private static final String USER = "sa";          // Replace with your SQL Server username
    private static final String PASSWORD = "SQL";     // Replace with your SQL Server password

    public static Connection getConnection() throws SQLException {
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
