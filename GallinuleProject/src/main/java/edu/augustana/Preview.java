package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
    private FlowPane previewTile3;
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

    @FXML
    private VBox printVBox1;

    @FXML
    private VBox printVBox2;

    @FXML
    private VBox printVBox3;

    private List<VBox> printList = new ArrayList<>();


    private PrintGymFile printer = new PrintGymFile(); // Create an instance of the printing class


    private final int cardsPerSection = 4;

    private LessonPlan currentLessonPlan;


    public Preview(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        HashMap<String, List<String>> finishedCards = CreatePlanController.getCurrentLessonPlan().getLessonMap();
        titleLabel.setText(CreatePlanController.getCurrentLessonPlan().getTitle());
        titleLabel2.setText(CreatePlanController.getCurrentLessonPlan().getTitle());
        titleLabel3.setText(CreatePlanController.getCurrentLessonPlan().getTitle());
//        previewLabel1.setText(
        populatePreview(finishedCards);

        // Set up the print button action
        printButton.setOnAction(event -> printPlan());

    }

    public void populatePreview(HashMap<String, List<String>> finishedCards) {
        final int[] count = {0};
        List<FlowPane> tempListTile = new ArrayList<>();
        List<Label> tempListEvent = new ArrayList<>(); //future use

        tempListTile.add(previewTile1);
        tempListTile.add(previewTile2);
        tempListTile.add(previewTile3);
        tempListEvent.add(eventLabel1);
        tempListEvent.add(eventLabel2);
        tempListEvent.add(eventLabel3);

        finishedCards.forEach((key, value) -> {

            for (String code : value) {

                ImageView imageView = CardDatabase.getCardByID(code).createHighResolutionImageView();
                CardView cardImg = new CardView(imageView);
                cardImg.setFitWidth(250);
                cardImg.setFitHeight(205);
                Label eventText = new Label(CardDatabase.getCardByID(code).getEquipments().toString());
//                CardDatabase.getCardByID(code.strip()).getEquipments().toString()
                VBox previewCardHolder = new VBox();

                previewCardHolder.getChildren().add(cardImg);
                previewCardHolder.getChildren().add(eventText);
                previewCardHolder.setMinHeight(200);
                previewCardHolder.setMinWidth(200);
                previewCardHolder.setAlignment(Pos.CENTER);
                previewCardHolder.getStyleClass().add("placeholder");
                tempListTile.get(count[0]).getChildren().add(previewCardHolder);
//                tempListEvent.get(count.get()).setText();
            }
            count[0]++;
        });
    }

    @FXML
    private void printPlan() {
        printer.printFile(printAnchor);
        new Alert(Alert.AlertType.INFORMATION, "Your Plan has been sent to print succesfully!").show();
    }

    @FXML
    private void switchToPreviewPage() throws IOException {
        System.out.println("Clicked");
        App.setRoot("Start");
    }

}
