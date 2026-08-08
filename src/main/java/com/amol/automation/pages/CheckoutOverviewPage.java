package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Checkout Overview Page.
 *
 * Contains only locators and page-level methods.
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
	 * Get overview page title.
	 */
	public String getOverviewTitle() {

		log.info("Getting checkout overview title");

		return ElementUtils.getText(overviewTitle);
	}

	/**
	 * Verify overview page displayed.
	 */
	public boolean isOverviewPageDisplayed() {

		log.info("Checking checkout overview page");

		return ElementUtils.isDisplayed(overviewTitle);
	}

	/**
     * Click Finish button.
     */
    public void clickFinish() {

        log.info("Clicking Finish button");

        ElementUtils.click(finishButton);
    }
}
