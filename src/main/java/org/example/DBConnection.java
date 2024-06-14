package org.example;

import java.sql.*;

public class DBConnection {

    static String url = "jdbc:mysql://localhost:3306/testdb";
    // Database credentials
    static String username = "root";
    static String password = "adminSQL";


    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found");
            e.printStackTrace();
            return;
        }

        // Try to connect to Database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to MySQL database!");
            // Perform database operations here
        } catch (SQLException e) {
            System.err.println("Error connecting to MySQL database: " + e.getMessage());
        }
    }

}
