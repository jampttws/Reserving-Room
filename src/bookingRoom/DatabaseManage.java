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
	private static ConfigFileManager cf = ConfigFileManager.getInstance();
	private final static String DATABASE = cf.getProperty("database.url");

	// reserving table
	public static final String TABLE_RESERVE = "reservingData";
	public static final String COLUMN_RESERVECODE = "reservingCode";
	public static final String COLUMN_ROOM = "roomNumber";
	public static final String COLUMN_ARRIVE = "arrive";
	public static final String COLUMN_DEPART = "depart";
	public static final String COLUMN_NAME = "name";

	// customer table
	public static final String TABLE_CUSTOMER = "customer";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NUMBER = "number";

	public static final String TABLE_ROOM = "room";
	
	//user table
	public static final String TABLE_USER = "user";
	public static final String COLUMN_PASS = "password";

	public static void connect() {

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
		// String sql3 = "CREATE TABLE customer(name varchar(255), id bigint,
		// number bigint)";
		// s.execute(sql3);

	}

	/**get data*/
	public static List<String> get(String want, String sql) {
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

	/**room controller*/
	public static List<String> SuggestRoom(String room) {
		String sql = "SELECT " + COLUMN_ROOM + " FROM " + TABLE_ROOM + " WHERE " + COLUMN_ROOM + " LIKE '" + room
				+ "%'";
		return get(COLUMN_ROOM, sql);
	}

	/**room controller*/
	public static List<String> emptyRoom(String roomname) {
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

	/** roomcontroll */
	public static List<String> emptyRoom(String roomname, String arrive, String depart) {
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

	/** confirm */
	public static void collectName(String name, long id, long number) {
		connect();
		String sql = "INSERT INTO " + TABLE_CUSTOMER + " VALUES" + String.format("('%s',%d,%d)", name, id, number);

		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/** confirm */
	public static void updateReserving(int reserveCode, String roomCode, String arrive, String depart, String name) {
		connect();
		String sql = "INSERT INTO " + TABLE_RESERVE + " VALUES"
				+ String.format("(%d,'%s','%s','%s','%s')", reserveCode, roomCode, arrive, depart, name);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	/** manager */
	public static List<String> databaseData(String want, String from) {
		String sql = String.format("SELECT %s FROM %s", want, from);
		return get(want, sql);
	}

	/** manager */
	public static List<String> databaseData(String want, String from, String name) {
		String sql = String.format("SELECT %s FROM %s WHERE %s LIKE '", want, from, COLUMN_NAME) + name + "%'";
		return get(want, sql);
	}

	/**room controller*/
	public static List<Booked> bookedList(String Name) {
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

	/**room controller*/
	public static List<Booked> bookedList() {
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

	/**manager*/
	public static void remove(String from, String where, String want) {
		connect();
		String sql = String.format("DELETE FROM %s WHERE %s = '%s'", from, where, want);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	/**signin*/
	public static void addUser(String name, String pass){
		connect();
		String sql = "INSERT INTO " + TABLE_USER + " VALUES" + String.format("('%s','%s')", name, pass);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**signin*/
	public static List<User> getUser(){
		List<User> user = new ArrayList<User>();
		List<String> name = databaseData(COLUMN_NAME, TABLE_USER);
		List<String> pass = databaseData(COLUMN_PASS, TABLE_USER);
		for (int i = 0; i < name.size(); i++) {
			user.add(new User(name.get(i), pass.get(i)));
		}
		return user;
	}
	
	
	public static void main(String[] args){
		connect();
		addUser("jamp","jamp");
	}
	
}