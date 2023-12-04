package edu.augustana;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LevelFilter implements CardFilter {
    private final String selectedLevel;
    private static final Map<String, String> levelMapping = Map.of(
            "ALL", "ALL",
            "Beginner", "B",
            "Advanced Beginner", "AB",
            "Intermediate", "I",
            "Advanced", "A"
    );;

    public LevelFilter(String selectedLevel) {

        this.selectedLevel = selectedLevel ;


    }

    public static Set<String> getFullLevelNames() {
        return levelMapping.keySet();
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByLevel(cards);
    }

    private List<Card> filterByLevel(List<Card> cards) {
        if (selectedLevel != null && !selectedLevel.equals("ALL")) {

            return cards.stream()
                    .filter(card -> matchesAnyLevel(card.getLevels()))
                    .collect(Collectors.toList());
        }else{
            return cards;
        }
    }

    private boolean matchesAnyLevel(List<String> cardLevels) {

            for (String cardLevel : cardLevels) {
                if (cardLevel.contains(levelMapping.get(selectedLevel))) {
                    return true;
                }
            }

        return false;
    }


}
