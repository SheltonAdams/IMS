/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /*
 * Program Name: GB MFG Inventory Management System
 * Programmer's Name: Alfred Beam
 * Program Description: A class that is the blueprint for "Material" objects.
 */
public class Material
{
    //attributes
    private int materialID;
    private String materialName;
    private String materialDescription;
    private double materialValue;
    private String materialVendor;
    
    
    // constructors
    public Material()
    {
        materialID = 0;
        materialName = "unknown";
        materialDescription = "unknown";
        materialValue = 0.0;
        materialVendor = "unknown";
    }

    public Material(int materialID, String materialName, String materialDescription, double materialValue, String materialVendor)
    {
        this.materialID = materialID;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.materialValue = materialValue;
        this.materialVendor = materialVendor;
    }
    
    // getters and setters

    @Override
    public String toString()
    {
        return "Material{" + "materialID=" + materialID + ", materialName=" + materialName + ", materialDescription=" + materialDescription + ", materialValue=" + materialValue + ", materialVendor=" + materialVendor + '}';
    }

    public int getMaterialID()
    {
        return materialID;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public String getMaterialDescription()
    {
        return materialDescription;
    }

    public double getMaterialValue()
    {
        return materialValue;
    }

    public String getMaterialVendor()
    {
        return materialVendor;
    }

    public void setMaterialID(int materialID)
    {
        this.materialID = materialID;
    }

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public void setMaterialDescription(String materialDescription)
    {
        this.materialDescription = materialDescription;
    }

    public void setMaterialValue(double materialValue)
    {
        this.materialValue = materialValue;
    }

    public void setMaterialVendor(String materialVendor)
    {
        this.materialVendor = materialVendor;
    }
    
    
    
}
