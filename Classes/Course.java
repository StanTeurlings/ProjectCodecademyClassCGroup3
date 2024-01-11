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

    // Constructor to create a new Course object
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

    // Getters and setters for courseName
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Getters and setters for courseSubject
    public String getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    // Getters and setters for introductionText
    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    // Getters and setters for difficulty
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Getters and setters for students
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Method to add a student to the course
    public void addStudent(Student student) {
        this.students.add(student);
    }

    // Getters and setters for contentList
    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    // Method to add content to the course
    public void addContent(Content content) {
        this.contentList.add(content);
    }

    // Getters and setters for certificates
    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    // Method to add a certificate to the course
    public void addCertificate(Certificate certificate) {
        this.certificates.add(certificate);
    }

    // Getters and setters for recommendedCourses
    public List<Course> getRecommendedCourses() {
        return recommendedCourses;
    }

    public void setRecommendedCourses(List<Course> recommendedCourses) {
        this.recommendedCourses = recommendedCourses;
    }

    // Method to add a recommended course
    public void addRecommendedCourse(Course course) {
        this.recommendedCourses.add(course);
    }
}
