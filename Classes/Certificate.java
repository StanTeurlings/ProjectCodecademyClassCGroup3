package Classes;

import java.util.List;

public class Certificate {
    private String certificateName;
    private List<Course> courses; // Assuming a many-to-many relationship with Course

    // Constructor to create a new Certificate object
    public Certificate(String certificateName) {
        this.certificateName = certificateName;
    }

    // Getter and setter for certificateName
    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    // Getter and setter for courses
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Method to add a course to the certificate's list of courses
    public void addCourse(Course course) {
        if (this.courses != null) {
            this.courses.add(course);
        } else {
            throw new IllegalStateException("Courses list is not initialized.");
        }
    }
}
