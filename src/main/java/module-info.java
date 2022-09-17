module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.InventoryManagement to javafx.fxml;
    exports com.example.InventoryManagement;
    exports com.example.InventoryManagement.Logic.PartLogic;
    opens com.example.InventoryManagement.Logic.PartLogic to javafx.fxml;
    exports com.example.InventoryManagement.Logic;
    opens com.example.InventoryManagement.Logic to javafx.fxml;

}