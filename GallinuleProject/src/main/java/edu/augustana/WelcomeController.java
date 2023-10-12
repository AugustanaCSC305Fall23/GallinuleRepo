package edu.augustana;

import java.io.IOException;
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

}
