package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Home/Products Page.
 *
 * Contains only page locators and page-level methods.
 */
public class HomePage {

	private static final Logger log = LoggerUtils.getLogger(HomePage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By pageTitle = By.className("title");

	private final By menuButton = By.id("react-burger-menu-btn");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Verifies that the Products page is displayed.
	 *
	 * @return true if Products page title is visible
	 */
	public boolean isHomePageDisplayed() {

		log.info("Checking Products page visibility");

		return ElementUtils.isDisplayed(pageTitle);
	}

	/**
	 * Gets the Products page title.
	 *
	 * @return page title
	 */
	public String getHomePageTitle() {

		log.info("Getting Products page title");

		return ElementUtils.getText(pageTitle);
	}

	/**
	 * Verifies that the menu button is displayed.
	 *
	 * @return true if menu button is visible
	 */
	public boolean isMenuDisplayed() {

		log.info("Checking menu visibility");

		return ElementUtils.isDisplayed(menuButton);
	}
}