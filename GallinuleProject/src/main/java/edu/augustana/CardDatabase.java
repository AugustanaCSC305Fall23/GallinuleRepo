package edu.augustana;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CardDatabase {

    private static CardDatabase theSingleDB = new CardDatabase();
    static final List<Card> allCards = new ArrayList<>();
    static final Map<String,Card> allCardsMap = new HashMap<>(); //declaration to avoid null references


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

    public static CardDatabase getDB(){
        return theSingleDB;
    }


    public static Card getCardByID(String cardID) {
        return allCardsMap.get(cardID);
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(allCards);
    }

    public List<String> getEventList() {
        Set<String> eventSet = new TreeSet<>();
        for (Card card : allCards) {
            eventSet.add(card.getEvent());
        }
        return new ArrayList<>(eventSet);
    }

    public List<String> getGenderList() {
        Set<String> genderSet = new TreeSet<>();
        for (Card card : allCards) {
            genderSet.add(card.getGender());
        }
        return new ArrayList<>(genderSet);
    }

    public List<String> getModelList() {
        Set<String> modelSet = new TreeSet<>();
        for (Card card : allCards) {
            modelSet.add(card.getModelSex());
        }
        return new ArrayList<>(modelSet);
    }

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

    public static Map<String, Card> getAllCardsMap() {
        return new HashMap<>(allCardsMap);
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        File csvFile = new File("src/main/resources/DEMO1.csv");
        addCardsFromCSVFile(csvFile);

    }

}
