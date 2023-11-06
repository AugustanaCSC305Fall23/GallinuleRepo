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
                String[] element8Split = nextLine[8].split(" ");
                String[] element9Split = nextLine[9].split(",");
                String[] element10Split = nextLine[10].split(",");


                List<String> element8List = Arrays.asList(element8Split);
                List<String> element9List = Arrays.asList(element9Split);
                List<String> element10List = Arrays.asList(element10Split);

                Card card = new Card(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], element8List, element9List, element10List);

                System.out.println(card);
            }
        }

    }

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