package bookingRoom;

import java.time.LocalDate;

/**
 * Basic information to reserve.
 * @author Narisa and Tanasorn
 *
 */
public class ReservationData {
	
	private String checkin;
	private String checkout;
	private int day;
	private int adult;
	private int child;

	/**Create new ReservationData*/
	public ReservationData(String checkin,String checkout,int day,int adult,int child){
		this.checkin = checkin;
		this.checkout = checkout;
		this.day = day;
		this.adult = adult;
		this.child = child;
	}

	/**Get check in date.*/
	public String getCheckin() {
		return checkin;
	}

	/**Get check out date.*/
	public String getCheckout() {
		return checkout;
	}

	/**Get day that you reserve*/
	public int getDay() {
		return day;
	}

	/**Get value of adult.*/
	public int getAdult() {
		return adult;
	}

	/**Get value of child*/
	public int getChild() {
		return child;
	}
	
}
