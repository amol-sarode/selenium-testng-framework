package com.amol.automation.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.base.BaseTest;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentTest;


public class LoginTest extends BaseTest {


	private static final Logger log =
			LoggerUtils.getLogger(LoginTest.class);



	@Test(
			description = "Verify login with valid user",
			groups = {"smoke", "regression"}
	)
	public void verifyValidLogin() {


		log.info("TEST STARTED : Verify Valid Login");


		ExtentTest loginNode =
				ExtentReportManager
				.getTest()
				.createNode("Valid Login Test Steps");



		String username = "standard_user";
		String password = "secret_sauce";



		loginNode.info("Launch SauceDemo application");


		loginNode.info("Enter username : " + username);


		loginNode.info("Enter password");


		loginNode.info("Click Login button");



		HomePage homePage =
				PageObjectManager
				.getLoginPage()
				.loginSuccessfully(username, password);



		loginNode.info("Verify Products page displayed");


		Assert.assertTrue(
				homePage.isHomePageDisplayed(),
				"Products page is not displayed after login"
		);


		loginNode.pass("Products page displayed successfully");



		loginNode.info("Verify Products page title");


		Assert.assertEquals(
				homePage.getHomePageTitle(),
				"Products",
				"Incorrect page title after login"
		);


		loginNode.pass("Products page title verified");



		loginNode.info("Verify menu button displayed");


		Assert.assertTrue(
				homePage.isMenuDisplayed(),
				"Menu button is not displayed"
		);


		loginNode.pass("Menu button displayed successfully");


		loginNode.pass("Valid login test completed successfully");


		log.info("TEST PASSED : Verify Valid Login");

	}





	@Test(
			description = "Verify login with locked user",
			groups = {"regression"}
	)
	public void verifyLockedUserLogin() {


		log.info("TEST STARTED : Verify Locked User Login");


		ExtentTest loginNode =
				ExtentReportManager
				.getTest()
				.createNode("Locked User Login Test Steps");



		LoginPage loginPage =
				PageObjectManager.getLoginPage();



		loginNode.info("Enter username : locked_out_user");


		loginNode.info("Enter password : secret_sauce");


		loginNode.info("Click Login button");



		loginPage.login(
				"locked_out_user",
				"secret_sauce"
		);



		loginNode.info("Verify error message displayed");



		Assert.assertTrue(
				loginPage.isErrorMessageDisplayed(),
				"Error message is not displayed"
		);



		loginNode.pass("Error message displayed successfully");



		loginNode.info("Verify locked user error message");



		Assert.assertTrue(
				loginPage.getErrorMessage()
				.contains("locked out"),
				"Locked user error message not displayed"
		);



		loginNode.pass("Locked user message verified");



		loginNode.pass("Locked user login test completed successfully");


		log.info("TEST PASSED : Verify Locked User Login");


	}





	@Test(
			description = "Verify login with invalid credentials",
			groups = {"regression"}
	)
	public void verifyInvalidLogin() {


		log.info("TEST STARTED : Verify Invalid Login");



		ExtentTest loginNode =
				ExtentReportManager
				.getTest()
				.createNode("Invalid Login Test Steps");



		LoginPage loginPage =
				PageObjectManager.getLoginPage();



		loginNode.info("Enter invalid username : invalid_user");


		loginNode.info("Enter invalid password : wrong_password");


		loginNode.info("Click Login button");



		loginPage.login(
				"invalid_user",
				"wrong_password"
		);



		loginNode.info("Verify error message displayed");



		Assert.assertTrue(
				loginPage.isErrorMessageDisplayed(),
				"Error message is not displayed"
		);



		loginNode.pass("Error message displayed successfully");



		loginNode.info("Verify invalid credential message");



		Assert.assertTrue(
				loginPage.getErrorMessage()
				.contains("Username and password do not match"),
				"Invalid login error message not displayed"
		);



		loginNode.pass("Invalid credentials message verified");



		loginNode.pass("Invalid login test completed successfully");


		log.info("TEST PASSED : Verify Invalid Login");


	}

}