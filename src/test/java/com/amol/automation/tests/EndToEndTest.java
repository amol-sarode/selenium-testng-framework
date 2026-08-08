package com.amol.automation.tests;

import org.testng.annotations.Test;

import com.amol.automation.actions.EndToEndActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.reports.ExtentReportManager;

public class EndToEndTest extends BaseTest {

	EndToEndActions endToEndActions = new EndToEndActions();

	@Test(description = "Verify complete purchase flow with valid user", groups = { "smoke", "regression" })
	public void verifyEndToEndPurchaseFlow() {

		ExtentReportManager.createTest("Verify complete purchase flow with valid user");
		ExtentReportManager.getTest().assignCategory("Sauce application testing");
		String username = "standard_user";
		String password = "secret_sauce";
		String firstName = "Amol";
		String lastName = "Sarode";
		String postalCode = "382481";
		endToEndActions.verifyEndToEndPurchaseFlow(username, password, firstName, lastName, postalCode);
	}
}