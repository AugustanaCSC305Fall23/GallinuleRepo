package edu.augustana;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.text.Text;

public class StartController implements Initializable {


    @FXML
    private ImageView MenuButton;

    @FXML
    private BorderPane bp;

    @FXML
    private VBox mainScreen;

    @FXML
    private VBox slider;

    @FXML
    private AnchorPane navPane;

    @FXML
    private HBox MenuHolder;

    @FXML
    private Text AppTitle;

    private int x = 0;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try { //start on the dashboard
            switchWelcome();
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
    private void switchCreatePlan() throws IOException {
        loadPage("CreatePlan");
    }

    @FXML
    private void switchViewAllCards() throws IOException {
        loadPage("ViewAllCard");
    }

    @FXML
    private void switchAboutPage() throws IOException{
        loadPage("AboutPage");
    }

    @FXML
    public void loadPage(String page) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource(page + ".fxml"));
        Parent root = loader.load();
        bp.setCenter(root);

    }

}