package com.example.InventoryManagement.Logic.PartLogic;

/**
 * the in-house part class creates an in-house part
 * @author Ohzecodes
 */
public class inHouse extends Part {;

    private int machineId;

    /**
     * Tha constructor for in the in-house part
     * @param id The ID for the in-house part
     * @param name The name for the in-house part
     * @param price The price for the in-house part
     * @param stock The stock for the in-house part
     * @param min The minimum number of items in the stock for the in-house part
     * @param max The maximum number of items in the stock for the in-house part
     * @param machineId The machine ID for the in-house part
     */
    public inHouse( int id, String name, Double price, int stock, int min, int max,int machineId) {
        super( id ,name, price, stock, min, max);
        this.machineId=machineId;
    }


    /**
     * Machine ID getter
     * @return MachineID This is the Machine ID for the in-house part
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Machine ID setter
     * @param machineId This is the number that will set the machine ID for the in-house part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
