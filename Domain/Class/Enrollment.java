package Domain.Class;

import java.time.LocalDate;

import Database.DatabaseHelper;

public class Enrollment {
    private Student student;
    private String studentEmail; // Add studentEmail property
    private Course course;
    private String courseName;
    private LocalDate enrollmentDate;
    private int certificateId;
    private String grade;
    private String employeeName;
    private String certificateName;

    public Enrollment(Student student, Course course, LocalDate enrollmentDate, int certificateId, String grade,
            String employeeName) {
        this.student = student;
        this.studentEmail = student.getStudentEmail(); // Initialize studentEmail from student object
        this.course = course;
        this.courseName = course.getCourseName();
        this.enrollmentDate = enrollmentDate;
        this.certificateId = certificateId;
        this.grade = grade;
        this.employeeName = employeeName;
    }

    public Enrollment(String studentEmail, String courseName, String certificateName, String enrollmentDateStr,
            int certificateId, String grade, String employeeName) {
        // Fetch Student and Course objects from the database based on the provided
        // email and course name
        Student student = DatabaseHelper.fetchStudentFromDatabase(studentEmail);
        Course course = DatabaseHelper.fetchCourseFromDatabase(courseName);

        // Parse enrollmentDate from string to LocalDate
        LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr);

        // Create the Enrollment object
        this.student = student;
        this.studentEmail = studentEmail; // Initialize studentEmail from provided parameter
        this.course = course;
        this.courseName = courseName;
        this.certificateName = certificateName;
        this.enrollmentDate = enrollmentDate;
        this.certificateId = certificateId;
        this.grade = grade;
        this.employeeName = employeeName;
    }

    // Getters and setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }
}
