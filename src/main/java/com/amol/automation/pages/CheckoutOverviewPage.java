package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Checkout Overview Page.
 *
 * Contains only checkout overview locators and page-level methods.
 */
public class CheckoutOverviewPage {

	private static final Logger log = LoggerUtils.getLogger(CheckoutOverviewPage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By overviewTitle = By.className("title");

	private final By finishButton = By.id("finish");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Gets the checkout overview page title.
	 *
	 * @return overview page title
	 */
	public String getOverviewTitle() {

		log.info("Getting checkout overview title");

		return ElementUtils.getText(overviewTitle);
	}

	/**
	 * Verifies that the checkout overview page is displayed.
	 *
	 * @return true if overview page is displayed
	 */
	public boolean isOverviewPageDisplayed() {

		log.info("Checking checkout overview page visibility");

		return ElementUtils.isDisplayed(overviewTitle);
	}

	/**
	 * Clicks the Finish button.
	 */
	public void clickFinish() {

		log.info("Clicking Finish button");

		ElementUtils.click(finishButton);
	}
}