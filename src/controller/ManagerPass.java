package controller;

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
	
	@FXML
	public void initialize(){
		enter.setOnAction(this::handleText);
	}
	
	public void handleText(ActionEvent event){
		if(userAdmin.getText().equals("admin")&&password.getText().equals("admin")){
			open.nextPage(event, "Manager.fxml");
		}
	}
	
	
}
