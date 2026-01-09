package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;

public class DatabaseConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    // Initialize from servlet context
    public static void initialize(ServletContext context) {
        URL = context.getInitParameter("dbUrl");
        USER = context.getInitParameter("dbUser");
        PASSWORD = context.getInitParameter("dbPassword");
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }
}