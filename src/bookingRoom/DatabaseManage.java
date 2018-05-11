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

	private Connection connect;
	private Statement s;
	
	private ConfigFileManager cf = ConfigFileManager.getInstance();
	private final String DATABASE = cf.getProperty("database.url");

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
			connect = DriverManager.getConnection(DATABASE, "jamp", "jamp");
			if (connect != null) {
				// System.out.println("Database Connected.");
				// connect.close();
			} else {
				System.out.println("Database Connect Failed.");
			}
			s = connect.createStatement();
			
//			 String sql3 = "CREATE TABLE customer(name varchar(255), email varchar(255), number bigint)";
//		     s.execute(sql3);
			
		} catch (ClassNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		// String sql1 = "CREATE TABLE reservingData(reserveCode int,
		// roomNumber varchar(255), arrive varchar(255), depart varchar(255),
		// name varchar(255))";
		// String sql2 = "CREATE TABLE customer(name varchar(255), id int)";
		// String sql4 = "DELETE FROM reservingData WHERE reserveCode =
		// 125520181";
		

	}

	/**
	 * Get list.
	 * @param want
	 * @param sql
	 * @return list
	 */
	public List<String> get(String want, String sql) {
		connect();
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
		String sql = "SELECT " + COLUMN_ROOM + " FROM " + TABLE_ROOM + " WHERE " + COLUMN_ROOM + " LIKE '" + room
				+ "%'";
		return get(COLUMN_ROOM, sql);
	}

	/**
	 * Get empty room.
	 * @param roomname.
	 * @return list of empty room.
	 */
	public List<String> emptyRoom(String roomname) {
		String sql = "SELECT " + COLUMN_ROOM + " FROM " + TABLE_RESERVE + " WHERE " + COLUMN_ROOM + " LIKE '" + roomname
				+ "%'";

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
		String sql = "SELECT " + COLUMN_ROOM + " FROM " + TABLE_RESERVE + " WHERE " + COLUMN_ROOM + " LIKE '" + roomname
				+ "%'" + " AND " + COLUMN_ARRIVE + " = '" + arrive + "' AND " + COLUMN_DEPART + " = '" + depart + "'";

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
		connect();
		String sql = "INSERT INTO " + TABLE_CUSTOMER + " VALUES" + String.format("('%s','%s',%d)", name, email, number);

		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/** Update reserving data to database. */
	public void updateReserving(int reserveCode, String roomCode, String arrive, String depart, String name) {
		connect();
		String sql = "INSERT INTO " + TABLE_RESERVE + " VALUES"
				+ String.format("(%d,'%s','%s','%s','%s')", reserveCode, roomCode, arrive, depart, name);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Get data that you want from database.
	 * @param want
	 * @param from
	 * @return list that you want.
	 */
	public List<String> databaseData(String want, String from) {
		String sql = String.format("SELECT %s FROM %s", want, from);
		return get(want, sql);
	}

	/**
	 * Get data that you want from database with name.
	 * @param want
	 * @param from
	 * @param name
	 * @return list that you want.
	 */
	public List<String> databaseData(String want, String from, String name) {
		String sql = String.format("SELECT %s FROM %s WHERE %s LIKE '", want, from, COLUMN_NAME) + name + "%'";
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
		connect();
		String sql = String.format("DELETE FROM %s WHERE %s = '%s'", from, where, want);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	/** Add user to database. */
	public void addUser(String name, String pass){
		connect();
		String sql = "INSERT INTO " + TABLE_USER + " VALUES" + String.format("('%s','%s')", name, pass);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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