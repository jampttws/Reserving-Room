package controller;

import bookingRoom.PageController;
import bookingRoom.User;
import bookingRoom.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SigninController extends ViewController{
	
	@FXML
	Button signup;
	@FXML
	TextField name;
	@FXML
	TextField password;
	@FXML
	Button signin;
	
	private PageController open = super.getController();
	
	@FXML
	public void initialize(){
		signup.setOnAction(this::showSignUp);
		signin.setOnAction(this::signInHandle);
	}
	
	public void signInHandle(ActionEvent event){
		User get = new User(name.getText(), password.getText());
		for(User u : User.getMember()){
			if(u.equals(get)){
				open.openPage("Home.fxml");
			}
		}
	}
	
	public void showSignUp(ActionEvent event){
			open.nextPage(event,"SignUp.fxml");
	}
	
	
	

}
