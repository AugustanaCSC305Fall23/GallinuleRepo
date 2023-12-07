package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PopupControl;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class LessonPlan extends PopupControl implements Serializable {
    private String title;
    private List<Card> savedCards;
    private HashMap<String, List<String>> lessonMap = new HashMap<>();
    private static List<LessonPlan> allLessonPlans = new ArrayList<>();

    public LessonPlan(String title) {

        savedCards = new ArrayList<>();

        this.title = title;
    }

    public LessonPlan(){
        savedCards = new ArrayList<>();
        this.title = "Untitled";
    }

    public TreeMap<String, Card> getCardsGroupedByEvent(){
        return null;
    }

    public void saveCard(Card newCard){
        savedCards.add(newCard);
    }

    public static List<LessonPlan> getAllLessonPlans() {
        return allLessonPlans;
    }

    public void removeCard(Card removeCard){ savedCards.remove(removeCard); }


    public String getOutlineText(){ return "placeholder"; }

    public void saveToFile(File lessonPlanFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(lessonPlanFile)) {
            gson.toJson(this, writer);
        }
    }

    public static LessonPlan loadFromFile(File LessonPlanFile) throws IOException {
        FileReader reader = new FileReader(LessonPlanFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,LessonPlan.class);
    }

    public java.util.HashMap<String, List<String>> getLessonMap() {
        return lessonMap;
    }

    public void renameLesson(String newName){
        title = newName;
    }
    public List<Card> getSavedCards(){ return savedCards; }
    public String getTitle(){ return title; }
}
