package controller;

import bookingRoom.Booked;
import bookingRoom.DatabaseManage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagerController{

	@FXML
    TableView<Booked> reserveData;
    @FXML
    TableColumn<Booked, String> name;
    @FXML
    TableColumn<Booked, String> roomCode;
    @FXML
    TableColumn<Booked, String> arrive;
    @FXML
    TableColumn<Booked, String> depart;
    @FXML
    Button showAll;
    @FXML
    Button search;
    @FXML
    TextField getName;
    @FXML
    TextField getRoom;
    @FXML 
    Button remove;
    
    private ObservableList<Booked> all_data;
    private ObservableList<Booked> search_data;
    private static DatabaseManage db = DatabaseManage.getInstance();
    
    @FXML
	public void initialize(){
    	showAll.setOnAction(this::allReserved);
    	search.setOnAction(this::searchName);
    	remove.setOnAction(this::removeData);
    }
    
  
    public void allReserved(ActionEvent event){	
        all_data = FXCollections.observableArrayList();
        for(Booked b : db.bookedList()) all_data.add(b);
        
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	roomCode.setCellValueFactory(new PropertyValueFactory<>("roomCode"));
    	arrive.setCellValueFactory(new PropertyValueFactory<>("arrive"));
    	depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
    	
    	reserveData.setItems(all_data);     
    }
    
    public void searchName(ActionEvent event){
    	String Name = getName.getText().trim();
    	
    	search_data = FXCollections.observableArrayList();
        for(Booked b : db.bookedList(Name)) search_data.add(b);
        
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	roomCode.setCellValueFactory(new PropertyValueFactory<>("roomCode"));
    	arrive.setCellValueFactory(new PropertyValueFactory<>("arrive"));
    	depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
    	
    	reserveData.setItems(search_data);    
    }
    
    public void removeData(ActionEvent event){
    	String room_code = getRoom.getText().trim();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(String.format("Do you want to remove room %s?", room_code));
		alert.showAndWait();
    	db.remove("reservingData", "roomNumber", room_code);
    }
    
    
    
}
