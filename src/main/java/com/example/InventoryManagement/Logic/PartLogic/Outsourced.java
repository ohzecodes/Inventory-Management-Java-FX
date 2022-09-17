package com.example.InventoryManagement.Logic.PartLogic;

/**
 * the outsourced part class creates an outsourced part
 * @author Ohzecodes
 */
public class Outsourced extends Part{
    private  String companyName;

    /**
     * Tha constructor for in the outsourced part
     * @param id The ID for the outsourced part
     * @param name The name for the outsourced part
     * @param price The price for the outsourced part
     * @param stock The stock for the outsourced part
     * @param min The minimum number of items in the stock for the outsourced part
     * @param max The maximum number of items in the stock for the outsourced part
     * @param companyName The company name from which the part will be outsourced
     */
    public Outsourced(int id,String name, Double price, int stock, int min, int max, String companyName){
   super( id,name, price, stock, min, max);
   this.companyName=companyName;

}

    /**
     * Getter for the company name
     * @return The company name from which the part will be outsourced
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Setter for the company name
     * @param companyName The company name from which the part will be outsourced
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
