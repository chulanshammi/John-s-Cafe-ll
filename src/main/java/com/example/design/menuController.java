package com.example.design;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.example.design.aidController.mapOfItems;

public class menuController {

    // Buttons on the main menu
    @FXML
    private Button aidButton;
    @FXML
    private Button didButton;
    @FXML
    private Button uidButton;
    @FXML
    private Button vidButton;
    @FXML
    private Button vrlButton;
    @FXML
    private Button ldiButton;
    @FXML
    private Button quitButton;

    // Text element to display messages to the user
    @FXML
    private Text messageText;

    // ArrayList to store selected dealers
    static ArrayList<List<String>> selectedDealers = new ArrayList<>();

    // Method to handle "AID" button click event, navigates to the AID view
    @FXML
    void aidClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) aidButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("aid.fxml"));
        stage.setTitle("AID");
        stage.setScene(new Scene(root));
    }

    // Method to handle "DID" button click event, navigates to the DID view
    @FXML
    void didClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) didButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("did.fxml"));
        stage.setTitle("DID");
        stage.setScene(new Scene(root));
    }

    // Method to handle "UID" button click event, navigates to the UID view
    @FXML
    void uidClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) uidButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("uid.fxml"));
        stage.setTitle("UID");
        stage.setScene(new Scene(root));
    }

    // Method to handle "VID" button click event, navigates to the VID view
    @FXML
    void vidClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) vidButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vid.fxml"));
        stage.setTitle("VID");
        stage.setScene(new Scene(root));
    }

    // Show text on the screen and then clear it after a short duration
    public static void showText(Text name, String message) {
        name.setText(message);
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(actionEvent -> name.setText(""));
        pause.play();
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
            showText(messageText, "Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file");
        }
    }

    // Method to handle "SDD" button release event, selects four random dealers
    @FXML
    void sddRelease(MouseEvent event) throws IOException {
        ArrayList<List<String>> dealers = new ArrayList<>();

        // Read dealer information from "Dealers.txt" and store in the 'dealers' list
        try (BufferedReader br = new BufferedReader(new FileReader("Dealers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> dealersSet = Arrays.asList(line.split(",\\s*"));
                dealers.add(dealersSet);
            }
        }

        // Randomly select four dealers from the 'dealers' list and add them to 'selectedDealers'
        Random random = new Random();
        while (selectedDealers.size() < 4) {
            int randomIndex = random.nextInt(dealers.size());
            List<String> selectedDealersSet = dealers.get(randomIndex);
            selectedDealers.add(selectedDealersSet);
            dealers.remove(randomIndex);
        }

        // Show a message indicating the successful selection of four dealers
        showText(messageText, "4 Dealers Selected Randomly!");

        // Debugging: Print the selected dealers
        System.out.println(selectedDealers);
    }

    // Method to handle "VRL" button click event, navigates to the VRL view
    @FXML
    void vrlClick(MouseEvent event) {
        try {
            // Attempt to navigate to the VRL view
            Stage stage = (Stage) vrlButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("vrl.fxml"));
            stage.setTitle("DID");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            // If VRL view navigation fails, show an error message
            showText(messageText, "Use SDD before VRL!");
        }
    }

    // Method to handle "LDI" button click event, navigates to the LDI view
    @FXML
    void ldiClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) ldiButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ldi.fxml"));
        stage.setTitle("DID");
        stage.setScene(new Scene(root));
    }

    // Method to handle "Quit" button click event, closes the application
    @FXML
    void quitClick(MouseEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
