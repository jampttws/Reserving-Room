package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import bookingRoom.BookingRequest;
import bookingRoom.DateManage;
import bookingRoom.PageController;
import bookingRoom.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Control the date and collect data of customer reserving.
 * 
 * @author Narisa and Tanasorn
 *
 */
public class HomeController extends ViewController {

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
	Label totalDate;
	@FXML
	Button manager;

	private PageController open = super.getController();
	private LocalDate now;
	private DateManage date;
	private Alert alert = new Alert(AlertType.WARNING);
	private static BookingRequest book = BookingRequest.getInstance();

	@FXML
	public void initialize() {
		arrive.setEditable(false);
		departure.setEditable(false);
		search.setOnAction(this::showRoom);
		signin.setOnAction(this::showSignin);
		signup.setOnAction(this::showSignup);
		arrive.setOnAction(this::warnDate);
		departure.setOnAction(this::warnDate);
		manager.setOnAction(this::showManager);

	}

	/** collect all data in this fxml */
	public boolean collect() {

		if (adult.getText().equals(""))
			adult.setText("0");
		if (children.getText().equals(""))
			children.setText("0");

		try {
			int numAdult = Integer.parseInt(adult.getText().trim());
			int numChildren = Integer.parseInt(children.getText().trim());
			if ((numAdult <= 20) && (numChildren <= 20)) {
				book.addData(date.getCheckin(), date.getCheckout(), date.days(), numAdult, numChildren);
				return true;
			}

		} catch (Exception e) {
			alert.setHeaderText("Invalid value, please insert again");
			alert.showAndWait();
			return false;
		}

		alert.setHeaderText("People text error");
		alert.showAndWait();
		return false;

	}

	/** Show date that costumer can reserve. */
	public void handleDate() {
		if (date.days() > 0)
			totalDate.setText(String.format("%d days", date.days()));
		else
			alertDate();

	}

	/** Alert reserving date */
	public void alertDate() {
		alert.setHeaderText("Please check your reserve date again");
		alert.showAndWait();
	}

	/** Show when choose the past date */
	public void warnDate(ActionEvent event) {

		if (arrive.getValue() == null || departure.getValue() == null)
			return;
		now = LocalDate.now();
		date = new DateManage(arrive.getValue(), departure.getValue());
		if (now.isAfter(date.getCheckin()) || now.isAfter(date.getCheckout()))
			alertDate();
		handleDate();
	}

	public void showManager(ActionEvent event) {
		open.openPage("ManagerLogin.fxml");
	}

	/** Show in another fxml. */
	public void showRoom(ActionEvent event) {
		if (adult.getText().equals("") && children.getText().equals("")) {
			alert.setHeaderText("How many guests?");
			alert.showAndWait();
		} else {
			if(collect()){
			open.openPage("SelectRoom.fxml");
			Stage stage = (Stage) search.getScene().getWindow();
			stage.close();
			}
			
		}

	}

	/** Show in another fxml. */
	public void showSignin(ActionEvent event) {
		open.openPage("Signin.fxml");
	}

	/** Show in another fxml. */
	public void showSignup(ActionEvent event) {
		open.openPage("SignUp.fxml");
	}
}
