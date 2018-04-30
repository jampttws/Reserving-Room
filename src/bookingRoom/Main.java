package bookingRoom;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		PageController controller = new PageController(primaryStage);
		controller.openPage("selectDate.fxml");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
