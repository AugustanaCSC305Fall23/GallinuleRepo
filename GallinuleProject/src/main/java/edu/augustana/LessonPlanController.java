package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPlanController implements Initializable {

    @FXML
    private ListView<String> lessonPlanListView;

    private AllPlansList allLessonPlan;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the selection mode to allow multiple selections
        lessonPlanListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            allLessonPlan = AllPlansList.loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Populate the ListView with lesson plan titles
        List<String> allPlanList = allLessonPlan.getAllLessonPlans();
        for (String lessonPlan : allPlanList) {
            lessonPlanListView.getItems().add(lessonPlan);
        }

        // Save the initial state to a JSON file
        try {
            allLessonPlan.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add any additional methods or event handlers as needed
}


