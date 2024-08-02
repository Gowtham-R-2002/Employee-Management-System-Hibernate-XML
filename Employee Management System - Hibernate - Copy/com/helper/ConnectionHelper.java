package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.exceptions.EmployeeException;

/**
 * <p>
 * Provides connection to the database for storing and retrieving data
 * </p>
 * @author   Gowtham R
 * @version  1.0
 */
public class ConnectionHelper {
    private static String connectionUrl = "jdbc:mysql://localhost:3306/ems";
    private static String userName = "root";
    private static String password = "";
    private static Connection connection = null;

    /**
     * <p>
     * Creates connection to the database with provided host address, username and password.
     * </p>
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                createConnection();
            }
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, userName,
                                                     password);
        } catch (SQLException e) {
            System.out.println("Connection failed !");
        } catch (ClassNotFoundException e) {
            System.out.println("Class cannot be loaded !");
        } catch (Exception e) {
            System.out.println("Error connecting to Database");
        }  
    }

    /**
     * <p> 
     * Closes the connection previously created
     * </p>
     */
    public static void closeConnection() throws SQLException{
        connection.close();
    }
}