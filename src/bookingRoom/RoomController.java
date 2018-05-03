package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	private PageController open = super.getController();
	private Total total = Total.getinstance();
	ObservableList<Integer> options = FXCollections.observableArrayList(1,2,3,4,5);
	
	@FXML
	public void initialize(){
		showData();
		confirm.setOnAction(this::showComfirmPage);
		numDeluxe.setItems(options);
		numStandard.setItems(options);
		numSuite.setItems(options);
		numSupe.setItems(options);
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

	public void showComfirmPage(ActionEvent event){
		open.openPage("ConfirmReserving.fxml");
	}
	
	public void showData(){
		List<String> readfile = total.read();
		costumerData.setText(String.format("You reserve %s days including adult %s children %s"
				,readfile.get(2),readfile.get(3),readfile.get(4)));
	}

	/** Add breakfast */
	public void addBreakfast(ActionEvent event){
		total.addbreakfast();
		System.out.println(total.getBreakfastList());
	}
	
	/** Add extra bed */
	public void addExtraBedButton(ActionEvent event){
		int count = 0;
		int totalBed = 0;
		total.addBed();
		for (int i = 0; i < total.getBedList().size(); i++) {
			totalBed += total.getBedList().get(i);
			count = count +1;
		}
		System.out.println(total.getBedList());
//		ConfirmController.countBed(count);
		System.out.println("all total");
		ConfirmController.countBed(totalBed);
		System.out.println("");
//		ConfirmController.showBedList();
	}
	
	
	
	
	


}
