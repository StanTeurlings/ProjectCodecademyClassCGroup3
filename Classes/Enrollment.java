package Classes;

import java.util.Date;

public class Enrollment {
    private String studentEmail;
    private String courseName;
    private String certificateName;
    private Date enrollmentDate;
    private String certificateId;
    private String grade;
    private String employeeName;

    public Enrollment(String studentEmail, String courseName, String certificateName,
                      Date enrollmentDate, String certificateId, String grade, String employeeName) {
        this.studentEmail = studentEmail;
        this.courseName = courseName;
        this.certificateName = certificateName;
        this.enrollmentDate = enrollmentDate;
        this.certificateId = certificateId;
        this.grade = grade;
        this.employeeName = employeeName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
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
}