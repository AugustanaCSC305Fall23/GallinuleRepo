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

/**
 * Controller for the "PreviewTextOnly" view, responsible for displaying a text-only preview of a lesson plan.
 */
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

    private final List<VBox> printList = new ArrayList<>();

    private final PrintGymFile printer = new PrintGymFile(); // Create an instance of the printing class

    /**
     * Initializes the ViewLessonPlanController with necessary data and actions.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch all cards and populate the preview
        HashMap<String, List<String>> finishedCards = CreatePlanController.getCurrentLessonPlan().getLessonMap();
        titleLabel.setText(CreatePlanController.getCurrentLessonPlan().getTitle());

        populatePreview(finishedCards);
        // Set up the print button action
        printButton.setOnAction(event -> printPlan());

    }

    /**
     * Populates the preview with finished cards.
     *
     * @param finishedCards A HashMap containing event names as keys and lists of card codes as values.
     */
    public void populatePreview(HashMap<String, List<String>> finishedCards) {
        motherVBox.setAlignment(Pos.TOP_CENTER);
        List<VBox> populateList = new ArrayList<>();
        finishedCards.forEach((key, value) -> {
            VBox temp = new VBox();
            temp.setAlignment(Pos.TOP_CENTER);
            Text tempText = new Text(key.substring(0, key.length() - 1));
            tempText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            temp.getChildren().add(tempText);
            for (String code : value) {
                temp.getChildren().add(new Text(CardDatabase.getCardByID(code).getTitle() + " - " + CardDatabase.getCardByID(code).getEquipments()));
            }

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
