package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
    private ComboBox<String> firstCombo;
    @FXML
    private ComboBox<String> secondCombo;
    @FXML
    private ListView<String> equipmentList;
    private List<String> equipment;

    @FXML
    private void switchToPreview() throws IOException {
        App.setRoot("Preview");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCards = CardDatabase.getAllCards();

        populateListView(searchCardList, allCards);
        populateEventRows(searchCardList, tilePane1);
        populateEventRows(searchCardList, tilePane2);
        populateEventBox(firstCombo);
        populateEventBox(secondCombo);
    }

    private void populateEventBox(ComboBox<String> box) {
        box.getItems().addAll("Tramp", "Vault", "Beam", "Uneven Bars", "Floor", "Parallel Bars", "Strength", "Horizontal Bars", "Pommel Horse", "Rings");

    }

    private void populateEventRows(ListView<Label> listView, TilePane pane){
        for(int i = 0; i < 7; i++){
            Button cardHolder = new Button("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(100);
            cardHolder.setOnMouseClicked(event -> {
                Tooltip img = new Tooltip("");
                cardHolder.setText(listView.getSelectionModel().getSelectedItem().getText());
                addCurrentEquipment(equipmentList, listView.getSelectionModel().getSelectedItem().getText().substring(0, listView.getSelectionModel().getSelectedItem().getText().indexOf('-')));
                cardHolder.setWrapText(true);
                cardHolder.setTooltip(img);
                img.setGraphic(listView.getSelectionModel().getSelectedItem().getTooltip().getGraphic());

            });
            pane.getChildren().add(cardHolder);


        }
    }

    private void populateListView(ListView<Label> listView, List<Card> allCards){
        for (Card card : allCards){
            Tooltip img = new Tooltip("");
            Label cardSample = new Label(String.format("%s- %s", card.getCode(), card.getTitle()));
            cardSample.getStyleClass().add("listView");
            cardSample.setPadding(new Insets(0, 0, 0, 10));
            cardSample.setPrefHeight(30);
            cardSample.setPrefWidth(215);
            cardSample.setTooltip(img);
            img.setGraphic(new CardView(card));
            listView.getItems().add(cardSample);
        }
    }

    private void addCurrentEquipment(ListView<String> equipmentView, String code){
        List<String> equipments = CardDatabase.getCardByID(code).getEquipments();
        for(String equipment: equipments){
            if(equipment.strip().equals("None")){
                return;
            }
            if (!equipmentView.getItems().contains(equipment)){
                equipmentView.getItems().addAll(equipment);
            }
        }
    }

}
