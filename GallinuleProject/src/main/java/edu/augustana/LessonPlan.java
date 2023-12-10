package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.PopupControl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class LessonPlan implements Serializable {
    private String title;
    private final List<Card> savedCards;
    private final HashMap<String, List<String>> lessonMap = new HashMap<>();
    private static final List<LessonPlan> allLessonPlans = new ArrayList<>();
    private boolean textOnlyPrint;


    /**
     * Creates a LessonPlan with the specified title.
     *
     * @param title The title of the LessonPlan.
     */
    public LessonPlan(String title) {
        savedCards = new ArrayList<>();
        this.title = title;
    }


    /**
     * Creates a LessonPlan with a default title "Untitled".
     */
    public LessonPlan(){
        savedCards = new ArrayList<>();
        this.title = "Untitled";
    }

    /**
     * Sets the title of the LessonPlan.
     *
     * @param title The new title for the LessonPlan.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Returns the cards grouped by event in a TreeMap.
     *
     * @return A TreeMap where keys are event names and values are the associated cards.
     */
    public TreeMap<String, Card> getCardsGroupedByEvent(){
        return null;
    }

    /**
     * Saves a new card to the LessonPlan.
     *
     * @param newCard The card to be saved.
     */
    public void saveCard(Card newCard){
        savedCards.add(newCard);
    }

    /**
     * Sets whether the LessonPlan is for text-only printing.
     *
     * @param change The flag indicating whether text-only printing is enabled.
     */
    public void setTextOnlyPrint(boolean change){
        textOnlyPrint = change;
    }

    /**
     * Returns whether the LessonPlan is for text-only printing.
     *
     * @return True if text-only printing is enabled, false otherwise.
     */
    public boolean getTextOnly(){
        return textOnlyPrint;
    }

    /**
     * Removes a card from the LessonPlan.
     *
     * @param removeCard The card to be removed.
     */
    public void removeCard(Card removeCard){ savedCards.remove(removeCard); }


    /**
     * Returns an outline text for the LessonPlan.
     *
     * @return The outline text.
     */
    public String getOutlineText(){ return "placeholder"; }

    /**
     * Saves the LessonPlan to a file using Gson for JSON serialization.
     *
     * @param lessonPlanFile The file to save the LessonPlan.
     * @throws IOException If an error occurs while saving the LessonPlan.
     */
    public void saveToFile(File lessonPlanFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(lessonPlanFile)) {
            gson.toJson(this, writer);
        }
    }

    /**
     * Loads a LessonPlan from a file using Gson for JSON deserialization.
     *
     * @param LessonPlanFile The file to load the LessonPlan from.
     * @return The loaded LessonPlan.
     * @throws IOException If an error occurs while loading the LessonPlan.
     */
    public static LessonPlan loadFromFile(File LessonPlanFile) throws IOException {
        FileReader reader = new FileReader(LessonPlanFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,LessonPlan.class);
    }

    /**
     * Returns the lesson map associated with the LessonPlan.
     *
     * @return The lesson map.
     */
    public HashMap<String, List<String>> getLessonMap() {
        return lessonMap;
    }

    /**
     * Returns the list of saved cards in the LessonPlan.
     *
     * @return The list of saved cards.
     */
    public List<Card> getSavedCards(){ return savedCards; }

    /**
     * Returns the title of the LessonPlan.
     *
     * @return The title.
     */
    public String getTitle(){ return title; }

    /**
     * Renames the LessonPlan with a new title.
     *
     * @param newTitle The new title for the LessonPlan.
     */
    public void renameLesson(String newTitle){
        title = newTitle;
    }

    /**
     * Returns a list of all LessonPlans.
     *
     * @return The list of all LessonPlans.
     */
    public static List<LessonPlan> getAllLessonPlans(){
        return allLessonPlans;
    }

    /**
     * Returns the string representation of the LessonPlan (its title).
     *
     * @return The title of the LessonPlan.
     */
    @Override
    public String toString() {
        return getTitle();
    }
}
