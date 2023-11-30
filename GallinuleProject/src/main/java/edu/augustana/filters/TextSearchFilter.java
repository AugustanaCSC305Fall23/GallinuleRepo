package edu.augustana.filters;

import edu.augustana.data.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextSearchFilter implements CardFilter {

    private final List<Card> allCards;

    public TextSearchFilter(List<Card> allCards) {
        this.allCards = allCards;
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterBySearchCriteria(cards);
    }

    public List<Card> search(String searchCriteria) {
        String[] keywords = searchCriteria.split(",");
        List<Card> filteredCards = new ArrayList<>(allCards);

        for (String keyword : keywords) {
            filteredCards = applyFilter(filteredCards, keyword.trim());
        }

        return filteredCards;
    }

    private List<Card> applyFilter(List<Card> cards, String filter) {
        return cards.stream()
                .filter(card -> cardContainsProperty(card, filter))
                .collect(Collectors.toList());
    }

    private boolean cardContainsProperty(Card card, String filter) {
        String filterLowerCase = filter.toLowerCase();
        List<String> propertiesToSearch = Arrays.asList(
                card.getCode(),
                card.getEvent(),
                card.getCategory(),
                card.getTitle(),
                card.getPackFolder(),
                card.getImg(),
                card.getGender(),
                card.getModelSex()
        );

        List<List<String>> listPropertiesToSearch = Arrays.asList(
                card.getLevel(),
                card.getEquipments(),
                card.getKeywords()
        );

        return propertiesToSearch.stream().anyMatch(prop -> prop.toLowerCase().contains(filterLowerCase))
                || listPropertiesToSearch.stream()
                .flatMap(List::stream)
                .anyMatch(item -> item.toLowerCase().contains(filterLowerCase));
    }

    private List<Card> filterBySearchCriteria(List<Card> cards) {
        return cards;
    }
}
