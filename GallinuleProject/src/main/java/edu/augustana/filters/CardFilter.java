package edu.augustana.filters;

import edu.augustana.data.Card;

import java.util.List;

public interface CardFilter {
    List<Card> filter(List<Card> cards);
}

