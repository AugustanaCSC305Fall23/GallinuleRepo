package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreatePlanController implements Initializable {

    @FXML
    private ListView<Label> searchCardList;

    @FXML
    private TilePane tilePane1;

    @FXML
    private TilePane tilePane2;

    @FXML
    private ListView<String> equipmentList;
    @FXML
    private Button testButton;

    @FXML
    private Button previewButton;

    @FXML
    private ComboBox<String> firstCombo;
    @FXML
    private ComboBox<String> secondCombo;
    @FXML
    private Label addRowButton;

    @FXML
    private TextField titleBar;

    @FXML
    private ComboBox<Object> filterBox;

    private static LessonPlan currentLessonPlan;

    private List<Card> allCards;

    private String equipment;

    private String codeCheck;

    @FXML
    private void switchToPreview() throws IOException {
        handleTitleChange();
        App.setRoot("Preview");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCards = CardDatabase.getAllCards();
        currentLessonPlan = new LessonPlan();
        populateListView(searchCardList, allCards);
        populateEventRows(searchCardList, tilePane1); //remove parameters. unnecessary (note for myself)
        populateEventRows(searchCardList, tilePane2);
        populateEventBox(firstCombo);
        populateEventBox(secondCombo);
        populateFilterBox();
    }

    private void populateEventBox(ComboBox<String> box) {
        box.getItems().addAll(CardDatabase.getDB().getEventList() );

    }

    @FXML
    private void handleTitleChange(){
        currentLessonPlan.renameLesson(titleBar.getText());
    }

    private void populateFilterBox(){
        CheckBox favorite = new CheckBox();
        Separator line = new Separator();
        ComboBox<String> genderFilter = new ComboBox<String>();
        ComboBox<String> eventFilter = new ComboBox<String>();
        ComboBox<String> levelFilter = new ComboBox<String>();
        ComboBox<String> modelFilter = new ComboBox<String>();

        favorite.setText("Only favorites?");
        genderFilter.setPromptText("Gender");
        eventFilter.setPromptText("Event");
        levelFilter.setPromptText("Level");
        modelFilter.setPromptText("Card Model");


        genderFilter.getItems().addAll("ALL", "Male", "Female", "Neutral");
        eventFilter.getItems().addAll(CardDatabase.getDB().getEventList());
        levelFilter.getItems().addAll("ALL", "Beginner", "Advance Beginner", "Intermediate", "Advance");
        modelFilter.getItems().addAll("Male", "Female");

        filterBox.getItems().addAll(favorite, line, genderFilter, eventFilter, levelFilter, modelFilter);
    }

    private void populateEventRows(ListView<Label> listView, TilePane pane){

        for(int i = 0; i < 8; i++){
            Label cardHolder = new Label("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(80);
            cardHolder.getStyleClass().add("eventRow");

            cardHolder.setOnMouseClicked(event -> {

                codeCheck = listView.getSelectionModel().getSelectedItem().getText().substring(0, listView.getSelectionModel().getSelectedItem().getText().indexOf('-'));
                equipment = CardDatabase.getCardByID(codeCheck).getEquipments().toString();
                currentLessonPlan.saveCard(CardDatabase.getCardByID(codeCheck));
                Tooltip img = new Tooltip("");

                if (CardDatabase.getCardByID(codeCheck).getEquipments().toString().strip().equals("None")){
                    return;
                }

                existingCodeCheckToAdd();
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
            ImageView imageView = card.createThumbnailImageView(); // we use thumbnail
            CardView cardView = new CardView(imageView);
            Tooltip img = new Tooltip("");
            Label cardSample = new Label(String.format("%s- %s", card.getCode(), card.getTitle()));
            cardSample.getStyleClass().add("listView");
            cardSample.setPadding(new Insets(0, 0, 0, 10));
            cardSample.setPrefHeight(30);
            cardSample.setPrefWidth(215);
            cardSample.setTooltip(img);
            img.setGraphic(cardView);
            listView.getItems().add(cardSample);
        }

    }



    @FXML
    private void goBackToStart() {
        try {
            App.setRoot("Start"); // Navigate back to the "CreatePlan" page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void existingCodeCheckToAdd(){
        if (!equipmentList.getItems().contains(String.format("%s- %s", codeCheck, equipment))){
            equipmentList.getItems().add(String.format("%s- %s", codeCheck, equipment));
        }
    }

    public static LessonPlan getCurrentLessonPlan(){ return currentLessonPlan; }

}

