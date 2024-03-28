package Domain.Class;

import java.util.ArrayList;
import java.util.Date;

import Domain.Enummeration.Gender;

public class Student {
    private String studentEmail;
    private String studentName;
    private Date birthDate;
    private Gender gender;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private ArrayList<Course> watchedCourses;

    public Student(String studentEmail, String studentName, Date birthDate,
                   Gender gender, String address, String postcode, String city, String country) {
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.watchedCourses = new ArrayList<>();
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
