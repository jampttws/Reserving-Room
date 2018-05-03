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
		} catch (ClassNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		// String sql1 = "CREATE TABLE reservingData(reserveCode int,
		// roomNumber varchar(255), arrive varchar(255), depart varchar(255),
		// name varchar(255))";
		// String sql2 = "CREATE TABLE customer(name varchar(255), id int)";
		// String sql3 = "CREATE TABLE room(roomNumber varchar(255), people
		// int)";
		// s.execute(sql3);

	}

	public static List<String> SuggestRoom(String room) {

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

	public static List<String> emptyRoom(String roomname) {
		connect();
		List<String> list = new ArrayList<String>();
		String sql = "SELECT roomNumber FROM reservingData WHERE name LIKE '" + roomname + "%'";

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
			for (String st : all) {
				if (s.equals(st))
					all.remove(s);
			}
		}

		return all;
	}

	public static void collectName(String name, int id) {

		String sql = "INSERT INTO customer VALUES" + String.format("(%s,%d)", name, id);
		// Statement s = connect.createStatement();

		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	public static void updateReserving(int reserveCode, String roomCode, String arrive, String depart, String name) {
		String sql = "INSERT INTO reservingData VALUES"
				+ String.format("(%d,%s,%s,%s,%s)", reserveCode, roomCode, arrive, depart, name);
		try {
			s.execute(sql);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
  connect();
//  SuggestRoom("spr");
  emptyRoom("dlx");
 }
}