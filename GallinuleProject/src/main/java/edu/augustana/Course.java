package edu.augustana;


import java.io.*;
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

    public void setLessons(List<LessonPlan> lessons) {
        this.lessons = lessons;
    }


    public static void saveCourse(Course course) {
        String fileName = course.getTitle()+".gymCourse";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(course);
            System.out.println("Course saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving the course to " + fileName);
        }
    }

    public static Course loadCourse(String fileName){
        Course course = null;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream((fileName)));
            course = (Course) input.readObject();

        } catch(IOException e){
            System.err.println("Error opening the course " + fileName);
        } catch(ClassNotFoundException cnfe){
            System.err.println("Object read is not a Course");

        }
        return course;
    }

}

