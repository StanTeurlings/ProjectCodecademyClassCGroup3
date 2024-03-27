package GUI;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class NavigationBar {

    private Stage primaryStage;

    public NavigationBar(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public MenuBar getMenuBar() {
        // Create a MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu homeMenu = new Menu("Home");
        Menu studentsMenu = new Menu("Students");
        Menu coursesMenu = new Menu("Courses");
        Menu enrollmentsMenu = new Menu("Enrollments");
        Menu certificatesMenu = new Menu("Certificates");

        // Create menu items for Home menu
        MenuItem homeMenuItem = new MenuItem("Home");
        homeMenuItem.setOnAction(e -> {
            // Navigate back to the homepage
            Homepage homepage = new Homepage();
            homepage.start(primaryStage);
        });
        homeMenu.getItems().add(homeMenuItem);

        // Create studentsMenu items
        MenuItem addStudent = new MenuItem("New Student");
        MenuItem viewStudents = new MenuItem("Students");
        viewStudents.setOnAction(e -> {
            Students studentsPage = new Students(primaryStage);
            primaryStage.setScene(studentsPage.getStudentLayout());
        });

        addStudent.setOnAction(e -> {
            AddStudent addStudentWindow = new AddStudent(primaryStage); // Pass primaryStage to AddStudent constructor
            primaryStage.setScene(addStudentWindow.getAddStudentScene());
            primaryStage.show();
        });

        MenuItem editStudent = new MenuItem("Edit Student");
        MenuItem deleteStudent = new MenuItem("Delete Student");
        studentsMenu.getItems().addAll(viewStudents, addStudent, editStudent, deleteStudent);

        // Create coursesMenu items
        MenuItem viewCourses = new MenuItem("Courses");
        viewCourses.setOnAction(e -> {
            Courses coursesPage = new Courses(primaryStage);
            primaryStage.setScene(coursesPage.getCoursesLayout());
        });
        MenuItem addCourse = new MenuItem("New Course");
        MenuItem editCourse = new MenuItem("Edit Course");
        MenuItem deleteCourse = new MenuItem("Delete Course");
        coursesMenu.getItems().addAll(viewCourses, addCourse, editCourse, deleteCourse);

        // Create enrollmentsMenu items
        MenuItem viewEnrollments = new MenuItem("Enrollments");
        viewEnrollments.setOnAction(e -> {
            Enrollments enrollmentsPage = new Enrollments(primaryStage);
            primaryStage.setScene(enrollmentsPage.getEnrollmentsLayout());
        });
        MenuItem addEnrollment = new MenuItem("New Enrollment");
        MenuItem editEnrollment = new MenuItem("Edit Enrollment");
        MenuItem deleteEnrollment = new MenuItem("Delete Enrollment");
        enrollmentsMenu.getItems().addAll(viewEnrollments, addEnrollment, editEnrollment, deleteEnrollment);

        // Create certificatesMenu items
        MenuItem viewCertificates = new MenuItem("Certificates");
        viewCertificates.setOnAction(e -> {
            Certificates certificatesPage = new Certificates(primaryStage);
            primaryStage.setScene(certificatesPage.getCertificatesLayout());
        });
        MenuItem addCertificate = new MenuItem("New Certificate");
        MenuItem editCertificate = new MenuItem("Edit Certificate");
        MenuItem deleteCertificate = new MenuItem("Delete Certificate");
        certificatesMenu.getItems().addAll(viewCertificates, addCertificate, editCertificate, deleteCertificate);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(homeMenu, studentsMenu, coursesMenu, enrollmentsMenu, certificatesMenu);

        return menuBar;
    }
}
