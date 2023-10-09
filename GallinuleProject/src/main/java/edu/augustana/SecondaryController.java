package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void handleMaleButtonClick() throws IOException {
        // Handle the "Male" button click here
        // You can change the behavior as needed
        App.setRoot("primary");
    }

    @FXML
    private void handleFemaleButtonClick() throws IOException {
        // Handle the "Female" button click here
        // You can change the behavior as needed
        App.setRoot("primary");
    }

    @FXML
    private void handleNeutralButtonClick() throws IOException {
        // Handle the "Neutral" button click here
        // You can change the behavior as needed
        App.setRoot("primary");
    }
}