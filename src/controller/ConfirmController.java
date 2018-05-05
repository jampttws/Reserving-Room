 package controller;

import java.util.List;

import bookingRoom.Booked;
import bookingRoom.BookingRequest;
import bookingRoom.DatabaseManage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConfirmController {
	
	@FXML
	TextField name;
	@FXML
	TextField id;
	@FXML
	TextField call;
	@FXML
	Label confirmBed;
	@FXML
	Label confirmBreakfast;
	@FXML
	Label bookingDay;
	@FXML
	Label costRoom;
	@FXML
	Button confirm;
	
	
	private static String day = BookingRequest.getInstance().getListFile().get(2);
	private String arrive = HomeController.readfile().get(0);
	private String depart = HomeController.readfile().get(1);
	
	@FXML
	public void initialize(){
		totalCostRoom();
		confirm.setOnAction(this::confirm);
	}
	
	public void totalCostRoom(){
		int result = 0;
		int days = Integer.parseInt(day);
		
		for(int i=0 ; i <  RoomController.printRoom().size() ; i++){
			result += RoomController.printRoom().get(i);
		}
		int sum = result+RoomController.showBreakfast()+RoomController.showExtraBed();
		bookingDay.setText(String.format("You reserve %s days", day));
		confirmBreakfast.setText(String.format("Add Breakfast %d bed = %d Baht",RoomController.countBreakfast(),RoomController.showBreakfast()));
		confirmBed.setText(String.format("Add extra-bed %d bed = %d Baht" ,RoomController.countExtraBed(),RoomController.showExtraBed()));
		costRoom.setText(String.format("( %d + %d +%d ) x %d days = %d Baht",result,RoomController.showBreakfast(),RoomController.showExtraBed(),days,sum*days));
		
	}
	
	public void confirm(ActionEvent event){
		collectIndatabase("suite");
	}
	
	public void collectIndatabase(String nameRoom){
		String Name = name.getText().trim();
		switch(nameRoom){
		case("suite"):
			for(String s : RoomController.SutList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				bookingRoom.DatabaseManage.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
		break;
		case("superior"):
			for(String s : RoomController.SprList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				bookingRoom.DatabaseManage.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
		break;
		case("standard"):
			for(String s : RoomController.StdList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				bookingRoom.DatabaseManage.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
		break;
		case("deluxe"):
			for(String s : RoomController.SutList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				bookingRoom.DatabaseManage.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
		break;
		default: 
			break;
		}
		DatabaseManage.collectName(Name, Integer.parseInt(id.getText().trim()), Integer.parseInt(call.getText().trim()));
	}

	
}
