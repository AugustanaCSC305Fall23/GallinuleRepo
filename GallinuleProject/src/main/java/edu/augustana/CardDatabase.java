package edu.augustana;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardDatabase {
    private static final List<Card> allCards = new ArrayList<>();

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static void addCardsFromCSVFile(File csvFile) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).build();
        String[] nextLine;
        boolean isFirstLine = true;
        while ((nextLine = reader.readNext()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
            } else {
                Card testCard = new Card(nextLine);
                allCards.add(testCard);
                System.out.println(testCard);
            }
        }
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        File csvFile = new File("src/main/resources/DEMO1.csv");
        addCardsFromCSVFile(csvFile);

    }
}
