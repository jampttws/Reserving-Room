package bookingRoom;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageController {
	private Stage primaryStage;
	
	public PageController(Stage stage) {
		this.primaryStage = stage;
	}

	public void openPage(String fxmlname) {
		URL url = getClass().getResource(fxmlname);
		FXMLLoader loader = new FXMLLoader(url);
		Parent root;
		try {
			root = loader.load();
			Object controller = loader.getController();
			if (controller instanceof ViewController) {
				((ViewController)controller).setController(this);
			}
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			System.out.println("Can't connect fxml");
		}	
	}
	
	public void nextPage(ActionEvent event,String namefile) {
		URL url = getClass().getResource(namefile);
		FXMLLoader loader = new FXMLLoader(url);
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("Can't connect fxml");
		}
		
		
	}

}
