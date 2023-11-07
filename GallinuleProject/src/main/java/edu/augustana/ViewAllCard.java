package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class ViewAllCard {
    @FXML
    private FlowPane flowPaneCards;

    @FXML
    private TextField searchTextField;

    private List<Card> allCards;

    private CardSearch cardSearch;

    @FXML
    private ComboBox genderFilter;

    @FXML
    void initialize() {
        System.out.println("Initializing");
        allCards = CardDatabase.getAllCards();
        cardSearch = new CardSearch(allCards);
        populateFlowPane(flowPaneCards, allCards);
        this.genderFilter.getItems().addAll(new String[]{"Male", "Female", "Neutral"});
        


    }

    @FXML
    void filterDropdown(){

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
        double spacingBetweenCards = 10.0;

        flowPane.getChildren().clear(); // Clear existing cards before adding new ones

        for (Card card : cards) {
            CardView cardView = new CardView(card);
            cardView.setSpacingBetweenCards(spacingBetweenCards);
            flowPane.getChildren().add(cardView);
        }
    }
}
