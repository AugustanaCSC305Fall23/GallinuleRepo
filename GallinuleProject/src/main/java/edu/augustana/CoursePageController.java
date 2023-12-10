package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the "CoursePage" view, responsible for displaying and managing gym courses.
 */
public class CoursePageController implements Initializable {

    @FXML
    private ListView<String> allCourseLV;

    /**
     * Initializes the CoursePageController.
     *
     * @param url            The location to resolve the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the selection mode to allow multiple selections
        allCourseLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        AllCourseList allCourses;
        try {
            allCourses = AllCourseList.loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Populate the ListView with lesson plan titles
        List<String> allCourseList = AllCourseList.getAllCourses();
        for (String lessonPlan : allCourseList) {
            allCourseLV.getItems().add(lessonPlan);
        }

        // Save the initial state to a JSON file
        try {
            allCourses.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
