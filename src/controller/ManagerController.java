package controller;

import bookingRoom.Booked;
import bookingRoom.DatabaseManage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagerController{

	@FXML
    TableView<Booked> reserveData;
    @FXML
    TableColumn<Booked, String> name;
    @FXML
    TableColumn<Booked, String> roomName;
    @FXML
    TableColumn<Booked, String> arrive;
    @FXML
    TableColumn<Booked, String> depart;
    
    private ObservableList<Booked> data;
    
    public void manage(){
    	
//        data = DatabaseManage.bookedList()
    	
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	roomName.setCellValueFactory(new PropertyValueFactory<>("room code"));
    	arrive.setCellValueFactory(new PropertyValueFactory<>("arrive date"));
    	depart.setCellValueFactory(new PropertyValueFactory<>("depart date"));
    	reserveData.setItems(null);
//        reserveData.setItems((ObservableList<Booked>) DatabaseManage.bookedList());
    	

    }
    
}
