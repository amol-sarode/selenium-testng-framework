package com.amol.automation.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.base.BaseTest;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.CartPage;
import com.amol.automation.pages.CheckoutCompletePage;
import com.amol.automation.pages.CheckoutOverviewPage;
import com.amol.automation.pages.CheckoutPage;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.utils.LoggerUtils;

public class EndToEndTest extends BaseTest {

	private static final Logger log = LoggerUtils.getLogger(EndToEndTest.class);

	@Test(description = "Verify complete purchase flow with valid user", groups = { "smoke", "regression" })
	public void verifyEndToEndPurchaseFlow() {

		log.info("===== SauceDemo E2E Purchase Started =====");

		String username = "standard_user";
		String password = "secret_sauce";

		// Login

		LoginPage loginPage = PageObjectManager.getLoginPage();

		HomePage homePage = loginPage.loginSuccessfully(username, password);

		Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");

		log.info("Login completed successfully");

		// Product Page

		ProductPage productPage = PageObjectManager.getProductPage();

		Assert.assertEquals(productPage.getProductsPageTitle(), "Products", "Products page title mismatch");

		log.info("Products page verified");

		// Add Product

		productPage.addBackpack();

		Assert.assertEquals(productPage.getCartCount(), "1", "Cart count is not updated");

		log.info("Product added to cart");

		// Cart Page

		CartPage cartPage = productPage.clickCart();

		Assert.assertEquals(cartPage.getCartTitle(), "Your Cart", "Cart page title mismatch");

		Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack", "Incorrect product added");

		log.info("Cart verified");

		// Checkout Information

		CheckoutPage checkoutPage = cartPage.clickCheckout();

		Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout: Your Information",
				"Checkout information page mismatch");

		CheckoutOverviewPage overviewPage = checkoutPage.checkout("Amol", "Sarode", "382481");

		Assert.assertEquals(overviewPage.getOverviewTitle(), "Checkout: Overview", "Checkout overview page mismatch");

		log.info("Checkout overview verified");

		// Complete Order

		CheckoutCompletePage completePage = overviewPage.clickFinish();

		Assert.assertEquals(completePage.getCompletePageTitle(), "Checkout: Complete!",
				"Checkout complete page mismatch");

		Assert.assertEquals(completePage.getThankYouMessage(), "Thank you for your order!",
				"Thank you message mismatch");

		log.info("Order completed successfully");

		// Navigate back home

		ProductPage productPageAfterOrder = completePage.clickBackHome();

		Assert.assertEquals(productPageAfterOrder.getProductsPageTitle(), "Products",
				"User is not redirected to Products page");

		log.info("Back to products page verified");

		log.info("===== SauceDemo E2E Purchase Completed =====");

	}

}