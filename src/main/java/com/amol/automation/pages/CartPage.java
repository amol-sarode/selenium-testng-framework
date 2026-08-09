package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Cart Page.
 *
 * Contains only cart-page locators and page-level methods.
 */
public class CartPage {

	private static final Logger log = LoggerUtils.getLogger(CartPage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By cartTitle = By.className("title");

	private final By productName = By.className("inventory_item_name");

	private final By checkoutButton = By.id("checkout");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Gets the cart page title.
	 *
	 * @return cart page title
	 */
	public String getCartTitle() {

		log.info("Getting cart page title");

		return ElementUtils.getText(cartTitle);
	}

	/**
	 * Gets the product name displayed in the cart.
	 *
	 * @return product name
	 */
	public String getProductName() {

		log.info("Getting product name from cart");

		return ElementUtils.getText(productName);
	}

	/**
	 * Clicks the Checkout button.
	 */
	public void clickCheckout() {

		log.info("Clicking Checkout button");

		ElementUtils.click(checkoutButton);
	}
}