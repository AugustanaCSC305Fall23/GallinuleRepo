package edu.augustana;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

    @FXML
    private void initializeComboBoxes() {
        genderFilter.getItems().add("ALL");
        genderFilter.getItems().addAll("Male", "Female", "Neutral");
        eventFilter.getItems().addAll(CardDatabase.getDB().getEventList());
        levelFilter.getItems().addAll(LevelFilter.getFullLevelNames());
        modelFilter.getItems().addAll("ALL", "Male", "Female");
        genderFilter.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        eventFilter.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        searchTextField.textProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        levelFilter.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        modelFilter.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

    }

    private void loadAllCards() {
        allCards = CardDatabase.getAllCards();
        cardSearch = new TextSearchFilter(allCards, searchTextField.getText().trim().toLowerCase());
        populateFlowPane(allCards);
    }


    void updateFilteredVisibleCards() {
        CardFilter titleFilter = new TextSearchFilter(allCards, searchTextField.getText().trim().toLowerCase());
        CardFilter genderFilter1 = new GenderFilter(genderFilter.getValue());
        CardFilter modelSexFilter = new ModelSexFilter(modelFilter.getValue());
        CardFilter levelFilter1 = new LevelFilter(levelFilter.getValue());
        CardFilter eventFilter1 = new EventFilter(eventFilter.getValue());
        CardFilter combinedAndFilter = new CombinedAndFilter( genderFilter1, modelSexFilter, levelFilter1, eventFilter1, titleFilter );
        List<Card> filteredCards = combinedAndFilter.filter(allCards);
        System.out.println("updating, found filtered cards: " + filteredCards.size() );
        Platform.runLater(() -> populateFlowPane(filteredCards));
    }



    private void populateFlowPane(List<Card> cards) {
        double spacingBetweenCards = 10.0;

        flowPaneCards.getChildren().clear();

        for (Card card : cards) {
            ImageView imageView = card.createThumbnailImageView(); // or card.createThumbnailImageView() based on your requirement
            CardView cardView = new CardView(imageView);
            cardView.setSpacingBetweenCards(spacingBetweenCards);
            flowPaneCards.getChildren().add(cardView);
        }
    }

}
