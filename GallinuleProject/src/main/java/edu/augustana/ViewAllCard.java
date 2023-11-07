package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewAllCard {
    @FXML
    private FlowPane flowPaneCards;

    @FXML
    private TextField searchTextField;

    private List<Card> allCards;

    private CardSearch cardSearch;

    @FXML
    void initialize() {
        allCards = CardDatabase.getAllCards();
        cardSearch = new CardSearch(allCards);
        populateFlowPane(flowPaneCards, allCards);
    }

    @FXML
    void handleSearch() {
        String searchCriteria = searchTextField.getText().trim().toLowerCase();

        if (searchCriteria.isEmpty()) {
            populateFlowPane(flowPaneCards, allCards); // Show all cards if the search field is empty
        } else {
            List<Card> searchResults = cardSearch.search(searchCriteria);
            populateFlowPane(flowPaneCards, searchResults);
        }
    }

    public void populateFlowPane(FlowPane flowPane, List<Card> cards) {
        double spacingBetweenCards = 10.0; // You can adjust this value as needed

        flowPane.getChildren().clear(); // Clear existing cards before adding new ones

        for (Card card : cards) {
            CardView cardView = new CardView(card);
            cardView.setSpacingBetweenCards(spacingBetweenCards);
            flowPane.getChildren().add(cardView);
        }
    }
}
