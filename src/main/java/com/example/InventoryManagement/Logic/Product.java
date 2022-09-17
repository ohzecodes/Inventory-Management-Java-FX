package com.example.InventoryManagement.Logic;

import com.example.InventoryManagement.Logic.PartLogic.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The product class
 * @author Mohammed Zafar
 */
public class Product {
//    private static int StaticProductId=1;
    private ObservableList<Part> associatedParts= FXCollections.observableArrayList();
    private  int id ;
    private  String name ;
    private  double price ;
    private  int stock ;
    private  int min ;
    private  int max;

    /**
     * The constructor Method of the product class
     * @param id The id of the product
     * @param name Name of the product
     * @param price The price of the product
     * @param stock The current stock of the product
     * @param min The minimum number of product in stock
     * @param max The maximum number of products in stock
     */
    public Product(  int id,String name, double price, int stock, int min, int max){
        this.id=id;
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.min=min;
        this.max=max;
    }

    /**
     * This will return all associated parts in the product
     * @return all associated parts
     */
    public ObservableList<Part> getAssociatedPartsProducts() {
        return associatedParts;
    }

    /**
     * This will set associatedparts in the Product to the list
     * @param associatedParts The list of parts
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * This will get the id the Product
     * @return  get the id the Product
     */
    public int getId(){return id;}

    /**
     * This will get the id the Product
     * @return  get the id the Product
     */
    public int getIdProduct() {
        return id;
    }

    /**
     * This will solve the id of the product
     * @param id ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This will return the product name
     * @return Product name
     */
    public String getNameProduct() {
        return name;
    }

    /**
     * This will save the product name
     * @param name The product name that you want to set it to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Product price getter
     * @return product price
     */
    public double getPriceProduct() {
        return price;
    }

    /**
     * Product Price setter
     * @param price The price that you want to set this product  to
     */


    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Product stock getter
     * @return numbers of item in stock
     */
    public int getStockProduct() {
        return stock;
    }

    /**
     * Product stock setter
     * @param stock the number that you want to set stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * gets the number of minimum products in the stock
     * @return the number of minimum products in the stock
     */
    public int getMinProduct() {
        return min;
    }

    /**
     * set the number of minimum products in the stock
     * @param min the number of minimum products in the stock
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * get the number of Maximum products in the stock
     * @return the number of Maximum products in the stock
     */
    public int getMaxProduct() {
        return max;
    }

    /**
     * sets the number of Maximum products in the stock
     * @param max the number of Maximum products in the stock
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * add the Part to List of associated part
     * @param part The part that you want to add the associated list
     */
    public void addAssociatedPart(Part part ) {associatedParts.add(part);}

    /**
     * Deletes the associated part
     * @param selectedAssociatedPart The part that you want to delete
     * @return True on success, false on failure
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart ) {return associatedParts.remove(selectedAssociatedPart);}

    /**
     * Get the list of Associated  parts
     * @return the list of Associated  parts
     */
    public ObservableList<Part> getAllAssociatedPartsProduct() {
        return associatedParts;
    }

}
