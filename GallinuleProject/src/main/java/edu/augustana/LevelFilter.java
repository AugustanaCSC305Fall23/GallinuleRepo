package edu.augustana;

import java.util.List;
import java.util.stream.Collectors;

public class LevelFilter implements CardFilter {

    private final String selectedLevel;

    public LevelFilter(String selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByLevel(cards);
    }

    private List<Card> filterByLevel(List<Card> cards) {
        return cards.stream()
                .filter(card -> card.getLevel().contains(selectedLevel))
                .collect(Collectors.toList());
    }
}
