package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;

import com.amol.automation.factory.ActionObjectManager;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.CartPage;
import com.amol.automation.pages.CheckoutCompletePage;
import com.amol.automation.pages.CheckoutOverviewPage;
import com.amol.automation.pages.CheckoutPage;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Business actions for complete SauceDemo purchase workflow.
 *
 * Responsibilities: - Execute business operations - Coordinate Page Objects -
 * Create business-level report nodes - Return UI values to Test layer
 *
 * Assertions do not belong here.
 */
public class EndToEndActions {

	private static final Logger log = LoggerUtils.getLogger(EndToEndActions.class);

	private final HomePage homePage;
	private final ProductPage productPage;
	private final CartPage cartPage;
	private final CheckoutPage checkoutPage;
	private final CheckoutOverviewPage checkoutOverviewPage;
	private final CheckoutCompletePage checkoutCompletePage;
	private final LoginActions loginActions;

	public EndToEndActions() {

		homePage = PageObjectManager.getHomePage();

		productPage = PageObjectManager.getProductPage();

		cartPage = PageObjectManager.getCartPage();

		checkoutPage = PageObjectManager.getCheckoutPage();

		checkoutOverviewPage = PageObjectManager.getCheckoutOverviewPage();

		checkoutCompletePage = PageObjectManager.getCheckoutCompletePage();

		loginActions = ActionObjectManager.getLoginActions();
	}

	// =========================================================
	// Login
	// =========================================================

	public void login(String username, String password) {

		ExtentReportManager.createNode("Login");

		log.info("Starting login");

		loginActions.login(username, password);

		log.info("Login completed");
	}

	// =========================================================
	// Products
	// =========================================================

	public String getProductsPageTitle() {

		ExtentReportManager.createNode("Verify Products Page");

		log.info("Getting Products page title");

		return productPage.getProductsPageTitle();
	}

	public boolean isHomePageDisplayed() {

		ExtentReportManager.createNode("Verify Products Page Displayed");

		log.info("Checking Products page");

		return homePage.isHomePageDisplayed();
	}

	public void addProduct(String productName) {

		ExtentReportManager.createNode("Add Product - " + productName);

		log.info("Adding product: {}", productName);

		productPage.addProduct(productName);
	}

	public String getCartCount() {

		ExtentReportManager.createNode("Verify Cart Count");

		log.info("Getting cart count");

		return productPage.getCartCount();
	}

	public void openCart() {

		ExtentReportManager.createNode("Click Cart");

		log.info("Clicking Cart");

		productPage.clickCart();
	}

	// =========================================================
	// Cart
	// =========================================================

	public String getCartTitle() {

		ExtentReportManager.createNode("Verify Cart Page");

		log.info("Getting Cart page title");

		return cartPage.getCartTitle();
	}

	public String getCartProductName() {

		ExtentReportManager.createNode("Verify Cart Product");

		log.info("Getting product name from Cart");

		return cartPage.getProductName();
	}

	public void proceedToCheckout() {

		ExtentReportManager.createNode("Click Checkout");

		log.info("Clicking Checkout");

		cartPage.clickCheckout();
	}

	// =========================================================
	// Checkout
	// =========================================================

	public String getCheckoutTitle() {

		ExtentReportManager.createNode("Verify Checkout Information Page");

		log.info("Getting Checkout Information page title");

		return checkoutPage.getCheckoutTitle();
	}

	public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {

		ExtentReportManager.createNode("Enter Checkout Information");

		log.info("Entering checkout information");

		checkoutPage.enterFirstName(firstName);

		checkoutPage.enterLastName(lastName);

		checkoutPage.enterPostalCode(postalCode);
	}

	public void continueCheckout() {

		ExtentReportManager.createNode("Click Continue");

		log.info("Clicking Continue");

		checkoutPage.clickContinue();
	}

	// =========================================================
	// Checkout Overview
	// =========================================================

	public String getOverviewTitle() {

		ExtentReportManager.createNode("Verify Checkout Overview Page");

		log.info("Getting Checkout Overview page title");

		return checkoutOverviewPage.getOverviewTitle();
	}

	public void finishOrder() {

		ExtentReportManager.createNode("Click Finish");

		log.info("Clicking Finish");

		checkoutOverviewPage.clickFinish();
	}

	// =========================================================
	// Order Complete
	// =========================================================

	public String getCompletePageTitle() {

		ExtentReportManager.createNode("Verify Order Completion Page");

		log.info("Getting Order Completion page title");

		return checkoutCompletePage.getCompletePageTitle();
	}

	public String getThankYouMessage() {

		ExtentReportManager.createNode("Verify Order Confirmation");

		log.info("Getting order confirmation message");

		return checkoutCompletePage.getThankYouMessage();
	}

	public void backToProducts() {

		ExtentReportManager.createNode("Click Back To Products");

		log.info("Clicking Back To Products");

		checkoutCompletePage.clickBackHome();
	}
}