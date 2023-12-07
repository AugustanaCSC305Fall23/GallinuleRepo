package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {

    @FXML
    private ListView<String> allCourseLV;

    private AllCourseList allCourses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the selection mode to allow multiple selections
        allCourseLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            allCourses = AllCourseList.loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Populate the ListView with lesson plan titles
        List<String> allCourseList = allCourses.getAllCourses();
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
