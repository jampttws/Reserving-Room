package bookingRoom;

import java.time.LocalDate;
/**
 * Manage check in and check out date to costumer
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
	 * the total date that costumer reserve room.
	 */
	public int days(){
		int getDate = checkout.getDayOfYear()-checkin.getDayOfYear();
		return getDate;
	}
	
	public LocalDate getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDate date1) {
		this.checkin = date1;
	}
	public LocalDate getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDate date2) {
		this.checkout = date2;
	}
	
	
}
