package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Controller for the "Preview" view, responsible for displaying a preview of lesson plans and their details.
 */
public class Preview implements Initializable {
    public Button backButton;

    @FXML
    private VBox motherVBox;

    private LessonPlan lessonPlan;

    @FXML
    private FlowPane flowPaneCards;

    @FXML
    private Label previewLabel1;

    @FXML
    private Label previewLabel2;

    @FXML
    private Button printButton;

    @FXML
    private Label eventLabel1;

    @FXML
    private Label eventLabel2;

    @FXML
    private Label eventLabel3;

    @FXML
    private Label titleLabel;
    @FXML
    private Label titleLabel2;

    @FXML
    private Label titleLabel3;

    private List<VBox> printList = new ArrayList<>();

    private PrintGymFile printer = new PrintGymFile(); // Create an instance of the printing class

    private final int cardsPerSection = 4;

    private LessonPlan currentLessonPlan;

    public Preview() {

    }

    /**
     * Initializes the PreviewController with necessary data and actions.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        HashMap<String, List<String>> finishedCards = CreatePlanController.getCurrentLessonPlan().getLessonMap();
        populatePreview(finishedCards);
        // Set up the print button action
        printButton.setOnAction(event -> printPlan());

    }


    /**
     * Populates the preview with information about each lesson plan.
     *
     * @param finishedCards A map containing finished lesson plans and their associated cards.
     */
    public void populatePreview(HashMap<String, List<String>> finishedCards) {
        motherVBox.setAlignment(Pos.TOP_CENTER);
        List<VBox> populateList = new ArrayList<>();
        finishedCards.forEach((key, value) -> {
            if (finishedCards.get(key).isEmpty()) {
                return; //only creates pages for filled rows
            }
            //each page
            VBox tempVBox = new VBox();
            tempVBox.setAlignment(Pos.TOP_CENTER);

            //each title
            Text tempTitle = new Text(CreatePlanController.getCurrentLessonPlan().getTitle());
            tempTitle.setFont(Font.font(36));
            tempTitle.setText(CreatePlanController.getCurrentLessonPlan().getTitle());
            tempVBox.getChildren().add(tempTitle);

            //each event
            Text tempEvent = new Text(key.substring(0, key.indexOf("-")));
            tempEvent.setFont(Font.font(25));
            tempVBox.getChildren().add(tempEvent);

            //place to display the cards
            FlowPane cardShelf = new FlowPane();
            tempVBox.getChildren().add(cardShelf);
            for (String code : value) {
                Card card = CardDatabase.getCardByID(code);

                if (card != null) {
                    VBox previewCardHolder = new VBox();

                    ImageView imageView = card.createHighResolutionImageView();
                    CardView cardImg = new CardView(imageView);
                    cardImg.setFitWidth(250);
                    cardImg.setFitHeight(205);

                    Label equipmentText = new Label(card.getEquipments().toString());

                    previewCardHolder.getChildren().add(cardImg);
                    previewCardHolder.getChildren().add(equipmentText);
                    previewCardHolder.setMinHeight(200);
                    previewCardHolder.setMinWidth(200);
                    previewCardHolder.setAlignment(Pos.CENTER);
                    previewCardHolder.getStyleClass().add("placeholder");
                    cardShelf.getChildren().add(previewCardHolder);
                } else {
                    System.out.println("Card with code " + code + " not found in the database.");
                }
            }

            populateList.add(tempVBox);
        });
        motherVBox.getChildren().addAll(populateList);
    }

    /**
     * Prints the lesson plan.
     */
    @FXML
    private void printPlan() {
        printList = new ArrayList<>();
        for (Node page : motherVBox.getChildren()) {
            if (page instanceof VBox) {
                printList.add((VBox) page);
            }
        }
        printer.printFile(printList);
        new Alert(Alert.AlertType.INFORMATION, "Your Plan has been sent to print successfully!").show();

    }

    @FXML
    private void switchToPreviewPage() throws IOException {
        System.out.println("Clicked");
        App.setRoot("Start");
    }

}