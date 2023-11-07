package edu.augustana;

import java.util.List;
import java.util.TreeMap;

public class LessonPlan extends Card {

    private List<String> cardIDs;

    private String title;

    public LessonPlan(String[] csvRowData) {
        super(csvRowData);
    }

    public TreeMap<String, Card> getCardsGroupedByEvent(){
        return null;
    }

    public void addCard(Card newCard){

    }

    public String getOutlineText(){ return "placeholder"; }
}
