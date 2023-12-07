package edu.augustana;

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
import javafx.scene.text.Text;

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
                VBox previewCardHolder = new VBox();

                ImageView imageView = CardDatabase.getCardByID(code).createHighResolutionImageView();
                CardView cardImg = new CardView(imageView);
                cardImg.setFitWidth(250);
                cardImg.setFitHeight(205);
                Label eventText = new Label(CardDatabase.getCardByID(code).getEquipments().toString());
                previewCardHolder.getChildren().add(cardImg);
                previewCardHolder.getChildren().add(eventText);

//                Text cardTitle = new Text(CardDatabase.getCardByID(code).getTitle());
//                Label eventText = new Label(CardDatabase.getCardByID(code).getEquipments().toString());
//                previewCardHolder.getChildren().add(cardTitle);
//                previewCardHolder.getChildren().add(eventText);


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
        printList = new ArrayList<>();
        for(Node page: printAnchor.getChildren()){
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
