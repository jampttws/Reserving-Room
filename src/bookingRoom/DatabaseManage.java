package bookingRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManage {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection connect = DriverManager.getConnection("jdbc:mysql://35.194.158.90", "jamp", "jamp");
		if(connect != null){
			System.out.println("Database Connected.");
//			connect.close();
		} else {
			System.out.println("Database Connect Failed.");
		}	
		
//		Statement s = connect.createStatement();
//		
//		String sql = "CREATE TABLE reservingData";
//		s.execute(sql);
	}

}
