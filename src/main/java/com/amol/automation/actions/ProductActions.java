package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.amol.automation.factory.ActionObjectManager;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentTest;

/**
 * Business actions for Product functionality.
 *
 * Handles the product add-to-cart business flow.
 */
public class ProductActions {

	private static final Logger log = LoggerUtils.getLogger(ProductActions.class);

	private final HomePage homePage;
	private final ProductPage productPage;
	private final LoginActions loginActions;

	/**
	 * Initializes required Page Objects and Actions.
	 */
	public ProductActions() {

		homePage = PageObjectManager.getHomePage();

		productPage = PageObjectManager.getProductPage();

		loginActions = ActionObjectManager.getLoginActions();
	}

	/**
	 * Verifies that a valid user can login and add a product to the cart.
	 *
	 * @param username valid username
	 * @param password valid password
	 */
	public void verifyProductAddToCart(String username, String password) {

		ExtentTest productNode = ExtentReportManager.createNode("Product Add To Cart Flow");

		productNode.info("[===== Product Flow Started =====]");

		log.info("===== Product Flow Started =====");

		// =====================================================
		// Login
		// =====================================================

		ExtentTest loginNode = productNode.createNode("Login");

		loginActions.login(username, password);

		loginNode.pass("Login completed successfully");

		loginNode.info("Verify Products page is displayed");

		Assert.assertTrue(homePage.isHomePageDisplayed(), "Products page is not displayed after login");

		loginNode.pass("Products page displayed successfully");

		log.info("Login completed successfully");

		// =====================================================
		// Product Page Verification
		// =====================================================

		ExtentTest productPageNode = productNode.createNode("Product Page Verification");

		productPageNode.info("Verify Products page title");

		Assert.assertEquals(productPage.getProductsPageTitle(), "Products", "Products page title mismatch");

		productPageNode.pass("Products page title verified successfully");

		log.info("Products page verified successfully");

		// =====================================================
		// Add Product To Cart
		// =====================================================

		ExtentTest addProductNode = productNode.createNode("Add Product To Cart");

		addProductNode.info("Add Sauce Labs Backpack to cart");

		productPage.addBackpack();

		addProductNode.pass("Sauce Labs Backpack added successfully");

		// =====================================================
		// Verify Cart Count
		// =====================================================

		addProductNode.info("Verify cart count");

		Assert.assertEquals(productPage.getCartCount(), "1", "Cart count not updated after adding product");

		addProductNode.pass("Cart count verified as 1");

		productNode.pass("[===== Product Flow Completed Successfully =====]");

		log.info("Product added to cart successfully");

		log.info("===== Product Flow Completed =====");
	}
}