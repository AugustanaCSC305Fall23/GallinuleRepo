package edu.augustana;

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
    private static final List<Card> allCards = new ArrayList<>();
    private static Map<String,Card> allCardsMap;

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static void addCardsFromCSVFile(File csvFile) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).build();
        String[] nextLine;
        allCardsMap = new HashMap<>();
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

    public static void main(String[] args) throws CsvValidationException, IOException {
        File csvFile = new File("src/main/resources/DEMO1.csv");
        addCardsFromCSVFile(csvFile);

    }

    public static Card getCardByID(String cardID) {
        return allCardsMap.get(cardID);
    }

}
