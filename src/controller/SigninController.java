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

/**
 * Controller for Signin UI that use to sign in. 
 * @author Narisa and Tanasorn
 *
 */
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
	public static boolean checkMember = false;
	
	@FXML
	public void initialize(){
		signup.setOnAction(this::showSignUp);
		signin.setOnAction(this::signInHandle);
	}
	
	/** Show when login the user name and password */
	public void signInHandle(ActionEvent event){
		User get = new User(name.getText(), User.setPasscode(password.getText()));
		if(signIn(get)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Member");
			alert.setContentText(String.format("User: %s", get.getName()));
			alert.showAndWait();
			checkMember = true;
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Member");
			alert.setContentText(String.format("Please check username and password again"));
			alert.showAndWait();
		}
	}
	
	
	/** 
	 * @return true if user name and password correct 
	 * */
	public static boolean signIn(User user){
		for(User u : User.getMember()){
			if(user.equals(u)){
				return true;
			}
		}
		return false;
	}
	

	
	public void showSignUp(ActionEvent event){
			open.nextPage(event,"SignUp.fxml");
	}

	
	

}
