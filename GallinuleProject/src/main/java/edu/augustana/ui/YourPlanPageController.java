package edu.augustana.ui;

import java.io.IOException;

import edu.augustana.ui.App;
import javafx.fxml.FXML;

public class YourPlanPageController {

    @FXML
    private void switchToBrowsePlan() throws IOException {
        App.setRoot("BrowsePlan");
    }

    @FXML
    private void switchToPremadePage() throws IOException {
        App.setRoot("PremadePage");
    }
}
