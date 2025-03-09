

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /*
 * Program Name: GB MFG Inventory Management System
 * Programmer's Name: Alfred Beam
 * Program Description: A class that is the blueprint for "Tool" objects.
 */
public class Tool
{
    // attrubutes
    private int toolID;
    private String toolName;
    private String toolSerialNumber;
    private double toolValue;
    
    // constructors
    public Tool()
    {
        toolID = 0;
        toolName = "Unknown";
        toolSerialNumber = "Unknown";
        toolValue = 0.0;
    }
    
    public Tool(int toolID, String toolName, String toolSerialNumber, double toolValue)
    {
        this.toolID = toolID;
        this.toolName = toolName;
        this.toolSerialNumber = toolSerialNumber;
        this.toolValue = toolValue;
    }
    
    // behaviors
    @Override    
    public String toString()
    {
        return "Tool{" + "toolID=" + toolID + ", toolName=" + toolName + ", toolSerialNumber=" + toolSerialNumber + ", toolValue=" + toolValue + '}';
    }

    // getters and setters
    public int getToolID()
    {
        return toolID;
    }
    public void setToolId(int toolID)    
    {
        this.toolID = toolID;
    }
    
    public String getToolName()
    {
        return toolName;
    }
    public void setToolName(String toolName)
    {
        this.toolName = toolName;
    }            
    
    public String getToolSerialNumber()
    {
        return toolSerialNumber;
    }
    public void setToolSerialNumber(String toolSerialNumber)
    {
        this.toolSerialNumber = toolSerialNumber;
    }
    
    public double getToolValue()
    {
        return toolValue;
    }
    public void setToolValue(double toolValue)
    {
        this.toolValue = toolValue;
    }    
    
}
