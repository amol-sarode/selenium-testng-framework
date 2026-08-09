package com.amol.automation.tests;

import org.testng.annotations.Test;

import com.amol.automation.actions.EndToEndActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.listeners.RetryAnalyzer;
import com.amol.automation.reports.ExtentReportManager;

/**
 * End-to-end test class for SauceDemo purchase functionality.
 *
 * Covers the complete purchase workflow: Login -> Product verification -> Add
 * product to cart -> Cart verification -> Checkout information -> Checkout
 * overview -> Complete order -> Back to products
 */
public class EndToEndTest extends BaseTest {

	private final EndToEndActions endToEndActions = new EndToEndActions();

	// =========================================================
	// End-to-End Purchase Flow
	// =========================================================

	@Test(description = "Verify complete purchase flow with valid user", groups = { "smoke",
			"regression" }, retryAnalyzer = RetryAnalyzer.class)
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