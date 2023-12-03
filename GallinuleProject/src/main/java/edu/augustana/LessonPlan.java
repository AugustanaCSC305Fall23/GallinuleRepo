package edu.augustana;

import javafx.scene.control.PopupControl;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LessonPlan extends PopupControl implements Serializable {
    private ArrayList<Card> cardSelections;
    private String title;
    private ArrayList<String> events;

    private List<Card> savedCards;

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


    public String getOutlineText(){ return "placeholder"; }

    public static void savePlan(LessonPlan plan) {
        String fileName = plan.title+".gymCourse";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(plan);
            System.out.println("Course saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving the course to " + fileName);
        }
    }

    public static LessonPlan loadPlan(String fileName){
        LessonPlan plan = null;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream((fileName)));
            plan = (LessonPlan) input.readObject();

        } catch(IOException e){
            System.err.println("Error opening the course " + fileName);
        } catch(ClassNotFoundException cnfe){
            System.err.println("Object read is not a LessonPlan");

        }
        return plan;
    }
    public void renameLesson(String newName){
        title = newName;
    }
    public List<Card> getSavedCards(){ return savedCards; }
    public String getTitle(){ return title; }
}
