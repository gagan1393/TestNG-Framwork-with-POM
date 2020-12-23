package com.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.pages.LoginPage;
import com.qa.factory.BasePage;
import com.qa.utils.ConfigReader;




public class BaseTest {
	public BasePage basePage;
	public LoginPage loginPage;
	public WebDriver driver;

	
	@BeforeTest
	public void setUp() {

		basePage = new BasePage();

		driver = basePage.init_driver(ConfigReader.getvalue("Browser"));
		loginPage = new LoginPage(driver);
		driver.get(ConfigReader.getvalue("url"));
		

	}

	@AfterMethod
	public void teardown(ITestResult result)
	{
		BasePage.getScreenshots(driver, result);
	}
	
	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}


}





