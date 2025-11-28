package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String dataFromPropertiesFile(String Key) throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/commonData.properties");
		
		Properties p = new Properties();
		p.load(fis);
		
		String value = p.getProperty(Key);
		return value;
		
	}
	
}
