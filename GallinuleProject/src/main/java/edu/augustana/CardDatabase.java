package edu.augustana;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDatabase {
    private static List<Card> allCards = new ArrayList<>();

    public static List<Card> getAllCards() {
        return allCards;
    }

    public void addCardsFromCSVFile(File csvFile) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).build();

        String [] nextLine;
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
    public Card getCardByID(String cardID){ return allCards.get(allCards.indexOf(cardID)); }




    public static void main(String[] args) {

        CardDatabase cardDatabase = new CardDatabase();

        File csvFile = new File("src/main/resources/DEMO1.csv");

        try {

            cardDatabase.addCardsFromCSVFile(csvFile);
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }


}

