package com.example.InventoryManagement.Logic;

import com.example.InventoryManagement.Logic.PartLogic.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * the Inventory Class
 * @author Ohzecodes
 *
 */
public class Inventory {
    private static ObservableList<Part> AllParts= FXCollections.observableArrayList();
    private static ObservableList<Product> AllProducts= FXCollections.observableArrayList();

    private static int nextProductId=0;

    /**
     *
     * @return The incremented ID  for the products
     */
    public static int getNextProductId() {
        nextProductId++;
        return nextProductId;
    }

    private static int nextPartid=0;

    /**
     * @return The incremented ID for the parts
     */
    public static int getNextPartid() {
        nextPartid++;
        return nextPartid;
    }

    /**
     * This will add a new part to the inventory
     * @param newPart A new part to add
     */
    public static void addPart(Part newPart){
        AllParts.add(newPart);
        return;}

    /**
     * This will add a new product to the inventory
     * @param newProduct A new product to add
     */
    public static void addProduct(Product newProduct){AllProducts.add(newProduct);}

    /**
     * This will update the SelectedPart on the current index
     * @param index The index on with the part that you want to update is
     * @param selectedPart The new part that you want to update
     */
    public static void updatePart(int index,Part  selectedPart){
        AllParts.set(index,selectedPart);
        return;}

    /**
     * This will update the newProduct on the current index
     * @param index The index On which the product that you want to update is
     * @param newProduct The new product that you want to update
     */

    public static void updateProduct(int index, Product newProduct){
        AllProducts.set(index,newProduct);
        return;}

    /**
     * This will remove the selected part
     * @param selectedPart The part that you want to remove
     * @return If the removal is successful return true Else false
     */
    public static boolean deletePart(Part selectedPart){
    return AllParts.remove(selectedPart);

       }

    /**
     * This will remove the selected product from the inventory.
     * @param selectedProduct The selected product that you want to remove
     * @return If the removal is successful return true else false
     */
    public static boolean deleteProduct(Product selectedProduct){
        return AllProducts.remove(selectedProduct);
    }

    /**
     *
     * @return All the parts in the inventory
     */
    public static ObservableList<Part> getAllParts(){
        return AllParts;
    }

    /**
     *
     * @return All the products in the inventory
     */

    public static ObservableList<Product> getAllProducts(){
        return AllProducts;
    }

    /**
     * This will Return the part with the specific ID
     * @param partId The id of the part that you want to find
     * @return the part with the specific ID
     */
    public static Part lookupPart(int partId){
        for (int i = 0; i < AllParts.size(); i++) {
         Part p=AllParts.get(i);
         if(p.getId()==partId){
             return p;
         }

        }
        return null;
    }

    /**
     * This will return the product with the specific ID
     * @param productId the id of the product that you want to find
     * @return The product with the specific ID
     */
    public static Product lookupProduct(int productId){
        for (Product p:AllProducts) {
            if(p.getIdProduct()==productId){
                return p;
            }
        }
        return null;
    }

    /**
     * This will return a list containing the parts which matches the name
     * @param partName The name of the part that you want to find
     * @return  A
     * list containing the parts which matches the name
     */
    public static ObservableList<Part> lookupPart(String partName){

        ObservableList<Part> parts=FXCollections.observableArrayList();
        for (Part p:AllParts) {
          if(  p.getName().contains(partName))
            parts.add(p);
        }


        return parts;
    }

    /**
     * This will return a list containing the products which matches the name
     * @param productName The name of the product that you want to find
     * @return A list containing the products that matches the name
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> products=FXCollections.observableArrayList();
        for (Product p:AllProducts) {
            if(  p.getNameProduct().contains(productName))
                products.add(p);
        }
        return products;
    }


}