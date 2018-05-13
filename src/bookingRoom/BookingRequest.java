package bookingRoom;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Collect date and people.
 * @author Narisa and Tanasorn
 *
 */
public class BookingRequest {
	
	private static BookingRequest instance;
	private List<ReservationData> listFile;
	
	/**Create BookingRequest object.*/
	public BookingRequest(){
		listFile = new ArrayList<ReservationData>();
	}

	public static BookingRequest getInstance(){
		if(instance == null){
			instance = new BookingRequest();
		}
		return instance;
	}

	/**Get list of BookingRequest.*/
	public List<ReservationData> getListFile() {
		return listFile;
	}

	/**Add any data in the list.*/
	public List<ReservationData> addData(LocalDate checkin,LocalDate checkout,int day,int adult,int child){
		listFile.add(new ReservationData(checkin.toString(), checkout.toString(), day, adult, child));
		return listFile;
		
	}
	

}
