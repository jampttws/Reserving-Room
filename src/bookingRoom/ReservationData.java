package bookingRoom;

import java.time.LocalDate;

public class ReservationData {
	
	private String checkin;
	private String checkout;
	private int day;
	private int adult;
	private int child;

	public ReservationData(String checkin,String checkout,int day,int adult,int child){
		this.checkin = checkin;
		this.checkout = checkout;
		this.day = day;
		this.adult = adult;
		this.child = child;
	}

	public String getCheckin() {
		return checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public int getDay() {
		return day;
	}

	public int getAdult() {
		return adult;
	}

	public int getChild() {
		return child;
	}
	
}
