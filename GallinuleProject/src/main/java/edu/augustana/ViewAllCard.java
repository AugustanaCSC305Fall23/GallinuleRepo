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
    private ComboBox<String> genderFilterCB;
    @FXML
    private ComboBox<String> eventFilter;
    @FXML
    private ComboBox<String> levelFilterCB;
    @FXML
    private ComboBox<String> modelFilterCB;

    private List<Card> allCards;

    private TextSearchFilter cardSearch;


    @FXML
    void initialize() {
        initializeComboBoxes();
        loadAllCards();
    }

    @FXML
    private void initializeComboBoxes() {
        genderFilterCB.getItems().add("ALL");
        genderFilterCB.getItems().addAll("Male", "Female", "Neutral");
        eventFilter.getItems().addAll(CardDatabase.getDB().getEventList());
        levelFilterCB.getItems().addAll(LevelFilter.getFullLevelNames());
        modelFilterCB.getItems().addAll("ALL", "Male", "Female");
        genderFilterCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        eventFilter.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        searchTextField.textProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        levelFilterCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        modelFilterCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

    }

    private void loadAllCards() {
        allCards = CardDatabase.getAllCards();
        //cardSearch = new TextSearchFilter(allCards, searchTextField.getText().trim().toLowerCase());
        populateFlowPane(allCards);
    }


    void updateFilteredVisibleCards() {
        CardFilter titleFilter = new TextSearchFilter(allCards, searchTextField.getText().trim().toLowerCase());
        CardFilter genderFilter1 = new GenderFilter(genderFilterCB.getValue());
        CardFilter modelSexFilter = new ModelSexFilter(modelFilterCB.getValue());
        CardFilter levelFilter1 = new LevelFilter(levelFilterCB.getValue());
        CardFilter eventFilter1 = new EventFilter(eventFilter.getValue());
        CardFilter combinedAndFilter = new CombinedAndFilter( genderFilter1, modelSexFilter, levelFilter1, eventFilter1, titleFilter );
        List<Card> filteredCards = combinedAndFilter.filter(allCards);
        Platform.runLater(() -> populateFlowPane(filteredCards));
    }



    private void populateFlowPane(List<Card> cards) {
        double spacingBetweenCards = 10.0;

        flowPaneCards.getChildren().clear();

        for (Card card : cards) {
            ImageView imageView = card.createThumbnailImageView();
            CardView cardView = new CardView(imageView);
            cardView.setSpacingBetweenCards(spacingBetweenCards);

            flowPaneCards.getChildren().add(cardView);
        }
    }

}
