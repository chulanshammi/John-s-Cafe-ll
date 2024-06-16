package com.example.design;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.design.aidController.mapOfItems;

public class vidController {

    @FXML
    private Button vidBack;

    @FXML
    private TableView<mapData> vidTable;

    @FXML
    private TableColumn<mapData, String> column1;

    @FXML
    private TableColumn<mapData, String> column2;

    @FXML
    private TableColumn<mapData, String> column3;

    @FXML
    private TableColumn<mapData, String> column4;

    @FXML
    private TableColumn<mapData, String> column5;

    @FXML
    private TableColumn<mapData, String> column6;

    @FXML
    private TableColumn<mapData, String> column7;

    @FXML
    private TableColumn<mapData, String> column8;

    // Method to handle "Back" button click event, navigates back to the menu
    @FXML
    void backClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) vidBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
    }

    // Inner class to represent data for each row in the TableView
    public static class mapData {
        private final String key;
        private final String column2Data;
        private final String column3Data;
        private final String column4Data;
        private final String column5Data;
        private final String column6Data;
        private final String column7Data;
        private final String column8Data;

        // Constructor to initialize the data for each row
        public mapData(String key, String column2Data, String column3Data, String column4Data, String column5Data, String column6Data, String column7Data, String column8Data) {
            this.key = key;
            this.column2Data = column2Data;
            this.column3Data = column3Data;
            this.column4Data = column4Data;
            this.column5Data = column5Data;
            this.column6Data = column6Data;
            this.column7Data = column7Data;
            this.column8Data = column8Data;
        }

        // Getters for the data fields
        public String getKey() {
            return key;
        }

        public String getColumn2Data() {
            return column2Data;
        }

        public String getColumn3Data() {
            return column3Data;
        }

        public String getColumn4Data() {
            return column4Data;
        }

        public String getColumn5Data() {
            return column5Data;
        }

        public String getColumn6Data() {
            return column6Data;
        }

        public String getColumn7Data() {
            return column7Data;
        }

        public String getColumn8Data() {
            return column8Data;
        }
    }

    // Method to initialize the TableView with data from mapOfItems
    public void initialize() {
        // Set cell value factories for each column to display data from mapData object
        column1.setCellValueFactory(new PropertyValueFactory<>("key"));
        column2.setCellValueFactory(new PropertyValueFactory<>("column2Data"));
        column3.setCellValueFactory(new PropertyValueFactory<>("column3Data"));
        column4.setCellValueFactory(new PropertyValueFactory<>("column4Data"));
        column5.setCellValueFactory(new PropertyValueFactory<>("column5Data"));
        column6.setCellValueFactory(new PropertyValueFactory<>("column6Data"));
        column7.setCellValueFactory(new PropertyValueFactory<>("column7Data"));
        column8.setCellValueFactory(new PropertyValueFactory<>("column8Data"));

        // Create an ObservableList to hold the data for the TableView
        ObservableList<mapData> data = FXCollections.observableArrayList();

        // Loop through the items in mapOfItems and add them to the data list
        for (String name : mapOfItems.keySet()) {
            List<String> columnsData = mapOfItems.get(name);

            // Check if there are at least 7 columns of data available
            if (columnsData.size() >= 7) {
                data.add(new mapData(name, columnsData.get(0),
                        columnsData.get(1), columnsData.get(2),
                        columnsData.get(3), columnsData.get(4),
                        columnsData.get(5), columnsData.get(6)));
            }
        }

        // Set the data list as the items of the TableView
        vidTable.setItems(data);
    }
}
