package edu.augustana;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CardFilter for filtering cards based on a specific gender.
 */
public class GenderFilter implements CardFilter {

    private final String selectedGender;

    /**
     * Constructs a GenderFilter with the specified gender.
     *
     * @param selectedGender The gender to filter by.
     */
    public GenderFilter(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    /**
     * Maps the selected gender to its corresponding code character.
     *
     * @param selectedGender The selected gender.
     * @return The code character corresponding to the selected gender.
     */
    private String getGenderCodeChar(String selectedGender) {

        if ("Male".equals(selectedGender)) {
            return "M";
        } else if ("Female".equals(selectedGender)) {
            return "F";
        } else if ("Neutral".equals(selectedGender)) {
            return "N";
        } else {
            return null; // Return null for other cases or "All"
        }
    }

    /**
     * Filters the list of cards based on the selected gender.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByGender(cards);
    }

    private List<Card> filterByGender(List<Card> cards) {
        String genderCodeChar = getGenderCodeChar(selectedGender);
        if (genderCodeChar != null) {
            return cards.stream()
                    .filter(card -> genderCodeChar.equalsIgnoreCase(card.getGender()) || "N".equalsIgnoreCase(selectedGender))
                    .collect(Collectors.toList());
        } else {
            return cards;
        }
    }
}

