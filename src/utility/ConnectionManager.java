package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager
{
	public static Properties loadPropertiesFile() throws Exception, ClassNotFoundException {
		Properties prop = new Properties();	
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close(); 
		return prop;
	}
	
	public Connection getConnection() throws Exception, ClassNotFoundException
	{
		
		Properties prop = loadPropertiesFile();
		
		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		final String username = prop.getProperty("username");
		final String password = prop.getProperty("password");
		
		Connection con;
		
		Class.forName(driver);
		
		con = DriverManager.getConnection(url,username,password);
		
		return con;
		
	}
	
}

