package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class PreviewTextOnlyController implements Initializable {
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
    private VBox motherVBox;

    private List<VBox> printList = new ArrayList<>();


    private PrintGymFile printer = new PrintGymFile(); // Create an instance of the printing class



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        HashMap<String, List<String>> finishedCards = CreatePlanController.getCurrentLessonPlan().getLessonMap();
        titleLabel.setText(CreatePlanController.getCurrentLessonPlan().getTitle());

        populatePreview(finishedCards);

        // Set up the print button action
        printButton.setOnAction(event -> printPlan());

    }

    public void populatePreview(HashMap<String, List<String>> finishedCards) {
        final int[] count = {0};
        motherVBox.setAlignment(Pos.TOP_CENTER);
        List<VBox> populateList = new ArrayList<>();
        finishedCards.forEach((key, value) -> {
            VBox temp = new VBox();
            temp.setAlignment(Pos.TOP_CENTER);
            Text tempText = new Text(key);
            tempText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            temp.getChildren().add(tempText);
            for (String code : value) {
                temp.getChildren().add(new Text(CardDatabase.getCardByID(code).getTitle() + " - " + CardDatabase.getCardByID(code).getEquipments()));
            }
            count[0]++;
            populateList.add(temp);
        });
        motherVBox.getChildren().addAll(populateList);
    }

    @FXML
    private void printPlan() {
        printer.printFileTextOnly(printAnchor);
    }

    @FXML
    private void switchToPreviewPage() throws IOException {
        System.out.println("Clicked");
        App.setRoot("Start");
    }

}
