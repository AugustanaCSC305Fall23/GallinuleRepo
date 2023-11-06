package edu.augustana;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class ViewAllCard {


    @FXML
    private FlowPane flowPaneCards;


    @FXML
    void initialize() {
        System.out.println("Starting initialize");
        List<Card> allCards = CardDatabase.getAllCards();
        populateFlowPane(flowPaneCards, allCards);
    }


    public void populateFlowPane(FlowPane flowPane, List<Card> cards) {
        double spacingBetweenCards = 10.0; // You can adjust this value as needed

        for (Card card : cards) {
            CardView cardView = new CardView(card);
            cardView.setSpacingBetweenCards(spacingBetweenCards);
            flowPane.getChildren().add(cardView);
        }
    }



}