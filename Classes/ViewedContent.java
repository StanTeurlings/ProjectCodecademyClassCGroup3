package Classes;

public class ViewedContent {
    private String studentEmail;
    private String courseName;
    private double percentageOfViewedContent;

    public ViewedContent(String studentEmail, String courseName, double percentageOfViewedContent) {
        this.studentEmail = studentEmail;
        this.courseName = courseName;
        this.percentageOfViewedContent = percentageOfViewedContent;
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

    public double getPercentageOfViewedContent() {
        return percentageOfViewedContent;
    }

    public void setPercentageOfViewedContent(double percentageOfViewedContent) {
        this.percentageOfViewedContent = percentageOfViewedContent;
    }
}