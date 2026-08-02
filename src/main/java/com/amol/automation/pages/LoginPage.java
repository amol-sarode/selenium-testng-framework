package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.amol.automation.base.BasePage;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Login Page.
 */
public class LoginPage extends BasePage {

	private static final Logger log = LoggerUtils.getLogger(LoginPage.class);

	@FindBy(id = "user-name")
	private WebElement txtUsername;

	@FindBy(id = "password")
	private WebElement txtPassword;

	@FindBy(id = "login-button")
	private WebElement btnLogin;

	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorMessage;

	/**
	 * Enter username
	 */
	public LoginPage enterUsername(String username) {

		log.info("Entering username : {}", username);
		enterText(txtUsername, username);
		log.info("Successfully entered username : "+username);
		return this;
	}

	/**
	 * Enter password
	 */
	public LoginPage enterPassword(String password) {
		log.info("Entering password");
		enterText(txtPassword, password);
		return this;

	}

	/**
	 * Click Login button
	 */
	public LoginPage clickLogin() {

		log.info("Clicking Login button");

		click(btnLogin);

		waitForPageLoad();

		return this;

	}

	/**
	 * Login for invalid/locked user
	 */
	public LoginPage login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		return clickLogin();
	}

	/**
	 * Login for valid user
	 */
	public HomePage loginSuccessfully(String username, String password) {

		login(username, password);
		log.info("Login successful");
		return PageObjectManager.getHomePage();

	}

	/**
	 * Get error message
	 */
	public String getErrorMessage() {

		return getText(errorMessage);

	}

	/**
	 * Verify error displayed
	 */
	public boolean isErrorMessageDisplayed() {

		return isDisplayed(errorMessage);

	}

}