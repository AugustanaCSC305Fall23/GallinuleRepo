package edu.augustana;


import java.util.List;
import java.io.*;

public class Course {
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

    public void setLessons(List<LessonPlan> lessons) {
        this.lessons = lessons;
    }



}

