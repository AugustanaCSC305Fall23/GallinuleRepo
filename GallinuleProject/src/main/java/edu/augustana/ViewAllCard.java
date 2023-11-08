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

    private TextSearchFilter cardSearch;

    @FXML
    private ComboBox genderFilter;

    @FXML
    private ComboBox eventFilter;
    @FXML
    private ComboBox levelFilter;
    @FXML
    private ComboBox modelFilter;

    @FXML
    void initialize() {
        System.out.println("Initializing");
        allCards = CardDatabase.getAllCards();
        cardSearch = new TextSearchFilter(allCards);
        populateFlowPane(flowPaneCards, allCards);
        this.genderFilter.getItems().addAll(new String[]{"Male", "Female", "Neutral"});
        this.eventFilter.getItems().addAll(new String[]{"Vault", "Beam", "Uneven Bars", "Floor", "Parallel Bars", "Horizontal Bars", "Pommel Horse", "Rings"});
        this.levelFilter.getItems().addAll(new String[]{"ALL", "B", "AB", "I", "A"});
        this.modelFilter.getItems().addAll(new String[]{"Male", "Female"});



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
