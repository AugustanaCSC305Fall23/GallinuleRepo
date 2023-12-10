package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

/**
 * Controller for the Lesson Plan view, responsible for managing lesson plans.
 */
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lessonPlanListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<LessonPlan> allPlanList = App.getCurrentOpenCourse().getLessons();
        for (LessonPlan lessonPlan : allPlanList) {
            lessonPlanListView.getItems().add(lessonPlan);
        }

    }

    /**
     * Opens the selected Lesson Plan for preview.
     * Invoked when the "Open" button is clicked.
     *
     * @throws IOException If an error occurs while opening the Lesson Plan.
     */
    @FXML
    private void openLessonPlan() throws IOException {

        LessonPlan selectedLessonPlan = lessonPlanListView.getSelectionModel().getSelectedItem();
        if (selectedLessonPlan != null) {

            CreatePlanController.currentLessonPlan = selectedLessonPlan;

            CreatePlanController controller = new CreatePlanController();
            controller.loadPlanPreview();
        }

    }

    /**
     * Opens the selected Lesson Plan for editing.
     * Invoked when the "Edit" button is clicked.
     *
     * @throws IOException If an error occurs while opening the Lesson Plan for editing.
     */
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

    /**
     * Deletes the selected Lesson Plan.
     * Invoked when the "Delete" button is clicked.
     *
     * @throws IOException If an error occurs while deleting the Lesson Plan.
     */
    @FXML
    private void deletePlan() throws IOException {
        LessonPlan selectedLessonPlan = lessonPlanListView.getSelectionModel().getSelectedItem();
        if (selectedLessonPlan != null) {
            lessonPlanListView.getItems().remove(selectedLessonPlan);
            App.getCurrentOpenCourse().removePlan(selectedLessonPlan);
            App.saveCourseToFile(App.getCurrentOpenCourseFile(), App.getCurrentOpenCourse().getLessons());

        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a plan to delete first!").show();
        }
    }

    /**
     * Duplicates the selected Lesson Plan.
     * Invoked when the "Duplicate" button is clicked.
     *
     * @throws IOException If an error occurs while duplicating the Lesson Plan.
     */
    @FXML
    private void duplicatePlan() throws IOException {
        LessonPlan selectedLessonPlan = lessonPlanListView.getSelectionModel().getSelectedItem();
        if (selectedLessonPlan != null) {
            lessonPlanListView.getItems().add(selectedLessonPlan);
            App.getCurrentOpenCourse().addPlan(selectedLessonPlan);
            App.saveCourseToFile(App.getCurrentOpenCourseFile(), App.getCurrentOpenCourse().getLessons());

        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a plan to duplicate first!").show();
        }
    }

    /**
     * Saves the current Lesson Plan.
     * Invoked when the "Save" button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void menuActionSave(ActionEvent event) {
        if (App.getCurrentOpenCourse() == null) {
            menuActionSaveAs(event);
        } else {
            saveCurrentMovieLogToFile(App.getCurrentOpenCourseFile());
        }
    }


    /**
     * Saves the current Lesson Plan with a new file name or path.
     * Invoked when the "Save As" button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     */
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

    /**
     * Saves the current Lesson Plan to the specified file.
     *
     * @param chosenFile The file where the Lesson Plan should be saved.
     */
    private void saveCurrentMovieLogToFile(File chosenFile) {
        if (chosenFile != null) {
            try {
                App.saveCourseToFile(chosenFile, lessonPlanListView.getItems());
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving gym course file: " + chosenFile).show();
            }


        }
    }

}