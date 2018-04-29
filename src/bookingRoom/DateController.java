package bookingRoom;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


/**
 * Control the date and collect data of costumer reserving.
 * @author Narisa Singngam
 *
 */
public class DateController extends ViewController {
	
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
	@FXML
	Button signup;
	@FXML
	TextField totalDate;
	
	private PageController open = super.getController();
	private LocalDate now;
	private DateManage date;
	private Alert alert;
	
	@FXML
	public void initialize(){
		search.setOnAction(this::showRoom);
		signin.setOnAction(this::showSignin);
		signup.setOnAction(this::showSignup);
		totalDate.setOnAction(this::handleDate);
		arrive.setOnAction(this::warnDate);
		departure.setOnAction(this::warnDate);
	}
	
	/** collect all data in this fxml */
	public void collect(){
		date = new DateManage(arrive.getValue(), departure.getValue());
		int numAdult = Integer.parseInt(adult.getText().trim());
		int numChildren = Integer.parseInt(children.getText().trim());
		CollectData data = new CollectData(date.getCheckin(),date.getCheckout(), date.days(), numAdult, numChildren);
		data.add();
			
	}
	
	/** Show date that costumer can reserve. */
	public void handleDate(ActionEvent event){
		date = new DateManage(arrive.getValue(), departure.getValue());
		totalDate.setText(String.format("%d days", date.days()));	
	}
	
	/**Show when choose the past date */
	public void warnDate(ActionEvent event){
		now = LocalDate.now();
		date = new DateManage(arrive.getValue(), departure.getValue());
		if(now.isAfter(date.getCheckin()) || now.isAfter(date.getCheckout())){
		alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Please check your reserve date again");
		alert.showAndWait();
		}	
	}
	
	/** Show in another fxml. */
	public void showRoom(ActionEvent event){
		open.openPage("SelectRoom.fxml");
		collect();
	}
	
	/** Show in another fxml. */
	public void showSignin(ActionEvent event){
		open.openPage("Signin.fxml");
	}
	
	/** Show in another fxml. */
	public void showSignup(ActionEvent event){
		open.openPage("SignUp.fxml");
	}
}
