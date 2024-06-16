package com.example.design;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.design.aidController.mapOfItems;
import static com.example.design.vrlController.name;

public class ldiController {

    @FXML
    private Button vrlBack;

    @FXML
    private Text dealerName;

    // Table columns to display the item attributes
    @FXML
    private TableColumn<List<String>, String> column1;
    @FXML
    private TableColumn<List<String>, String> column2;
    @FXML
    private TableColumn<List<String>, String> column3;
    @FXML
    private TableColumn<List<String>, String> column4;

    // TableView to display the list of items
    @FXML
    private TableView<List<String>> ldiTable;

    // Method to handle "Back" button click event, navigates back to the menu
    @FXML
    void backClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) vrlBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
    }

    // This method is called during the initialization of the controller
    public void initialize() {
        // Set the dealer name in the GUI (assuming it's set in the 'vrlController' class)
        dealerName.setText(name);

        // Set cell value factories to display item attributes in each column
        column1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        column2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        column3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        column4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));

        // Add the list of item values (attribute lists) from the mapOfItems to the TableView
        ldiTable.getItems().addAll(mapOfItems.values());
    }
}
