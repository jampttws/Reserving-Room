package bookingRoom;

import java.time.LocalDate;

public class Booked {
	
	private String roomCode;
	private String arrive;
	private String depart;
	private String name;
	private String reserveCode;
    private LocalDate date = LocalDate.now();
    private static int num = 0;
    
	
	public Booked(String room, String arrive, String depart, String name){

		this.roomCode = room;
		this.arrive = arrive;
		this.depart = depart;
		this.name = name;
		this.reserveCode = String.format("%d%d%d%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear(), ++num);
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
