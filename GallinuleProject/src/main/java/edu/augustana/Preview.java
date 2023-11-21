package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Preview implements Initializable {


    private LessonPlan lessonPlan;

    @FXML
    private Button saveButton;

    @FXML
    private AnchorPane previewFileAnchorPane;

    @FXML
    private TilePane previewTile1;

    @FXML
    private TilePane previewTile2;


//    public void populatePreview(){ commenting out for now for push
//        List<Card> finishedCards = CreatePlanController.savedCards;
//        for(Card card: finishedCards){
//            Label previewCardHolder = new Label(card.getImg());
//            previewCardHolder.setPrefHeight(80);
//            previewCardHolder.setPrefWidth(100);
//        }
//
//    }

    @FXML
    void printPlan(){

        PrintGymFile printer = new PrintGymFile();
        printer.printFile(previewFileAnchorPane);

    }

    public void handleSaveButton(ActionEvent event) {
        if (lessonPlan != null) {
            LessonPlan.savePlan(lessonPlan);
        }
    }

    @FXML
    private void switchToStart() throws IOException {
        App.setRoot("Start");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        populatePreview();
    }
}
