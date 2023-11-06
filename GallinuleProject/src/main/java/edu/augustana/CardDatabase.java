package edu.augustana;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDatabase {
    private static List<Card> allCards = new ArrayList<>();

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static void addCardsFromCSVFile(File csvFile) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).withSkipLines(1).build();
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null) {
            // Create a Card object from the CSV data
            Card card = new Card(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4],
                    nextLine[5], nextLine[6], nextLine[7], Collections.singletonList(nextLine[8]), Collections.singletonList(nextLine[9]), Collections.singletonList(nextLine[10]));

            // Add the Card object to the allCards list
            allCards.add(card);
        }
    }
}
