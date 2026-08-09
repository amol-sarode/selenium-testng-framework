package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;

import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Business actions for Login functionality.
 *
 * Acts as an intermediate layer between test classes and LoginPage.
 */
public class LoginActions {

	private static final Logger log = LoggerUtils.getLogger(LoginActions.class);

	private final LoginPage loginPage;

	/**
	 * Initializes LoginPage.
	 */
	public LoginActions() {

		loginPage = PageObjectManager.getLoginPage();
	}

	// =========================================================
	// Login
	// =========================================================

	/**
	 * Performs login using supplied credentials.
	 *
	 * @param username username
	 * @param password password
	 */
	public void login(String username, String password) {

		ExtentReportManager.createNode("Login Process");

		ExtentReportManager.info("[===== Login Process Started =====]");

		log.info("===== Login Process Started =====");

		loginPage.enterUsername(username);

		loginPage.enterPassword(password);

		loginPage.clickLogin();

		ExtentReportManager.info("[===== Login Process Completed =====]");

		log.info("===== Login Process Completed =====");
	}

	// =========================================================
	// Login Error
	// =========================================================

	/**
	 * Verifies whether login error message is displayed.
	 *
	 * @return true if error message is displayed
	 */
	public boolean isErrorMessageDisplayed() {

		log.info("Checking login error message");

		return loginPage.isErrorMessageDisplayed();
	}

	/**
     * Gets login error message.
     *
     * @return error message
     */
    public String getErrorMessage() {

        log.info(
                "Getting login error message"
        );

        return loginPage.getErrorMessage();
    }
}
