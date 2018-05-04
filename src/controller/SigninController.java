package controller;

import bookingRoom.PageController;
import bookingRoom.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SigninController extends ViewController{
	@FXML
	Button signup;
	
	private PageController open = super.getController();
	
	@FXML
	public void initialize(){
		signup.setOnAction(this::showSignUp);
	}
	
	public void showSignUp(ActionEvent event){
			open.nextPage(event,"SignUp.fxml");
	}
	

}
