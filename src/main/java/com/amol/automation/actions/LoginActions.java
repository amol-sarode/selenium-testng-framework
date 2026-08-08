package com.amol.automation.actions;

import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.reports.ExtentReportManager;

public class LoginActions {

	private final LoginPage loginPage;

	public LoginActions() {

		loginPage = PageObjectManager.getLoginPage();
	}

	public void login(String username, String password) {

		ExtentReportManager.createNode("Login Process");
		ExtentReportManager.info("[===== Login process started =====]");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		ExtentReportManager.info("[===== Login process completed =====]");
	}
}