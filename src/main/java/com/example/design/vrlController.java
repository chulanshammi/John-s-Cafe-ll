package com.example.design;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.design.menuController.selectedDealers;

public class vrlController {

    @FXML
    private Button vrlBack;

    @FXML
    private TextField dealerName;

    @FXML
    private TableView<List<String>> vrlTable;

    @FXML
    private TableColumn<List<String>, String> column3;

    @FXML
    private TableColumn<List<String>, String> column1;

    @FXML
    private TableColumn<List<String>, String> column2;

    static String name;

    // Method to handle "Back" button click event, navigates back to the menu
    @FXML
    void backClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) vrlBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
    }

    // Method to store the selected dealer's name
    @FXML
    void vrlSelectClick(MouseEvent event) {
        name = dealerName.getText();
    }

    // Method to initialize the TableView with data from selectedDealers list
    public void initialize() {
        ArrayList<List<String>> table = new ArrayList<>();

        // Add selected dealers' data to the table list
        table.add(Arrays.asList(selectedDealers.get(0).get(0), selectedDealers.get(0).get(1), selectedDealers.get(0).get(2)));
        table.add(Arrays.asList(selectedDealers.get(1).get(0), selectedDealers.get(1).get(1), selectedDealers.get(1).get(2)));
        table.add(Arrays.asList(selectedDealers.get(2).get(0), selectedDealers.get(2).get(1), selectedDealers.get(2).get(2)));
        table.add(Arrays.asList(selectedDealers.get(3).get(0), selectedDealers.get(3).get(1), selectedDealers.get(3).get(2)));

        // Set cell value factories for each column to display data from the table list
        column1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        column2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        column3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));

        // Add the table data to the TableView
        vrlTable.getItems().addAll(table);
    }
}
