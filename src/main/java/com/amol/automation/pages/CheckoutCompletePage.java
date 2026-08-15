package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object for SauceDemo Checkout Complete Page.
 *
 * Responsibilities:
 * - Store checkout completion locators
 * - Perform UI operations
 * - Return UI state
 * - Report UI operation INFO/PASS/FAIL
 *
 * Assertions and business decisions remain in
 * Action/Test layers.
 */
public class CheckoutCompletePage {

    private static final Logger log =
            LoggerUtils.getLogger(CheckoutCompletePage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By completeTitle =
            By.className("title");

    private final By thankYouMessage =
            By.className("complete-header");

    private final By backHomeButton =
            By.id("back-to-products");

    // =========================================================
    // Complete Page Title
    // =========================================================

    /**
     * Gets checkout complete page title.
     *
     * @return complete page title
     */
    public String getCompletePageTitle() {

        ExtentReportManager.info(
                "Get checkout complete page title");

        try {

            String title =
                    ElementUtils.getText(completeTitle);

            log.info(
                    "Checkout complete page title : {}",
                    title);

            ExtentReportManager.pass(
                    "Checkout complete page title retrieved successfully");

            return title;

        } catch (Exception e) {

            log.error(
                    "Unable to get checkout complete page title",
                    e);

            ExtentReportManager.fail(
                    "Unable to get checkout complete page title: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Order Completion Page
    // =========================================================

    /**
     * Checks whether order completion page is displayed.
     *
     * @return true if completion page is displayed
     */
    public boolean isOrderCompletePageDisplayed() {

        ExtentReportManager.info(
                "Verify checkout complete page is displayed");

        try {

            boolean displayed =
                    ElementUtils.isDisplayed(
                            completeTitle);

            if (displayed) {

                ExtentReportManager.pass(
                        "Checkout complete page is displayed");

            } else {

                ExtentReportManager.fail(
                        "Checkout complete page is not displayed");
            }

            return displayed;

        } catch (Exception e) {

            log.error(
                    "Unable to verify checkout complete page",
                    e);

            ExtentReportManager.fail(
                    "Unable to verify checkout complete page: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Thank You Message
    // =========================================================

    /**
     * Gets order thank-you message.
     *
     * @return thank-you message
     */
    public String getThankYouMessage() {

        ExtentReportManager.info(
                "Get order thank-you message");

        try {

            String message =
                    ElementUtils.getText(
                            thankYouMessage);

            log.info(
                    "Thank-you message retrieved successfully");

            ExtentReportManager.pass(
                    "Order thank-you message retrieved successfully");

            return message;

        } catch (Exception e) {

            log.error(
                    "Unable to get order thank-you message",
                    e);

            ExtentReportManager.fail(
                    "Unable to get order thank-you message: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Back To Products
    // =========================================================

    /**
     * Clicks Back Home button.
     */
    public void clickBackHome() {

        ExtentReportManager.info(
                "Click Back To Products button");

        try {

            ElementUtils.click(
                    backHomeButton);

            log.info(
                    "Back To Products button clicked successfully");

            ExtentReportManager.pass(
                    "Back To Products button clicked successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to click Back To Products button",
                    e);

            ExtentReportManager.fail(
                    "Unable to click Back To Products button: "
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