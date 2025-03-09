/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Shelton M Adams
 */
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDB {

    // constants
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/gb_mfg_ims";
    private final String USER_NAME = "root";
    private final String PASSWORD = "devry123";

    // Insert Employee into the database
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (first_name, last_name, email, phone_number, hire_date, job_title, department, salary, access_level, username, password_hash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = EmpDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getHireDate());
            stmt.setString(6, employee.getJobTitle());
            stmt.setString(7, employee.getDepartment());
            stmt.setDouble(8, employee.getSalary());
            stmt.setString(9, employee.getAccessLevel());
            stmt.setString(10, employee.getUsername());
            stmt.setString(11, sha256(employee.getPasswordHash())); // Store hashed password

            stmt.executeUpdate();
            System.out.println("Employee added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployeeList() throws ClassNotFoundException, SQLException {
        // create an empty ArrayList
        ArrayList<Employee> list = new ArrayList<Employee>();

        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        // get the records from the database
        String strSQL = "SELECT * FROM gb_mfg_ims.employee";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(strSQL);

        while (rs.next()) {
            String employee_id = rs.getString(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString(3);
            String email = rs.getString(4);
            String phone_number = rs.getString(5);
            String hire_date = rs.getString(6);
            String job_title = rs.getString(7);
            String department = rs.getString(8);
            double salary = rs.getDouble(9);
            String access_level = rs.getString(10);
            String user_name = rs.getString(11);
            String password = rs.getString(12);

            Employee employee = new Employee(employee_id, first_name, last_name, email, phone_number, hire_date, job_title, department, salary, access_level, user_name, password);

            list.add(employee);
        }

        // close connection to the database
        conn.close();

        // return the ArrayList
        return list;
    }

    // Authenticate Employee (Login)
    public boolean authenticateEmployee(String username, String password) {
        String sql = "SELECT password_hash FROM Employee WHERE username = ?";

        try (Connection conn = EmpDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                return storedHash.equals(sha256(password)); // Hash and compare

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hash password using SHA-256 (Better to use BCrypt)
    private String sha256(String password) {

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmployeeExists(String employeeID) throws SQLException, ClassNotFoundException {
        // Ensure the MySQL driver is loaded
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD)) {
            // Corrected query: Check the correct table name 'employee'
            String query = "SELECT COUNT(*) FROM gb_mfg_ims.employee WHERE employee_id = ?";

            // Prepare the statement and set the employee ID parameter
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, employeeID);

                // Execute the query and check the result
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0; // Returns true if the employee exists
                    }
                }
            }
        } catch (SQLException e) {
            // Optionally, handle or log the SQLException here
            throw e;
        }
        // Handle the ClassNotFoundException here if needed

        return false; // Employee does not exist
    }

    public void deleteEmployee(String index) throws ClassNotFoundException, SQLException {
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        // !debug! print index value
        //JOptionPane.showMessageDialog(null,"index = " + index);
        // write the delete commant to the SQL database as a prepared statement
        String sqlStr = "DELETE FROM gb_mfg_ims.employee WHERE employee_id = (?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, index);
        pstmt.execute();

        // close connection to the database
        conn.close();

    }
}
