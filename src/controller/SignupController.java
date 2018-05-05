package controller;

import bookingRoom.PageController;
import bookingRoom.User;
import bookingRoom.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignupController extends ViewController{
	
	@FXML
	TextField name;
	@FXML
	TextField password;
	@FXML
    TextField confirmpass;
	@FXML
	Button signup;
	
	private PageController open = super.getController();
	
	@FXML
	public void initialize(){
		signup.setOnAction(this::signUpHandle);
	}
	
	public void signUpHandle(ActionEvent event){
		if(password.getText().equals(confirmpass.getText())){
			User.addUser(name.getText(), password.getText());
		}
		open.openPage("Signin.fxml");
	}


}
