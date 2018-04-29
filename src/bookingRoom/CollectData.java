package bookingRoom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class CollectData {
	
	private LocalDate checkin;
	private LocalDate checkout;
	private int day;
	private int child;
	private int adult;
	
	public CollectData(LocalDate checkin,LocalDate checkout,int day,int adult,int child){
		this.checkin = checkin;
		this.checkout = checkout;
		this.day = day;
		this.child = child;
		this.adult = adult;
		
	}
	
	public void add(){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/bookingRoom/text.txt"));
//			for (int i = 0; i < list.size(); i++) {
//				list.add(new CollectData(date1, date2, day, adult, children));
				bw.write(checkin.toString()+";"+checkout.toString()+";"+day+";"+adult+";"+child);
				bw.newLine();
				bw.close();
//			}
		} catch (IOException e) {
			System.out.println("file not found");
		}
	}

	
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.of(2019, 1,1);
		CollectData d = new CollectData(date1, date2,155, 144,6);
		d.add();
	}

}
