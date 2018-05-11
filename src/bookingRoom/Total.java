package bookingRoom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Total class for collect value consist of extra bed, breakfast, etc.≥
 * @author Narisa and Tanasorn
 *
 */
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
	
	/**Get instance of Total.*/
	public static Total getinstance() {
		if (instance == null) {
			instance = new Total();
		}
		return instance;
	}

	/**
	 * Check room name 
	 * @param Type of hotel room.
	 * @return true if name in list nameRoom equal name of type.
	 */
	public boolean roomName(String name) {
		for (String nr : nameRoom) {
			if (nr.equals(name))
				return false;
		}
		return true;
	}

	/** Get name room lst */
	public List<String> getNameRoom() {
		return nameRoom;
	}

	/** Add name room */
	public void addNameRoom(String name) {
		if (roomName(name)) {
			nameRoom.add(name);
		}
	}

	/** Add room price */
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

	/** Add breakfast price*/
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

	/** Add extra bed price */
	public void addBed() {
		bedList.add(500);
	}
	
	/**
	 * Get breakfast list
	 * @return List of breakfast
	 */
	public List<Integer> getBreakfastList() {
		return breakfastList;
	}
	
	/**
	 * Get extra bed list
	 * @return List of extra bed
	 */
	public List<Integer> getBedList() {
		return bedList;
	}
}
