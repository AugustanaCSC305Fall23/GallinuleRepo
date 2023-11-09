package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class CardSelectToPreview {
    private ArrayList<Card> cardSelections;

    public CardSelectToPreview() {
        cardSelections = new ArrayList<>();
    }

    public void removeCardSelection(Card selectedCard) {
        cardSelections.remove(selectedCard);
    }

    public void addCardSelection(Card selectedCard) {
        cardSelections.add(selectedCard);
    }

    public List<Card> getMovieWatchRecords() {
        return cardSelections;
    }

}
