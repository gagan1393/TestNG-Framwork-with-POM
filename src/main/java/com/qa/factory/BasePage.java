package com.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import com.qa.utils.ConfigReader;
import com.qa.utils.OptionsManager;
import com.qa.utils.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Gagan Prashar
 *
 */
public class BasePage {

	public WebDriver driver = null;
	public OptionsManager optionsManager;
	public EventFiringWebDriver e_driver;
	public WebEventListener eventlistener;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Object highlight;

	/**
	 * This method is used to initialize the browser on the basis of given
	 * browser...
	 * 
	 * @param browser
	 * @return This return webdriver driver
	 */
	public WebDriver init_driver(String browser) {
		System.out.println("browser value is : " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("-incognito");
			options.addArguments("-ignore-certificate-errors");
			tlDriver.set(new ChromeDriver(options));
		} 
		else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} 
		else if (browser.equals("Edge")) {
			tlDriver.set(new EdgeDriver());
		} 
		else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

//		e_driver = new EventFiringWebDriver(driver);
//		eventlistener = new WebEventListener();
//		e_driver.register(eventlistener);
//		driver = e_driver;
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}

	private void init_remoteDriver(String browser, String browserVersion) {

		System.out.println("Running test on remote grid: " + browser);

		if (browser.equals("chrome")) {

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(ConfigReader.getvalue("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browser.equals("firefox")) {

			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);

			try {
				tlDriver.set(new RemoteWebDriver(new URL(ConfigReader.getvalue("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to take the screenshot It will return the path of
	 * screenshot
	 */
	 public static String getScreenshots(WebDriver driver, ITestResult result)
	 {
		 if(ITestResult.FAILURE==result.getStatus())
		 {
			 try
			 {
				 TakesScreenshot ts=(TakesScreenshot)driver;
				 File source=ts.getScreenshotAs(OutputType.FILE);
				 String SCREENSHOTS_PATH = System.getProperty("user.dir") + "./Screenshots/"+result.getName()+".png";
				 FileUtils.copyFile(source, new File(SCREENSHOTS_PATH));
				 System.out.println("Screenshot Taken");
				 byte[] ImagesBytes = IOUtils.toByteArray(new FileInputStream(SCREENSHOTS_PATH));
				 return Base64.getEncoder().encodeToString(ImagesBytes);
			 }
			 catch (Exception e)
			 {

				 System.out.println("Exception while taking screenshot "+e.getMessage());
			 }

		 }
		 return null;

	 }

}
