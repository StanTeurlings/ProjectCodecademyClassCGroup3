package Domain.Class;

public class Certificate {
    private int certificateID;
    private double grade;
    private String employeeName;
    private String certificateName;

    public Certificate(int certificateID, double grade, String employeeName) {
        this.certificateID = certificateID;
        this.grade = grade;
        this.employeeName = employeeName;
    }

    public Certificate(String certificateName) {
        this.certificateName = certificateName;
    }

    // Getters and setters
    public int getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(int certificateID) {
        this.certificateID = certificateID;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
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
