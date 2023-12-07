package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AllCourseList {
    public static final String ALLCOURSE_FILENAME = "AllCourse.json";
    private static List<String> allCourses = new ArrayList<>();

    public void addCourse(LessonPlan plan) {
        allCourses.add(plan.getTitle());
    }

    public void removeCourse(LessonPlan plan) {
        allCourses.remove(plan.getTitle());
    }

    public boolean contains(LessonPlan plan) {
        return allCourses.contains(plan.getTitle());
    }

    public static List<String> getAllCourses(){
        return allCourses;
    }



    public static AllCourseList loadFromFile() throws IOException {
        FileReader reader = new FileReader(new File(ALLCOURSE_FILENAME));
        Gson gson = new Gson();
        return gson.fromJson(reader, AllCourseList.class);
    }
    //send file
    public void saveToFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String allPlansJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(new File(ALLCOURSE_FILENAME)));
        writer.println(allPlansJSON);
//        writer.flush();
        writer.close();
    }
}
