package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Select the room and other option in hotel
 * @author Narisa Singngam
 *
 */
public class RoomController {
	
	@FXML
	ComboBox<Room> bedDeluxe;
	@FXML
	ComboBox<Room> bedSuite;
	@FXML
	ComboBox<Room> bedSupe;
	@FXML
	ComboBox<Room> bedStandard;
	@FXML
	Label costumerData;
	@FXML
	Button breakfast;
	
	public static List<String> list = new ArrayList<>();
	
	@FXML
	public void initialize(){

		selectBed(bedDeluxe);
		selectBed(bedStandard);
		selectBed(bedSuite);
		selectBed(bedSupe);
//		breakfast.setOnAction(this::addBreakfast);
		showData();
	}
	
	
	public void showData(){
		read();
		costumerData.setText(String.format("You reserve %s days including adult %s children %s",list.get(2),list.get(3),list.get(4)));
	}
	
	/** Add type of bed in combo box*/
	public void selectBed(ComboBox<Room> bed){
		if(bed != null){
			bed.getItems().addAll(Room.values());
			bed.getSelectionModel().select(0);
		}
	}
	
	/** Add Breakfast in combo box */
	public void addBreakfast(ActionEvent event){
		list.add("yes");
		for (String type : list) {
			System.out.println(type);
		}
	}
	
	/** Read costumer data. */
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
