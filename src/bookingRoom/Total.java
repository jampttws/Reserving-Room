package bookingRoom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Total {

	private List<Integer> bedList;
	private static Total instance;
	private List<Integer> breakfastList;
	private List<Integer> costRoom;
	private List<String> nameRoom;

	private Total() {
		bedList = new ArrayList<>();
		breakfastList = new ArrayList<>();
		costRoom = new ArrayList<>();
		nameRoom = new ArrayList<>();
	}
	
	public static Total getinstance() {
		if (instance == null) {
			instance = new Total();
		}
		return instance;
	}

	public boolean roomName(String name) {
		for (String nr : nameRoom) {
			if (nr.equals(name))
				return false;
		}
		return true;
	}

	public List<String> getNameRoom() {
		return nameRoom;
	}

	public void addNameRoom(String name) {
		if (roomName(name)) {
			nameRoom.add(name);
		}
	}
	

	public void addPrice(int room) {
		costRoom.add(room);
	}

	/** 
	 * 
	 * @return Price of room.
	 */
	public int getRoomPrice(){
		int result = 0;
		for (int i = 0; i < costRoom.size(); i++) {
			result += costRoom.get(i);
		}
		return result;
	}


	public void addbreakfast() {
		breakfastList.add(300);
	}

	/** Sum all breakfast */
	public int showBreakfast() {
		int totalfood = 0;
		for (int i = 0; i < getBreakfastList().size(); i++)
			totalfood += getBreakfastList().get(i);
		return totalfood;
	}

	/** Count amount of breakfast */
	public int countBreakfast() {
		return getBreakfastList().size();
	}

	/** Sum all ExtraBed */
	public int showExtraBed() {
		int totalBed = 0;
		for (int i = 0; i < getBedList().size(); i++)
			totalBed += getBedList().get(i);
		return totalBed;
	}

	/** Count amount of extra bed */
	public int countExtraBed() {
		return getBedList().size();
	}

	public void addBed() {
		bedList.add(500);
	}

	public List<Integer> getBreakfastList() {
		return breakfastList;
	}

	public List<Integer> getBedList() {
		return bedList;
	}
}
