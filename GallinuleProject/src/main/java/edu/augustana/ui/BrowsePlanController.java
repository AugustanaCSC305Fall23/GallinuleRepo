package edu.augustana.ui;

import java.io.IOException;

import edu.augustana.ui.App;
import javafx.fxml.FXML;

public class BrowsePlanController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToPremadePage() throws IOException {
        App.setRoot("PremadePage");
    }

    @FXML
    private void switchToYourPlanPage() throws IOException {
        App.setRoot("YourPlanPage");
    }


}