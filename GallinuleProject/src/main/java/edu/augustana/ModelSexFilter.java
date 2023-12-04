package edu.augustana;

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
        if (selectedModelSex != null && !selectedModelSex.isEmpty() && !selectedModelSex.contains("All")) {
            return cards.stream()
                    .filter(card -> card.getModelSex().equalsIgnoreCase(selectedModelSex))
                    .collect(Collectors.toList());
        } else{
            return cards;
        }
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