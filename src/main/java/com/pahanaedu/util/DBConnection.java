package com.pahanaedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/pahanaedu_db";
    private static String USER = "root";
    private static String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage()); // ❌ likely still uncovered
            }
        }
}

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn.isValid(2);
        } catch (SQLException e) {
            return false;
        }
    }

    // Temporary main method to test the connection
    public static void main(String[] args) {
        if (testConnection()) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}
