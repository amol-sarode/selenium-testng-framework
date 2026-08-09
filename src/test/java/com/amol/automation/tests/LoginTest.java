package com.amol.automation.tests;

import org.testng.annotations.Test;

import com.amol.automation.actions.LoginActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.dataprovider.TestDataProvider;
import com.amol.automation.reports.ExtentReportManager;

public class LoginTest extends BaseTest {

	LoginActions loginActions = new LoginActions();

	@Test(description = "Verify login with valid user", groups = { "smoke",
			"regression" }, 
			
			dataProvider = "loginData",
			dataProviderClass = TestDataProvider.class)
	public void verifyLogin(String username, String password) {

		ExtentReportManager.createTest("Verify login with user : " + username);

		ExtentReportManager.getTest().assignCategory("Sauce application testing");

		loginActions.login(username, password);
	}
}
