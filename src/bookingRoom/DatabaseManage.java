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
 * @author Narisa and Tanasorn
 *
 */

public class DatabaseManage {

	private Connection connect;
	private Statement s;
	
	private ConfigFileManager cf = ConfigFileManager.getInstance();
	private final String DATABASE = cf.getProperty("database.url");
    private final String USERNAME = "jamp";
	private final String PASSWORD = "jamp";
	
	// reserving table
	public final String TABLE_RESERVE = "reservingData";
	public final String COLUMN_RESERVECODE = "reservingCode";
	public final String COLUMN_ROOM = "roomNumber";
	public final String COLUMN_ARRIVE = "arrive";
	public final String COLUMN_DEPART = "depart";
	public final String COLUMN_NAME = "name";

	// customer table
	public final String TABLE_CUSTOMER = "customer";
	public final String COLUMN_EMAIL = "email";
	public final String COLUMN_NUMBER = "number";

	public final String TABLE_ROOM = "room";
	
	//user table
	public final String TABLE_USER = "user";
	public final String COLUMN_PASS = "password";
	
	private static DatabaseManage instance;
	
	public static DatabaseManage getInstance(){
		if(instance == null){
			instance = new DatabaseManage();
			
		}
		return instance;
	}

	public void connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
			if (connect == null) 
				System.out.println("Database Connect Failed.");
			s = connect.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Get list.
	 * @param expected data.
	 * @param database command.
	 * @return list that you expected.
	 */
	public List<String> get(String want, String sql) {
		List<String> code = new ArrayList<String>();
		ResultSet rs = null;
		try {
			s.execute(sql);
			rs = s.executeQuery(sql);

			String str = "";
			while (rs.next()) {
				str = rs.getString(want);
				code.add(str);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return code;
	}

	/**
	 * Get room from room table.
	 * @param room that want to find.
	 * @return list of room.
	 */
	public List<String> SuggestRoom(String room) {
		String sql =  String.format("SELECT %s FROM %s WHERE %s LIKE '%s%%'", COLUMN_ROOM, TABLE_ROOM, COLUMN_ROOM, room);
		  return get(COLUMN_ROOM, sql);
	}

	/**
	 * Get empty room.
	 * @param roomname.
	 * @return list of empty room.
	 */
	public List<String> emptyRoom(String roomname) {
		String sql = String.format("SELECT %s FROM %s WHERE %s LIKE '%s%%'", COLUMN_ROOM, TABLE_RESERVE, COLUMN_ROOM, roomname);

		List<String> all = SuggestRoom(roomname);

		for (String s : get(COLUMN_ROOM, sql)) {
			if (all.contains(s)) {
				all.remove(s);
			}
		}
		return all;
	}

	/**
	 * Get empty room with arrive and depart date.
	 * @param roomname
	 * @param arrive
	 * @param depart
	 * @return list of room.
	 */
	public List<String> emptyRoom(String roomname, String arrive, String depart) {
		String sql = String.format("SELECT %s FROM %s WHERE %s LIKE '%s%%' AND %s = '%s' AND %s = '%s'",
			    COLUMN_ROOM, TABLE_RESERVE, COLUMN_ROOM, roomname, COLUMN_ARRIVE, arrive,  COLUMN_DEPART, depart);
		
		List<String> all = SuggestRoom(roomname);

		for (String s : get(COLUMN_ROOM, sql)) {
			if (all.contains(s)) {
				all.remove(s);
			}
		}

		return all;
	}

	/**Collect name in database.*/
	public void collectName(String name, String email, long number) {
		String sql = String.format("INSERT INTO %s VALUES('%s','%s',%d)", TABLE_CUSTOMER, name, email, number);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/** Update reserving data to database. */
	public void updateReserving(int reserveCode, String roomCode, String arrive, String depart, String name) {
		String sql = String.format("INSERT INTO %s VALUES(%d,'%s','%s','%s','%s')",
				TABLE_RESERVE, reserveCode, roomCode, arrive, depart, name);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	 /**
	  * Get data that you want from database.
	  * @param expected data.
	  * @param table that you want to get data.
	  * @return list that you want.
	  */
	 public List<String> databaseData(String want, String from) {
	    String sql = String.format("SELECT %s FROM %s", want, from);
	    return get(want, sql);
	 }

	 /**
	  * Get data that you want from database with name.
	  * @param expected data.
	  * @param table that you want to get data.
	  * @param expected name.
	  * @return list that you want.
	  */
	 public List<String> databaseData(String want, String from, String name) {
	   String sql = String.format("SELECT %s FROM %s WHERE %s LIKE '%s'", want, from, COLUMN_NAME, name);
	   return get(want, sql);
	 }

	/**
	 * Get reserving list with name.
	 * @param Name
	 * @return list of reserving.
	 */
	public List<Booked> bookedList(String Name) {
		List<Booked> bk = new ArrayList<Booked>();
		List<String> roomCode = databaseData(COLUMN_ROOM, TABLE_RESERVE, Name);
		List<String> arrive = databaseData(COLUMN_ARRIVE, TABLE_RESERVE, Name);
		List<String> depart = databaseData(COLUMN_DEPART, TABLE_RESERVE, Name);
		List<String> name = databaseData(COLUMN_NAME, TABLE_RESERVE, Name);
		for (int i = 0; i < name.size(); i++) {
			bk.add(new Booked(roomCode.get(i), arrive.get(i), depart.get(i), name.get(i)));
		}
		return bk;
	}

	/**
	 * Get reserving list. 
	 * @return list of reserving.
	 */
	public List<Booked> bookedList() {
		List<Booked> bk = new ArrayList<Booked>();
		List<String> roomCode = databaseData(COLUMN_ROOM, TABLE_RESERVE);
		List<String> arrive = databaseData(COLUMN_ARRIVE, TABLE_RESERVE);
		List<String> depart = databaseData(COLUMN_DEPART, TABLE_RESERVE);
		List<String> name = databaseData(COLUMN_NAME, TABLE_RESERVE);
		for (int i = 0; i < name.size(); i++) {
			bk.add(new Booked(roomCode.get(i), arrive.get(i), depart.get(i), name.get(i)));
		}
		return bk;
	}

	/** Remove data from database. */
	public void remove(String from, String where, String want) {
		String sql = String.format("DELETE FROM %s WHERE %s = '%s'", from, where, want);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	/** Add user to database. */
	public void addUser(String name, String pass){
		String sql =  String.format("INSERT INTO %s VALUES('%s','%s')", TABLE_USER, name, pass);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get all user.
	 * @return list of user.
	 */
	public List<User> getUser(){
		List<User> user = new ArrayList<User>();
		List<String> name = databaseData(COLUMN_NAME, TABLE_USER);
		List<String> pass = databaseData(COLUMN_PASS, TABLE_USER);
		for (int i = 0; i < name.size(); i++) {
			user.add(new User(name.get(i), pass.get(i)));
		}
		return user;
	}
	
	
}