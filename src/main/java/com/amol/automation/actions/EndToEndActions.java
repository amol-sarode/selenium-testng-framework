package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.CartPage;
import com.amol.automation.pages.CheckoutCompletePage;
import com.amol.automation.pages.CheckoutOverviewPage;
import com.amol.automation.pages.CheckoutPage;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentTest;

/**
 * Business actions for the complete SauceDemo purchase flow.
 *
 * Handles the end-to-end business workflow from login through successful order
 * completion.
 */
public class EndToEndActions {

	private static final Logger log = LoggerUtils.getLogger(EndToEndActions.class);

	private final LoginPage loginPage;
	private final HomePage homePage;
	private final ProductPage productPage;
	private final CartPage cartPage;
	private final CheckoutPage checkoutPage;
	private final CheckoutOverviewPage checkoutOverviewPage;
	private final CheckoutCompletePage checkoutCompletePage;

	/**
	 * Initializes all required Page Objects.
	 */
	public EndToEndActions() {

		loginPage = PageObjectManager.getLoginPage();
		homePage = PageObjectManager.getHomePage();
		productPage = PageObjectManager.getProductPage();
		cartPage = PageObjectManager.getCartPage();
		checkoutPage = PageObjectManager.getCheckoutPage();
		checkoutOverviewPage = PageObjectManager.getCheckoutOverviewPage();
		checkoutCompletePage = PageObjectManager.getCheckoutCompletePage();
	}

	/**
	 * Verifies the complete purchase flow with a valid user.
	 *
	 * @param username   valid username
	 * @param password   valid password
	 * @param firstName  customer first name
	 * @param lastName   customer last name
	 * @param postalCode customer postal code
	 */
	public void verifyEndToEndPurchaseFlow(String username, String password, String firstName, String lastName,
			String postalCode) {

		ExtentTest e2eNode = ExtentReportManager.createNode("End To End Purchase Flow");

		e2eNode.info("[===== E2E Purchase Flow Started =====]");

		log.info("===== SauceDemo E2E Purchase Started =====");

		// =====================================================
		// Login
		// =====================================================

		ExtentTest loginNode = e2eNode.createNode("Login");

		loginNode.info("Enter username");

		loginPage.enterUsername(username);

		loginNode.info("Enter password");

		loginPage.enterPassword(password);

		loginNode.info("Click Login button");

		loginPage.clickLogin();

		Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");

		loginNode.pass("Login completed and Products page displayed");

		log.info("Login completed successfully");

		// =====================================================
		// Product Verification
		// =====================================================

		ExtentTest productNode = e2eNode.createNode("Product Verification");

		productNode.info("Verify Products page title");

		Assert.assertEquals(productPage.getProductsPageTitle(), "Products", "Products page title mismatch");

		productNode.pass("Products page verified successfully");

		log.info("Products page verified");

		// =====================================================
		// Add Product
		// =====================================================

		ExtentTest addProductNode = e2eNode.createNode("Add Product");

		addProductNode.info("Add Sauce Labs Backpack to cart");

		productPage.addBackpack();

		addProductNode.pass("Sauce Labs Backpack added to cart");

		addProductNode.info("Verify cart count");

		Assert.assertEquals(productPage.getCartCount(), "1", "Cart count is not updated");

		addProductNode.pass("Cart count verified as 1");

		log.info("Product added to cart successfully");

		// =====================================================
		// Cart Verification
		// =====================================================

		ExtentTest cartNode = e2eNode.createNode("Cart Verification");

		cartNode.info("Open shopping cart");

		productPage.clickCart();

		cartNode.pass("Shopping cart opened successfully");

		cartNode.info("Verify cart title");

		Assert.assertEquals(cartPage.getCartTitle(), "Your Cart", "Cart page title mismatch");

		cartNode.pass("Cart title verified successfully");

		cartNode.info("Verify product in cart");

		Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack", "Incorrect product added");

		cartNode.pass("Sauce Labs Backpack verified in cart");

		log.info("Cart verified successfully");

		// =====================================================
		// Checkout Information
		// =====================================================

		ExtentTest checkoutNode = e2eNode.createNode("Checkout Information");

		checkoutNode.info("Click Checkout button");

		cartPage.clickCheckout();

		checkoutNode.pass("Checkout button clicked successfully");

		checkoutNode.info("Verify checkout information page");

		Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout: Your Information",
				"Checkout information page mismatch");

		checkoutNode.pass("Checkout information page verified");

		checkoutNode.info("Enter first name");

		checkoutPage.enterFirstName(firstName);

		checkoutNode.pass("First name entered successfully");

		checkoutNode.info("Enter last name");

		checkoutPage.enterLastName(lastName);

		checkoutNode.pass("Last name entered successfully");

		checkoutNode.info("Enter postal code");

		checkoutPage.enterPostalCode(postalCode);

		checkoutNode.pass("Postal code entered successfully");

		checkoutNode.info("Click Continue button");

		checkoutPage.clickContinue();

		checkoutNode.pass("Continue button clicked successfully");

		// =====================================================
		// Checkout Overview
		// =====================================================

		ExtentTest overviewNode = e2eNode.createNode("Checkout Overview");

		overviewNode.info("Verify checkout overview page");

		Assert.assertEquals(checkoutOverviewPage.getOverviewTitle(), "Checkout: Overview",
				"Checkout overview page mismatch");

		overviewNode.pass("Checkout overview page verified successfully");

		log.info("Checkout overview verified");

		// =====================================================
		// Complete Order
		// =====================================================

		ExtentTest completeNode = e2eNode.createNode("Complete Order");

		completeNode.info("Click Finish button");

		checkoutOverviewPage.clickFinish();

		completeNode.pass("Finish button clicked successfully");

		completeNode.info("Verify checkout complete page");

		Assert.assertEquals(checkoutCompletePage.getCompletePageTitle(), "Checkout: Complete!",
				"Checkout complete page title mismatch");

		completeNode.pass("Checkout complete page verified");

		completeNode.info("Verify thank-you message");

		Assert.assertEquals(checkoutCompletePage.getThankYouMessage(), "Thank you for your order!",
				"Thank-you message mismatch");

		completeNode.pass("Thank-you message verified successfully");

		log.info("Order completed successfully");

		// =====================================================
		// Back To Products
		// =====================================================

		ExtentTest backHomeNode = e2eNode.createNode("Back To Products");

		backHomeNode.info("Click Back Home button");

		checkoutCompletePage.clickBackHome();

		backHomeNode.pass("Back Home button clicked successfully");

		backHomeNode.info("Verify Products page after order");

		Assert.assertEquals(productPage.getProductsPageTitle(), "Products", "User is not redirected to Products page");

		backHomeNode.pass("User redirected to Products page successfully");

		log.info("Back to Products page verified");

		// =====================================================
		// Flow Completed
		// =====================================================

		e2eNode.pass("[===== E2E Purchase Flow Completed Successfully =====]");

		log.info("===== SauceDemo E2E Purchase Completed =====");
	}
}