package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPlanController implements Initializable {

    @FXML
    private ListView<LessonPlan> lessonPlanListView;

    @FXML
    private Button loadButton;

    @FXML
    private Button editPlanBtn;

    @FXML
    private Button delPlanBtn;

    @FXML
    private Button saveAsBtn;

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

    @FXML
    private void openEditPage() throws IOException {
        LessonPlan selectedLessonPlan = lessonPlanListView.getSelectionModel().getSelectedItem();
        if (selectedLessonPlan != null) {

            CreatePlanController.currentLessonPlan = selectedLessonPlan;

            App.setRoot("CreatePlan");
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a plan to edit first!").show();
        }
    }

    @FXML
    private void deletePlan() {
        LessonPlan selectedLessonPlan = lessonPlanListView.getSelectionModel().getSelectedItem();
        if (selectedLessonPlan != null) {
            lessonPlanListView.getItems().remove(selectedLessonPlan);
            App.getCurrentOpenCourse().removePlan(selectedLessonPlan);

        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a plan to delete first!").show();
        }
    }

    @FXML
    private void menuActionSave(ActionEvent event) {
        if (App.getCurrentOpenCourse() == null) {
            menuActionSaveAs(event);
        } else {
            saveCurrentMovieLogToFile(App.getCurrentOpenCourseFile());
        }
    }

    @FXML
    private void menuActionSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Gym Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Gym Course (*.gymCourse)", "*.gymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonPlanListView.getScene().getWindow();
        File chosenFile = fileChooser.showSaveDialog(mainWindow);
        saveCurrentMovieLogToFile(chosenFile);
    }

    private void saveCurrentMovieLogToFile(File chosenFile) {
        if (chosenFile != null) {
            try {
                App.saveCourseToFile(chosenFile);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving gym course file: " + chosenFile).show();
            }


        }
    }

}