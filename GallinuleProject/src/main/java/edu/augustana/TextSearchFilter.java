package edu.augustana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A filter implementation that performs text-based filtering on a list of cards.
 */
public class TextSearchFilter implements CardFilter {

    /**
     * Sets the search criteria for filtering.
     *
     * @param searchCriteria The search criteria to be applied.
     */
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    private final List<Card> allCards;
    private String searchCriteria;

    /**
     * Constructs a TextSearchFilter with the specified list of cards and search criteria.
     *
     * @param allCards       The list of all cards to be filtered.
     * @param searchCriteria The initial search string sent by the user for filtering.
     */
    public TextSearchFilter(List<Card> allCards, String searchCriteria) {
        this.allCards = allCards;
        this.searchCriteria = searchCriteria;
    }

    /**
     * Filters the provided list of cards based on the current search criteria.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    @Override
    public List<Card> filter(List<Card> cards) {
        return filterBySearchCriteria(cards);
    }

    /**
     * Searches and filters cards based on the specified search criteria.
     *
     * @param searchCriteria The search criteria for filtering.
     * @return The filtered list of cards.
     */
    public List<Card> search(String searchCriteria) {
        String[] keywords = searchCriteria.split(",");
        List<Card> filteredCards = new ArrayList<>(allCards);

        for (String keyword : keywords) {
            filteredCards = applyFilter(filteredCards, keyword.trim());
        }

        return filteredCards;
    }

    private List<Card> applyFilter(List<Card> cards, String filter) {
        if (searchCriteria.isEmpty()) {
            return cards; // Show all cards if the search field is empty
        } else {
            return cards.stream()
                    .filter(card -> cardContainsProperty(card, filter))
                    .collect(Collectors.toList());
        }
    }

    private boolean cardContainsProperty(Card card, String filter) {
        String filterLowerCase = filter.toLowerCase();
        List<String> propertiesToSearch = Arrays.asList(
                card.getCode(),
                card.getEvent(),
                card.getCategory(),
                card.getTitle(),
                card.getPackFolder(),
                card.getImage(),
                card.getGender(),
                card.getModelSex()
        );

        List<List<String>> listPropertiesToSearch = Arrays.asList(
                card.getLevels(),
                card.getEquipments(),
                card.getKeywords()
        );

        return propertiesToSearch.stream().anyMatch(prop -> prop.toLowerCase().contains(filterLowerCase))
                || listPropertiesToSearch.stream()
                .flatMap(List::stream)
                .anyMatch(item -> item.toLowerCase().contains(filterLowerCase));
    }

    private List<Card> filterBySearchCriteria(List<Card> cards) {
        return search(searchCriteria);
    }
}
