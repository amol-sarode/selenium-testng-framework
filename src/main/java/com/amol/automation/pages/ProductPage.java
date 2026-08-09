package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Products Page.
 *
 * Contains only product-page locators and page-level methods.
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
	 * Gets the Products page title.
	 *
	 * @return Products page title
	 */
	public String getProductsPageTitle() {

		log.info("Getting Products page title");

		return ElementUtils.getText(productsTitle);
	}

	/**
	 * Adds Sauce Labs Backpack to the cart.
	 */
	public void addBackpack() {

		log.info("Adding Sauce Labs Backpack to cart");

		ElementUtils.click(addBackpackButton);
	}

	/**
	 * Gets the cart badge count.
	 *
	 * @return cart item count
	 */
	public String getCartCount() {

		log.info("Getting cart badge count");

		return ElementUtils.getText(cartBadge);
	}

	/**
	 * Opens the shopping cart.
	 */
	public void clickCart() {

		log.info("Opening shopping cart");

		ElementUtils.click(cartIcon);
	}
}