package edu.augustana;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String title;

    private List<LessonPlan> lessons;

    /**
     * Constructs a Course with the specified list of lesson plans.
     *
     * @param lessons The list of lesson plans.
     */
    public Course(List<LessonPlan> lessons) {
        this.title = "Untitled";
        this.lessons = lessons;
    }

    /**
     * Constructs a Course with the specified title and list of lesson plans.
     *
     * @param title   The title of the course.
     * @param lessons The list of lesson plans.
     */
    public Course(String title, List<LessonPlan> lessons) {
        this.title = title;
        this.lessons = lessons;
    }

    /**
     * Gets the title of the course.
     *
     * @return The title of the course.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the list of lesson plans in the course.
     *
     * @return The list of lesson plans.
     */
    public List<LessonPlan> getLessons() {
        return lessons;
    }

    /**
     * Gets a list of titles for all lesson plans in the course.
     *
     * @return A list of lesson plan titles.
     */
    public List<String> getAllPlanTitles() {
        List<String> allTitles = new ArrayList<>();
        for (LessonPlan plan : lessons) {
            allTitles.add(plan.getTitle());
        }
        return allTitles;
    }

    /**
     * Sets the list of lesson plans in the course.
     *
     * @param lessons The list of lesson plans to set.
     */
    public void setLessons(List<LessonPlan> lessons) {
        this.lessons = lessons;
    }

    /**
     * Adds a lesson plan to the course.
     *
     * @param plan The lesson plan to add.
     */
    public void addPlan(LessonPlan plan) {
        lessons.add(plan);
    }

    /**
     * Removes a lesson plan from the course.
     *
     * @param lessonPlan The lesson plan to remove.
     */
    public void removePlan(LessonPlan lessonPlan) {
        lessons.remove(lessonPlan);
    }


    /**
     * Saves the course to a file.
     *
     * @param fileToSave The file to save the course to.
     * @throws IOException If an error occurs while saving the course.
     */
    public void saveCourse(File fileToSave) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String courseJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(fileToSave));
        writer.println(courseJSON);
        writer.close();

    }

    /**
     * Loads a course from a file.
     *
     * @param fileToLoad The file to load the course from.
     * @return The loaded course.
     * @throws FileNotFoundException If the file is not found.
     */
    public static Course loadCourse(File fileToLoad) throws FileNotFoundException {
        FileReader reader = new FileReader(fileToLoad);
        Gson gson = new Gson();
        return gson.fromJson(reader, Course.class);
    }

}

