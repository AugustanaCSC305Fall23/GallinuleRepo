package edu.augustana;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelFilterTest {

    Card testCard1;
    Card testCard2;
    Card testCard3;

    @BeforeEach
    public void setUp() {
        // Sample cards for testing
        //CODE,Event,Category,Title,Pack Folder,Image,Gender,Model Sex,Level,Equipment,Keywords
        testCard1 = new Card(new String[]{"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"});
        testCard2 = new Card(new String[]{"S30", "ALL", "Shapes", "Open Shoulder Drill", "Demo1", "2.png", "N", "F", "ALL", "Stick/Band", "shoulder flexibility"});
        testCard3 = new Card(new String[]{"H5", "Floor", "Handstand", "Half Handstand", "Demo1", "3.png", "N", "F", "B AB I", "Pacman/tall block/resi", "L handstand, pike handstand, handstand"});
    }

    @Test
    void filter_shouldReturnAllCards_whenSelectedLevelIsAll() {
        LevelFilter levelFilter = new LevelFilter("ALL");
        List<Card> cards = Arrays.asList(testCard1, testCard2, testCard3);

        List<Card> filteredCards = levelFilter.filter(cards);

        assertEquals(cards, filteredCards);
    }

    @Test
    void filter_shouldReturnMatchingCards_whenSelectedLevelIsBeginner() {
        LevelFilter levelFilter = new LevelFilter("Beginner");
        List<Card> cards = Arrays.asList(testCard1, testCard2, testCard3);

        List<Card> filteredCards = levelFilter.filter(cards);

        assertEquals(List.of(testCard3), filteredCards);
    }

    @Test
    void filter_shouldReturnMatchingCards_whenSelectedLevelIsIntermediate() {
        LevelFilter levelFilter = new LevelFilter("Intermediate");
        List<Card> cards = Arrays.asList(testCard1, testCard2, testCard3);

        List<Card> filteredCards = levelFilter.filter(cards);

        List<Card> expectedEmptyList = new ArrayList<>();

        assertEquals(List.of(testCard3), filteredCards);
    }

}
