package Domain.Class;

import java.util.ArrayList;
import java.util.List;

public class RecommendedCourse {
    private List<Course> recommendedCourses;

    public RecommendedCourse() {
        this.recommendedCourses = new ArrayList<>();
    }

    // Getters and setters
    public List<Course> getRecommendedCourses() {
        return new ArrayList<>(recommendedCourses);
    }

    public void addRecommendedCourse(Course course) {
        this.recommendedCourses.add(course);
    }
}
