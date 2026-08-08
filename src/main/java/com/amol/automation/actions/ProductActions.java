package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentTest;

public class ProductActions {

	private static final Logger log = LoggerUtils.getLogger(ProductActions.class);

	// =========================================================
	// Page Objects
	// =========================================================

	private final LoginPage loginPage;
	private final HomePage homePage;
	private final ProductPage productPage;

	// =========================================================
	// Constructor
	// =========================================================

	public ProductActions() {

		loginPage = PageObjectManager.getLoginPage();
		homePage = PageObjectManager.getHomePage();
		productPage = PageObjectManager.getProductPage();
	}

	// =========================================================
	// Product Business Flow
	// =========================================================

	public void verifyProductAddToCart(String username, String password) {

		ExtentTest productNode = ExtentReportManager.createNode("Product Add To Cart Flow");

		try {

			productNode.info("[===== Product Flow Started =====]");

			log.info("===== Product Flow Started =====");

			// =================================================
			// Login
			// =================================================

			ExtentTest loginNode = productNode.createNode("Login");

			loginNode.info("Login with valid user : " + username);

			loginPage.enterUsername(username);

			loginNode.pass("Username entered successfully");

			loginPage.enterPassword(password);

			loginNode.pass("Password entered successfully");

			loginPage.clickLogin();

			loginNode.pass("Login button clicked successfully");

			// =================================================
			// Verify Home Page
			// =================================================

			loginNode.info("Verify Products page is displayed");

			Assert.assertTrue(homePage.isHomePageDisplayed(), "Products page is not displayed after login");

			loginNode.pass("Products page displayed successfully");

			log.info("Login completed successfully");

			// =================================================
			// Product Page
			// =================================================

			ExtentTest productPageNode = productNode.createNode("Product Page Verification");

			productPageNode.info("Verify Products page title");

			Assert.assertEquals(productPage.getProductsPageTitle(), "Products", "Products page title mismatch");

			productPageNode.pass("Products page title verified successfully");

			log.info("Products page verified");

			// =================================================
			// Add Product
			// =================================================

			ExtentTest addProductNode = productNode.createNode("Add Product To Cart");

			addProductNode.info("Add Sauce Labs Backpack to cart");

			productPage.addBackpack();

			addProductNode.pass("Sauce Labs Backpack added successfully");

			addProductNode.info("Verify cart count");

			Assert.assertEquals(productPage.getCartCount(), "1", "Cart count not updated after adding product");

			addProductNode.pass("Cart count verified as 1");

			productNode.pass("[===== Product Flow Completed Successfully =====]");

			log.info("Product added to cart successfully");

			log.info("===== Product Flow Completed =====");

		} catch (Exception e) {

			productNode.fail("Product flow failed : " + e.getMessage());

			log.error("Product flow failed", e);

			throw e;
		}
	}
}