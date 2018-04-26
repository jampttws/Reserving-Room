package bookingRoom;


import java.io.IOException;

import javafx.event.ActionEvent;

public class SigninController {
	
	private OpenPage open = new OpenPage();
	
	public void showSignUp(ActionEvent event) throws IOException{
		open.nextPage(event,"SignUp.fxml");
	}
	

}
