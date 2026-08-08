package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Home/Products Page.
 *
 * Contains only locators and page-level methods.
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
	 * Verify Products page displayed.
	 */
	public boolean isHomePageDisplayed() {

		log.info("Checking Products page visibility");

		return ElementUtils.isDisplayed(pageTitle);
	}

	/**
	 * Get Products page title.
	 */
	public String getHomePageTitle() {

		log.info("Getting Products page title");

		return ElementUtils.getText(pageTitle);
	}

	/**
     * Verify menu button displayed.
     */
    public boolean isMenuDisplayed() {

        log.info("Checking menu visibility");

        return ElementUtils.isDisplayed(menuButton);
    }
}
