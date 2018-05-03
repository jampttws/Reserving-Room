 package bookingRoom;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmController {
	
	@FXML
	static
	Label confirmBed;
	
	@FXML
	public void initialize(){
		showBedList();
	}
	
	private static Total total = Total.getinstance();
	
	public static void countBed(int count){
		System.out.println(count);
//		confirmBed.setText(String.format("%d",count));
	}
	public static void showBedList() {
		System.out.println(" print from cc");
		System.out.println("Breakfast included");
//		confirmBed.setText("Breakfast included");

		
	}
	
	
	
}
