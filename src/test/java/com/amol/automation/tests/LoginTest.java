package com.amol.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.actions.LoginActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.constants.FrameworkConstants;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ExcelUtils;

/**
 * Test class for SauceDemo Login functionality.
 *
 * Covers: - Valid login - Invalid login - Excel-based test data
 *
 * Screenshot handling is managed by TestListener.
 */
public class LoginTest extends BaseTest {

	private final LoginActions loginActions = new LoginActions();

	// =========================================================
	// Valid Login
	// =========================================================

	@Test(description = "Verify login with valid user", groups = { "smoke", "regression" })
	public void verifyLogin() {

		ExtentReportManager.createTest("Verify login with valid user");

		ExtentReportManager.getTest().assignCategory("Sauce application testing");

		String[] loginData = ExcelUtils.getRowData(FrameworkConstants.LOGIN_EXCEL_FILE, FrameworkConstants.LOGIN_SHEET,
				1);

		String username = loginData[0];
		String password = loginData[1];

		loginActions.login(username, password);

		ExtentReportManager.pass("Valid login completed successfully");
	}

	// =========================================================
	// Invalid Login
	// =========================================================

	@Test(description = "Verify login with invalid credentials", groups = { "negative", "regression" })
	public void verifyInvalidLogin() {

		ExtentReportManager.createTest("Verify login with invalid credentials");

		ExtentReportManager.getTest().assignCategory("Negative Login Testing");

		String[] loginData = ExcelUtils.getRowData(FrameworkConstants.LOGIN_EXCEL_FILE, FrameworkConstants.LOGIN_SHEET,
				3);

		String username = loginData[0];
		String password = loginData[1];
		String expectedErrorMessage = loginData[2];

		/*
		 * Invalid login.
		 *
		 * SauceDemo displays the red error message here.
		 */
		loginActions.login(username, password);

		/*
		 * Read actual error message from UI.
		 */
		String actualErrorMessage = loginActions.getErrorMessage();

		/*
		 * Test validation.
		 *
		 * If this assertion fails:
		 *
		 * TestNG -> TestListener.onTestFailure()
		 *
		 * -> Screenshot captured
		 *
		 * -> Screenshot attached to Extent Report
		 */
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
				"Login error message does not match expected message");

		ExtentReportManager.pass("Invalid login error message verified successfully");
	}
}