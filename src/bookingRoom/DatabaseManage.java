package bookingRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Connect and collect data to database.
 * 
 * @author Tanasorn Tritawisup
 *
 */
public class DatabaseManage {

	private static Connection connect;
	private static Statement s;

	public static void connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://35.194.158.90/MintDB?useSSL=false", "jamp", "jamp");
			if (connect != null) {
				// System.out.println("Database Connected.");
				// connect.close();
			} else {
				System.out.println("Database Connect Failed.");
			}
			s = connect.createStatement();
			
			String sql4 = "DELETE FROM reservingData WHERE reserveCode = 125520181";
			s.execute(sql4);
			
		} catch (ClassNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
//		 String sql1 = "CREATE TABLE reservingData(reserveCode int,
		// roomNumber varchar(255), arrive varchar(255), depart varchar(255),
		// name varchar(255))";
		// String sql2 = "CREATE TABLE customer(name varchar(255), id int)";
//		String sql3 = "CREATE TABLE customer(name varchar(255), id int, number int)";
	

		

	}

	
	public static List<String> SuggestRoom(String room) {
        connect();
		List<String> code = new ArrayList<String>();

		String sql = "SELECT roomNumber FROM room WHERE roomNumber LIKE '" + room + "%'";

		ResultSet rs = null;
		try {
			s.execute(sql);
			rs = s.executeQuery(sql);

			String str = "";
			while (rs.next()) {
				str = rs.getString("roomNumber");
				code.add(str);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}

		// System.out.println(code);
		return code;

	}

	/**roomcontroll*/
	public static List<String> emptyRoom(String roomname) {
		connect();
		List<String> list = new ArrayList<String>();
		String sql = "SELECT roomNumber FROM reservingData WHERE roomNumber LIKE '" + roomname + "%'";

		ResultSet rs = null;
		try {
			s.execute(sql);
			rs = s.executeQuery(sql);

			String str = "";
			while (rs.next()) {
				str = rs.getString("roomNumber");
				list.add(str);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}

		List<String> all = SuggestRoom(roomname);

		for (String s : list) {
			if(all.contains(s)){
				all.remove(s);
			}
		}

		return all;
	}

	/**confirm*/
	public static void collectName(String name, int id, int number) {
        connect();
		String sql = "INSERT INTO customer VALUES" + String.format("('%s',%d,%d)", name, id, number);

		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/**confirm*/
	public static void updateReserving(int reserveCode, String roomCode, String arrive, String depart, String name) {
		String sql = "INSERT INTO reservingData VALUES"
				+ String.format("(%d,'%s','%s','%s','%s')", reserveCode, roomCode, arrive, depart, name);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	
	/**manager*/
	public static List<String> databaseData(String want, String from){
		connect();
		List<String> get = new ArrayList<String>();
		String sql = String.format("SELECT %s FROM %s", want, from);
		ResultSet rs = null;
		try {
			s.execute(sql);
			rs = s.executeQuery(sql);

            String str = "";
			while (rs.next()) {
				str = rs.getString(want);
				get.add(str);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return get;
	}
	
	/**manager*/
	public static List<String> databaseData(String want, String from, String name){
		connect();
		List<String> get = new ArrayList<String>();
		String sql = String.format("SELECT %s FROM %s WHERE name LIKE '", want, from) + name + "%'";
		ResultSet rs = null;
		try {
			s.execute(sql);
			rs = s.executeQuery(sql);

            String str = null;
			while (rs.next()) {
				str = rs.getString(want);
				get.add(str);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return get;
	}
	
	
	public static List<Booked> bookedList(String Name){
		List<Booked> bk = new ArrayList<Booked>();
		List<String> roomCode = databaseData("roomNumber", "reservingData", Name);
		List<String> arrive = databaseData("arrive", "reservingData", Name);
	    List<String> depart = databaseData("depart", "reservingData", Name);
	    List<String> name = databaseData("name", "reservingData", Name);
		for(int i = 0; i < name.size(); i++){
			bk.add(new Booked(roomCode.get(i), arrive.get(i), depart.get(i), name.get(i)));
		}
		return bk;
	}
	
	public static List<Booked> bookedList(){
		List<Booked> bk = new ArrayList<Booked>();
		List<String> roomCode = databaseData("roomNumber", "reservingData");
		List<String> arrive = databaseData("arrive", "reservingData");
	    List<String> depart = databaseData("depart", "reservingData");
	    List<String> name = databaseData("name", "reservingData");
		for(int i = 0; i < name.size(); i++){
			bk.add(new Booked(roomCode.get(i), arrive.get(i), depart.get(i), name.get(i)));
		}
		return bk;
	}
	
	public static void remove(String from, String where, String want){
		connect();
		String sql = String.format("DELETE FROM %s WHERE %s = '%s'", from, where, want);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		connect();
		// System.out.print(SuggestRoom("dlx"));
//		 System.out.print(bookedList());
//		 updateReserving(5520180, "spr1", "2018-05-20", "2018-05-22", "Mint");
//		System.out.print(peopleReserve("roomNumber", "reservingData", "jamp"));
//		 remove("customer", "name", "l");
	}
}