package bookingRoom;

import java.time.LocalDate;
/**
 * Manage check in and check out date to customer
 * @author Narisa Singngam
 *
 */
public class DateManage{

	private LocalDate checkin;
	private LocalDate checkout;
	
	public DateManage(LocalDate checkin,LocalDate checkout){
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	/**
	 * The total date that customer reserve room.
	 */
	public int days(){
		int getDate = checkout.getDayOfYear()-checkin.getDayOfYear();
		return getDate;
	}
	
	/** Get check in date */
	public LocalDate getCheckin() {
		return checkin;
	}
	
	/** Get check out date */
	public LocalDate getCheckout() {
		return checkout;
	}
	
	
}
