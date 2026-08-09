package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Checkout Complete Page.
 *
 * Contains only checkout completion locators and page-level methods.
 */
public class CheckoutCompletePage {

	private static final Logger log = LoggerUtils.getLogger(CheckoutCompletePage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By completeTitle = By.className("title");

	private final By thankYouMessage = By.className("complete-header");

	private final By backHomeButton = By.id("back-to-products");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Gets the checkout complete page title.
	 *
	 * @return complete page title
	 */
	public String getCompletePageTitle() {

		log.info("Getting checkout complete page title");

		return ElementUtils.getText(completeTitle);
	}

	/**
	 * Verifies that the order completion page is displayed.
	 *
	 * @return true if completion page is displayed
	 */
	public boolean isOrderCompletePageDisplayed() {

		log.info("Checking checkout complete page visibility");

		return ElementUtils.isDisplayed(completeTitle);
	}

	/**
	 * Gets the thank-you message displayed after order completion.
	 *
	 * @return thank-you message
	 */
	public String getThankYouMessage() {

		log.info("Getting thank-you message");

		return ElementUtils.getText(thankYouMessage);
	}

	/**
	 * Clicks the Back Home button.
	 */
	public void clickBackHome() {

		log.info("Clicking Back Home button");

		ElementUtils.click(backHomeButton);
	}
}