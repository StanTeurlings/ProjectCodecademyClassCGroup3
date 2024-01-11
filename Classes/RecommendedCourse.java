package Classes;

public class RecommendedCourse {
    private String courseName;
    private String recommendedCourse;

    public RecommendedCourse(String courseName, String recommendedCourse) {
        this.courseName = courseName;
        this.recommendedCourse = recommendedCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRecommendedCourse() {
        return recommendedCourse;
    }

    public void setRecommendedCourse(String recommendedCourse) {
        this.recommendedCourse = recommendedCourse;
    }
}