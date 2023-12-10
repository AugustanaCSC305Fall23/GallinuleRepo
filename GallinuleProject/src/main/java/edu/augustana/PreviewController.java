package edu.augustana;

import edu.augustana.Card;
import edu.augustana.CardDatabase;
import edu.augustana.CardView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the "Preview" view, responsible for displaying a preview of cards and their details.
 */
public class PreviewController implements Initializable {

    @FXML
    private VBox previewVBox;

    /**
     * Initializes the PreviewController with necessary data and actions.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        List<Card> allCards = CardDatabase.getAllCards();
        populatePreview(allCards);
    }

    /**
     * Populates the preview VBox with TitledPane elements, each representing a card with its details.
     *
     * @param allCards The list of all cards to be displayed in the preview.
     */
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
    
    /**
     * Populates the specified VBox with images of the card.
     *
     * @param imagesVBox The VBox to be populated with card images.
     * @param card       The card for which images are to be displayed.
     */
    private void populateCardImages(VBox imagesVBox, Card card) {
        double spacingBetweenCards = 10.0;

        ImageView imageView = card.createThumbnailImageView(); // use thumbnail
        CardView cardView = new CardView(imageView);
        cardView.setSpacingBetweenCards(spacingBetweenCards);

        imagesVBox.getChildren().add(cardView);
    }

    /**
     * Populates the specified VBox with equipment details from the card.
     *
     * @param equipmentVBox The VBox to be populated with equipment details.
     * @param card          The card for which equipment details are to be displayed.
     */
    private void populateEquipmentList(VBox equipmentVBox, Card card) {
        // Customize this based on how you want to display the equipment list
        for (String equipment : card.getEquipments()) {
            Label equipmentLabel = new Label(equipment);
            equipmentVBox.getChildren().add(equipmentLabel);
        }
    }

}
