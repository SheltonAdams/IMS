
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /*
 * Program Name: GB MFG Inventory Management System
 * Programmer's Name: Alfred Beam
 * Program Description: A border class to add, update, and delete data to the SQL database GB_MFG_IMS.
 */
public class MaterialDB
{
    // constants
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/gb_mfg_ims";
    private final String USER_NAME = "root";
    private final String PASSWORD = "devry123";
    
    // behaviors
    
        // adding a tool to the database
    public void addMaterial(Material material) throws ClassNotFoundException, SQLException
    {
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // write the student record to the database
        String sqlStr = "INSERT INTO materials (MaterialName, MaterialDescription, MaterialValue, MaterialVendor) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, material.getMaterialName());
        pstmt.setString(2, material.getMaterialDescription());
        pstmt.setDouble(3, material.getMaterialValue());
        pstmt.setString(4, material.getMaterialVendor());        
        pstmt.execute();
        
        // close the connection
        conn.close();
        
    }
    
        public ArrayList<Material> getMaterialList() throws ClassNotFoundException, SQLException
    {
        // create an empty ArrayList
        ArrayList<Material> list = new ArrayList<Material>();
        
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // get the records from the database
        String strSQL = "SELECT * FROM materials";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(strSQL);
        
        while(rs.next())
        {
            int MaterialID = rs.getInt(1);
            String MaterialName = rs.getString(2);
            String MaterialDescription = rs.getString(3);
            double MaterialValue = rs.getDouble(4);
            String MaterialVendor = rs.getString(5);
                        
            Material material = new Material(MaterialID, MaterialName, MaterialDescription, MaterialValue, MaterialVendor);
            
            list.add(material);
        }
        
        // close connection to the database
        conn.close();
        
        // return the ArrayList
        return list;        
    }
        
        public void deleteMaterial(String index) throws ClassNotFoundException, SQLException
    {
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // !debug! print index value
        //JOptionPane.showMessageDialog(null,"index = " + index);
        
        // write the delete commant to the SQL database as a prepared statement
        String sqlStr = "DELETE FROM materials WHERE MaterialID = (?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, index);
        pstmt.execute();
        
        // close connection to the database
        conn.close();
                
    }
    
}
