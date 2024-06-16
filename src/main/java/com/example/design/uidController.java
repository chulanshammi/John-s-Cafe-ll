package com.example.design;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.design.aidController.mapOfItems;
import static com.example.design.aidController.validation;
import static com.example.design.menuController.showText;

public class uidController {

    @FXML
    private Button uidBack;

    @FXML
    private Text uidText;

    @FXML
    private TextField uidCodeTextField;

    @FXML
    private Text searchUidText;

    @FXML
    private TextField uidBrand;

    @FXML
    private TextField uidCategory;

    @FXML
    private TextField uidDate;

    @FXML
    private TextField uidName;

    @FXML
    private TextField uidPrice;

    @FXML
    private TextField uidQuantity;

    @FXML
    private DatePicker uidDatePicker;

    // Method to handle "Back" button click event, navigates back to the menu
    @FXML
    void backClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) uidBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
    }

    // Event handler for input validation on uidCodeTextField
    @FXML
    void uidCodeTyped(KeyEvent event) {
        validation(uidCodeTextField);
    }

    // Event handler for input validation on uidPrice
    @FXML
    void uidPriceTyped(KeyEvent event) {
        validation(uidPrice);
    }

    // Event handler for input validation on uidQuantity
    @FXML
    void uidQuantityTyped(KeyEvent event) {
        validation(uidQuantity);
    }

    // Method to clear all input fields
    @FXML
    void clearAllClick(MouseEvent event) {
        uidName.clear();
        uidBrand.clear();
        uidPrice.clear();
        uidQuantity.clear();
        uidCategory.clear();
        uidDatePicker.setValue(null);
    }

    // Method to handle "Update" button release event, updates item information
    @FXML
    void uidUpdateRelease(MouseEvent event) {
        String uidCode = uidCodeTextField.getText();

        // Get the new values of the item
        List<String> newValues = new ArrayList<>();
        newValues.add(uidName.getText());
        newValues.add(uidBrand.getText());
        newValues.add(uidPrice.getText());
        newValues.add(uidQuantity.getText());

        // Calculate the total price based on price and quantity
        double price = Double.parseDouble(uidPrice.getText());
        int quantity = Integer.parseInt(uidQuantity.getText());
        newValues.add(String.valueOf(price * quantity));

        newValues.add(uidCategory.getText());
        newValues.add(uidDatePicker.getValue().toString());

        // Update the item information in the mapOfItems HashMap
        mapOfItems.put(uidCode, newValues);

        // Clear all input fields after update
        uidName.setText("");
        uidBrand.setText("");
        uidPrice.setText("");
        uidQuantity.setText("");
        uidCategory.setText("");
        uidDatePicker.setValue(null);

        // Debugging: Print the updated mapOfItems
        System.out.println(mapOfItems);

        // Show "Item Updated!" message using the showText method from menuController
        showText(uidText, "Item Updated!");
    }

    // Method to handle "Search" button click event, search for an item by its code
    @FXML
    void uidSearch(MouseEvent event) {
        String uidCode = uidCodeTextField.getText();
        if (mapOfItems.containsKey(uidCode)) {
            // If the item is found in the mapOfItems, display its details

            // Clear the searchUidText, which is used to show an error message
            searchUidText.setText("");

            // Get the attributes of the item using its code from mapOfItems
            List<String> valuesForKey = mapOfItems.get(uidCode);

            // Extract individual attributes
            String name = valuesForKey.get(0);
            String brand = valuesForKey.get(1);
            String price = valuesForKey.get(2);
            String quantity = valuesForKey.get(3);
            String category = valuesForKey.get(5);
            String date = valuesForKey.get(6);

            // Convert the date string to a LocalDate object
            LocalDate date1 = LocalDate.parse(date);

            // Display the details in the respective input fields
            uidName.setText(name);
            uidBrand.setText(brand);
            uidPrice.setText(price);
            uidQuantity.setText(quantity);
            uidCategory.setText(category);
            uidDatePicker.setValue(date1);
        } else {
            // If the item is not found, clear the input field and show an error message
            uidCodeTextField.clear();
            showText(searchUidText, "* Item not available");
        }
    }

    // Method to handle "SID" button release event, saves item information to a file
    @FXML
    void sidRelease(MouseEvent event) {
        try {
            FileWriter file = new FileWriter("Items.txt");
            for (String key : mapOfItems.keySet()) {
                List<String> values = mapOfItems.get(key);

                file.write(key + ": ");
                for (String value : values) {
                    file.write(value + ", ");
                }
                file.write("\n");
            }
            file.close();
            showText(uidText, "Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file");
        }
    }
}
