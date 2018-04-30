package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
	@FXML
	Label costumerData;
	
	public String [] typeBed = {"Bed","King","Double x2"};
	public static List<String> list = new ArrayList<>();
	
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
		showData();
	}
	
	
	public void showData(){
		read();
		costumerData.setText(String.format("You reserve %s days including adult %s children %s",list.get(2),list.get(3),list.get(4)));
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
	
	
	public void read(){
		File file = new File("src/bookingRoom/text.txt");
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine().trim();
				String[] text = line.split(";");
				for (int i = 0; i < text.length; i++) {
					list.add(text[i]);
				}			
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
			
	}
	
	public static void main(String[] args) {
		RoomController r = new RoomController();
		r.read();
		for (String type : list) {
			System.out.println(type);
		}
	}
	


}
