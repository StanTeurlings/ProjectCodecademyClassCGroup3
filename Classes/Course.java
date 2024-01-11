package Classes;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private String courseSubject;
    private String introductionText;
    private String difficulty;
    private List<Student> students;
    private List<Content> contentList;
    private List<Certificate> certificates;
    private List<Course> recommendedCourses;

    public Course(String courseName, String courseSubject, String introductionText, String difficulty) {
        this.courseName = courseName;
        this.courseSubject = courseSubject;
        this.introductionText = introductionText;
        this.difficulty = difficulty;
        this.students = new ArrayList<>();
        this.contentList = new ArrayList<>();
        this.certificates = new ArrayList<>();
        this.recommendedCourses = new ArrayList<>();
    }

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public void addContent(Content content) {
        this.contentList.add(content);
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public void addCertificate(Certificate certificate) {
        this.certificates.add(certificate);
    }

    public List<Course> getRecommendedCourses() {
        return recommendedCourses;
    }

    public void setRecommendedCourses(List<Course> recommendedCourses) {
        this.recommendedCourses = recommendedCourses;
    }

    public void addRecommendedCourse(Course course) {
        this.recommendedCourses.add(course);
    }
}
