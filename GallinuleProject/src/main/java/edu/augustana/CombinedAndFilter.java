package edu.augustana;


import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of CardFilter for combining multiple filters with logical AND operation.
 */
public class CombinedAndFilter implements CardFilter {

    private final CardFilter[] filters;

    /**
     * Constructs a CombinedAndFilter with the specified filters.
     *
     * @param filters The filters to be combined with AND operation.
     */
    public CombinedAndFilter(CardFilter... filters) {
        this.filters = filters;
    }

    /**
     * Filters the list of cards based on the combined AND operation of the specified filters.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    @Override
    public List<Card> filter(List<Card> cards) {
        List<Card> filteredCards = new ArrayList<>(cards);
        for (CardFilter filter : filters) {
            filteredCards.retainAll(filter.filter(cards));
        }
        return filteredCards;
    }

}