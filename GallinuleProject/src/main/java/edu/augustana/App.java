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


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static Course currentOpenCourse;
    private static File currentOpenCourseFile;

    public static File getCurrentOpenCourseFile() {
        return currentOpenCourseFile;
    }


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

        addCardsFromCardPacksDirectory("CardPacks");

        currentOpenCourseFile = new File("Courses/Untitled.gymCourse");
        currentOpenCourse = Course.loadCourse(currentOpenCourseFile);
        //currentOpenCourse = new Course("Untitled", new ArrayList<>()); // or load it from last saved location?
        launch();
    }


    public static void addCardsFromCardPacksDirectory(String cardPacksDirectory) throws CsvValidationException, IOException {
        File directory = new File(cardPacksDirectory);

        // Check if the given directory exists
        if (directory.exists() && directory.isDirectory()) {
            processDirectory(directory);
        } else {
            System.out.println("Invalid directory: " + cardPacksDirectory);
        }
    }

    private static void processDirectory(File directory) throws CsvValidationException, IOException {
        File[] files = directory.listFiles();

        // Iterate through all files in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                    CardDatabase.addCardsFromCSVFile(file);
                } else if (file.isDirectory()) {
                    // Recursively process subdirectories
                    processDirectory(file);
                }
            }
        }
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