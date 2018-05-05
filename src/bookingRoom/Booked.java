package bookingRoom;

import java.sql.Date;
import java.time.LocalDate;

public class Booked {
	
	private String roomCode;
	private String arrive;
	private String depart;
	private String name;
	private String reserveCode;
    private LocalDate date = LocalDate.now();
    private int num = 0;
    
	
	public Booked(String room, String arrive, String depart, String name){
		this.roomCode = roomCode;
		this.arrive = arrive;
		this.depart = depart;
		this.name = name;
		this.reserveCode = String.format("%d%d%d%d", date.getDayOfYear(), date.getMonthValue(), date.getYear(), ++num);
	}

	public String getRoomCode() {
		return roomCode;
	}

	public String getArrive() {
		return arrive;
	}

	public String getDepart() {
		return depart;
	}

	public String getName() {
		return name;
	}
	
	public int getReserveCode(){
		return Integer.parseInt(reserveCode);
	}
	 

}
