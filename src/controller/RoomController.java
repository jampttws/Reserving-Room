package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bookingRoom.DatabaseManage;
import bookingRoom.PageController;
import bookingRoom.Total;
import bookingRoom.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
		confirm.setOnAction(this::showComfirmPage);
		numDeluxe.setItems(room);
		numStandard.setItems(room);
		numSuite.setItems(room);
		numSupe.setItems(room);
//		selectDelux.setOnAction(this::selectDeluxRoom);
		breakfastDeluxe.setOnAction(this::addBreakfast);
		breakfastSuite.setOnAction(this::addBreakfast);
		breakfastSupe.setOnAction(this::addBreakfast);
		breakfastStandard.setOnAction(this::addBreakfast);
		bedDeluxe.setOnAction(this::addExtraBedButton);
		bedSuite.setOnAction(this::addExtraBedButton);
		bedSupe.setOnAction(this::addExtraBedButton);
		bedStandard.setOnAction(this::addExtraBedButton);
		deluxe.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("dlx").size()));
		suite.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("sut").size()));
		superior.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("spr").size()));
		standard.setText(String.format("%s rooms avilable",DatabaseManage.emptyRoom("std").size()));
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
	}

	/** Add breakfast */
	public void addBreakfast(ActionEvent event){
		total.addbreakfast();
	}
	
	/** select number of room*/
	public void selectSupeRoom(ActionEvent event){
		int numRoom = (int) numSupe.getSelectionModel().getSelectedItem();
		System.out.println(numRoom);
	}
	
	/** select number of room*/
	public void selectStandardRoom(ActionEvent event){
		int numRoom = (int) numStandard.getSelectionModel().getSelectedItem();
		System.out.println(numRoom);
	}
	
	/** select number of room*/
	public void selectSuiteRoom(ActionEvent event){
		int numRoom = (int) numSuite.getSelectionModel().getSelectedItem();
		System.out.println(numRoom);
	}
	
	/** select number of room*/
	public void selectDeluxRoom(ActionEvent event){
		int numRoom = (int) numDeluxe.getSelectionModel().getSelectedItem();
		System.out.println(numRoom);
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

}
