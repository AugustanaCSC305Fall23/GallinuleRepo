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
    @FXML
    private ComboBox<String> genderFilter;
    @FXML
    private ComboBox<String> eventFilter;
    @FXML
    private ComboBox<String> levelFilter;
    @FXML
    private ComboBox<String> modelFilter;

    private List<Card> allCards;
    private TextSearchFilter cardSearch;

    @FXML
    void initialize() {
        initializeComboBoxes();
        loadAllCards();
    }

    private void initializeComboBoxes() {
        genderFilter.getItems().addAll("Male", "Female", "Neutral");
        eventFilter.getItems().addAll("Shapes", "Vault", "Beam", "Uneven Bars", "Floor", "Parallel Bars", "Horizontal Bars", "Pommel Horse", "Rings");
        levelFilter.getItems().addAll("ALL", "B", "AB", "I", "A");
        modelFilter.getItems().addAll("Male", "Female");
    }

    private void loadAllCards() {
        allCards = CardDatabase.getAllCards();
        cardSearch = new TextSearchFilter(allCards);
        populateFlowPane(allCards);
    }

    @FXML
    void handleSearch() {
        String searchCriteria = searchTextField.getText().trim().toLowerCase();

        if (searchCriteria.isEmpty()) {
            populateFlowPane(allCards); // Show all cards if the search field is empty
        } else {
            List<Card> searchResults = cardSearch.search(searchCriteria);
            populateFlowPane(searchResults);
        }
    }

    @FXML
    void handleEventFilter() {
        String selectedEvent = eventFilter.getValue(); // Get the selected event from the ComboBox

        if (selectedEvent != null && !selectedEvent.isEmpty()) {
            // Apply the event filter
            CardFilter eventFilter = new EventFilter(selectedEvent);
            List<Card> filteredCards = eventFilter.filter(allCards);
            populateFlowPane(filteredCards);
        } else {
            // If no event is selected, show all cards
            populateFlowPane(allCards);
        }
    }

    @FXML
    void handleLevelFilter() {
        String selectedLevel = levelFilter.getValue(); // Get the selected level from the ComboBox

        if (selectedLevel != null && !selectedLevel.isEmpty() && !selectedLevel.equals("ALL")) {
            // Apply the level filter
            CardFilter levelFilter = new LevelFilter(selectedLevel);
            List<Card> filteredCards = levelFilter.filter(allCards);
            populateFlowPane(filteredCards);
        } else {
            // If "ALL" or no level is selected, show all cards
            populateFlowPane(allCards);
        }
    }



    private void populateFlowPane(List<Card> cards) {
        double spacingBetweenCards = 10.0;

        flowPaneCards.getChildren().clear(); // Clear existing cards before adding new ones

        for (Card card : cards) {
            CardView cardView = new CardView(card);
            cardView.setSpacingBetweenCards(spacingBetweenCards);
            flowPaneCards.getChildren().add(cardView);
        }
    }
}
