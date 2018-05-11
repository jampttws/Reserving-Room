package bookingRoom;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Collect date and prople.
 * @author Narisa and Tanasorn
 *
 */
public class BookingRequest {
	
	private static BookingRequest instance;
	private List<String> listFile;
	
	/**Create BookingRequest object.*/
	public BookingRequest(){
		listFile = new ArrayList<String>();
	}

	public static BookingRequest getInstance(){
		if(instance == null){
			instance = new BookingRequest();
		}
		return instance;
	}

	/**Get list of BookingRequest.*/
	public List<String> getListFile() {
		return listFile;
	}

	/**Add any data in the list.*/
	public List<String> addData(LocalDate checkin,LocalDate checkout,int day,int adult,int child){
		listFile.add(checkin.toString());
		listFile.add(checkout.toString());
		listFile.add(String.format("%d",day));
		listFile.add(String.format("%d", adult));
		listFile.add(String.format("%d", child));
		return listFile;
		
	}
	

}
