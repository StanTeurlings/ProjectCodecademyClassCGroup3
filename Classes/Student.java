package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    // variables
    private String studentEmail;
    private String studentName;
    private LocalDate brithDate;
    private String gender;
    private String address;
    private String city;
    private String country;
    private ArrayList<Modules> watchedModules;
    private ArrayList<Certificate> certificates;

    // constructer
    public Student(String studentEmail, String studentName, LocalDate birthDate, String gender, String address,
            String city, String country) {
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.brithDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.watchedModules = new ArrayList<>();
        this.certificates = new ArrayList<>();
    }

    // getters
    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDate getBrithDate() {
        return brithDate;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Modules> getWatchedModules() {
        return watchedModules;
    }

    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }

    // setters
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setBrithDate(LocalDate brithDate) {
        this.brithDate = brithDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWatchedModules(ArrayList<Modules> watchedModules) {
        this.watchedModules = watchedModules;
    }

    public void setCertificates(ArrayList<Certificate> certificates) {
        this.certificates = certificates;
    }
}
