package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Preview {


    private LessonPlan lessonPlan;

    @FXML
    private Button saveButton;

    @FXML
    private AnchorPane previewFileAnchorPane;


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


}
