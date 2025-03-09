
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
public class WarehouseDB
{
    // constants
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/gb_mfg_ims";
    private final String USER_NAME = "root";
    private final String PASSWORD = "devry123";
    
    // behaviors
    // adding a tool to the database
    public void add(Tool tool) throws ClassNotFoundException, SQLException
    {
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // write the student record to the database
        String sqlStr = "INSERT INTO tools (ToolName, ToolSerialNumber, ToolValue) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, tool.getToolName());
        pstmt.setString(2, tool.getToolSerialNumber());
        pstmt.setDouble(3, tool.getToolValue());
        pstmt.execute();
        
        // close the connection
        conn.close();
        
    }
    
        public ArrayList<Tool> getAll() throws ClassNotFoundException, SQLException
    {
        // create an empty ArrayList
        ArrayList<Tool> list = new ArrayList<Tool>();
        
        // check for the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        // get the records from the database
        String strSQL = "SELECT * FROM tools";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(strSQL);
        
        while(rs.next())
        {
            int ToolID = rs.getInt(1);
            String ToolName = rs.getString(2);
            String ToolSerialNumber = rs.getString(3);
            double ToolValue = rs.getDouble(4);
                        
            Tool tool = new Tool(ToolID, ToolName, ToolSerialNumber, ToolValue);
            
            list.add(tool);
        }
        
        // close connection to the database
        conn.close();
        
        // return the ArrayList
        return list;        
    }
        
        public void delete(Tool tool) throws ClassNotFoundException, SQLException
    {
        // stub to delete tools from the database        
    }
    
}
