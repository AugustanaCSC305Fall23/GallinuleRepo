package edu.augustana;

import javafx.scene.control.PopupControl;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.lang.Object;
import javafx.stage.Window;
import javafx.stage.PopupWindow;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Tooltip;

public class LessonPlan extends PopupControl implements Serializable {

    private List<String> cardIDs;
    private ArrayList<Card> cardSelections;
    private String title;

    public LessonPlan() {
        cardSelections = new ArrayList<>();
    }

    public TreeMap<String, Card> getCardsGroupedByEvent(){
        return null;
    }

    public void addCard(Card newCard){

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
}
