package edu.augustana;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String title ;
    private List<LessonPlan> lessons;

    public Course(List<LessonPlan> lessons) {
        this.title = "Untitled";
        this.lessons = lessons;
    }
    public Course(String title, List<LessonPlan> lessons) {
        this.title = title;
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LessonPlan> getLessons() {
        return lessons;
    }

    public List<String> getAllPlanTitles(){
        List<String> allTitles= new ArrayList<>();
        for (LessonPlan plan: lessons){
            allTitles.add(plan.getTitle());
        }
        return allTitles;
    }

    public void setLessons(List<LessonPlan> lessons) {
        this.lessons = lessons;
    }

    public void addPlan(LessonPlan plan) {
        lessons.add(plan);
    }

    public void removePlan(LessonPlan lessonPlan) {
        lessons.remove(lessonPlan);
    }


    public void saveCourse( File fileToSave) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String courseJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(fileToSave));
        writer.println(courseJSON);
        writer.close();

    }

    public static Course loadCourse(File fileToLoad) throws FileNotFoundException {
        FileReader reader = new FileReader(fileToLoad);
        Gson gson = new Gson();
        return gson.fromJson(reader, Course.class);
    }

}

