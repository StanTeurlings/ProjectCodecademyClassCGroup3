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
        Menu contentsMenu = new Menu("Contents");

        // Create menu items for Home menu
        MenuItem homeMenuItem = new MenuItem("Back to Home");
        homeMenuItem.setOnAction(e -> {
            // Navigate back to the homepage
            Homepage homepage = new Homepage();
            homepage.start(primaryStage);
        });
        homeMenu.getItems().add(homeMenuItem);

        // Create studentsMenu items
        MenuItem viewStudents = new MenuItem("All Students");
        viewStudents.setOnAction(e -> {
            Students studentsPage = new Students(primaryStage);
            primaryStage.setScene(studentsPage.getStudentLayout());
        });

        MenuItem addStudent = new MenuItem("New Student");
        addStudent.setOnAction(e -> {
            AddStudent addStudentWindow = new AddStudent(primaryStage); // Pass primaryStage to AddStudent constructor
            primaryStage.setScene(addStudentWindow.getAddStudentScene());
            primaryStage.show();
        });
        studentsMenu.getItems().addAll(viewStudents, addStudent);

        // Create coursesMenu items
        MenuItem viewCourses = new MenuItem("All Courses");
        viewCourses.setOnAction(e -> {
            Courses coursesPage = new Courses(primaryStage);
            primaryStage.setScene(coursesPage.getCoursesLayout());
        });

        MenuItem addCourse = new MenuItem("New Course");
        addCourse.setOnAction(e -> {
            AddCourses addCoursesWindow = new AddCourses(primaryStage);
            primaryStage.setScene(addCoursesWindow.getAddCourseScene());
            primaryStage.show();
        });
        coursesMenu.getItems().addAll(viewCourses, addCourse);

        // Create enrollmentsMenu items
        MenuItem viewEnrollments = new MenuItem("All Enrollments");
        viewEnrollments.setOnAction(e -> {
            Enrollments enrollmentsPage = new Enrollments(primaryStage);
            primaryStage.setScene(enrollmentsPage.getEnrollmentsLayout());
        });

        MenuItem addEnrollment = new MenuItem("New Enrollment");
        addEnrollment.setOnAction(e -> {
            AddEnrollment addEnrollmentWindow = new AddEnrollment(primaryStage);
            primaryStage.setScene(addEnrollmentWindow.getAddEnrollmentScene());
            primaryStage.show();
        });

        enrollmentsMenu.getItems().addAll(viewEnrollments, addEnrollment);

        // Create certificatesMenu items
        MenuItem viewCertificates = new MenuItem("All Certificates");
        viewCertificates.setOnAction(e -> {
            Certificates certificatesPage = new Certificates(primaryStage);
            primaryStage.setScene(certificatesPage.getCertificatesLayout());
        });
        
        MenuItem addCertificate = new MenuItem("New Certificate");
        addCertificate.setOnAction(e -> {
            AddCertificate addCertificateWindow = new AddCertificate(primaryStage);
            primaryStage.setScene(addCertificateWindow.getAddCertificateScene());
            primaryStage.show();
        });

        certificatesMenu.getItems().addAll(viewCertificates, addCertificate);

        // Create contentMenu items
        MenuItem viewContents = new MenuItem("All Contents");
        viewContents.setOnAction(e -> {
            Contents contentsPage = new Contents(primaryStage);
            primaryStage.setScene(contentsPage.getContentsLayout());
        });

        MenuItem addContent = new MenuItem("New Content");
        addContent.setOnAction(e -> {
            AddContent addContentWindow = new AddContent(primaryStage);
            primaryStage.setScene(addContentWindow.getAddContentScene());
            primaryStage.show();
        });

        contentsMenu.getItems().addAll(viewContents, addContent);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(homeMenu, studentsMenu, coursesMenu, enrollmentsMenu, certificatesMenu, contentsMenu);

        return menuBar;
    }
}