package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Cart Page.
 *
 * Contains only locators and page-level methods.
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
	 * Get cart title.
	 */
	public String getCartTitle() {

		log.info("Getting cart title");

		return ElementUtils.getText(cartTitle);
	}

	/**
	 * Get product name from cart.
	 */
	public String getProductName() {

		log.info("Getting product name");

		return ElementUtils.getText(productName);
	}

	/**
     * Click checkout button.
     */
    public void clickCheckout() {

        log.info("Clicking checkout button");

        ElementUtils.click(checkoutButton);
    }
}
