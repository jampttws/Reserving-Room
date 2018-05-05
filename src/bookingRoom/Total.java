package bookingRoom;

import java.util.ArrayList;
import java.util.List;


public class Total {

	private List<Integer> bedList ;
	private static Total instance;
	private List<Integer> breakfastList;
	private List<Integer> costRoom;
	private String nameRoom;

	
	private Total(){
		bedList = new ArrayList<>();
		breakfastList = new ArrayList<>();
		costRoom = new ArrayList<>();
		
		
	}
	public String getNameRoom() {
		return nameRoom;
	}
	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}
	public static Total getinstance(){
		if(instance==null){
			instance = new Total();
		}
		return instance;
	}
	
	public void addCost(int room){
		costRoom.add(room);
	}
	
	public void addbreakfast(){
		breakfastList.add(300);
	}
	
	public void addBed(){
		bedList.add(500);
	}
	
	public List<Integer> getTypeRoom(){
		return costRoom;
	}
	
	public List<Integer> getBreakfastList() {
		return breakfastList;
	}
	public List<Integer> getBedList(){
		return bedList;
	}

}