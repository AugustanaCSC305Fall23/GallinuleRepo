package edu.augustana;


import java.util.ArrayList;
import java.util.List;

public class CombinedAndFilter implements CardFilter {

    private final CardFilter[] filters;

    public CombinedAndFilter(CardFilter... filters) {
        this.filters = filters;
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        List<Card> filteredCards = new ArrayList<>(cards);
        for (CardFilter filter : filters) {
            filteredCards.retainAll(filter.filter(cards));
        }


        return filteredCards;
    }

}