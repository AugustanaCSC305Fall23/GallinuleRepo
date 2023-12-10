package edu.augustana;

import edu.augustana.Card;

import java.util.List;

/**
 * Interface representing a card filter.
 */
public interface CardFilter {
    /**
     * Filters a list of cards based on specific criteria.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    List<Card> filter(List<Card> cards);
}

