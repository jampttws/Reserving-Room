package bookingRoom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class CollectData {
	
	private LocalDate checkin;
	private LocalDate checkout;
	private int day;
	private String child;
	private String adult;

	public CollectData(LocalDate checkin,LocalDate checkout,int day,String adult,String child){
		this.checkin = checkin;
		this.checkout = checkout;
		this.day = day;
		this.child = child;
		this.adult = adult;
	}
	
	public void add(){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/bookingRoom/text.txt"));
				bw.write(checkin.toString()+";"+checkout.toString()+";"+day+";"+adult+";"+child);
				bw.newLine();
				bw.close();
		} catch (IOException e) {
			System.out.println("file not found");
		}
	}

}
