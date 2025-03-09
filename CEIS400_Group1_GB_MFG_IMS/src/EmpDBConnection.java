/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Shelton M Adams
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmpDBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gb_mfg_ims";
    private static final String USER = "root"; // Change if using another username
    private static final String PASSWORD = "devry123"; // Add your MySQL password if set

    public static Connection getConnection() {
        try {
            // Explicitly load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the JAR to the project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console.");
            e.printStackTrace();
        }
        return null;
    }
}
