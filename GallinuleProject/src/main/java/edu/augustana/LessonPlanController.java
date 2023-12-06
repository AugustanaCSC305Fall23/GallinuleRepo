package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPlanController implements Initializable {

    @FXML
    private ListView<String> lessonPlanListView;

    private static List<LessonPlan> allLessonPlans;

    public LessonPlanController() {
        // Initialize the list of LessonPlans (you may retrieve it from your data source)
        allLessonPlans = LessonPlan.getAllLessonPlans();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the selection mode to allow multiple selections
        lessonPlanListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Populate the ListView with lesson plan titles
        for (LessonPlan lessonPlan : allLessonPlans) {
            lessonPlanListView.getItems().add(lessonPlan.getTitle());
        }
    }

    // Add any additional methods or event handlers as needed
}


