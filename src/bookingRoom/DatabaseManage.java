package bookingRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManage {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection connect = DriverManager.getConnection("jdbc:h2:/Users/user/Documents/workspace/Reserving-Room");
		Class.forName("org.h2.Driver");
		if(connect != null){
			System.out.println("Database Connected.");
			connect.close();
		} else {
			System.out.println("Database Connect Failed.");
		}	
		
		Statement s = connect.createStatement();
		
		String sql = "CREATE TABLE 'reservingData'";
		s.execute(sql);
	}

}
