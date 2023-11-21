package edu.augustana;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Preview implements Initializable {
    private LessonPlan lessonPlan;
    @FXML
    private FlowPane flowPaneCards;

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
        populatePreview(finishedCards);

        // Set up the print button action
        printButton.setOnAction(event -> printPlan());

    }

        public void populatePreview(List<Card> finishedCards){

        for(Card card: finishedCards){
            CardView cardImg = new CardView(card);
            cardImg.setPrefWidth(60);
            cardImg.setPrefHeight(40);

            Label previewCardHolder = new Label(card.getTitle());

            previewCardHolder.setGraphic(cardImg);
            previewCardHolder.setPrefHeight(250);
            previewCardHolder.setPrefWidth(250);
            previewTile1.getChildren().add(previewCardHolder);
        }


    }

    @FXML
    private void printPlan() {
        printer.printFile(flowPaneCards);
    }


    @FXML
    private void switchToStart() throws IOException {
        App.setRoot("Start");
    }
}
