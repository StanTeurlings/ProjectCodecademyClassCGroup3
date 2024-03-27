package Domain.Class;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private String subject;
    private String introductionText;
    private List<Module> modules;

    public Course(String courseName, String subject, String introductionText) {
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.modules = new ArrayList<>();
    }

    // Getters and setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
}
