package com.example.design;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.example.design.aidController.mapOfItems;
import static com.example.design.aidController.validation;
import static com.example.design.menuController.showText;

public class didController {

    @FXML
    private Button didBack;

    @FXML
    private Text didText;

    @FXML
    private TextField didCodeTextField;

    // Text elements to display the details of a specific item
    @FXML
    private Text didBrand;
    @FXML
    private Text didCategory;
    @FXML
    private Text didDate;
    @FXML
    private Text didName;
    @FXML
    private Text didPrice;
    @FXML
    private Text didQuantity;
    @FXML
    private Text didTotalPrice;

    @FXML
    private Text searchDidText;

    // Method to handle "Back" button click event, navigates back to the menu
    @FXML
    void backClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) didBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
    }

    // Event handler for input validation on didCodeTextField
    @FXML
    void didCodeTyped(KeyEvent event) {
        validation(didCodeTextField);
    }

    // Event handler for the "Delete" button click event
    @FXML
    void didDeleteRelease(MouseEvent event) {
        // Get the item code to delete
        String searchCode = didCodeTextField.getText();

        // Remove the item from the mapOfItems HashMap
        mapOfItems.remove(searchCode);

        // Clear all text elements to show the item details
        didCodeTextField.clear();
        didName.setText("");
        didBrand.setText("");
        didPrice.setText("");
        didQuantity.setText("");
        didTotalPrice.setText("");
        didCategory.setText("");
        didDate.setText("");

        System.out.println(mapOfItems);

        // Show "Item Deleted!" message using the showText method from menuController
        showText(didText, "Item Deleted!");
    }

    // Event handler for the "Search" button click event
    @FXML
    void didSearchClick(MouseEvent event) {
        // Get the item code to search for
        String searchCode = didCodeTextField.getText();

        if (mapOfItems.containsKey(searchCode)) {
            // If the item is found in the mapOfItems, display its details

            // Clear the searchDidText, which is used to show an error message
            searchDidText.setText("");

            // Get the attributes of the item using its code from mapOfItems
            List<String> valuesForKey = mapOfItems.get(searchCode);

            // Extract individual attributes
            String name = valuesForKey.get(0);
            String brand = valuesForKey.get(1);
            String price = valuesForKey.get(2);
            String quantity = valuesForKey.get(3);
            String total = valuesForKey.get(4);
            String category = valuesForKey.get(5);
            String date = valuesForKey.get(6);

            // Display the details in the respective text elements
            didName.setText(name);
            didBrand.setText(brand);
            didPrice.setText(price);
            didQuantity.setText(quantity);
            didTotalPrice.setText(total);
            didCategory.setText(category);
            didDate.setText(date);

        } else {
            // If the item is not found, clear the input field and show an error message
            didCodeTextField.clear();
            showText(searchDidText, "* Item not available");
        }
    }

    // Method to handle the "Save" button click event
    @FXML
    void sidRelease(MouseEvent event) {
        try {
            // Write the item information from mapOfItems to a file named "Items.txt"
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

            // Show "Saved Successfully!" message using the showText method from menuController
            showText(didText, "Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file");
        }
    }
}
