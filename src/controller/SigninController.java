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

public class SigninController extends ViewController{
	
	@FXML
	Button signup;
	@FXML
	TextField name;
	@FXML
	PasswordField password;
	@FXML
	Button signin;
	
	private PageController open = super.getController();
	
	@FXML
	public void initialize(){
		signup.setOnAction(this::showSignUp);
		signin.setOnAction(this::signInHandle);
	}
	
	/** Show when login the user name and password */
	public void signInHandle(ActionEvent event){
		User get = new User(name.getText(), password.getText());
		signIn(get);
	}
	
	/** 
	 * @return true if user name and password correct 
	 * */
	public static void signIn(User user){
		for(User u : User.getMember()){
			if(u.equals(user)){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Member");
				alert.setContentText(String.format("User: %s", user.getName()));
				alert.showAndWait();
			}
		}

	}
	
	public void showSignUp(ActionEvent event){
			open.nextPage(event,"SignUp.fxml");
	}
	
	
	

}
