 package controller;

import java.util.List;

import bookingRoom.BookingRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmController {
	
	@FXML
	Label confirmBed;
	@FXML
	Label confirmBreakfast;
	@FXML
	Label bookingDay;
	
	private static BookingRequest day = BookingRequest.getInstance();
	
	@FXML
	public void initialize(){
		confirmBed.setText(String.format("Add extra-bed %d bed = %d Baht" ,RoomController.countExtraBed(),RoomController.showExtraBed()));
		confirmBreakfast.setText(String.format("Add Breakfast %d bed = %d Baht",RoomController.countBreakfast(),RoomController.showBreakfast()));
		bookingDay.setText(String.format("You reserve %s days", day.getListFile().get(2)));
	}
	
}
