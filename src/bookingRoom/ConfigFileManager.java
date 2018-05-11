package bookingRoom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileManager {
	
	
	private static ConfigFileManager instance = new ConfigFileManager();;
	public static final String CONFIGFILE = "reservation.config";
	private Properties properties;
	
	private ConfigFileManager(){
		readFile(CONFIGFILE);
	}
	
	public static ConfigFileManager getInstance(){
		return instance;
	}
	
	public void readFile(String filename){
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream input = loader.getResourceAsStream(filename);
		
		if(input == null){
			System.out.println("Can not open file"); return;
		}
		properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				input.close();
			} catch (IOException e) {
			}
		}
	}
	
	public String getProperty(String name){
		return properties.getProperty(name,"");
	}
	
	public Properties getProperty(){
		return this.properties;
	}
	

}
