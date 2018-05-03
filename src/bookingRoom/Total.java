package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Total {

	private List<Integer> bedList ;
	private static Total instance;
	private List<Integer> breakfastList;
	private List<String> listFile;

	
	private Total(){
		bedList = new ArrayList<>();
		breakfastList = new ArrayList<>();
		listFile =  new ArrayList<String>();
		
	}
	public static Total getinstance(){
		if(instance==null){
			instance = new Total();
		}
		return instance;
	}
	
	public void addbreakfast(){
		breakfastList.add(300);
	}
	
	public void addBed(){
		bedList.add(500);
	}
	
	public List<Integer> getBreakfastList() {
		return breakfastList;
	}
	public List<Integer> getBedList(){
		return bedList;
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

	
	

	
}
