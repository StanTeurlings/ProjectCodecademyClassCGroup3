package Classes;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Student {
    private SimpleStringProperty studentEmail;
    private SimpleStringProperty studentName;
    private SimpleStringProperty birthdate; // Here I'm using a string to simplify, but consider using a LocalDate
    private SimpleStringProperty gender;
    private SimpleStringProperty address;
    private SimpleStringProperty city;
    private SimpleStringProperty country;

    public Student(String studentEmail, String studentName, String birthdate, String gender, String address, String city, String country) {
        this.studentEmail = new SimpleStringProperty(studentEmail);
        this.studentName = new SimpleStringProperty(studentName);
        this.birthdate = new SimpleStringProperty(birthdate);
        this.gender = new SimpleStringProperty(gender);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.country = new SimpleStringProperty(country);
    }

    // Getters
    public String getStudentEmail() {
        return studentEmail.get();
    }

    public String getStudentName() {
        return studentName.get();
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getCountry() {
        return country.get();
    }

    // Setters
    public void setStudentEmail(String value) {
        studentEmail.set(value);
    }

    public void setStudentName(String value) {
        studentName.set(value);
    }

    public void setBirthdate(String value) {
        birthdate.set(value);
    }

    public void setGender(String value) {
        gender.set(value);
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public void setCity(String value) {
        city.set(value);
    }

    public void setCountry(String value) {
        country.set(value);
    }

    // Property getters
    public SimpleStringProperty studentEmailProperty() {
        return studentEmail;
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public SimpleStringProperty birthdateProperty() {
        return birthdate;
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }
}
