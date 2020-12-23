package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.constant.Constants;



public class ConfigReader {

	private ConfigReader() {
		
	}

	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 * @return it return Properties prop reference
	 */
	
	
	public static String getvalue(String key) {

		Properties prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(Constants.CONFIGURATION_FILE_PATH);
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
