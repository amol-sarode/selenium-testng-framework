package com.amol.automation.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.base.BaseTest;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.utils.LoggerUtils;

public class ProductTest extends BaseTest {

	private static final Logger log = LoggerUtils.getLogger(ProductTest.class);

	@Test(description = "Verify product add to cart with valid user", groups = { "smoke", "regression" })
	public void verifyProductAddToCart() {

		log.info("===== Product Test Started =====");

		String username = "standard_user";
		String password = "secret_sauce";
		log.info("Login with valid user : {}", username);
		HomePage homePage = PageObjectManager.getLoginPage().loginSuccessfully(username, password);
		Assert.assertTrue(homePage.isHomePageDisplayed(), "Products page is not displayed after login");

		log.info("Login completed successfully");

		ProductPage productPage = PageObjectManager.getProductPage();

		Assert.assertEquals(productPage.getProductsPageTitle(), "Products", "Products page title mismatch");

		log.info("Products page verified");

		productPage.addBackpack();

		Assert.assertEquals(productPage.getCartCount(), "1", "Cart count not updated after adding product");

		log.info("Product added to cart successfully");

		log.info("===== Product Test Completed =====");

	}

}