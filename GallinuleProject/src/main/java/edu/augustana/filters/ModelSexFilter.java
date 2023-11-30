package edu.augustana.filters;

import edu.augustana.data.Card;

import java.util.List;
import java.util.stream.Collectors;

public class ModelSexFilter implements CardFilter {
    private final String selectedModelSex;

    public ModelSexFilter(String selectedModelSex) {
        this.selectedModelSex = getGenderMapping(selectedModelSex);
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByModelSex(cards);
    }

    private List<Card> filterByModelSex(List<Card> cards) {
        return cards.stream()
                .filter(card -> card.getModelSex().equalsIgnoreCase(selectedModelSex))
                .collect(Collectors.toList());
    }

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