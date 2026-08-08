package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

public class LoginPage {

	private static final Logger log = LoggerUtils.getLogger(LoginPage.class);
	private final By txtUsername = By.id("user-name");
	private final By txtPassword = By.id("password");
	private final By btnLogin = By.id("login-button");
	private final By errorMessage = By.xpath("//h3[@data-test='error']");

	public void enterUsername(String username) {

		ElementUtils.enterText(txtUsername, username);
		ExtentReportManager.info("Enter username : "+username);
		log.info("Enter username : "+username);
	}

	public void enterPassword(String password) {

		ElementUtils.enterText(txtPassword, password);
		ExtentReportManager.info("Enter password : "+password);
		log.info("Enter password : "+password);
	}

	public void clickLogin() {
		ElementUtils.click(btnLogin);
		ExtentReportManager.info("Click Login button");
		log.info("Click Login button");
	}

	public boolean isErrorMessageDisplayed() {

		log.info("Checking whether error message is displayed");
		ExtentReportManager.info("Verify error message is displayed");
		boolean displayed = ElementUtils.isDisplayed(errorMessage);

		if (displayed) {

			ExtentReportManager.pass("Error message is displayed");

		} else {

			ExtentReportManager.fail("Error message is not displayed");
		}

		return displayed;
	}

	public String getErrorMessage() {

		log.info("Getting error message");
		String message = ElementUtils.getText(errorMessage);
		ExtentReportManager.info("Error message : " + message);
		return message;
	}
}