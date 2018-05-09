package bookingRoom;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookingRequest {
	
	private static BookingRequest instance;
	private List<String> listFile;
	
	private BookingRequest(){
		listFile = new ArrayList<String>();
	}

	public static BookingRequest getInstance(){
		if(instance == null){
			instance = new BookingRequest();
		}
		return instance;
	}

	public List<String> getListFile() {
		return listFile;
	}

	public List<String> addData(LocalDate checkin,LocalDate checkout,int day,int adult,int child){
		listFile.add(checkin.toString());
		listFile.add(checkout.toString());
		listFile.add(String.format("%d",day));
		listFile.add(String.format("%d", adult));
		listFile.add(String.format("%d", child));
		return listFile;
		
	}
	

}
