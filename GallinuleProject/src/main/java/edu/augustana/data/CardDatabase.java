package edu.augustana.data;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDatabase {
    static final List<Card> allCards = new ArrayList<>();
    static final Map<String,Card> allCardsMap = new HashMap<>(); //declaration to avoid null references


    public static void addCardsFromCSVFile(File csvFile) throws CsvValidationException, IOException {
        //Use try for CSVReader to ensure close after reading
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


    public static Card getCardByID(String cardID) {
        return allCardsMap.get(cardID);
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(allCards);
    }

    public static Map<String, Card> getAllCardsMap() {
        return new HashMap<>(allCardsMap);
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        File csvFile = new File("src/main/resources/DEMO1.csv");
        addCardsFromCSVFile(csvFile);

    }

}
