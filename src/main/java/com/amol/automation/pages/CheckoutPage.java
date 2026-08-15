package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object for SauceDemo Checkout Information Page.
 *
 * Responsibilities:
 * - Store checkout page locators
 * - Perform checkout UI operations
 * - Log UI operation INFO/PASS/FAIL
 * - Return UI state
 *
 * Assertions and business decisions belong to
 * Action/Test layers.
 */
public class CheckoutPage {

    private static final Logger log =
            LoggerUtils.getLogger(CheckoutPage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By checkoutTitle =
            By.className("title");

    private final By firstName =
            By.id("first-name");

    private final By lastName =
            By.id("last-name");

    private final By postalCode =
            By.id("postal-code");

    private final By continueButton =
            By.id("continue");

    // =========================================================
    // Checkout Title
    // =========================================================

    /**
     * Gets checkout information page title.
     *
     * @return checkout page title
     */
    public String getCheckoutTitle() {

        ExtentReportManager.info(
                "Get checkout information page title");

        try {

            String title =
                    ElementUtils.getText(
                            checkoutTitle);

            log.info(
                    "Checkout page title : {}",
                    title);

            ExtentReportManager.pass(
                    "Checkout page title retrieved successfully");

            return title;

        } catch (Exception e) {

            log.error(
                    "Unable to get checkout page title",
                    e);

            ExtentReportManager.fail(
                    "Unable to get checkout page title: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // First Name
    // =========================================================

    /**
     * Enters first name.
     *
     * @param fname first name
     */
    public void enterFirstName(
            String fname) {

        ExtentReportManager.info(
                "Enter first name");

        try {

            ElementUtils.enterText(
                    firstName,
                    fname);

            log.info(
                    "First name entered successfully");

            ExtentReportManager.pass(
                    "First name entered successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to enter first name",
                    e);

            ExtentReportManager.fail(
                    "Unable to enter first name: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Last Name
    // =========================================================

    /**
     * Enters last name.
     *
     * @param lname last name
     */
    public void enterLastName(
            String lname) {

        ExtentReportManager.info(
                "Enter last name");

        try {

            ElementUtils.enterText(
                    lastName,
                    lname);

            log.info(
                    "Last name entered successfully");

            ExtentReportManager.pass(
                    "Last name entered successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to enter last name",
                    e);

            ExtentReportManager.fail(
                    "Unable to enter last name: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Postal Code
    // =========================================================

    /**
     * Enters postal code.
     *
     * @param zip postal code
     */
    public void enterPostalCode(
            String zip) {

        ExtentReportManager.info(
                "Enter postal code");

        try {

            ElementUtils.enterText(
                    postalCode,
                    zip);

            log.info(
                    "Postal code entered successfully");

            ExtentReportManager.pass(
                    "Postal code entered successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to enter postal code",
                    e);

            ExtentReportManager.fail(
                    "Unable to enter postal code: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Continue
    // =========================================================

    /**
     * Clicks Continue button.
     */
    public void clickContinue() {

        ExtentReportManager.info(
                "Click Continue button");

        try {

            ElementUtils.click(
                    continueButton);

            log.info(
                    "Continue button clicked successfully");

            ExtentReportManager.pass(
                    "Continue button clicked successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to click Continue button",
                    e);

            ExtentReportManager.fail(
                    "Unable to click Continue button: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Utility
    // =========================================================

    private String getExceptionMessage(
            Exception e) {

        if (e == null) {
            return "Unknown error";
        }

        String message =
                e.getMessage();

        return message != null
                && !message.trim().isEmpty()
                ? message
                : e.getClass().getSimpleName();
    }
}