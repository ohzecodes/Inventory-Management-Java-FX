/*
* Java docs in demo/JavaDocs
* main application in /demo/src/main/java/com/example/InventoryManagement/MainApplication.java
* Docs about Future enhancement and logical errors can be found on the mainApplication.java and in the corresponding JavaDoc page
* */

package com.example.InventoryManagement;

import com.example.InventoryManagement.Logic.Inventory;
import com.example.InventoryManagement.Logic.PartLogic.Outsourced;
import com.example.InventoryManagement.Logic.PartLogic.Part;
import com.example.InventoryManagement.Logic.PartLogic.inHouse;
import com.example.InventoryManagement.Logic.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.InventoryManagement.TransformInput.SentenceCase;

/**
 * The main class to run the program.
 *
 *
 * Future enhancements:
 * 1. I can connect the application to a database and perform  CRUD operations (Create, Read, update, and Delete) on the data.
 * This will allow multiple users to work on the data simultaneously and hold the data for future data analytics.
 * 2. I can allow the user to set delete properties on the part and products: Delete cascade and delete null.
 * 3. I can allow the user to change themes: Dark theme, light theme.
 *
 *
 *
 * Logical errors:
 * 1. Inventory of both  has to be between min and max. Excluding the two values. A message is displayed to user if this happens.
 * 2. User can't delete product if there are associated parts. A message is displayed if this happens.
 * 3. Field cant be empty. A message is displayed to user if this happens.
 * 4. Another Error happens when user is trying to modify an object that is not selected.
 * Before passing the item to the modify screen, I check item if an item is selected, if not then display a message to the screen.
 * @author Ohzecodes
 */
public class MainApplication extends Application {

    /**
     * The start function for the Fx-application.
     * @param stage the state object of the Fx-application.
     * @throws IOException
     *
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("Inventory System!");
        stage.setScene(scene);
        stage.show();
    }

//* Java docs in Project/JavaDocs
//* main application in Project/demo/src/main/java/com/example/InventoryManagement/MainApplication.java
    /**
     * The main method runs the Fx-application.
     * @param args a string Array for command line interface.

     */
    public static void main(String[] args) {
//        System.out.println(SentenceCase("bigger B"));
        Part part=new inHouse(Inventory.getNextPartid(),SentenceCase("gear"),5.0,20,4,31,996);
        Inventory.addPart(part);

        Inventory.addPart(new Outsourced( Inventory.getNextPartid(),"Outsouced Handles",5.0,30,4,50,"XYZ Comapany"));

        Product p =new Product(Inventory.getNextProductId(),"Mountain bike",256.99,5,4,15);
        p.addAssociatedPart(part);
        Inventory.addProduct(p);
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"Road bike",192.99,15,2,25));
        launch();
    }
}