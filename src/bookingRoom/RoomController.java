package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RoomController {
	
	
	@FXML
	ComboBox<box> boxDeluxe;
	@FXML
	ComboBox<box> boxSuite;
	@FXML
	ComboBox<box> boxSupe;
	@FXML
	ComboBox<box> boxStandard;
	@FXML
	ComboBox<Bed> bedDeluxe;
	@FXML
	ComboBox<Bed> bedSuite;
	@FXML
	ComboBox<Bed> bedSupe;
	@FXML
	ComboBox<Bed> bedStandard;
	
	public String [] typeBed = {"Bed","King","Double x2"};
	
	@FXML
	public void initialize(){
		addBreakfast(boxDeluxe);
		addBreakfast(boxSuite);
		addBreakfast(boxSupe);
		addBreakfast(boxStandard);
		selectBed(bedDeluxe);
		selectBed(bedStandard);
		selectBed(bedSuite);
		selectBed(bedSupe);
	}
	
	public void selectBed(ComboBox<Bed> bed){
		if(bed != null){
			bed.getItems().addAll(Bed.values());
			bed.getSelectionModel().select(0);
		}
	}
	
	public void addBreakfast(ComboBox<box> breakfast){
		if(breakfast != null){
			breakfast.getItems().addAll(box.values());
			breakfast.getSelectionModel().select(0);
		}
		
	}
	
	public int read(){
		File file = new File("src/bookingRoom/text.txt");
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine().trim();
				String[] text = line.split(";");
				return Integer.parseInt(text[2]);				
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		return 0;		
	}
	
	public static void main(String[] args) {
		RoomController r = new RoomController();
		System.out.println(r.read());
	}
	


}
