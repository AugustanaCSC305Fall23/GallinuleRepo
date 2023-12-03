package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Preview implements Initializable {
    public Button backButton;
    @FXML
    private AnchorPane printAnchor;
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
    private FlowPane previewTile1;

    @FXML
    private FlowPane previewTile2;
    @FXML
    private Label titleLabel;

    private PrintGymFile printer = new PrintGymFile(); // Create an instance of the printing class


    private final int cardsPerSection = 4;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        List<Card> finishedCards = CreatePlanController.getCurrentLessonPlan().getSavedCards();
        titleLabel.setText(CreatePlanController.getCurrentLessonPlan().getTitle());
//        previewLabel1.setText(
        populatePreview(finishedCards);

        // Set up the print button action
        printButton.setOnAction(event -> printPlan());

    }

    public void populatePreview(List<Card> finishedCards) {

        for (Card card : finishedCards) {
            CardView cardImg = new CardView(card);
            cardImg.setFitWidth(250);
            cardImg.setFitHeight(205);
            Label eventText = new Label(card.getEquipments().toString());


            VBox previewCardHolder = new VBox();

            previewCardHolder.getChildren().add(cardImg);
            previewCardHolder.getChildren().add(eventText);
            previewCardHolder.setMinHeight(200);
            previewCardHolder.setMinWidth(200);
            previewCardHolder.setAlignment(Pos.CENTER);
            previewCardHolder.getStyleClass().add("placeholder");
            previewTile1.getChildren().add(previewCardHolder);
        }
    }

    @FXML
    private void printPlan() {
        printer.printFile(printAnchor);
    }

    @FXML
    private void switchToPreviewPage() throws IOException {
        System.out.println("Clicked");
        App.setRoot("Start");
    }

}
