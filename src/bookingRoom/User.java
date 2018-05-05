package bookingRoom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private String password;
	
	public User(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public static void addUser(String name, String password){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("src/bookingRoom/user.txt"));
			bw.write(name + ";" + password + ";");
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static List<User> list = new ArrayList<User>();
	
	public static List<User> getMember(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/bookingRoom/user.txt"));

			while (br.ready()){
				String phrase = br.readLine().trim();
				String[] user = phrase.split(";");
				list.add(new User(user[0], user[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	

}
