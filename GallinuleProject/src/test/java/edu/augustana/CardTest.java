package edu.augustana;

import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CardTest {

    private Card testCard;
    private String[] testCsvRowData;

    @BeforeEach
    public void setUp() {
        // Sample CSV data for testing
        testCsvRowData = new String[]{"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"};

        // Create a Card object for testing
        testCard = new Card(testCsvRowData);
    }

    @Test
    public void testGetCode() {
        assertEquals("S1", testCard.getCode());
    }

    @Test
    public void testGetEvent() {
        assertEquals("ALL", testCard.getEvent());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Shapes", testCard.getCategory());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Hollow", testCard.getTitle());
    }

    @Test
    public void testGetPackFolder() {
        assertEquals("DEMO1", testCard.getPackFolder());
    }

    @Test
    public void testGetImg() {
        assertEquals("1.png", testCard.getImage());
    }

    @Test
    public void testGetGender() {
        assertEquals("N", testCard.getGender());
    }

    @Test
    public void testGetModelSex() {
        assertEquals("F", testCard.getModelSex());
    }

    @Test
    public void testGetLevels() {
        List<String> expectedLevels = Arrays.asList("ALL");
        assertEquals(expectedLevels, testCard.getLevels());
    }

    @Test
    public void testGetEquipments() {
        List<String> expectedEquipments = Arrays.asList("None");
        assertEquals(expectedEquipments, testCard.getEquipments());
    }

    @Test
    public void testGetKeywords() {
        String expectedKeywords = "hollow, dish, banana";
        assertEquals(expectedKeywords, testCard.getKeywords().get(0));
    }


    @Test
    public void testCreateImageView() {
        // Initialize JavaFX Toolkit on the Application Thread
        Platform.startup(() -> {
            String[] csvRowData = {"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"};
            Card card = new Card(csvRowData);

            // Run the actual test inside the JavaFX thread
            Platform.runLater(() -> {
                assertNotNull(card.createImageView());
            });
        });
    }

    @Test
    public void testToString() {
        String expectedToString = "Card{" +
                "code='S1', event='ALL', category='Shapes', title='Hollow', packFolder='DEMO1', img='1.png', " +
                "gender='N', modelSex='F', level=[ALL], equipments=[None], keywords=[hollow, dish, banana]}";
        assertEquals(expectedToString, testCard.toString());
    }
}
