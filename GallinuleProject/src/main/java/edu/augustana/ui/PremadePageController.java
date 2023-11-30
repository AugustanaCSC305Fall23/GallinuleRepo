package edu.augustana.ui;

import java.io.IOException;

import edu.augustana.ui.App;
import javafx.fxml.FXML;

public class PremadePageController {

    @FXML
    private void switchToBrowsePlan() throws IOException {
        App.setRoot("BrowsePlan");
    }

    @FXML
    private void switchToYourPlanPage() throws IOException {
        App.setRoot("YourPlanPage");
    }
}
