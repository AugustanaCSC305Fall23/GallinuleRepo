package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;

public class PremadePlanPage {

    @FXML
    private void switchToBrowsePlan() throws IOException {
        App.setRoot("BrowsePlan");
    }

    @FXML
    private void switchToYourPlanPage() throws IOException {
        App.setRoot("YourPlanPage");
    }
}
