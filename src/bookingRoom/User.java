package bookingRoom;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * User class for manage user.
 * @author Narisa and Tanasorn
 *
 */
public class User {
	
	private String name;
	private String password;
	
	/**Create new user.*/
	public User(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	/**Get name.*/
	public String getName(){
		return name;
	}
	
	/**Get password.*/
	public String getPassword(){
		return password;
	}
	
	private static DatabaseManage db = DatabaseManage.getInstance();
	
	/**
	 * To encrypt the password.
	 * @param passcode
	 * @return encrypted password.
	 */
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
	
	/**
	 * Add the user to database.
	 * @param name
	 * @param password
	 */
	public static void addUser(String name, String password){
         db.addUser(name, setPasscode(password));

	}
	
	/**
	 * Get all member from Database.
	 * @return list of user.
	 */
	public static List<User> getMember(){
		return db.getUser();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) return false;
	    if (obj.getClass() != this.getClass()) return false;
	    User other = (User)obj;
		return this.getName().equals(other.getName()) && this.getPassword().equals(other.getPassword());
	}
	


	

}
