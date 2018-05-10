package bookingRoom;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	
	public static String setPasscode(String passcode){
		String collectPass = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] detal = digest.digest(passcode.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte bytes : detal) {
                  stringBuffer.append(String.format("%02x", bytes & 0xff));
            
            }
            collectPass = stringBuffer.toString();

        } catch (NoSuchAlgorithmException e) {
        	System.out.print(e.getMessage());
        }
		return collectPass;
    }
	
	public static void addUser(String name, String password){
         DatabaseManage.addUser(name, setPasscode(password));
	}
	
	public static List<User> getMember(){
		return DatabaseManage.getUser();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) return false;
	    if (obj.getClass() != this.getClass()) return false;
	    User other = (User)obj;
		return this.getName().equals(other.getName()) && this.getPassword().equals(other.getPassword());
	}
	


	

}
