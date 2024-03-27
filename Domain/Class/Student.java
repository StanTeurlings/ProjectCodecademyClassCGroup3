package Domain.Class;

import java.util.ArrayList;

public class Student {
    private String studentEmail;
    private String studentName;
    private String birthDate; // Assuming this is a String representation of the date
    private String gender;
    private String address;
    private String city;
    private String country;
    private ArrayList<Course> watchedCourses;

    public Student(String studentEmail, String studentName, String birthDate,
            String gender, String address, String city, String country) {
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.watchedCourses = new ArrayList<>();
    }

    // Getters and setters
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<Course> getWatchedCourses() {
        return new ArrayList<>(watchedCourses);
    }

    public void watchCourse(Course course) {
        this.watchedCourses.add(course);
    }
}
