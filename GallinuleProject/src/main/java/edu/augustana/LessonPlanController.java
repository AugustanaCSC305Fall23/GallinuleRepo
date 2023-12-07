package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPlanController implements Initializable {

    @FXML
    private ListView<LessonPlan> lessonPlanListView;

    @FXML
    private Button loadButton;

//    private AllPlansList allLessonPlan;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lessonPlanListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<LessonPlan> allPlanList = App.getCurrentOpenCourse().getLessons();
        for (LessonPlan lessonPlan : allPlanList) {
            lessonPlanListView.getItems().add(lessonPlan);
        }

    }
    @FXML
    private void openLessonPlan() throws IOException {

        LessonPlan selectedLessonPlan = lessonPlanListView.getSelectionModel().getSelectedItem();
        if (selectedLessonPlan != null) {

            CreatePlanController.currentLessonPlan = selectedLessonPlan;

            CreatePlanController controller = new CreatePlanController();
            controller.switchToPreview();
        }
    }


}


