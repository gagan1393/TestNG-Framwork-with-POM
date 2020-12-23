package com.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.qa.constant.Constants;
import com.qa.factory.BasePage;
import com.qa.utils.ConfigReader;



public class Test_01_Login extends BaseTest
{
	private static String title;
	private LoginPage loginPage = new LoginPage(BasePage.getDriver());
	
	@Test(priority = 1)
	public void user_gets_the_title_of_the_page() {
		title = loginPage.getLoginPageTitle();
		System.out.println("Page title is: " + title);
	}

	@Test(priority = 2)
	public void page_title_should_be() {
		Assert.assertTrue(title.contains(Constants.LOGINPAGE_TITLE));
	}

	@Test(priority = 3)
	public void forgot_your_password_link_should_be_displayed() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test(priority = 4)
	public void user_enters_username() {
		loginPage.enterUserName(ConfigReader.getvalue("username"));
	}

	@Test(priority = 5)
	public void user_enters_password() {
		loginPage.enterPassword(ConfigReader.getvalue("password"));
	}

	@Test(priority = 6)
	public void user_clicks_on_login_button() {
		loginPage.clickOnLogin();
	}


}
