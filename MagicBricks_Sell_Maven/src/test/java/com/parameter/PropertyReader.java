package com.parameter;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
public class PropertyReader {
	public static Properties loadPropertiesFile()
	{
		Properties property = new Properties();
	try {
		property.load( new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//PropertyData//ProfileData.properties"));
	} catch (IOException e) {
		e.printStackTrace();
	}
		return property;
	}
}