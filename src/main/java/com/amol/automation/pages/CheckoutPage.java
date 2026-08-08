package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object class for SauceDemo Checkout Page.
 *
 * Contains only locators and page-level methods.
 */
public class CheckoutPage {

	private static final Logger log = LoggerUtils.getLogger(CheckoutPage.class);

	// =========================================================
	// Locators
	// =========================================================

	private final By checkoutTitle = By.className("title");

	private final By firstName = By.id("first-name");

	private final By lastName = By.id("last-name");

	private final By postalCode = By.id("postal-code");

	private final By continueButton = By.id("continue");

	// =========================================================
	// Page Methods
	// =========================================================

	/**
	 * Get checkout page title.
	 */
	public String getCheckoutTitle() {

		log.info("Getting checkout title");

		return ElementUtils.getText(checkoutTitle);
	}

	/**
	 * Enter first name.
	 */
	public void enterFirstName(String fname) {

		log.info("Entering first name");

		ElementUtils.enterText(firstName, fname);
	}

	/**
	 * Enter last name.
	 */
	public void enterLastName(String lname) {

		log.info("Entering last name");

		ElementUtils.enterText(lastName, lname);
	}

	/**
	 * Enter postal code.
	 */
	public void enterPostalCode(String zip) {

		log.info("Entering postal code");

		ElementUtils.enterText(postalCode, zip);
	}

	/**
     * Click Continue button.
     */
    public void clickContinue() {

        log.info("Clicking Continue button");

        ElementUtils.click(continueButton);
    }
}
