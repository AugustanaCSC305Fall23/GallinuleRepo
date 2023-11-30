package edu.augustana.ui;

import edu.augustana.data.Card;
import edu.augustana.data.CardDatabase;
import edu.augustana.data.CardView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PreviewController implements Initializable {

    @FXML
    private VBox previewVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        List<Card> allCards = CardDatabase.getAllCards();
        populatePreview(allCards);
    }

    private void populatePreview(List<Card> allCards) {
        for (Card card : allCards) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(String.format("%s - %s", card.getCode(), card.getTitle()));

            HBox planHBox = new HBox();
            planHBox.setSpacing(20);

            // First section: Title event
            VBox titleEventVBox = new VBox();
            titleEventVBox.getChildren().add(new Label("Title Event: " + card.getEvent()));
            planHBox.getChildren().add(titleEventVBox);

            // Second section: Images of the cards
            VBox imagesVBox = new VBox();
            imagesVBox.getChildren().add(new Label("Card Images: "));

            // Use a method similar to populateFlowPane to add card images
            populateCardImages(imagesVBox, card);
            planHBox.getChildren().add(imagesVBox);

            // Third section: List of equipment needed
            VBox equipmentVBox = new VBox();
            equipmentVBox.getChildren().add(new Label("Equipment Needed: "));

            // Customize this section based on your requirements
            populateEquipmentList(equipmentVBox, card);

            planHBox.getChildren().add(equipmentVBox);

            titledPane.setContent(planHBox);
            titledPane.setExpanded(false);  // Collapsed by default

            // Add each TitledPane to the main VBox
            previewVBox.getChildren().add(titledPane);
        }
    }


    // Method to populate the second section with card images
    private void populateCardImages(VBox imagesVBox, Card card) {
        double spacingBetweenCards = 10.0;

        CardView cardView = new CardView(card);
        cardView.setSpacingBetweenCards(spacingBetweenCards);

        imagesVBox.getChildren().add(cardView);
    }

    private void populateEquipmentList(VBox equipmentVBox, Card card) {
        // Customize this based on how you want to display the equipment list
        for (String equipment : card.getEquipments()) {
            Label equipmentLabel = new Label(equipment);
            equipmentVBox.getChildren().add(equipmentLabel);
        }
    }

}
