package edu.augustana;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.text.Text;

/**
 * Controller class for the start screen of the application.
 * Manages navigation between different screens using a side menu.
 */
public class StartController implements Initializable {

    @FXML
    private ImageView MenuButton;



    @FXML
    private BorderPane borderPane;

    @FXML
    private static BorderPane bp;

    @FXML
    private VBox mainScreen;

    @FXML
    private VBox slider;

    @FXML
    private AnchorPane navPane;

    @FXML
    private Button createplanbutton;

    @FXML
    private HBox MenuHolder;

    @FXML
    private Text AppTitle;

    private static LessonPlan holdUntilDiscardLessonPlan;

    private int x = 0;

    /**
     * Gets the clicked lesson plan from the search bar held until discard.
     *
     * @return The lesson plan held until discard.
     */
    public static LessonPlan getHoldUntilDiscardLessonPlan() {
        return holdUntilDiscardLessonPlan;
    }

    /**
     * Sets the lesson plan to be held until discard.
     *
     * @param newLP The new lesson plan to be held until discard.
     */
    public static void setHoldUntilDiscardLessonPlan(LessonPlan newLP) {
        holdUntilDiscardLessonPlan = newLP;
    }

    /**
     * Initializes the controller with the starting FXML page.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources specific to this controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bp = borderPane;
        try { //start on the CreatePlanScreen
            switchCreatePlan();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MenuButton.setOnMouseClicked(Event -> {
            if (x == -200) {
                x = 0;
            } else {
                x = -200;
            }
            TranslateTransition slideAction = new TranslateTransition();
            TranslateTransition slideAction2 = new TranslateTransition();
            TranslateTransition slideAction3 = new TranslateTransition();
            TranslateTransition slideAction4 = new TranslateTransition();
            slideAction.setDuration(Duration.seconds(0.4));
            slideAction2.setDuration(Duration.seconds(0.4));

            slideAction.setNode(navPane);
            slideAction.setToX(x);
            slideAction2.setNode(bp);
            slideAction2.setToX(x);
            slideAction3.setNode(MenuButton);
            slideAction3.setToX(-(x));
            slideAction4.setNode(AppTitle);
            slideAction4.setToX(-(x));

            slideAction.play();
            slideAction2.play();
            slideAction3.play();
            slideAction4.play();

        });

    }


    @FXML
    private void switchWelcome() throws IOException {
        loadPage("Welcome");
    }

    @FXML
    private void switchBrowse() throws IOException {
        loadPage("BrowsePlan");
    }

    @FXML
    static void switchCreatePlan(LessonPlan enteredLessonPlan) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("CreatePlan.fxml"));
            Parent root = fxmlLoader.load();
            CreatePlanController controller = new CreatePlanController();
            System.out.println(enteredLessonPlan + " - StartController");
//            CreatePlanController.setCurrentLessonPlan(enteredLessonPlan);
            bp.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void switchCreatePlan() throws IOException {
        switchCreatePlan(new LessonPlan());
    }

    @FXML
    public void switchCreatePlanStartController() throws IOException {
        LessonPlanController.setSelectedLessonPlanTransfer(null);
        loadPage("CreatePlan");
    }

    @FXML
    private void switchViewAllCards() throws IOException {
        loadPage("ViewAllCard");
    }

    @FXML
    private void switchAboutPage() throws IOException {
        loadPage("AboutPage");
    }

    @FXML
    private void switchToCoursePage() throws IOException {
        loadPage("CoursePage");
    }


    /**
     * Loads the specified FXML page into the BorderPane's center.
     *
     * @param page The name of the FXML page (without the .fxml extension) to load.
     * @throws IOException If an error occurs while loading the FXML page.
     */
    @FXML
    public void loadPage(String page) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource(page + ".fxml"));
        Parent root = loader.load();
        bp.setCenter(root);

    }

}