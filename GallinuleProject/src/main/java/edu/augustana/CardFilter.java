package edu.augustana;

import edu.augustana.Card;

import java.util.List;

public interface CardFilter {
    List<Card> filter(List<Card> cards);
}

