package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amol.automation.base.BasePage;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Products Page.
 */
public class ProductPage extends BasePage {

	private static final Logger log =
			LoggerUtils.getLogger(ProductPage.class);

	@FindBy(className = "title")
	private WebElement productsTitle;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addBackpackButton;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartIcon;

	/**
	 * Get Products page title.
	 */
	public String getProductsPageTitle() {

		log.info("Getting Products page title");

		return getText(productsTitle);

	}

	/**
	 * Add Sauce Labs Backpack to cart.
	 */
	public ProductPage addBackpack() {

		log.info("Adding Sauce Labs Backpack to cart");

		click(addBackpackButton);

		return this;

	}

	/**
	 * Get cart badge count.
	 */
	public String getCartCount() {

		log.info("Getting cart count");

		return getText(cartBadge);

	}

	/**
	 * Open shopping cart.
	 */
	public CartPage clickCart() {

		log.info("Opening shopping cart");

		click(cartIcon);

		return PageObjectManager.getCartPage();

	}

}