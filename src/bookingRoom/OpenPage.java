package bookingRoom;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenPage{

	public void popPage(String namefile) throws IOException{
		URL url = getClass().getResource(namefile);
		FXMLLoader loader = new FXMLLoader(url);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();	
	}
	
	public void nextPage(ActionEvent event,String namefile) throws IOException{
		URL url = getClass().getResource(namefile);
		FXMLLoader loader = new FXMLLoader(url);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

}
