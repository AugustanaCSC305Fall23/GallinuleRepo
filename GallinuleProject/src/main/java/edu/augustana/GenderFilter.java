package edu.augustana;

import java.util.List;
import java.util.stream.Collectors;

public class GenderFilter implements CardFilter {

    private final String selectedGender;

    public GenderFilter(String selectedGender) {
        this.selectedGender = selectedGender;
    }

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

