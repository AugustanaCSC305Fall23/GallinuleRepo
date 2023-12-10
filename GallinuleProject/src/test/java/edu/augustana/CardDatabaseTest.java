package edu.augustana;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardDatabaseTest {

    @Test
    void addCardsFromCSVFile() {
        try {
            File csvFile = new File("CardPacks/DEMO1/Demo1.csv");
            CardDatabase.addCardsFromCSVFile(csvFile);
            Card specificCard = CardDatabase.getCardByID("S1");
            assertNotNull(specificCard);
            assertEquals("Hollow", specificCard.getTitle());
        } catch (CsvValidationException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    void getEventList() throws CsvValidationException, IOException {
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/DEMO1/Demo1.csv"));
        List<String> eventList = CardDatabase.getDB().getEventList();

        // Check that the event list contains the expected elements
        assertEquals(7, eventList.size());
        assertTrue(eventList.contains("ALL"));
        assertTrue(eventList.contains("Floor"));
        assertTrue(eventList.contains("Uneven Bars"));
        assertTrue(eventList.contains("Uneven Bars"));
        assertTrue(eventList.contains("Beam"));
        assertTrue(eventList.contains("Tramp"));
        assertTrue(eventList.contains("Strength"));


    }

    @Test
    void getCardByID() throws CsvValidationException, IOException {
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/DEMO1/Demo1.csv"));

        // Check that getCardByID returns the correct card
        Card card = CardDatabase.getCardByID("S1");
        assertNotNull(card);
        assertEquals("Hollow", card.getTitle());

        // Check that it returns null for a non-existent card
        assertNull(CardDatabase.getCardByID("NonExistentCard"));
    }

    @Test
    void getAllCardsMap() throws CsvValidationException, IOException {
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/DEMO1/Demo1.csv"));
        Map<String, Card> allCardsMap = CardDatabase.getAllCardsMap();

        // Check that the map contains the expected number of entries
        assertEquals(20, allCardsMap.size());

        // Check that it contains a specific card
        assertTrue(allCardsMap.containsKey("S1"));
        assertNotNull(allCardsMap.get("S1"));
        assertEquals("Hollow", allCardsMap.get("S1").getTitle());
    }

    @Test
    void getModelList() throws CsvValidationException, IOException {
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/DEMO1/Demo1.csv"));
        List<String> modelList = CardDatabase.getDB().getModelList();

        // Check that the model list contains the expected elements
        assertEquals(1, modelList.size());
        assertTrue(modelList.contains("F"));

    }

    @Test
    void getGenderList() throws CsvValidationException, IOException {
        CardDatabase.addCardsFromCSVFile(new File("CardPacks/DEMO1/Demo1.csv"));
        List<String> genderList = CardDatabase.getDB().getGenderList();

        // Check that the gender list contains the expected elements
        assertEquals(2, genderList.size());
        assertTrue(genderList.contains("F"));
        assertTrue(genderList.contains("N"));
    }


}
