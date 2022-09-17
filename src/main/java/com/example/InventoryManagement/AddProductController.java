package com.example.InventoryManagement;

import com.example.InventoryManagement.Logic.Inventory;
import com.example.InventoryManagement.Logic.PartLogic.Part;
import com.example.InventoryManagement.Logic.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
 * Controller for the add product screen
 * @author  Mohammed Zafar
 */
public class AddProductController implements Initializable {

    public Button addpart;
    public TextField productid;
    public TextField productname;
    public TextField productinv;
    public TextField productprice;
    public TextField productmax;
    public TextField productmin;
    public Button rmassosciatedbtn;
    public Button savebtn;
    public Button backbtn;
    public TableView partTableView;

    public TableColumn partid;
    public TableColumn partname;
    public TableColumn partinv;
    public TableColumn partprice;
    public TableView associatedparts;

    public TableColumn apartid;
    public TableColumn apartname;
    public TableColumn apartinv;
    public TableColumn apartprice;
    public TextField searchPart;
    public Label errors;
    ObservableList<Part> BottomList= FXCollections.observableArrayList();

    /**
     * Set the top table and places Parts from inventory in it
     */
    private void initparttable(){

        partTableView.setItems(Inventory.getAllParts());
        partid.setCellValueFactory(new PropertyValueFactory<>("id"));
        partname.setCellValueFactory(new PropertyValueFactory<>("name"));
        partinv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partprice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Set the bottom table and places Parts from BottomList in it.
     *  The bottom list populates the Associated parts when saving
     */
    private  void initApartTable(){
//        BottomList.addAll(p.getAllAssociatedPartsProduct());
        associatedparts.setItems(BottomList);
        apartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        apartname.setCellValueFactory(new PropertyValueFactory<>("name"));
        apartinv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apartprice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * A helper method for saving
     * Validate the input field before saving using regex
     * @return false if we are not good to go true otherwise.
     */
    private boolean validate(){

        boolean a=(!productname.getText().isEmpty());
        boolean b = (!productinv.getText().isEmpty())  && productinv.getText().matches("\\d+");
        boolean c= (!productprice.getText().isEmpty()) &&  productprice.getText().matches("(\\d{0,})\\.*(\\d+)");
        boolean d= (!productmax.getText().isEmpty())   && productmax.getText().matches("\\d+");
        boolean e= (!productmin.getText().isEmpty())   && productmin.getText().matches("\\d+");

        errors.setText("");
        errors.setStyle("-fx-text-fill: red");
        if (!(a&&b&&c&&d&&e)){
            errors.setText("Errors: \n\t");
        }
        if (!a) {
            System.out.println("Name is Empty");
            errors.setText(errors.getText()+"Name is Empty\n\t");
        }  if (!b) {
            System.out.println("Inventory Level is Empty or not a number");
            errors.setText(errors.getText()+ "Inventory Level is Empty or not a number\n\t");
        }if(!c) {
            System.out.println("Part Price  is Empty or not a Double ");
            errors.setText(errors.getText()+ "Price is Empty or not a Double \n\t");
        }if(!d){
            System.out.println("Max is Empty or not a Number ");
            errors.setText(errors.getText()+ "Max is Empty or not a Number  \n\t");
        }if(!e){
            System.out.println("Min is Empty or not a Number ");
            errors.setText(errors.getText()+ "Min is Empty or not a Number  \n\t");
        }


        boolean abcd=true;
        if(a&&b&&c&&d&&e){
            int inv= Integer.parseInt(productinv.getText());
            int max=Integer.parseInt(productmax.getText());
            int min=    Integer.parseInt(productmin.getText());
            boolean  ca = min<max;
            boolean invCheck=inv>min&&inv<max;
            abcd=ca&&invCheck;
            if (!ca){
                errors.setText("Errors\n\tMax should greater than min");
            }if(!invCheck){
                errors.setText(errors.getText()+ "\n\tInv should be between min and max");
            }
        }
        return a&&b&&c&&d&&e&&abcd;
    }

    /**
     * runs when this screen initializes
     * sets the styles for buttons and the style for the product id  text box. Also Disables product id text box for editing.
     *
     * @param url   The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle  - The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productid.setText("auto generated");
        productid.setEditable(false);
        productid.setStyle(DisableStyle);
        addpart.setStyle(addStyle);

        rmassosciatedbtn.setStyle(deleteStyle);
        Double d =66.0;
        savebtn.setStyle(addStyle);
        savebtn.setPrefWidth(d);
        backbtn.setStyle(deleteStyle);
        backbtn.setPrefWidth(d);
        addpart.setText(SudoSentenceCase(addpart.getText()));
        rmassosciatedbtn.setText(SudoSentenceCase(rmassosciatedbtn.getText()));
    initparttable();
    initApartTable();
    }
    /**
     * Goes back to main screen. and does not save the associated parts.
     * @param actionEvent An Event representing some type of action
     * @throws IOException Happens if mainScreenView.fxml is not found
     */
    public void backtomain(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) backbtn.getScene().getWindow();
        stage.close();
        Stage ps = new Stage();

        Parent root = FXMLLoader.load((getClass().getResource("MainScreenView.fxml")));
        ps.setTitle("Main Screen");
        ps.setScene(new Scene(root, 1000, 500));
        ps.show();

    }

    /**
     * if passes validation Saves the basic fields into the products and copies the bottom list of parts to the associated parts then return to main.
     * @param actionEvent An Event representing some type of action.
     * @throws IOException from backtomain method
     */
    public void save(ActionEvent actionEvent)throws  IOException{
//        int id,String name, double price, int stock, int min, int max
        if (validate()) {
            String name = productname.getText();
            double price = Double.parseDouble(productprice.getText());
            int stock = Integer.parseInt(productinv.getText());
            int min = Integer.parseInt(productmin.getText());
            int max = Integer.parseInt(productmax.getText());
            Product p = new Product(Inventory.getNextProductId(), name, price, stock, min, max);
            for (Part part : BottomList) {
                p.addAssociatedPart(part);
            }

            Inventory.addProduct(p);
            backtomain(actionEvent);
        }
    }

    /**
     * Add the selected part in the top table (All part list) to the bottom list
     */
    public void addToAssociated(){
       Part p= (Part) partTableView.getSelectionModel().getSelectedItem();
    if(p==null){
        errors.setStyle("-fx-text-fill: red");
        System.out.println("ERROR:Select a part");
        errors.setText("Error\n\t"+"Select a part to add");
    }
    else {
        errors.setText("");
        BottomList.add(p);
        partTableView.getSelectionModel().clearSelection();


    }
    }

    /**
     * Deletes the selected part from the bottom list
     */
    public void rmAssociated(){
        Part p= (Part) associatedparts.getSelectionModel().getSelectedItem();
        if(p==null){
            errors.setStyle("-fx-text-fill: red");
            System.out.println("ERROR:select a part");
            errors.setText("Error\n\t"+"Select a part to remove");
        }else{
            errors.setText("");
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner( (Stage) rmassosciatedbtn.getScene().getWindow());
            alert.getDialogPane().setContentText(SudoSentenceCase("please confirm that you want to delete part?"));
            alert.getDialogPane().setHeaderText(SudoSentenceCase("Delete part"));

            Optional<ButtonType> R=alert.showAndWait();
            if(R.get()==ButtonType.OK) {
                BottomList.remove(p);
        }


        }
        associatedparts.getSelectionModel().clearSelection();
    }

    /**
     * onSearchPart filters the top table (All parts) for the value in the search field.
     * Checks if the value in the search box could be ID or String using Regex
     * @param actionEvent An Event representing some type of action.
     */
    public void onSearchPart(ActionEvent actionEvent) {
        String searchString = searchPart.getText();
//        I will use regex to find if the given search string has numbers.

        ObservableList<Part> pArray;
        if (searchString.matches("\\d+")) {
//           number

            Part p = Inventory.lookupPart(Integer.parseInt(searchString));
             pArray = FXCollections.observableArrayList();
            if(p!=null) pArray.add(p);
            partTableView.setItems(pArray);
        } else {
//           string
            pArray = Inventory.lookupPart(searchString);

            partTableView.setItems(pArray);

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
