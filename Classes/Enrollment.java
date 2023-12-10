package Classes;

import java.time.LocalDate;

public class Enrollment {
    // variables
    private Student student;
    private Course course;
    private LocalDate enrollmenDate;

    // constructor
    public Enrollment(Student student, Course course, LocalDate enrollmenDate) {
        this.student = student;
        this.course = course;
        this.enrollmenDate = enrollmenDate;
    }

    // getters
    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrollmenDate() {
        return enrollmenDate;
    }

    public Student getStudent() {
        return student;
    }

    // setters
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEnrollmenDate(LocalDate enrollmenDate) {
        this.enrollmenDate = enrollmenDate;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
