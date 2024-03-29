package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


/**
 * Controller class for the Create Plan view.
 */
public class CreatePlanController implements Initializable {
    @FXML
    public TextField searchTextField;
    @FXML
    private CheckBox textOnlyCheck;
    @FXML
    private VBox motherVBox;
    @FXML
    private ListView<Label> searchCardList;

    @FXML
    private ListView<String> equipmentList;

    @FXML
    private TextField titleBar;

    @FXML
    private ComboBox<Object> filterBox;

    public static LessonPlan currentLessonPlan;

    private List<Card> allCards;

    private String equipment;

    private String codeCheck;

    @FXML
    private Button previewButton;

    @FXML
    private Button helpBtn;

    private ViewAllCard viewAllCard;

    int rowCount = 1;

    String beforeEventChange;

    private static File currentLessonPlanFile = null;

    private LessonPlan editingLessonPlan;

    @FXML
    void switchToPreview() throws IOException {

        
        setTextOnly();
        currentLessonPlan.renameLesson(titleBar.getText());

        // Add the current lesson plan to the list
        LessonPlan.getAllLessonPlans().add(currentLessonPlan);
        if (currentLessonPlan.getTextOnly()) {
            loadTxtPlanPreview();
        } else {
            loadImgPlanPreview();
        }


    }

    @FXML
    void switchToStart() throws IOException {
        App.setRoot("Start");

    }

    @FXML
    void loadImgPlanPreview() throws IOException {
        App.setRoot("Preview");
    }
    //filter objects

    @FXML
    void loadTxtPlanPreview() throws IOException {
        App.setRoot("PreviewTextOnly");
    }
    private final CheckBox favoriteCheckBox = new CheckBox();
    private final ComboBox<String> genderFilter = new ComboBox<String>();
    private final ComboBox<String> eventFilter = new ComboBox<String>();
    private final ComboBox<String> levelFilter = new ComboBox<String>();
    private final ComboBox<String> modelFilter = new ComboBox<String>();

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

    /**
     * Initializes the Create Plan view.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources specific to the locale.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCards = CardDatabase.getAllCards();
        textSearchFilter = new TextSearchFilter(allCards, "");
        currentLessonPlan = new LessonPlan();
        editingLessonPlan = LessonPlanController.getSelectedLessonPlanTransfer();
        System.out.println(LessonPlanController.getSelectedLessonPlanTransfer() + " - New Check");
        populateListView(allCards);
        motherVBox.setAlignment(Pos.TOP_LEFT);
        System.out.println(editingLessonPlan + "- CreatePlanController");
        if(editingLessonPlan != null){
            System.out.println("EDITING - CreatePlanController");
            HashMap<String, List<String>> lessonMap = editingLessonPlan.getLessonMap();
            System.out.println(lessonMap);
            lessonMap.forEach((key, code) -> {

                createEditingBox(lessonMap, key);

            });

        } else {
            createEventBox();
        }
        populateFilterBox();

        Tooltip tooltip = new Tooltip("This the Create Plan Page." + "\n" + "Double click on " + "'Untitled' Title bar to change the Title. " + "\n" + "Click on the cards you want in your plan and the cards stock you want to place it in" + "\n");
        helpBtn.setTooltip(tooltip);

        titleBar.textProperty().addListener((obs, oldV, newV) -> currentLessonPlan.setTitle(newV));

        // Add event handler for Enter key in searchCardList
        searchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSearch();
            }
        });


        genderFilter.setOnAction(event -> {
            applyGenderFilter(genderFilter.getValue());
        });

        eventFilter.setOnAction(event -> {
            applyEventFilter(eventFilter.getValue());
        });

        levelFilter.setOnAction(event -> {
            applyLevelFilter(levelFilter.getValue());
        });

        modelFilter.setOnAction(event -> {
            applyModelSexFilter(modelFilter.getValue());
        });

        favoriteCheckBox.setOnAction(event -> {
            showFavCards();
        });

    }
    private void createEditingBox(Map<String, List<String>> map, String key){
        VBox tempVBox = new VBox();
        System.out.println("got into the function");
        tempVBox.setAlignment(Pos.BOTTOM_LEFT);
        tempVBox.setPrefHeight(150);
        //event selector
        ComboBox<String> tempCombo = new ComboBox<String>();
        tempCombo.setPrefWidth(220);
        tempCombo.setPrefHeight(40);
        tempCombo.getStyleClass().add("comboBox");
        tempCombo.setValue(key);
        populateEventBox(tempCombo);
        TilePane tempTile = new TilePane();
        tempTile.setPrefWidth(645);
        tempTile.setPrefHeight(100);
        tempTile.getStyleClass().add("cardHolder");
        populateEventRowsEditing(tempTile, tempCombo, map.get(key));

        tempVBox.getChildren().add(tempCombo);
        tempVBox.getChildren().add(tempTile);
        motherVBox.getChildren().add(tempVBox);

    }

    private void createEventBox() {
        VBox tempVBox = new VBox();
        tempVBox.setAlignment(Pos.BOTTOM_LEFT);
        tempVBox.setPrefHeight(150);
        //event selector
        ComboBox<String> tempCombo = new ComboBox<String>();
        tempCombo.setPrefWidth(220);
        tempCombo.setPrefHeight(40);
        tempCombo.getStyleClass().add("comboBox");
        tempCombo.setValue("ALL-" + rowCount);
        currentLessonPlan.getLessonMap().put("ALL-" + rowCount, new ArrayList<String>());
        populateEventBox(tempCombo);
        //event row
        TilePane tempTile = new TilePane();
        tempTile.setPrefWidth(645);
        tempTile.setPrefHeight(100);
        tempTile.getStyleClass().add("cardHolder");
        populateEventRows(tempTile, tempCombo);

        tempVBox.getChildren().add(tempCombo);
        tempVBox.getChildren().add(tempTile);
        motherVBox.getChildren().add(tempVBox);
    }

    @FXML
    public void addNewRow() {
        rowCount++;
        createEventBox();
    }

    public void setEditingPlan(LessonPlan enteredLessonPlan){
        System.out.println(editingLessonPlan + "setEditingPlan");
        editingLessonPlan = enteredLessonPlan;
    }

    public void setTextOnly(){

        currentLessonPlan.setTextOnlyPrint(textOnlyCheck.isSelected());

    }

    private void populateEventBox(ComboBox<String> box) {
        List<String> eventList = CardDatabase.getDB().getEventList();
        for (String event : eventList) {
            event = String.format("%s-%d", event, rowCount);
            box.getItems().add(event);
        }
        box.setOnMouseClicked(event -> {
            beforeEventChange = box.getValue();
        });

        box.setOnAction(event -> {
            currentLessonPlan.getLessonMap().put(box.getValue(), currentLessonPlan.getLessonMap().get(beforeEventChange));
            currentLessonPlan.getLessonMap().remove(beforeEventChange);
        });

    }


    private void populateFilterBox() {

        favoriteCheckBox.setText("Only favorites?");
        genderFilter.setPromptText("Gender");
        eventFilter.setPromptText("Event");
        levelFilter.setPromptText("Level");
        modelFilter.setPromptText("Card Model");


        genderFilter.getItems().addAll("ALL", "Male", "Female", "Neutral");
        eventFilter.getItems().addAll(CardDatabase.getDB().getEventList());
        levelFilter.getItems().addAll("ALL", "Beginner", "Advance Beginner", "Intermediate", "Advance");
        modelFilter.getItems().addAll("Male", "Female");

        filterBox.getItems().addAll(favoriteCheckBox, genderFilter, eventFilter, levelFilter, modelFilter);
    }

    private void populateEventRowsEditing(TilePane pane, ComboBox<String> eventCombo, List<String> loadedCards){
        Button removeButton = new Button("remove");
        removeButton.getStyleClass().add("eventRow");
        for (int i = 0; i < 8; i++) {
            VBox removeHolder = new VBox();
            removeHolder.setAlignment(Pos.TOP_CENTER);
            Label cardHolder = new Label("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(80);
            cardHolder.getStyleClass().add("eventRow");


            removeHolder.getChildren().add(cardHolder);
            if(loadedCards.size() > i){
                cardHolder.setText(loadedCards.get(i));
                cardHolder.setDisable(true);
            }
            cardHolderListener(cardHolder, eventCombo, removeHolder, editingLessonPlan);
            pane.getChildren().add(removeHolder);

        }
    }
    private void populateEventRows(TilePane pane, ComboBox<String> eventCombo){

        for (int i = 0; i < 8; i++) {
            VBox removeHolder = new VBox();
            removeHolder.setAlignment(Pos.TOP_CENTER);
            Label cardHolder = new Label("+");
            cardHolder.setPrefHeight(100);
            cardHolder.setPrefWidth(80);
            cardHolder.getStyleClass().add("eventRow");
            removeHolder.getChildren().add(cardHolder);

            cardHolderListener(cardHolder, eventCombo, removeHolder, currentLessonPlan);

            pane.getChildren().add(removeHolder);

        }
    }


    private void cardHolderListener(Label cardHolder, ComboBox<String> eventBox, VBox removeHolder, LessonPlan currentLessonPlan) {
        cardHolder.setOnMouseClicked(event -> {
            Label selectedLabel = searchCardList.getSelectionModel().getSelectedItem();
            if (selectedLabel != null) {
                codeCheck = searchCardList.getSelectionModel().getSelectedItem().getText().substring(0, searchCardList.getSelectionModel().getSelectedItem().getText().indexOf('-'));
            }

            currentLessonPlan.getLessonMap().get(eventBox.getValue()).add(codeCheck);
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
            double sceneX = cardHolder.getLayoutX();
            double sceneY = cardHolder.getLayoutY();



            Button removeButton = new Button("remove");
            removeButton.getStyleClass().add("eventRow");
            cardHolder.setDisable(true);
            removeHolder.getChildren().add(removeButton);
            removeButton.setOnMouseClicked(event2 -> {
                cardHolder.setDisable(false);
                removeHolder.getChildren().remove(removeButton);
                currentLessonPlan.getLessonMap().get(eventBox.getValue()).remove(cardHolder.getText().substring(0, cardHolder.getText().indexOf("-")));
                cardHolder.setText("+");
                equipmentList.getItems().remove(codeCheck + "- " + CardDatabase.getCardByID(codeCheck).getEquipments());
            });

        });
    }


    private void populateListView(List<Card> cards) {
        searchCardList.getItems().clear();

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

    public void saveCurrentPlanToFile(ActionEvent actionEvent) throws IOException {

        String newTitle = currentLessonPlan.getTitle();


        if (App.getCurrentOpenCourse().getAllPlanTitles().contains(newTitle)) {

            int count = 1;
            while (App.getCurrentOpenCourse().getAllPlanTitles().contains(newTitle + "(" + count + ")")) {
                count++;
            }
            newTitle = newTitle + "(" + count + ")";
            currentLessonPlan.setTitle(newTitle);
        }
        new Alert(Alert.AlertType.INFORMATION, "Your Lesson plan has been saved!").show();

        App.getCurrentOpenCourse().getLessons().add(currentLessonPlan);

    }

    private void menuActionSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lesson Plan File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Lesson Plan (*.gymplan)", "*.gymplan");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = previewButton.getScene().getWindow();
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


    private void applyCardFilter(CardFilter filter) {
        List<Card> filteredCards = filter.filter(allCards);
        // Clear and repopulate the listView with the filtered cards
        searchCardList.getItems().clear();
        populateListView(filteredCards);
    }


    private void applyGenderFilter(String selectedGender) {
        if (selectedGender != null) {
            GenderFilter genderFilter = new GenderFilter(selectedGender);
            applyCardFilter(genderFilter);
        }

    }

    private void applyEventFilter(String selectedEvent) {
        if (selectedEvent != null) {
            EventFilter eventFilter = new EventFilter(selectedEvent);
            applyCardFilter(eventFilter);
        }
    }

    private void applyLevelFilter(String selectedLevel) {
        if (selectedLevel != null) {
            LevelFilter levelFilter = new LevelFilter(selectedLevel);
            applyCardFilter(levelFilter);
        }
    }

    private void applyModelSexFilter(String selectedModelSex) {
        if (selectedModelSex != null) {
            ModelSexFilter modelSexFilter = new ModelSexFilter(selectedModelSex);
            applyCardFilter(modelSexFilter);
        }
    }

    private void showFavCards() {
        if (favoriteCheckBox.isSelected()) {
            List<Card> favCards = App.getFavCards();
            populateListView(favCards);
        } else {
            // If the checkbox is not selected, show all cards
            populateListView(allCards);
        }
    }

    public static void setCurrentLessonPlan(LessonPlan editingLessonPlan){
        currentLessonPlan = editingLessonPlan;
    }

}
