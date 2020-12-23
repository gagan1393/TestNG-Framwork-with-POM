package com.qa.constant;



public class Constants {

	public static final String TEST_DATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/TestData/TestData.xlsx";
	public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
	public static final String CONFIGURATION_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/config/Config.properties";
	public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "/src/test/resources/ExtentReports/";
		
	public static final int PAGE_LOAD_TIMEOUT = 30;
	public static final int IMPLICIT_WAIT = 15;
	public static final int EXPLICIT_WAIT = 15;
	public static final int WEBDRIVER_WAIT = 10;
	

	public static final int SHORT_WAIT = 1000;
	public static final int MEDIUM_WAIT = 3000;
	public static final int LONG_WAIT = 5000;
	
	public static final String LOGINPAGE_TITLE = "Login - My Store";
}

