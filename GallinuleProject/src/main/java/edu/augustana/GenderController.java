package edu.augustana;

import java.io.IOException;

import javafx.fxml.FXML;

public class GenderController {

    @FXML
    private void switchToWelcome() throws IOException {
        App.setRoot("Welcome");
    }

    @FXML
    private void handleMaleButtonClick() throws IOException {
        // Handle the "Male" button click here
        // You can change the behavior as needed
        App.setRoot("Start");
    }

    @FXML
    private void handleFemaleButtonClick() throws IOException {
        // Handle the "Female" button click here
        // You can change the behavior as needed
        App.setRoot("Start");
    }

    @FXML
    private void handleNeutralButtonClick() throws IOException {
        // Handle the "Neutral" button click here
        // You can change the behavior as needed
        App.setRoot("Start");
    }
}