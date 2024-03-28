package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Domain.Class.Course;
import Domain.Class.Student;
import Domain.Enummeration.Difficulty;
import Domain.Enummeration.Gender;

public class DatabaseHelper {

    public static Student fetchStudentFromDatabase(String email) {
        Student student = null;
        String query = "SELECT * FROM Student WHERE StudentEmail = ?";

        try (Connection conn = DatabaseConnector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student(
                            rs.getString("StudentEmail"),
                            rs.getString("StudentName"),
                            rs.getDate("Birthdate"),
                            Gender.valueOf(rs.getString("Gender")),
                            rs.getString("Address"),
                            rs.getString("City"),
                            rs.getString("Country"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public static Course fetchCourseFromDatabase(String name) {
        Course course = null;
        String query = "SELECT * FROM Course WHERE CourseName = ?";

        try (Connection conn = DatabaseConnector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    course = new Course(
                            rs.getString("CourseName"),
                            rs.getString("CourseSubject"),
                            rs.getString("IntroductionText"),
                            // Assuming you have a method to retrieve Difficulty based on a string
                            Difficulty.valueOf(rs.getString("Difficulty")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }
}
