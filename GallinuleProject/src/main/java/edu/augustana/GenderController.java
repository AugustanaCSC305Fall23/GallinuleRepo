package edu.augustana;

import java.io.IOException;

import javafx.fxml.FXML;

/**
 * Controller class for the gender selection screen.
 * Manages the navigation to the welcome screen and handles button clicks for gender selection.
 */
public class GenderController {

    @FXML
    private void switchToWelcome() throws IOException {
        App.setRoot("Welcome");
    }

    @FXML
    private void handleMaleButtonClick() throws IOException {
        // Handle the "Male" button click here
        App.setRoot("Start");
    }

    @FXML
    private void handleFemaleButtonClick() throws IOException {
        // Handle the "Female" button click here
        App.setRoot("Start");
    }

    @FXML
    private void handleNeutralButtonClick() throws IOException {
        // Handle the "Neutral" button click here
        App.setRoot("Start");
    }
}