package edu.augustana;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of CardFilter for filtering cards based on a specific level.
 */
public class LevelFilter implements CardFilter {
    private final String selectedLevel;
    private static final Map<String, String> levelMapping = Map.of(
            "ALL", "ALL",
            "Beginner", "B",
            "Advanced Beginner", "AB",
            "Intermediate", "I",
            "Advanced", "A"
    );

    /**
     * Constructs a LevelFilter with the specified level.
     *
     * @param selectedLevel The level to filter by.
     */

    public LevelFilter(String selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    /**
     * Gets the full names of all available levels.
     *
     * @return A set of full level names.
     */
    public static Set<String> getFullLevelNames() {
        return levelMapping.keySet();
    }

    /**
     * Filters the list of cards based on the selected level.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByLevel(cards);
    }

    private List<Card> filterByLevel(List<Card> cards) {
        if (selectedLevel != null && !selectedLevel.equals("ALL")) {

            return cards.stream()
                    .filter(card -> matchesAnyLevel(card.getLevels()))
                    .collect(Collectors.toList());
        } else {
            return cards;
        }
    }

    /**
     * Checks if any of the specified levels match the selected level.
     *
     * @param cardLevels The list of levels associated with a card.
     * @return {@code true} if any level matches the selected level, otherwise {@code false}.
     */
    private boolean matchesAnyLevel(List<String> cardLevels) {

        for (String cardLevel : cardLevels) {
            if (cardLevel.contains(levelMapping.get(selectedLevel))) {
                return true;
            }
        }
        return false;
    }

}
