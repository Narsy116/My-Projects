/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Narasimha
 */
package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/campus_recruitment_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Register the JDBC driver (optional, for older versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Open a connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }
}
