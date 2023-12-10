package edu.augustana;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A filter implementation for filtering cards based on the model's sex .
 */
public class ModelSexFilter implements CardFilter {
    private final String selectedModelSex;

    /**
     * Constructs a ModelSexFilter with the specified model's sex.
     *
     * @param selectedModelSex The model's sex to filter by.
     */
    public ModelSexFilter(String selectedModelSex) {
        this.selectedModelSex = getGenderMapping(selectedModelSex);
    }

    /**
     * Filters the list of cards based on the selected model's sex.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByModelSex(cards);
    }

    private List<Card> filterByModelSex(List<Card> cards) {

        if (selectedModelSex != null && !selectedModelSex.isEmpty() && !selectedModelSex.contains("All")) {
            return cards.stream()
                    .filter(card -> card.getModelSex().equalsIgnoreCase(selectedModelSex))
                    .collect(Collectors.toList());
        } else {
            return cards;
        }

    }

    /**
     * Maps the selected model's sex to its corresponding code character.
     *
     * @param selectedModelSex The selected model's sex.
     * @return The code character corresponding to the selected model's sex.
     */
    private String getGenderMapping(String selectedModelSex) {
        if ("Male".equals(selectedModelSex)) {
            return "M";
        } else if ("Female".equals(selectedModelSex)) {
            return "F";
        } else if ("Neutral".equals(selectedModelSex)) {
            return "N";
        } else {
            return null; // Return null for other cases or "All"
        }
    }
}