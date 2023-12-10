package edu.augustana;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Represents a database for managing Card objects and provides methods to interact with the card data.
 */
public class CardDatabase {

    private static final CardDatabase theSingleDB = new CardDatabase();
    protected static final List<Card> allCards = new ArrayList<>();
    protected static final Map<String, Card> allCardsMap = new HashMap<>(); //declaration to avoid null references


    /**
     * Adds cards from a CSV file to the database.
     *
     * @param csvFile The CSV file containing card data.
     * @throws CsvValidationException If an error occurs during CSV validation.
     * @throws IOException            If an I/O error occurs.
     */
    public static void addCardsFromCSVFile(File csvFile) throws CsvValidationException, IOException {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).build()) {
            String[] nextLine;
            boolean isFirstLine = true;
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    Card card = new Card(nextLine);
                    allCardsMap.put(card.getCode(), card);
                    allCards.add(card);
                }
            }
        }
    }

    /**
     * Gets the instance of the CardDatabase.
     *
     * @return The instance of the CardDatabase.
     */
    public static CardDatabase getDB() {
        return theSingleDB;
    }


    /**
     * Gets a Card object by its ID.
     *
     * @param cardID The ID of the card.
     * @return The Card object corresponding to the given ID.
     */
    public static Card getCardByID(String cardID) {
        return allCardsMap.get(cardID);
    }

    /**
     * Gets a list of all cards in the database.
     *
     * @return A list of all cards in the database.
     */
    public static List<Card> getAllCards() {
        return new ArrayList<>(allCards);
    }

    /**
     * Gets a list of unique event names from all cards.
     *
     * @return A list of unique event names.
     */
    public List<String> getEventList() {
        Set<String> eventSet = new TreeSet<>();
        for (Card card : allCards) {
            eventSet.add(card.getEvent());
        }
        return new ArrayList<>(eventSet);
    }

    /**
     * Gets a list of unique gender values from all cards.
     *
     * @return A list of unique gender values.
     */
    public List<String> getGenderList() {
        Set<String> genderSet = new TreeSet<>();
        for (Card card : allCards) {
            genderSet.add(card.getGender());
        }
        return new ArrayList<>(genderSet);
    }

    /**
     * Gets a list of unique model sex values from all cards.
     *
     * @return A list of unique model sex values.
     */
    public List<String> getModelList() {
        Set<String> modelSet = new TreeSet<>();
        for (Card card : allCards) {
            modelSet.add(card.getModelSex());
        }
        return new ArrayList<>(modelSet);
    }

    /**
     * Gets a list of unique level values from all cards.
     *
     * @return A list of unique level values.
     */
    public List<String> getLevelList() {
        Set<String> levelSet = new TreeSet<>();

        for (Card card : allCards) {
            List<String> cardLevels = card.getLevels();
            for (String level : cardLevels) {
                levelSet.add(level);
            }
        }

        return new ArrayList<>(levelSet);
    }

    /**
     * Gets a copy of the map containing all cards.
     *
     * @return A copy of the map containing all cards.
     */
    public static Map<String, Card> getAllCardsMap() {
        return new HashMap<>(allCardsMap);
    }

    /**
     * Main method for testing card database functionality.
     *
     * @param args Command-line arguments.
     * @throws CsvValidationException If an error occurs during CSV validation.
     * @throws IOException            If an I/O error occurs.
     */
    public static void main(String[] args) throws CsvValidationException, IOException {
        File csvFile1 = new File("src/main/resources/DEMO1.csv");
        File csvFile2 = new File("src/main/resources/DEMO2.csv");

        addCardsFromCSVFile(csvFile1);
        addCardsFromCSVFile(csvFile2);

    }

}