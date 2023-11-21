package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;

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

    @FXML
    private Button previewButton;

    @FXML
    private ComboBox<String> firstCombo;
    @FXML
    private ComboBox<String> secondCombo;
    @FXML
    private ListView<String> equipmentList;
    @FXML
    private Label addRowButton;



    private List<Card> allCards;

    private List<String> equipment;

    public static List<Card> savedCards;

    private String codeCheck;

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

        for(int i = 0; i < 8; i++){
            Label cardHolder = new Label("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(80);
            cardHolder.getStyleClass().add("eventRow");

            cardHolder.setOnMouseClicked(event -> {

                codeCheck = listView.getSelectionModel().getSelectedItem().getText().substring(0, listView.getSelectionModel().getSelectedItem().getText().indexOf('-'));
                //savedCards.add(CardDatabase.getCardByID(codeCheck)); Having issues implementing 
                System.out.println(savedCards);
                Tooltip img = new Tooltip("");
                if(!cardHolder.getText().equals("+")){
                    removeCurrentEquipment(equipmentList, cardHolder.getText().substring(0, cardHolder.getText().indexOf('-')));
                }
                addCurrentEquipment(equipmentList, codeCheck);
                cardHolder.setText(listView.getSelectionModel().getSelectedItem().getText());
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
            if (!equipmentView.getItems().contains(String.format("%s- %s", code, equipment))){
                equipmentView.getItems().add(String.format("%s- %s", code, equipment));
            }
        }

    }
    private void removeCurrentEquipment(ListView<String> equipmentView, String code){
        int equipmentIndex = 0;
        for(String equipment: equipmentView.getItems()){

            if(equipment.substring(0, equipment.indexOf('-')).equals(code)){

                equipmentView.getItems().remove(equipmentIndex);

            }
            equipmentIndex++;
        }


    }

    public List<Card> getSavedCards(){ return savedCards; }



    @FXML
    private void goBackToStart() {
        try {
            App.setRoot("Start"); // Navigate back to the "CreatePlan" page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

