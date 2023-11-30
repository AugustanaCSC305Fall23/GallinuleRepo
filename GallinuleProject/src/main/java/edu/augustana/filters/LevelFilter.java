package edu.augustana.filters;

import edu.augustana.data.Card;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LevelFilter implements CardFilter {
    private final List<String> selectedLevels;
    private final Map<String, String> levelMapping;

    public LevelFilter(String selectedLevel) {
        this.selectedLevels = Arrays.asList(selectedLevel.split(" "));
        this.levelMapping = initializeLevelMapping();
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByLevel(cards);
    }

    private List<Card> filterByLevel(List<Card> cards) {
        return cards.stream()
                .filter(card -> matchesAnyLevel(card.getLevel()))
                .collect(Collectors.toList());
    }

    private boolean matchesAnyLevel(List<String> cardLevels) {
        for (String selectedLevel : selectedLevels) {
            for (String cardLevel : cardLevels) {
                if (cardLevel.contains(levelMapping.get(selectedLevel))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Map<String, String> initializeLevelMapping() {
        Map<String, String> mapping = Map.of(
                "ALL", "ALL",
                "Beginner", "B",
                "AB", "AB",
                "I", "I",
                "A", "A"
        );
        return mapping;
    }
}
