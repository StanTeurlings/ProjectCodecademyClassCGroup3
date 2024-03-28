package Domain.Class;

import java.util.ArrayList;
import java.util.List;

import Domain.Enummeration.Difficulty;

public class Course {
    private String courseName;
    private String courseSubject;
    private String introductionText;
    private Difficulty difficulty;

    private List<Module> modules;

    public Course(String courseName, String courseSubject, String introductionText, Difficulty difficulty) {
        this.courseName = courseName;
        this.courseSubject = courseSubject;
        this.introductionText = introductionText;
        this.difficulty = difficulty;
        this.modules = new ArrayList<>();
    }

    // Getters and setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return courseName;
    }

}
