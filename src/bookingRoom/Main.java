package bookingRoom;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = getClass().getResource("selectDate.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Reserving Room");
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
