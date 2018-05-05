package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bookingRoom.Booked;
import bookingRoom.BookingRequest;
import bookingRoom.DatabaseManage;
import bookingRoom.PageController;
import bookingRoom.Total;
import bookingRoom.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


/**
 * Select the room and other option in hotel
 * @author Narisa Singngam
 *
 */
public class RoomController extends ViewController{
	
	@FXML
	ComboBox numDeluxe;
	@FXML
	ComboBox numSuite;
	@FXML
	ComboBox numSupe;
	@FXML
	ComboBox numStandard;
	@FXML
	Label costumerData;
	@FXML
	Button breakfastDeluxe;
	@FXML
	Button breakfastSuite;
	@FXML
	Button breakfastSupe;
	@FXML
	Button breakfastStandard;
	@FXML
	Button bedDeluxe;
	@FXML
	Button bedSuite;
	@FXML
	Button bedSupe;
	@FXML
	Button bedStandard;
	@FXML
	Button confirm;
	@FXML
	Label deluxe;
	@FXML
	Label suite;
	@FXML
	Label superior;
	@FXML
	Label standard;
	@FXML
	Button selectDelux;
	@FXML
	Button selectSuite;
	@FXML
	Button selectSupe;
	@FXML
	Button selectStandard;
	
	private PageController open = super.getController();
	private static Total total = Total.getinstance();
	ObservableList<Integer> room = FXCollections.observableArrayList(1,2,3,4,5);
	
	@FXML
	public void initialize(){
		showData();
		setCombobox();
		confirm.setOnAction(this::showComfirmPage);
		breakfastDeluxe.setOnAction(this::addBreakfast);
		breakfastSuite.setOnAction(this::addBreakfast);
		breakfastSupe.setOnAction(this::addBreakfast);
		breakfastStandard.setOnAction(this::addBreakfast);
		bedDeluxe.setOnAction(this::addExtraBedButton);
		bedSuite.setOnAction(this::addExtraBedButton);
		bedSupe.setOnAction(this::addExtraBedButton);
		bedStandard.setOnAction(this::addExtraBedButton);
		selectDelux.setOnAction(this::selectDeluxRoom);
		selectSuite.setOnAction(this::selectSuiteRoom);
		selectSupe.setOnAction(this::selectSupeRoom);
		selectStandard.setOnAction(this::selectStandardRoom);
		
	}
	
	@FXML
	public void setCombobox(){
		numDeluxe.setItems(room);
		numDeluxe.getSelectionModel().select(0);
		numStandard.setItems(room);
		numStandard.getSelectionModel().select(0);
		numSuite.setItems(room);
		numSuite.getSelectionModel().select(0);
		numSupe.setItems(room);
		numSupe.getSelectionModel().select(0);
	}
	
	/** Open ConfirmReserving page */
	public void showComfirmPage(ActionEvent event){
		open.openPage("ConfirmReserving.fxml");	
	}
	/** Show the list of reserving day */
	public void showData(){
		List<String> readfile = HomeController.readfile();
		costumerData.setText(String.format("You reserve %s days including adult %s children %s"
				,readfile.get(2),readfile.get(3),readfile.get(4)));
		deluxe.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("dlx").size()));
		suite.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("sut").size()));
		superior.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("spr").size()));
		standard.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("std").size()));
	}

	/** Add breakfast */
	public void addBreakfast(ActionEvent event){
		total.addbreakfast();
	}
	
	private static List<String> SprList = Select("spr", 1);
	private static List<String> SutList = Select("sut", 1);
	private static List<String> StdList = Select("std", 1);
	private static List<String> DlxList = Select("dlx", 1);
	
	/** select number of room */
	public void selectSupeRoom(ActionEvent event) {
		int numRoom = (int) numSupe.getSelectionModel().getSelectedItem();
		int cost = numRoom * 2500;
		total.addCost((cost));
		total.addNameRoom("superior");
		getRoom();
		if(!canReserve("spr", numRoom)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setContentText("You cannot reserve this room on this date.");
			alert.showAndWait();
		} 
		SprList = Select("spr", numRoom);
		
	}
	
	/** Print Room in class Confirmcontroller*/
	public static List<Integer> printRoom(){
		return total.getTypeRoom();
	}
	
	 /** Print Type room in class Confirmcontroller*/
	 public static List<String> getRoom(){
		  return total.getNameRoom();
     }
	
	/** select number of room */
	public void selectStandardRoom(ActionEvent event) {
		int numRoom = (int) numStandard.getSelectionModel().getSelectedItem();
		int cost = numRoom * 2000;	
		total.addCost((cost));
		total.addNameRoom("standard");
		getRoom();
		if(!canReserve("std", numRoom)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setContentText("You cannot reserve this room on this date.");
			alert.showAndWait();
		} 
	    StdList = Select("std", numRoom);
		
	}
	

	
	/** select number of room */
	public void selectSuiteRoom(ActionEvent event){
		int numRoom = (int) numSuite.getSelectionModel().getSelectedItem();
		int cost = numRoom * 3500;
		total.addCost((cost));
		total.addNameRoom("suite");
		getRoom();
		if(!canReserve("sut", numRoom)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setContentText("You cannot reserve this room on this date.");
			alert.showAndWait();
		}
		SutList = Select("sut", numRoom);
		
	}
	
	/** select number of room */
	public void selectDeluxRoom(ActionEvent event) {
		int numRoom = (int) numDeluxe.getSelectionModel().getSelectedItem();
		int cost = numRoom * 3000;
		total.addCost((cost));
		total.addNameRoom("deluxe");
		getRoom();
		if(!canReserve("dlx", numRoom)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setContentText("You cannot reserve this room on this date.");
			alert.showAndWait();
		}
		DlxList = Select("dlx", numRoom);
		
	}
	
	/** Sum all breakfast*/
	public static int showBreakfast(){
		int totalfood = 0;
		for (int i = 0; i < total.getBreakfastList().size(); i++) totalfood += total.getBreakfastList().get(i);
		return totalfood;
	}
	public static int countBreakfast(){
		return total.getBreakfastList().size();
	}
	/** Add extra bed */
	public void addExtraBedButton(ActionEvent event){
		total.addBed();
	}
	/** Sum all ExtraBed*/
	public static int showExtraBed(){
		int totalBed = 0;
		for (int i = 0; i < total.getBedList().size(); i++) totalBed += total.getBedList().get(i);
		return totalBed;
	}
	
	public static int countExtraBed(){
		return total.getBedList().size(); 
	}
	
	public static List<String> Select(String room, int number) {
		List<String> list = DatabaseManage.emptyRoom(room);
		List<String> newList = new ArrayList<String>();
		for(int i = 1; i <= number; i++){
		   newList.add(list.get(0));
		   list.remove(0);
		}
		return newList;
	}
	
	
	public static boolean canReserve(String room, int number){
		String arrive = HomeController.readfile().get(0);
		String depart = HomeController.readfile().get(1);
		for(String str : Select(room, number)){
			for(Booked b : DatabaseManage.bookedList()){
				if(str.equals(b.getRoomCode())){
					if(arrive.equals(b.getArrive()) || depart.equals(b.getDepart())) return false;
					else if(arrive.equals(b.getDepart()) || depart.equals(b.getArrive())) return false;
					else if(arrive.compareTo(b.getArrive()) < 0) return false;
					else if(depart.compareTo(b.getDepart()) > 0) return false;
					else return true;
				} 
				return true;
			}
		}
		return true;
	}
	
	public static void ty() throws SQLException{
//		System.out.println(HomeController.readfile().get(0));
		if(canReserve("dlx1",1)){
			System.out.println("123");
		}else{
			System.out.println("ควย");
		}
	}
	
	public static List<String> getSprList(){ return SprList; }
    public static List<String> getStdList(){ return StdList; }
	public static List<String> getSutList(){ return SutList; }
	public static List<String> getDlxList(){ return DlxList; }
	
	public static void main(String[] args) throws SQLException{
		ty();
		SprList = Select("spr",1);
		System.out.println(SprList);
		
	}

	
}
