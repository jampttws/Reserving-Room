package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RoomController {
	
	
	@FXML
	Label label;
	@FXML
	TextField text;
	
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
