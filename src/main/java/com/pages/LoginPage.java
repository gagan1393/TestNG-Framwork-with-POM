package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constant.Constants;
import com.qa.factory.BasePage;
import com.qa.utils.TestUtil;

public class LoginPage {

	private WebDriver driver;

	// 1. By Locators: OR 
	private By emailId = By.id("email");
	private By password = By.id("passwd");
	private By signInButton = By.id("SubmitLogin");
	private By forgotPwdLink = By.linkText("Forgot your password?");

	// 2. Constructor of the page class:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// 3. page actions: features(behavior) of the page the form of methods:

	public String getLoginPageTitle() {
		return BasePage.getDriver().getTitle();
	}

	public boolean isForgotPwdLinkExist() {
		
		return TestUtil.waitForElementToBeVisible(BasePage.getDriver(), forgotPwdLink, Constants.EXPLICIT_WAIT).isDisplayed();
		//return driver.findElement(forgotPwdLink).isDisplayed();
	}

	public void enterUserName(String username) {
		BasePage.getDriver().findElement(emailId).sendKeys(username);
	}

	public void enterPassword(String pwd) {
		BasePage.getDriver().findElement(password).sendKeys(pwd);
	}

	public void clickOnLogin() {
		BasePage.getDriver().findElement(signInButton).click();
	}

}
