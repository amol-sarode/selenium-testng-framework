package com.amol.automation.tests;

import org.testng.annotations.Test;

import com.amol.automation.actions.ProductActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.listeners.RetryAnalyzer;
import com.amol.automation.reports.ExtentReportManager;

/**
 * Test class for SauceDemo product functionality.
 *
 * Covers: - Login with valid user - Product page verification - Add product to
 * cart - Cart count verification
 */
public class ProductTest extends BaseTest {

	private final ProductActions productActions = new ProductActions();

	// =========================================================
	// Product Add To Cart
	// =========================================================

	@Test(description = "Verify product add to cart with valid user", groups = { "smoke",
			"regression" }, retryAnalyzer = RetryAnalyzer.class)
	public void verifyProductAddToCart() {

		ExtentReportManager.createTest("Verify Product Add To Cart");

		ExtentReportManager.getTest().assignCategory("Product Testing");

		String username = "standard_user";
		String password = "secret_sauce";

		productActions.verifyProductAddToCart(username, password);
	}
}