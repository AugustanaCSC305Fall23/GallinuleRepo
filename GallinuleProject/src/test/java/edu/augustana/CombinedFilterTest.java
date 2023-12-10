package edu.augustana;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CombinedFilterTest {

    String[] testCsvRowData1;

    String[] testCsvRowData2;

    String[] testCsvRowData3;

    Card testCard1;

    Card testCard2;

    Card testCard3;

    @BeforeEach
    public void setUp() {
        // Sample CSV data for testing
        testCsvRowData1 = new String[]{"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"};
        testCsvRowData2 = new String[]{"S30", "ALL", "Shapes", "Open Shoulder Drill", "Demo1", "2.png", "N", "F", "ALL", "Stick/Band", "shoulder flexibility"};
        testCsvRowData3 = new String[]{"H5", "Floor", "Handstand", "Half Handstand", "Demo1", "3.png", "N", "F", "B AB I", "Pacman/tall block/resi", "L handstand, pike handstand, handstand"};


        testCard1 = new Card(testCsvRowData1);
        testCard2 = new Card(testCsvRowData2);
        testCard3 = new Card(testCsvRowData3);
    }

    @Test
    void filter_shouldReturnFilteredCards_whenUsingAndOperation() {

        List<Card> cards = Arrays.asList(testCard1, testCard2, testCard3);

        // Create mock filters
        CardFilter filter1 = new MockFilter(true); // A filter that always returns true
        CardFilter filter2 = new MockFilter(card -> card.getCode().equals("S1")); // A filter that filters by a specific code

        // Combine filters using AND operation
        CombinedAndFilter combinedFilter = new CombinedAndFilter(filter1, filter2);

        // Apply the combined filter
        List<Card> filteredCards = combinedFilter.filter(cards);

        // Since the first filter always returns true, the result should be the same as the input list
        assertEquals(List.of(testCard1), filteredCards);

    }

    // Mock implementation of CardFilter for testing
    private static class MockFilter implements CardFilter {
        private final boolean expectedResult;
        private final java.util.function.Predicate<Card> predicate;

        MockFilter(boolean expectedResult) {
            this.expectedResult = expectedResult;
            this.predicate = null;
        }

        MockFilter(java.util.function.Predicate<Card> predicate) {
            this.expectedResult = true;
            this.predicate = predicate;
        }

        @Override
        public List<Card> filter(List<Card> cards) {
            if (predicate != null) {
                return cards.stream().filter(predicate).collect(Collectors.toList());
            } else {
                return expectedResult ? cards : List.of();
            }
        }
    }
}
