package bookingRoom;

import java.time.LocalDate;

/**
 * Booked class for collect reserve data.
 * @author Narisa and Tanasorn
 *
 */
public class Booked {
	
	private String roomCode;
	private String arrive;
	private String depart;
	private String name;
	private String reserveCode;
    private LocalDate date = LocalDate.now();
    private static int num = 0;
    
    /**Create Booked object.*/
	public Booked(String room, String arrive, String depart, String name){

		this.roomCode = room;
		this.arrive = arrive;
		this.depart = depart;
		this.name = name;
		this.reserveCode = String.format("%d%d%d%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear(), ++num);
	}

	/**Get room code.*/
	public String getRoomCode() {
		return roomCode;
	}

	/**Get arrive date.*/
	public String getArrive() {
		return arrive;
	}

	/**Get depart date.*/
	public String getDepart() {
		return depart;
	}

	/**Get name.*/
	public String getName() {
		return name;
	}
	
	/**Get resserve code.*/
	public int getReserveCode(){
		return Integer.parseInt(reserveCode);
	}
	
	 

}
