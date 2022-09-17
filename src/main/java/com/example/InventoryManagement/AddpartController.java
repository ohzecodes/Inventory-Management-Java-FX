package com.example.InventoryManagement;

import com.example.InventoryManagement.Logic.Inventory;
import com.example.InventoryManagement.Logic.PartLogic.Outsourced;
import com.example.InventoryManagement.Logic.PartLogic.inHouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.InventoryManagement.Styling.*;
import static com.example.InventoryManagement.TransformInput.SentenceCase;


/**
 * Controller for Add Part screen
 * @author Mohammed Zafar
 */
public class AddpartController implements Initializable {
    public Button backbtn;
    public Label errors;
    @FXML
    private RadioButton inhouseradiobtn, outsourcedradiobtn;
    public TextField partid;
    public TextField partname;
    public TextField partinv; //int
    public TextField partprice; //double
    public TextField partmax; //int
    public TextField partmin; //int
    public Text partmachinelabel; //string| int
    public TextField partmachineid;
    public Button savebtn;

    /**
     * Goes back to main screen
     * @param actionEvent An Event representing some type of action
     * @throws IOException Happens if mainScreenView.fxml is not found
     */
    public void backtomain(ActionEvent actionEvent) throws IOException{

            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.close();
            Stage ps = new Stage();

            Parent root = FXMLLoader.load((getClass().getResource("MainScreenView.fxml")));
            ps.setTitle("Main Screen");
            ps.setScene(new Scene(root, 1000, 500));
            ps.show();

    }

    /**
     * runs when this screen initializes
     * sets the styles for buttons and the style for the partid textbox. Also Disables partid text box for editing.
     * @param url   The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle  - The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);

        partid.setText("auto generated");
        partid.setEditable(false);
        partid.setStyle(DisableStyle);
        gettype(new ActionEvent());

        Double d =66.0;
        savebtn.setStyle(addStyle);
        savebtn.setPrefWidth(d);
        backbtn.setStyle(deleteStyle);
        backbtn.setPrefWidth(d);

    }

    /**
     * A helper method for saving
     * Validates the input field before saving using regex
     * @return false if we are not good to go true otherwise.
     */
    private boolean validate(){
        boolean a=(!partname.getText().isEmpty());
        boolean b = (!partinv.getText().isEmpty())  && partinv.getText().matches("\\d+");
        boolean c= (!partprice.getText().isEmpty()) &&  partprice.getText().matches("(\\d{0,})\\.*(\\d+)");
        boolean d= (!partmax.getText().isEmpty())   && partmax.getText().matches("\\d+");
        boolean e= (!partmin.getText().isEmpty())   && partmin.getText().matches("\\d+");
        boolean f= inhouseradiobtn.isSelected()? partmachineid.getText().matches("\\d+") : (!partmachineid.getText().isEmpty());
        errors.setText("");
        errors.setStyle("-fx-text-fill: red");
        if (!(a&&b&&c&&d&&e&&f)){
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
            errors.setText(errors.getText()+ "Part Price is Empty or not a Double \n\t");
        }if(!d){
            System.out.println("Max is Empty or not a Number ");
            errors.setText(errors.getText()+ "Max is Empty or not a Number  \n\t");
        }if(!e){
            System.out.println("Min is Empty or not a Number ");
            errors.setText(errors.getText()+ "Min is Empty or not a Number  \n\t");
        }if((!f)&&inhouseradiobtn.isSelected()){
            System.out.println("MachineID is Empty or not a Number ");
            errors.setText(errors.getText()+ "MachineId is Empty or not a Number  \n\t");
        }if((!f)&&outsourcedradiobtn.isSelected()){
            System.out.println("Company name is Empty");
            errors.setText(errors.getText()+ "Company name is Empty  \n\t");
        }


        boolean abcd=true;
        if(a&&b&&c&&d&&e&&f){
            int inv=     Integer.parseInt(partinv.getText());
            int max=    Integer.parseInt(partmax.getText());
            int min=    Integer.parseInt(partmin.getText());
            boolean  ca = min<max;
            boolean invCheck=inv>min&&inv<max;
            abcd=ca&&invCheck;
            if (!ca){
                errors.setText("Errors\n\tMax should greater than min");
            }if(!invCheck){
                errors.setText(errors.getText()+ "\n\tInv should be between min and max");
            }


        }
        return a&&b&&c&&d&&e&&f&&abcd;

    }

    /**
     * if it passes validation then Saves the part and return to Main
     * @param actionEvent  An Event representing some type of action.
     * @throws IOException Happens if mainscreenview.FXML is not found
     */
    public void onsave(ActionEvent actionEvent) throws IOException {
        if (validate()) {
            String name = SentenceCase(partname.getText().trim());
            int stock = Integer.parseInt(partinv.getText().trim());
            Double price = Double.valueOf(partprice.getText().trim());
            int max = Integer.parseInt(partmax.getText().trim());
            int min = Integer.parseInt(partmin.getText().trim());

            if (outsourcedradiobtn.isSelected()) {
                String companyName = partmachineid.getText().trim();
                Outsourced p = new Outsourced(Inventory.getNextPartid(), name, price, stock, min, max, companyName);
                Inventory.addPart(p);
            } else if (inhouseradiobtn.isSelected()) {
                int machineId = Integer.parseInt(partmachineid.getText().trim());
                inHouse p = new inHouse(Inventory.getNextPartid(), name, price, stock, min, max, machineId);
                Inventory.addPart(p);
            }
            this.backtomain(actionEvent);
        }else {
            System.out.println("something failed");
        }
    }

    /**
     * gets the type of the part from the radio button and sets corresponding labels and text fields
     * @param actionEvent An Event representing some type of action.
     */
    public void gettype(ActionEvent actionEvent) {
//
        if(outsourcedradiobtn.isSelected()){
            partmachinelabel.setText("Company Name");
            partmachineid.setPromptText("Company Name");
        }
        else if(inhouseradiobtn.isSelected())
        {
            partmachinelabel.setText("Machine Id");
            partmachineid.setPromptText("Machine Id ");
        }

    }
}
