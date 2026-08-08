package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Checkout Complete Page.
 *
 * Contains only locators and page-level methods.
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
	 * Get complete page title.
	 */
	public String getCompletePageTitle() {

		log.info("Getting complete page title");

		return ElementUtils.getText(completeTitle);
	}

	/**
	 * Verify order complete page displayed.
	 */
	public boolean isOrderCompletePageDisplayed() {

		log.info("Checking order complete page");

		return ElementUtils.isDisplayed(completeTitle);
	}

	/**
	 * Get thank you message.
	 */
	public String getThankYouMessage() {

		log.info("Getting thank you message");

		return ElementUtils.getText(thankYouMessage);
	}

	/**
     * Click Back Home button.
     */
    public void clickBackHome() {

        log.info("Clicking Back Home button");

        ElementUtils.click(backHomeButton);
    }
}
