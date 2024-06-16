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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.design.menuController.showText;

public class aidController {

    // Fields representing GUI components defined in the FXML file
    @FXML
    private Button aidBack;
    @FXML
    private Text aidText;
    @FXML
    private TextField codeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private DatePicker purchasedDatePicker;

    // Hashmap for Items: Stores item information using item codes as keys and lists of attributes as values
    public static HashMap<String, List<String>> mapOfItems = new HashMap<>();

    // Creating a decimalFormat to format prices
    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    // Method to handle "Back" button click event, navigates back to the menu
    @FXML
    void backClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) aidBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
    }

    // Method for input validation, clears text field if non-numeric characters are entered
    public static void validation(TextField text) {
        String input = text.getText();
        if (!input.matches("\\d*\\.?\\d*")) {
            text.clear();
        }
    }

    // Event handler for input validation on codeTextField
    @FXML
    void codeTyped(KeyEvent event) {
        validation(codeTextField);
    }

    // Event handler for input validation on priceTextField
    @FXML
    void priceTyped(KeyEvent event) {
        validation(priceTextField);
    }

    // Event handler for input validation on quantityTextField
    @FXML
    void quantityTyped(KeyEvent event) {
        validation(quantityTextField);
    }

    // Event handler for the "Submit" button click event
    @FXML
    void aidSubmitRelease(MouseEvent event) {
        // Show "Item Added!" message using the showText method from menuController
        showText(aidText, "Item Added!");

        // Extract input values from the text fields and date picker
        String codeInput = codeTextField.getText();
        String nameInput = nameTextField.getText();
        String brandInput = brandTextField.getText();
        double priceInput = Double.parseDouble(priceTextField.getText());
        String formatPriceInput = decimalFormat.format(priceInput);
        int quantityInput = Integer.parseInt(quantityTextField.getText());
        double totalPrice = (priceInput * quantityInput);
        String formatTotalPrice = decimalFormat.format(totalPrice);
        String categoryInput = categoryTextField.getText();
        String dateInput = purchasedDatePicker.getValue().toString();

        // Create a list to store the item attributes
        List<String> map = new ArrayList<>();
        map.add(nameInput);
        map.add(brandInput);
        map.add(formatPriceInput);
        map.add(String.valueOf(quantityInput));
        map.add(formatTotalPrice);
        map.add(categoryInput);
        map.add(dateInput);

        // Add the item information to the mapOfItems HashMap using the item code as the key
        mapOfItems.put(codeInput, map);
        System.out.println(mapOfItems);

        // Clear all input fields after submitting the item
        clearAllClick(event);
    }

    // Method to clear all input fields and date picker
    @FXML
    void clearAllClick(MouseEvent event) {
        codeTextField.clear();
        nameTextField.clear();
        brandTextField.clear();
        priceTextField.clear();
        quantityTextField.clear();
        categoryTextField.clear();
        purchasedDatePicker.setValue(null);
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
            showText(aidText, "Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file");
        }
    }
}
