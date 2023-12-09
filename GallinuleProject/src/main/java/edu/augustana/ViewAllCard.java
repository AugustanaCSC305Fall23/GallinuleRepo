package edu.augustana;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    @FXML
    private CheckBox favcheckBox;


    private List<Card> allCards;

    private TextSearchFilter cardSearch;

    private Stage stage;

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
        favcheckBox.setSelected(false);
        CardFilter titleFilter = new TextSearchFilter(allCards, searchTextField.getText().trim().toLowerCase());
        CardFilter genderFilter1 = new GenderFilter(genderFilterCB.getValue());
        CardFilter modelSexFilter = new ModelSexFilter(modelFilterCB.getValue());
        CardFilter levelFilter1 = new LevelFilter(levelFilterCB.getValue());
        CardFilter eventFilter1 = new EventFilter(eventFilter.getValue());
        CardFilter combinedAndFilter = new CombinedAndFilter( genderFilter1, modelSexFilter, levelFilter1, eventFilter1, titleFilter );
        List<Card> filteredCards = combinedAndFilter.filter(allCards);
        Platform.runLater(() -> populateFlowPane(filteredCards));
    }

    @FXML
    void showFavCards() {
        if (favcheckBox.isSelected()) {
            List<Card> favCards = App.getFavCards();
            Platform.runLater(() -> populateFlowPane(favCards));
        }else{
            Platform.runLater(()-> populateFlowPane(allCards));
        }
    }



    private void populateFlowPane(List<Card> cards) {

        double spacingBetweenCards = 10.0;

        flowPaneCards.getChildren().clear();

        for (Card card : cards) {
            ImageView imageView = card.createThumbnailImageView();
            CardView cardView = new CardView(imageView);
            cardView.setSpacingBetweenCards(spacingBetweenCards);

            cardView.setOnMouseClicked(event -> {
                try {
                    showSingleCardPopup(card);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            flowPaneCards.getChildren().add(cardView);
        }
    }

    private void showSingleCardPopup(Card card) throws IOException {
        //Popup popup = new Popup();
        SingleCardView singleCardView = new SingleCardView(card);

        // Use singleCardBorderPane in your Scene or wherever you need it
        Scene scene = new Scene(singleCardView.getRootBorderPane());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();

        stage.show();

//        popup.getContent().add(singleCardView);
//        popup.show(stage);
    }



}
