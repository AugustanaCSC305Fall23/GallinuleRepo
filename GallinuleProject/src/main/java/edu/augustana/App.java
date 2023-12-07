package edu.augustana;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static Course currentOpenCourse;
    private static File currentOpenCourseFile;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Start"), 1275, 725);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void stop() throws IOException{
        currentOpenCourse.saveCourse(currentOpenCourseFile);
    }

    public static void main(String[] args) throws CsvValidationException, IOException {

        CardDatabase.addCardsFromCSVFile(new File("CardPacks/Demo1/Demo1.csv"));
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/Demo2/Demo2.csv"));

        currentOpenCourseFile = new File("Courses/Untitled.gymCourse");
        currentOpenCourse = Course.loadCourse(currentOpenCourseFile);
        //currentOpenCourse = new Course("Untitled", new ArrayList<>()); // or load it from last saved location?
        launch();
    }

    public static Course getCurrentOpenCourse() {
        return currentOpenCourse;
    }

    //somewhere in the GUI, you would let the user choose a file (with a file chooser)
    // adn then you'd call this method
    public static void openCourseFromFile(File courseFile) throws FileNotFoundException {
        currentOpenCourseFile = courseFile;
        currentOpenCourse = Course.loadCourse(courseFile);
    }
    public static void saveCourseToFile(File courseFile) throws IOException {
        currentOpenCourseFile = courseFile;
        currentOpenCourse.saveCourse( courseFile);
    }

}