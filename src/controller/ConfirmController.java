package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import bookingRoom.Booked;
import bookingRoom.BookingRequest;
import bookingRoom.ConfigFileManager;
import bookingRoom.DatabaseManage;
import bookingRoom.Total;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for Confirming UI that show receipt and enter confirmation.
 * @author Narisa and Tanasorn
 *
 */
public class ConfirmController {

	@FXML
	TextField name;
	@FXML
	TextField email;
	@FXML
	TextField call;
	@FXML
	Label confirmBed;
	@FXML
	Label confirmBreakfast;
	@FXML
	Label bookingDay;
	@FXML
	Label costRoom;
	@FXML
	Button confirm;
	@FXML
	Label nameRoom;
	@FXML
	Label discount;
	@FXML
	ComboBox<CurrencyRate> comboBox;
	@FXML
	Label totalPrice;

	private static DatabaseManage db = DatabaseManage.getInstance();
	
	private static BookingRequest br = BookingRequest.getInstance();
	private String day = br.getListFile().get(2);
	private String arrive = br.getListFile().get(0);
	private String depart = br.getListFile().get(1);
	
	private static Total total = Total.getinstance();
	private CurrencyRate currency;
	private int days = Integer.parseInt(day);
	private int sum = total.getRoomPrice() + total.showBreakfast() + total.showExtraBed();
	
	private static ConfigFileManager cf = ConfigFileManager.getInstance();
	private final String ACCESS_KEY = cf.getProperty("access.key");
	private final String BASE_URL = cf.getProperty("base.url");
	
	private long tel = 0;

	@FXML
	public void initialize() throws IOException {
		if (comboBox != null) {
			comboBox.getItems().addAll(currency.values());
			comboBox.getSelectionModel().select(0);
		}
		handleCostRoom();
		comboBox.setOnAction(event -> {
			try {
				handleCurrencyRate(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		confirm.setOnAction(this::confirm);
	}

	public void handleCostRoom() {

		String name = "";

		for (String n : total.getNameRoom()) {
			name += " " + n + " ";
		}

		bookingDay.setText(String.format("You reserve %s days", day));
		confirmBreakfast.setText(
				String.format("Add Breakfast %d bed = %d Baht", total.countBreakfast(), total.showBreakfast()));
		confirmBed
				.setText(String.format("Add extra-bed %d bed = %d Baht", total.countExtraBed(), total.showExtraBed()));
		nameRoom.setText("Including " + name + " room");
		costRoom.setText(String.format("( %d + %d +%d ) x %d days = %d Baht", total.getRoomPrice(),
				total.showBreakfast(), total.showExtraBed(), days, sum * days));
		if (SigninController.checkMember)
			discount.setText("Member discount 15% ");
	}

	/** Show when select currency on combo box */
	@FXML
	public void handleCurrencyRate(ActionEvent event) throws IOException {
		currency = comboBox.getValue();
		double thaiCurrency = currency.matchCurrency(readUrl(), currency.THB);
		double rate = currency.matchCurrency(readUrl(), currency);
		double result = (sum * days) * (rate / thaiCurrency);
		if (SigninController.checkMember)
			totalPrice.setText(String.format("Total 	%4g", result * (0.85)));
		else
			totalPrice.setText(String.format("Total 	%4g", result));

	}

	/**
	 * Read the data of web service
	 * 
	 * @return currency data
	 * @throws IOException
	 */
	public String readUrl() throws IOException {
		URL url = new URL(BASE_URL + ACCESS_KEY);
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		InputStream in = connect.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null)
			sb.append(line);
		reader.close();
		String data = sb.toString();
		return data;

	}

	/**Show that you reserve successfully.*/
	public void confirm(ActionEvent event) {
		if (error()) {
			for (int i = 0; i < total.getNameRoom().size(); i++) {
				collectIndatabase(total.getNameRoom().get(i));
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("You reserve successfully!");
			alert.showAndWait();
		} 
	}

	/**
	 * Collect the room that you reserve in Database.
	 * @param nameRoom
	 */
	public void collectIndatabase(String nameRoom) {
		String Name = name.getText().trim();
		
		switch (nameRoom) {
		case ("suite"):
			for (String s : RoomController.SutList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				db.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			
			}
			break;
		case ("superior"):
			for (String s : RoomController.SprList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				db.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
			break;
		case ("standard"):
			for (String s : RoomController.StdList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				db.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
			break;
		case ("deluxe"):
			for (String s : RoomController.DlxList) {
				Booked bk = new Booked(s, arrive, depart, Name);
				db.updateReserving(bk.getReserveCode(), bk.getRoomCode(), arrive, depart, Name);
			}
			break;
		default:
			break;
		}
		db.collectName(Name, email.getText().trim(), tel);
		
			
	}
	
	public boolean error(){
		try{
			tel = Long.parseLong(call.getText().trim());
			
		}catch(Exception e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Invalid value.");
			alert.showAndWait();
			return false;
		}
		
		return true;
	}
}
