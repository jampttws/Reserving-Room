package controller;

import bookingRoom.ConfigFileManager;
import bookingRoom.PageController;
import bookingRoom.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ManagerPass extends ViewController{

	@FXML
	TextField userAdmin;
	@FXML
	PasswordField password;
	@FXML
	Button enter;
	
	private PageController open = super.getController();
	private static ConfigFileManager cf = ConfigFileManager.getInstance();
	private String USER = cf.getProperty("user.name");
	private String PASS = cf.getProperty("password.pass");
	
	@FXML
	public void initialize(){
		enter.setOnAction(this::handleText);
	}
	
	public void handleText(ActionEvent event){
		if(userAdmin.getText().equals(USER)&&password.getText().equals(PASS)){
			open.nextPage(event, "Manager.fxml");
		}
	}
	
	
}
