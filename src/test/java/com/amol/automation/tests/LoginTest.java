package com.amol.automation.tests;

import org.testng.annotations.Test;

import com.amol.automation.actions.LoginActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.reports.ExtentReportManager;

public class LoginTest extends BaseTest {


	LoginActions loginActions = new LoginActions();
	@Test(description = "Verify login with valid user", groups = { "smoke", "regression" })
	public void verifyValidLogin() {
		
		ExtentReportManager.createTest("Verify login with valid user");
		ExtentReportManager.getTest().assignCategory("Sauce application testing");
		loginActions.login("standard_user", "secret_sauce");
	}
}