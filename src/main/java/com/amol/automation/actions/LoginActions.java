package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;

import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Business actions for Login functionality.
 *
 * Responsibilities: - Perform login business operation - Coordinate LoginPage -
 * Create high-level business reporting nodes - Provide login-related UI state
 * to Test layer
 *
 * Assertions belong to the Test layer.
 *
 * Reporting Flow:
 *
 * Test | v LoginActions.login() | v ExtentReportManager.createNode("Login") |
 * +--> LoginPage.enterUsername() | --> INFO / PASS / FAIL | +-->
 * LoginPage.enterPassword() | --> INFO / PASS / FAIL | +-->
 * LoginPage.clickLogin() --> INFO / PASS / FAIL
 */
public class LoginActions {

	private static final Logger log = LoggerUtils.getLogger(LoginActions.class);

	private final LoginPage loginPage;

	// =========================================================
	// Constructor
	// =========================================================

	/**
	 * Initializes Login Page Object.
	 */
	public LoginActions() {

		loginPage = PageObjectManager.getLoginPage();
	}

	// =========================================================
	// Login
	// =========================================================

	/**
	 * Performs complete login business operation.
	 *
	 * Creates one business-level Login node.
	 *
	 * Detailed UI reporting is handled by LoginPage.
	 *
	 * @param username username
	 * @param password password
	 */
	public void login(String username, String password) {

		ExtentReportManager.createNode("Login");

		log.info("===== Login Process Started =====");

		try {

			loginPage.enterUsername(username);
			loginPage.enterPassword(password);
			loginPage.clickLogin();
			log.info("===== Login Process Completed =====");

		} catch (Exception e) {

			log.error("Login process failed", e);

			throw e;
		}
	}

	// =========================================================
	// Login Error
	// =========================================================

	/**
	 * Checks whether login error message is displayed.
	 *
	 * No assertion is performed here.
	 *
	 * @return true if login error message is displayed
	 */
	public boolean isErrorMessageDisplayed() {

		log.info("Checking login error message");

		return loginPage.isErrorMessageDisplayed();
	}

	// =========================================================
	// Get Login Error
	// =========================================================

	/**
	 * Gets login error message.
	 *
	 * @return login error message
	 */
	public String getErrorMessage() {

		log.info("Getting login error message");

		return loginPage.getErrorMessage();
	}
}