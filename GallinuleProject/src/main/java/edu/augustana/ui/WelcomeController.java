package edu.augustana.ui;

import java.io.IOException;

import edu.augustana.ui.App;
import javafx.fxml.FXML;

public class WelcomeController {

    @FXML
    private void switchToGender() throws IOException {
        App.setRoot("Gender");
    }


    @FXML
    private void switchToBrowsePlan() throws IOException {
        App.setRoot("BrowsePlan");
    }

    @FXML
    private void switchToViewAllCard() throws IOException {
        App.setRoot("ViewAllCard");
    }

}
