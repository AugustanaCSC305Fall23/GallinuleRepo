package edu.augustana;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Preview implements Initializable {

    @FXML
    private FlowPane flowPaneCards;

    @FXML
    private Button printButton;

    private PrintGymFile printer = new PrintGymFile(); // Create an instance of the printing class

    private final int cardsPerSection = 4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        List<Card> allCards = CardDatabase.getAllCards();
        populatePreview(allCards);

        // Set up the print button action
        printButton.setOnAction(event -> printPlan());
    }

    private void populatePreview(List<Card> allCards) {
        int cardCount = 0;
        for (Card card : allCards) {
            // Create a CardView for each card
            CardView cardView = new CardView(card);

            // Set spacing between card views
            cardView.setSpacingBetweenCards(10.0);

            // Add the CardView to the FlowPane
            flowPaneCards.getChildren().add(cardView);

            cardCount++;
        }
    }

    @FXML
    private void printPlan() {
        printer.printFile(flowPaneCards);
    }

    @FXML
    private void goBackToCreatePlan() {
        try {
            App.setRoot("CreatePlan"); // Navigate back to the "CreatePlan" page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
