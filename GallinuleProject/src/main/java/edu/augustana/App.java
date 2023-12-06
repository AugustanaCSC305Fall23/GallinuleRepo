package edu.augustana;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private AllPlansList allPlansList;


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

    public static void main(String[] args) throws CsvValidationException, IOException {

        CardDatabase.addCardsFromCSVFile(new File("CardPacks/Demo1/Demo1.csv"));
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/Demo2/Demo2.csv"));

        launch();
    }

    @Override
    public void stop() throws IOException{
        allPlansList.saveToFile();
    }

}