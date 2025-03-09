/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Shelton M Adams
 */
import java.sql.*;

public class EmployeeDB {
    

    // Insert Employee into the database
  public void addEmployee(Employee employee) {
    String sql = "INSERT INTO Employee (first_name, last_name, email, phone_number, hire_date, job_title, department, salary, access_level, username, password_hash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   
    try (Connection conn = EmpDBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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


    // Authenticate Employee (Login)
    public boolean authenticateEmployee(String username, String password) {
        String sql = "SELECT password_hash FROM Employee WHERE username = ?";
        
        try (Connection conn = EmpDBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
}

