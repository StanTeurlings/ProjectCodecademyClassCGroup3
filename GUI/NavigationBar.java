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

        // Create studentsMenu items
        MenuItem addStudent = new MenuItem("New Student");
        MenuItem editStudent = new MenuItem("Edit Student");
        MenuItem deleteStudent = new MenuItem("Delete Student");
        studentsMenu.getItems().addAll(addStudent, editStudent, deleteStudent);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(homeMenu, studentsMenu);

        // Example: Link back to homepage
        homeMenu.setOnAction(e -> {
            // Assume Homepage is another scene or stage you wish to navigate to
            Homepage homepage = new Homepage();
            homepage.start(primaryStage);
        });

        return menuBar;
    }
}