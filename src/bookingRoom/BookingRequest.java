package bookingRoom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingRequest {
	
	private static BookingRequest instance;
	private List<String> listFile;
	
	private BookingRequest(){
		listFile = new ArrayList<String>();
	}

	public static BookingRequest getInstance(){
		if(instance == null){
			instance = new BookingRequest();
		}
		return instance;
	}
	
	public List<String> read(){
		File file = new File("src/bookingRoom/text.txt");
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine().trim();
				String[] text = line.split(";");
				for (int i = 0; i < text.length; i++) {
				listFile.add(text[i]);
				}			
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		return listFile;	
	}

	public List<String> getListFile() {
		return listFile;
	}

	public void add(LocalDate checkin,LocalDate checkout,int day,int adult,int child){
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
