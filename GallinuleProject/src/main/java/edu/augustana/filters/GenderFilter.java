package edu.augustana.filters;

import edu.augustana.data.Card;

import java.util.List;
import java.util.stream.Collectors;

public class GenderFilter implements CardFilter {

    private final String selectedGender;

    public GenderFilter(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByGender(cards);
    }

    private List<Card> filterByGender(List<Card> cards) {
        return cards.stream()
                .filter(card -> card.getGender().equalsIgnoreCase(selectedGender))
                .collect(Collectors.toList());
    }
}

