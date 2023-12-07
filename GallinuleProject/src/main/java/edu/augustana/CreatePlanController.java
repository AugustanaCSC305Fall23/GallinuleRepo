package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreatePlanController implements Initializable {

    @FXML
    public TextField searchTextField;
    @FXML
    private CheckBox textOnlyCheck;

    @FXML
    private ListView<Label> searchCardList;

    @FXML
    private TilePane tilePane1;

    @FXML
    private TilePane tilePane2;

    @FXML
    private TilePane tilePane3;

    @FXML
    private ListView<String> equipmentList;

    @FXML
    private ComboBox<String> eventCombo1;

    @FXML
    private ComboBox<String> eventCombo2;

    @FXML
    private ComboBox<String> eventCombo3;

//    private List<ComboBox<String>> comboBoxList = ArrayList<>();

    @FXML
    private TextField titleBar;

    @FXML
    private ComboBox<Object> filterBox;

    private boolean textOnly;

    private static LessonPlan currentLessonPlan;

    private List<Card> allCards;

    private String equipment;

    private String codeCheck;

    @FXML
    private Button previewButton;


    private static File currentLessonPlanFile = null;


    @FXML
    private void switchToPreview() throws IOException {
        setTextOnly();
        handleTitleChange();


        // Add the current lesson plan to the list
        LessonPlan.getAllLessonPlans().add(currentLessonPlan);
        if(currentLessonPlan.getTextOnly()){
            App.setRoot("PreviewTextOnly");
        } else {
            App.setRoot("Preview");
        }

    }
    //HashMap lists
    private List<String> event1;
    private List<String> event2;
    private List<String> event3;
    //filter objects
    private ComboBox<String> genderFilter = new ComboBox<String>();
    private ComboBox<String> eventFilter = new ComboBox<String>();
    private ComboBox<String> levelFilter = new ComboBox<String>();
    private ComboBox<String> modelFilter = new ComboBox<String>();

    public static File getCurrentLessonPlanFile() {
        return currentLessonPlanFile;
    }

    public static void loadCurrentPlanFromFile(File LessonPlanFile) throws IOException {
        currentLessonPlan = LessonPlan.loadFromFile(LessonPlanFile);
        currentLessonPlanFile = LessonPlanFile;
    }
    public void saveCurrentPlanToFile(File chosenFile) throws IOException {
        currentLessonPlan.saveToFile(chosenFile);
        currentLessonPlanFile = chosenFile;
    }

    private TextSearchFilter textSearchFilter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCards = CardDatabase.getAllCards();
        textSearchFilter = new TextSearchFilter(allCards, "");
        currentLessonPlan = new LessonPlan();
        populateListView(allCards);
        populateEventRows(tilePane1); //remove parameters. unnecessary (note for myself)
        populateEventRows(tilePane2);
        populateEventRows(tilePane3);
        populateEventBox(eventCombo1);
        populateEventBox(eventCombo2);
        populateEventBox(eventCombo3);
        populateFilterBox();

        // Add event handler for Enter key in searchCardList
        searchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSearch();
            }
        });
    }



    public void setTextOnly(){

        currentLessonPlan.setTextOnlyPrint(textOnlyCheck.isSelected());

    }

    private void populateEventBox(ComboBox<String> box) {
//        comboBoxList.add(eventCombo1);
//        comboBoxList.add(eventCombo2);
//        comboBoxList.add(eventCombo3);
        box.getItems().addAll(CardDatabase.getDB().getEventList());
        eventBoxListener(box);
    }

    private void eventBoxListener(ComboBox<String> box){
        box.setOnAction(event -> {

        if(box.equals(eventCombo1)){
            System.out.println("Combo1 changed");
            if(currentLessonPlan.getLessonMap().containsKey(box.getValue())){
                if(currentLessonPlan.getLessonMap().containsKey(box.getValue() + "2")){
                    currentLessonPlan.getLessonMap().put(box.getValue() + "3", new ArrayList<String>());
                } else {
                    currentLessonPlan.getLessonMap().remove(eventCombo1.getValue());
                    currentLessonPlan.getLessonMap().put(box.getValue() +"2", new ArrayList<String>());
                }
            } else {
                System.out.println("properly removed");
                currentLessonPlan.getLessonMap().remove(eventCombo1.getValue());
                currentLessonPlan.getLessonMap().put(box.getValue(), new ArrayList<String>());
            }
        } else if(box.equals(eventCombo2)){
            System.out.println("Combo2 changed");
            if(currentLessonPlan.getLessonMap().containsKey(box.getValue())){
                if(currentLessonPlan.getLessonMap().containsKey(box.getValue() + "2")){
                    currentLessonPlan.getLessonMap().remove(eventCombo2.getValue());
                    currentLessonPlan.getLessonMap().put(box.getValue() + "3", new ArrayList<String>());
                } else {
                    currentLessonPlan.getLessonMap().remove(eventCombo2.getValue());
                    currentLessonPlan.getLessonMap().put(box.getValue() +"2", new ArrayList<String>());
                }
            } else {
                System.out.println("properly removed");
                currentLessonPlan.getLessonMap().remove(eventCombo2.getValue());
                currentLessonPlan.getLessonMap().put(box.getValue(), new ArrayList<String>());
            }
        } else if(box.equals(eventCombo3)){
            System.out.println("Combo3 changed");
            if(currentLessonPlan.getLessonMap().containsKey(box.getValue())){
                if(currentLessonPlan.getLessonMap().containsKey(box.getValue() + "2")){
                    currentLessonPlan.getLessonMap().remove(eventCombo3.getValue());
                    currentLessonPlan.getLessonMap().put(box.getValue() + "3", new ArrayList<String>());
                } else {
                    currentLessonPlan.getLessonMap().remove(eventCombo3.getValue());
                    currentLessonPlan.getLessonMap().put(box.getValue() +"2", new ArrayList<String>());
                }
            } else {
                System.out.println("properly removed");
                currentLessonPlan.getLessonMap().remove(eventCombo3.getValue());
                currentLessonPlan.getLessonMap().put(box.getValue(), new ArrayList<String>());
            }
        }



        });
    }

    @FXML
    private void handleTitleChange() {
        currentLessonPlan.renameLesson(titleBar.getText());
    }

    private void populateFilterBox() {
        CheckBox favorite = new CheckBox();
        Separator line = new Separator();


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


    private void populateEventRows(TilePane pane){

        for (int i = 0; i < 8; i++) {
            Label cardHolder = new Label("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(80);
            cardHolder.getStyleClass().add("eventRow");

            cardHolderListener(cardHolder);

            pane.getChildren().add(cardHolder);

        }
    }



    private void cardHolderListener(Label cardHolder){
        eventCombo1.setValue("ALL");
        eventCombo2.setValue("Floor");
        eventCombo3.setValue("Beam");

        currentLessonPlan.getLessonMap().put("ALL", new ArrayList<String>());
        currentLessonPlan.getLessonMap().put("Floor", new ArrayList<String>());
        currentLessonPlan.getLessonMap().put("Beam", new ArrayList<String>());
        cardHolder.setOnMouseClicked(event -> {
            codeCheck = searchCardList.getSelectionModel().getSelectedItem().getText().substring(0, searchCardList.getSelectionModel().getSelectedItem().getText().indexOf('-'));
            if(cardHolder.getParent() == tilePane1){
                currentLessonPlan.getLessonMap().get(eventCombo1.getValue()).add(codeCheck);
            } else if(cardHolder.getParent() == tilePane2){
                currentLessonPlan.getLessonMap().get(eventCombo2.getValue()).add(codeCheck);
            } else if(cardHolder.getParent() == tilePane3){
                currentLessonPlan.getLessonMap().get(eventCombo3.getValue()).add(codeCheck);
            }

            equipment = CardDatabase.getCardByID(codeCheck).getEquipments().toString();
            currentLessonPlan.saveCard(CardDatabase.getCardByID(codeCheck));
            Tooltip img = new Tooltip("");

            if (CardDatabase.getCardByID(codeCheck).getEquipments().toString().strip().equals("None")) {
                return;
            }

            existingCodeCheckToAdd();
            cardHolder.setText(searchCardList.getSelectionModel().getSelectedItem().getText());
            cardHolder.setWrapText(true);
            cardHolder.setTooltip(img);
            img.setGraphic(searchCardList.getSelectionModel().getSelectedItem().getTooltip().getGraphic());

        });
    }




    private void populateListView(List<Card> cards){


        for (Card card : cards) {
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
            searchCardList.getItems().add(cardSample);
        }

    }

    private void handleSearch() {
        String searchCriteria = searchTextField.getText(); // Get the text from the search field
        textSearchFilter.setSearchCriteria(searchCriteria);

        List<Card> filteredCards = textSearchFilter.search(searchCriteria);

        // Clear and repopulate the listView with the filtered cards
        searchCardList.getItems().clear();
        populateListView(filteredCards);

    }




    private void existingCodeCheckToAdd() {
        if (!equipmentList.getItems().contains(String.format("%s- %s", codeCheck, equipment))) {
            equipmentList.getItems().add(String.format("%s- %s", codeCheck, equipment));
        }
    }

    public static LessonPlan getCurrentLessonPlan() {
        return currentLessonPlan;
    }



//    public List<ComboBox<String>> getEventCombos(){
//        return comboBoxList;
//    }


    //FXML code
    @FXML
    private void goBackToStart() {
        try {
            App.setRoot("Start"); // Navigate back to the "CreatePlan" page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void saveCurrentPlanToFile(ActionEvent actionEvent) throws IOException {
        if (currentLessonPlanFile == null) {
            menuActionSaveAs();
        } else {
            saveCurrentPlanToFile(currentLessonPlanFile);
        }
    }

    private void menuActionSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lesson Plan File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Lesson Plan (*.gymplan)", "*.gymplan");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = previewButton.getScene().getWindow(); // Assuming previewButton is part of your UI
        File chosenFile = fileChooser.showSaveDialog(mainWindow);

        if (chosenFile != null) {
            try {

                currentLessonPlan.saveToFile(chosenFile);

                currentLessonPlanFile = chosenFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

