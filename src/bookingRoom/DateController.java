package bookingRoom;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DateController {
	
	@FXML
	DatePicker arrive;
	@FXML
	DatePicker departure;
	@FXML
	TextField adult;
	@FXML
	TextField children;
	@FXML
	Button search;
	@FXML
	Button signin;
	
	private OpenPage open = new OpenPage();
	
	public void showRoom(ActionEvent event) throws IOException{
		open.nextPage(event,"SelectRoom.fxml");	
	}
	
	public void showSignin(ActionEvent event) throws IOException{
		open.popPage("Signin.fxml");
	}
	
	public void showSignup(ActionEvent event) throws IOException{
		open.popPage("SignUp.fxml");
	}

}
