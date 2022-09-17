package com.example.InventoryManagement;
import com.example.InventoryManagement.Logic.Inventory;
import com.example.InventoryManagement.Logic.PartLogic.Part;

import com.example.InventoryManagement.Logic.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import static com.example.InventoryManagement.Styling.*;
import static com.example.InventoryManagement.TransformInput.SudoSentenceCase;

/**
 * The main Screen Controller controls the actions on the  main screen
 * @author Mohammmad Zafar
 *
 */
public class MainScreenController implements Initializable {
    // buttons
    public Button addPartBtn;
    public Button modifyPartBtn;
    public Button deletePartBtn;
    public Button addProductBtn;
    public Button modifyProductBtn;
    public Button deleteProductBtn;
    public Button exitBtn;

    //Define the parts table & Columns
    public TableView<Part> partTableView;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partPriceCol;

    //Define the product table & Columns
    public TableView<Product> productTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInvCol;
    public TableColumn productPriceCol;

    // Define search box
    public TextField searchProduct;
    public TextField searchPart;
    // Define warning
    public Label warningForParts;
    public Label warningForProducts;




    /**
     * The onPartsAdd changes screen when the add button is pressed in the part container.
     * @throws IOException  IOException is thrown on fxml not found
     */
    @FXML
    protected void onPartsAdd() throws IOException {

            Stage stage = (Stage) addPartBtn.getScene().getWindow();
            stage.close();
            Stage ps = new Stage();

            Parent root = FXMLLoader.load((getClass().getResource("AddPart.fxml")));
            ps.setTitle("Add a part");
            ps.setScene(new Scene(root, 450, 500));
            ps.show();

    }

    /**
     * The onPartsModify changes screen when the modify button is pressed in the part container.
     * it populates the value of the field in on the new screen with the selected item in table.
     * if no item is selected it shows an error message
     * @throws IOException IOException is thrown on ModifyPart.fxml is not found
     */
    @FXML
    protected void onPartsModify() throws IOException  {
    Part p=partTableView.getSelectionModel().getSelectedItem();
    if(p==null){
        System.out.println("ERROR: PLEASE SELECT AN ITEM");
        warningForParts.setText(SudoSentenceCase("ERROR: ")+SudoSentenceCase("PLEASE SELECT AN ITEM"));
        warningForParts.setStyle("-fx-text-fill:red");
    }
    else {
        warningForParts.setText("");
        Stage stage = (Stage) modifyPartBtn.getScene().getWindow();
        stage.close();
        Stage ps = new Stage();

        FXMLLoader n = new FXMLLoader((getClass().getResource("ModifyPart.fxml")));
        Parent root = n.load();
        ModifyPartController mod = n.getController();
        mod.set(p);

        ps.setTitle("Modify a part");
        ps.setScene(new Scene(root, 450, 500));
        ps.show();
    }

    }

    /**
     * The  onPartsDelete deletes the selected part from inventory after showing a dialog box
     @param actionEvent An Event representing some type of action.
     */
    @FXML
    protected void onPartsDelete(ActionEvent actionEvent) {
        Part p=partTableView.getSelectionModel().getSelectedItem();
        if(p==null){
            System.out.println("ERROR: PLEASE SELECT AN ITEM");
            warningForParts.setText(SudoSentenceCase("ERROR: ")+SudoSentenceCase("PLEASE SELECT AN ITEM"));
            warningForParts.setStyle("-fx-text-fill:red");

        } else {
            warningForParts.setText("");
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner( (Stage) deletePartBtn.getScene().getWindow());
            alert.getDialogPane().setContentText(SudoSentenceCase("please Confirm that you want to delete part?"));
            alert.getDialogPane().setHeaderText(SudoSentenceCase("Delete part"));

            Optional<ButtonType> R=alert.showAndWait();
            if(R.get()==ButtonType.OK) {
                Inventory.deletePart(p);
            }
        }
    }

    /**
     * The onProductsModify changes screen when the modify button is pressed in the product container.
     * it populates the value of the field in on the new screen with the selected item in table.
     * the application also places associated parts in the bottom table.
     * if no item is selected it shows an error message
     * @throws IOException IOException is thrown on ModidyProduct.fxml is not found
     */
    @FXML
    protected void onProductsModify() throws IOException {
    //change screen
    Product p=productTable.getSelectionModel().getSelectedItem();
    if (p==null){
        warningForProducts.setLayoutY(230);
        warningForProducts.setText(SudoSentenceCase("ERROR: ")+SudoSentenceCase("PLEASE SELECT AN ITEM"));
        warningForProducts.setStyle("-fx-text-fill:red");
        System.out.println("ERROR: PLEASE SELECT AN ITEM");
    }else {
        Stage stage = (Stage) addPartBtn.getScene().getWindow();
        stage.close();
        Stage ps = new Stage();

        FXMLLoader n = new FXMLLoader((getClass().getResource("ModifyProduct.fxml")));
        Parent root = n.load();
        ModifyProductController mod = n.getController();
        try {
            mod.set(p);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }



        ps.setTitle("Add a product");
        ps.setScene(new Scene(root, 700, 650));
        ps.show();}
    }


    /**
     * the onProductsAdd chnages the screen  when the add button is pressed in the products container
     * @throws IOException
     */
    @FXML
    protected void onProductsAdd() throws IOException {
        //change screen
        Stage stage = (Stage) addPartBtn.getScene().getWindow();
        stage.close();
        Stage ps = new Stage();

        Parent root = FXMLLoader.load((getClass().getResource("AddProduct.fxml")));
        ps.setTitle("Add a product");
        ps.setScene(new Scene(root, 700, 650));
        ps.show();

    }
    /**
     * The onProductDelete deletes the selected product if it has no associate parts from inventory after showing a dialog box.
     *
     */
    @FXML
    protected void onProductsDelete() {
        Product p=productTable.getSelectionModel().getSelectedItem();
        if(p==null){
            System.out.println("ERROR: PLEASE SELECT AN ITEM");
            warningForProducts.setLayoutY(230);
            warningForProducts.setText(SudoSentenceCase("ERROR: ")+SudoSentenceCase("PLEASE SELECT AN ITEM"));
            warningForProducts.setStyle("-fx-text-fill:red");
        }else{
warningForProducts.setText("");
         if(p.getAllAssociatedPartsProduct()==null||p.getAllAssociatedPartsProduct().size()==0) {
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"");
             alert.initModality(Modality.APPLICATION_MODAL);
             alert.initOwner( (Stage) deleteProductBtn.getScene().getWindow());
             alert.getDialogPane().setContentText(SudoSentenceCase("please Confirm that you want to delete Product?"));
             alert.getDialogPane().setHeaderText(SudoSentenceCase("Delete Product"));
             Optional<ButtonType> R=alert.showAndWait();
             if(R.get()==ButtonType.OK)
             Inventory.deleteProduct(p);
         }else{
             System.out.println("ERROR: CAN'T DELETE PRODUCT BECAUSE IT HAS ASSOCIATED PARTS ");
           warningForProducts.setLayoutY(warningForParts.getLayoutY()+30);
             warningForProducts.setText(SudoSentenceCase("ERROR: ")+SudoSentenceCase("CAN'T DELETE PRODUCT BECAUSE IT HAS ASSOCIATED PARTS"));
             warningForProducts.setStyle("-fx-text-fill:red");
             productTable.getSelectionModel().clearSelection();
         }

        }
    }

    /**
     * Quits the application
     */
    @FXML
    protected void onExitClick() {
        System.out.println("exit");
      System.exit(0);
    }

    /**
     * runs when this screen initializes
     * sets the styles for buttons and the fields in the table
     * @param url   The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle  - The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //btn styling
        addPartBtn.setStyle(addStyle);
        modifyPartBtn.setStyle(modifyStyle);
        deletePartBtn.setStyle(deleteStyle);
        addProductBtn.setStyle(addStyle);
        modifyProductBtn.setStyle(modifyStyle);
        deleteProductBtn.setStyle(deleteStyle);
        exitBtn.setStyle(deleteStyle);
//      setting sentence case
        addPartBtn.setText(SudoSentenceCase(addPartBtn.getText()));
        modifyPartBtn.setText(SudoSentenceCase(modifyPartBtn.getText()));
        deletePartBtn.setText(SudoSentenceCase(deletePartBtn.getText()));
        addProductBtn.setText(SudoSentenceCase(addProductBtn.getText()));
        modifyProductBtn.setText(SudoSentenceCase(modifyProductBtn.getText()));
        deleteProductBtn.setText(SudoSentenceCase(deleteProductBtn.getText()));
        exitBtn.setText(SudoSentenceCase(exitBtn.getText()));


        //      setting table for Parts
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        //setting products table
        productTable.setItems(Inventory.getAllProducts());

//        getIdProduct
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("NameProduct"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stockProduct"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("priceProduct"));



    }

    /**
     * onSearchPart filters the part table for the value in the search field.
     * @param actionEvent An Event representing some type of action.
     */
    public void onSearchPart(ActionEvent actionEvent) {
        String searchString = searchPart.getText();
//        I will use regex to find if the given search string has numbers.
        ObservableList<Part> pArray;
       if  (searchString.matches("\\d+")){
//           number

    Part p =Inventory.lookupPart(Integer.parseInt(searchString));

        pArray = FXCollections.observableArrayList();
        if(p!=null) {
            pArray.add(p);
        }
        partTableView.setItems(pArray);

       }
       else{
//
        pArray= Inventory.lookupPart(searchString);
           partTableView.setItems(pArray);

       }
        if(pArray.isEmpty()){
            System.out.println("Nothing was Found");
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner( (Stage) searchPart.getScene().getWindow());
            alert.getDialogPane().setHeaderText(SudoSentenceCase("Nothing was Found"));
            Optional<ButtonType> R=alert.showAndWait();

        }
    }

    /**
     * onSearchProduct filters the products table for the value in the search field.
     * @param actionEvent An Event representing some type of action.
     */
    public void onSearchProduct(ActionEvent actionEvent) {
        String searchString = searchProduct.getText();
//        I will use regex to find if the given search string has numbers.
        ObservableList<Product> pArray;
        if  (searchString.matches("\\d+")){
//           number

            Product p =Inventory.lookupProduct(Integer.parseInt(searchString));
        pArray= FXCollections.observableArrayList();
        if(p!=null) {
            pArray.add(p);
        } productTable.setItems(pArray);
        }
        else{
//           string
             pArray= Inventory.lookupProduct(searchString);

            productTable.setItems(pArray);

        }
        if (pArray.isEmpty()){
            System.out.println("Nothing was Found");
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner( (Stage) searchPart.getScene().getWindow());
            alert.getDialogPane().setHeaderText(SudoSentenceCase("Nothing was Found"));
            Optional<ButtonType> R=alert.showAndWait();

        }


    }
}