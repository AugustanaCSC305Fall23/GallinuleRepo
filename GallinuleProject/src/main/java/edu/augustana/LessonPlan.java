package edu.augustana;

import javafx.scene.control.PopupControl;

import java.util.List;
import java.util.TreeMap;
import java.lang.Object;
import javafx.stage.Window;
import javafx.stage.PopupWindow;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Tooltip;

public class LessonPlan extends PopupControl {

    private List<String> cardIDs;

    private String title;

    public LessonPlan(String[] csvRowData) {
        super();
    }

    public TreeMap<String, Card> getCardsGroupedByEvent(){
        return null;
    }

    public void addCard(Card newCard){

    }

    public String getOutlineText(){ return "placeholder"; }
}
