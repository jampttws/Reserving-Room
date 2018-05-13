package controller;

import bookingRoom.PageController;
import bookingRoom.User;
import bookingRoom.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignupController extends ViewController{
	
	@FXML
	TextField name;
	@FXML
	PasswordField password;
	@FXML
	PasswordField confirmpass;
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
			open.nextPage(event,"Signin.fxml");	
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Member");
			alert.setContentText(String.format("Please check username and password again"));
			alert.showAndWait();
		}
		
	}


}
