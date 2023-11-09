package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreatePlanController implements Initializable {

    @FXML
    private ListView<Label> searchCardList;

    @FXML
    private TilePane tilePane1;

    @FXML
    private TilePane tilePane2;

    @FXML
    private Button testButton;

    private List<Card> allCards;

    @FXML
    private Button previewButton;

    @FXML
    private void switchToPreview() throws IOException {
        App.setRoot("Preview");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCards = CardDatabase.getAllCards();


        for (int i = 0; i < 7; i++) {
            Button cardHolder = new Button("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(100);
            tilePane1.getChildren().add(cardHolder);
        }
        populateListView(searchCardList, allCards);
    }

    private void populateListView(ListView<Label> listView, List<Card> allCards) {
        for (Card card : allCards) {
            Label cardSample = new Label(String.format("%s - %s", card.getCode(), card.getTitle()));
            cardSample.getStyleClass().add("listView");
            cardSample.getStylesheets().add("src/main/resources/edu/augustana/Style.css");
            cardSample.setPadding(new Insets(0, 0, 0, 10));
            cardSample.setPrefHeight(30);
            cardSample.setPrefWidth(215);
            listView.getItems().add(cardSample);
        }
    }
}
