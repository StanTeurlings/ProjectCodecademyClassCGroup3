package Classes;

import java.util.List;

public class Certificate {
    private String certificateName;
    private List<Course> courses;

    public Certificate(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if (this.courses != null) {
            this.courses.add(course);
        } else {
            throw new IllegalStateException("Courses list is not initialized.");
        }
    }
}
