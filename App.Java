package DoodleJump;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
public class App extends Application {

    @Override
	public void start(Stage stage) {
		// Create top-level object, set up the scene, and show the stage here.
    PaneOrganizer organizer = new PaneOrganizer();
    Scene scene = new Scene(organizer.getRoot());
    stage.setScene(scene);
    stage.setTitle("DoodleJump!");
    stage.show();
  }

	public static void main(String[] argv) {
        	// launch is a method inherited from Application
		launch(argv);
	}
}
