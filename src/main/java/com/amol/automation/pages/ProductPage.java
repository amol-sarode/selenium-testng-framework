package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Products Page.
 *
 * Contains only locators and page-level methods.
 */
public class ProductPage {

	private static final Logger log = LoggerUtils.getLogger(ProductPage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By productsTitle = By.className("title");

	private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");

	private final By cartBadge = By.className("shopping_cart_badge");

	private final By cartIcon = By.className("shopping_cart_link");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Get Products page title.
	 */
	public String getProductsPageTitle() {

		log.info("Getting Products page title");

		return ElementUtils.getText(productsTitle);
	}

	/**
	 * Add Sauce Labs Backpack to cart.
	 */
	public void addBackpack() {

		log.info("Adding Sauce Labs Backpack to cart");

		ElementUtils.click(addBackpackButton);
	}

	/**
	 * Get cart badge count.
	 */
	public String getCartCount() {

		log.info("Getting cart badge count");

		return ElementUtils.getText(cartBadge);
	}

	/**
     * Open shopping cart.
     */
    public void clickCart() {

        log.info("Opening shopping cart");

        ElementUtils.click(cartIcon);
    }
}
