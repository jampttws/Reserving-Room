package bookingRoom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		FileOutputStream os;
		try {
			 os = new FileOutputStream("src/bookingRoom/user.txt",true);
	            
	            os.write(name.getBytes());
	            os.write(";".getBytes());
	            os.write(password.getBytes());
	            os.write(";".getBytes());
	            os.write("\n".getBytes());
	            os.close();

		} catch (IOException e) {
			System.out.println("file not found");
		}
	}
	
	public static List<User> list = new ArrayList<User>();
	
	public static List<User> getMember(){

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("src/bookingRoom/user.txt"));
			
			while (br.ready()){
				String phrase = br.readLine().trim();
				String[] user = phrase.split(";");
				list.add(new User(user[0], user[1]));
			}
		} catch (IOException e) {
			System.out.println("file not found");
		}
		return list;
	}
	
	public static List<User> getUser(){
		return list;
	}

	

}
