package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a list of all courses and provides methods to manipulate and manage the list.
 */
public class AllCourseList {

    /**
     * The filename for storing the list of all courses in JSON format.
     */
    public static final String ALLCOURSE_FILENAME = "AllCourse.json";

    /**
     * The list containing titles of all courses.
     */
    private static List<String> allCourses = new ArrayList<>();

    /**
     * Adds a new course to the list.
     *
     * @param plan The LessonPlan object representing the course to be added.
     */
    public void addCourse(LessonPlan plan) {
        allCourses.add(plan.getTitle());
    }

    /**
     * Removes a course from the list.
     *
     * @param plan The LessonPlan object representing the course to be removed.
     */
    public void removeCourse(LessonPlan plan) {
        allCourses.remove(plan.getTitle());
    }

    /**
     * Checks if the list contains a specific course.
     *
     * @param plan The LessonPlan object representing the course to be checked.
     * @return true if the course is present, false otherwise.
     */
    public boolean contains(LessonPlan plan) {
        return allCourses.contains(plan.getTitle());
    }

    /**
     * Gets the list of titles of all courses.
     *
     * @return The list of all courses.
     */
    public static List<String> getAllCourses(){
        return allCourses;
    }

    /**
     * Loads the list of all courses from a JSON file.
     *
     * @return An instance of AllCourseList containing the loaded data.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public static AllCourseList loadFromFile() throws IOException {
        FileReader reader = new FileReader(new File(ALLCOURSE_FILENAME));
        Gson gson = new Gson();
        return gson.fromJson(reader, AllCourseList.class);
    }
    //send file
    /**
     * Saves the list of all courses to a JSON file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveToFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String allPlansJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(new File(ALLCOURSE_FILENAME)));
        writer.println(allPlansJSON);
//        writer.flush();
        writer.close();
    }
}