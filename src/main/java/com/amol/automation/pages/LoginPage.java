package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Login Page.
 *
 * Contains login-page locators and page-level actions.
 */
public class LoginPage {

	private static final Logger log = LoggerUtils.getLogger(LoginPage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By txtUsername = By.id("user-name");

	private final By txtPassword = By.id("password");

	private final By btnLogin = By.id("login-button");

	private final By errorMessage = By.xpath("//h3[@data-test='error']");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Enter username.
	 *
	 * @param username username
	 */
	public void enterUsername(String username) {

		ElementUtils.enterText(txtUsername, username);

		log.info("Username entered successfully");

		ExtentReportManager.info("Enter username : " + username);
	}

	/**
	 * Enter password.
	 *
	 * @param password password
	 */
	public void enterPassword(String password) {

		ElementUtils.enterText(txtPassword, password);

		log.info("Password entered successfully");

		/*
		 * Do not log the actual password.
		 */
		ExtentReportManager.info("Password entered successfully");
	}

	/**
	 * Click Login button.
	 */
	public void clickLogin() {

		ElementUtils.click(btnLogin);

		log.info("Login button clicked");

		ExtentReportManager.info("Click Login button");
	}

	/**
	 * Verify login error message is displayed.
	 *
	 * Page layer only checks the UI condition. Test layer is responsible for
	 * assertion.
	 *
	 * @return true if error message is displayed
	 */
	public boolean isErrorMessageDisplayed() {

		log.info("Checking whether login error message is displayed");

		ExtentReportManager.info("Verify login error message is displayed");

		return ElementUtils.isDisplayed(errorMessage);
	}

	/**
	 * Get login error message.
	 *
	 * @return error message text
	 */
	public String getErrorMessage() {

		log.info("Getting login error message");

		String message = ElementUtils.getText(errorMessage);

		ExtentReportManager.info("Error message : " + message);

		return message;
	}
}
