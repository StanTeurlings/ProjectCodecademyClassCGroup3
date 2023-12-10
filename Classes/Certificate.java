package Classes;

public class Certificate {
    // variables
    private int certificateID;
    private double grade;
    private String employeeName;

    // constructor
    public Certificate(int certificateID, double grade, String employeeName) {
        this.certificateID = certificateID;
        this.grade = grade;
        this.employeeName = employeeName;
    }

    // getters
    public int getCertificateID() {
        return certificateID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getGrade() {
        return grade;
    }

    // setters
    public void setCertificateID(int certificateID) {
        this.certificateID = certificateID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
