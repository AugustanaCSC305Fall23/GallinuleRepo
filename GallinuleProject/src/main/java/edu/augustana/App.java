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
import java.util.List;


/**
 * JavaFX App hat serves as the main entry point for the application.
 */
public class App extends Application {

    private static Scene scene;

    private static Course currentOpenCourse;
    private static File currentOpenCourseFile;

    private static FavoriteSet favorites;

    /**
     * Retrieves the currently open course file.
     *
     * @return The current open course file.
     */
    public static File getCurrentOpenCourseFile() {
        return currentOpenCourseFile;
    }

    /**
     * Retrieves the set of favorites.
     *
     * @return The set of favorites.
     */
    public static FavoriteSet getFavorites() {
        return favorites;
    }

    /**
     * Entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Start"), 1275, 725);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml The name of the FXML file to load.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Retrieves a list of favorite cards.
     *
     * @return The list of favorite cards.
     */
    public static List<Card> getFavCards() {
        List<Card> favCards = new ArrayList<>();
        for (String favCardId : favorites.getFavoriteIDs()) {
            Card card = CardDatabase.getCardByID(favCardId);
            favCards.add(card);
        }
        return favCards;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Method called when the application is stopped.
     *
     * @throws IOException If an I/O error occurs while saving data.
     */
    @Override
    public void stop() throws IOException {
        currentOpenCourse.saveCourse(currentOpenCourseFile);
        favorites.saveToFile();
    }

    /**
     * Main method that launches the application.
     *
     * @param args Command-line arguments.
     * @throws CsvValidationException If an error occurs during CSV validation.
     * @throws IOException            If an I/O error occurs.
     */
    public static void main(String[] args) throws CsvValidationException, IOException {
        addCardsFromCardPacksDirectory("CardPacks");
        favorites = FavoriteSet.loadFromFile();

        currentOpenCourseFile = new File("Courses/Untitled.gymCourse");
        currentOpenCourse = Course.loadCourse(currentOpenCourseFile);
        launch();
    }

    /**
     * Adds cards from the specified card packs directory.
     *
     * @param cardPacksDirectory The directory containing card packs.
     * @throws CsvValidationException If an error occurs during CSV validation.
     * @throws IOException            If an I/O error occurs.
     */
    public static void addCardsFromCardPacksDirectory(String cardPacksDirectory) throws CsvValidationException, IOException {
        File directory = new File(cardPacksDirectory);
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

    /**
     * Retrieves the currently open course.
     *
     * @return The currently open course.
     */
    public static Course getCurrentOpenCourse() {
        return currentOpenCourse;
    }

    /**
     * Opens a course from the specified file.
     *
     * @param courseFile The file from which to open the course.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void openCourseFromFile(File courseFile) throws FileNotFoundException {
        currentOpenCourseFile = courseFile;
        currentOpenCourse = Course.loadCourse(courseFile);
    }

    /**
     * Saves the course to the specified file.
     *
     * @param courseFile  The file to which the course will be saved.
     * @param lessonPlans The list of lesson plans to be saved.
     * @throws IOException If an I/O error occurs while saving the course.
     */
    public static void saveCourseToFile(File courseFile, List<LessonPlan> lessonPlans) throws IOException {
        currentOpenCourseFile = courseFile;
        currentOpenCourse.setLessons(lessonPlans);
        currentOpenCourse.saveCourse(courseFile);
    }

}