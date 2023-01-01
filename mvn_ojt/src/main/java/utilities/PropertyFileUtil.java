package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {

	public static String getValueForKey(String key) throws Throwable
	{
		Properties config = new Properties();
		config.load(new FileInputStream("D:\\testmind\\mvn_ojt\\PropertyFile\\Environment.properties"));
		return config.getProperty(key);
		
		
		
	}
	
	
	
}
