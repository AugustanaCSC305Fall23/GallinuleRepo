package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AllPlansList implements Serializable {

    public static final String ALLPLANS_FILENAME = "AllPlans.json";
    private static List<String> allLessonPlans = new ArrayList<>();

    public void addPlan(LessonPlan plan) {
        allLessonPlans.add(plan.getTitle());
    }

    public void removePlan(LessonPlan plan) {
        allLessonPlans.remove(plan.getTitle());
    }

    public boolean contains(LessonPlan plan) {
        return allLessonPlans.contains(plan.getTitle());
    }

    public static List<String> getAllLessonPlans(){
        return allLessonPlans;
    }



    public static AllPlansList loadFromFile() throws IOException {
        FileReader reader = new FileReader(new File(ALLPLANS_FILENAME));
        Gson gson = new Gson();
        return gson.fromJson(reader, AllPlansList.class);
    }
//send file
    public void saveToFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String allPlansJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(new File(ALLPLANS_FILENAME)));
        writer.println(allPlansJSON);
//        writer.flush();
        writer.close();
    }
}
