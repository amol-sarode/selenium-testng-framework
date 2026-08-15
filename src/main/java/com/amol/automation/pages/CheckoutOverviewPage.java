package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object for SauceDemo Checkout Overview Page.
 *
 * Responsibilities:
 * - Store checkout overview locators
 * - Perform UI operations
 * - Report UI operation status
 * - Return page state
 *
 * Reporting architecture:
 *
 * Test
 *   -> Test title created by Listener from @Test(description)
 *
 * Action
 *   -> Business node using createNode()
 *
 * Page Object
 *   -> info()
 *   -> pass()
 *   -> fail()
 *
 * Assertions remain in Test layer.
 */
public class CheckoutOverviewPage {

    private static final Logger log =
            LoggerUtils.getLogger(CheckoutOverviewPage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By overviewTitle =
            By.className("title");

    private final By finishButton =
            By.id("finish");

    // =========================================================
    // Get Overview Title
    // =========================================================

    /**
     * Gets checkout overview page title.
     *
     * @return overview page title
     */
    public String getOverviewTitle() {

        ExtentReportManager.info(
                "Get checkout overview page title");

        try {

            String title =
                    ElementUtils.getText(overviewTitle);

            log.info(
                    "Checkout overview title retrieved : {}",
                    title);

            ExtentReportManager.pass(
                    "Checkout overview page title retrieved successfully");

            return title;

        } catch (Exception e) {

            log.error(
                    "Unable to get checkout overview page title",
                    e);

            ExtentReportManager.fail(
                    "Unable to get checkout overview page title: "
                            + e.getMessage());

            throw e;
        }
    }

    // =========================================================
    // Overview Page Display
    // =========================================================

    /**
     * Checks whether checkout overview page is displayed.
     *
     * @return true if overview page is displayed
     */
    public boolean isOverviewPageDisplayed() {

        ExtentReportManager.info(
                "Verify checkout overview page is displayed");

        try {

            boolean displayed =
                    ElementUtils.isDisplayed(overviewTitle);

            if (displayed) {

                ExtentReportManager.pass(
                        "Checkout overview page is displayed");

            } else {

                ExtentReportManager.fail(
                        "Checkout overview page is not displayed");
            }

            return displayed;

        } catch (Exception e) {

            log.error(
                    "Unable to verify checkout overview page",
                    e);

            ExtentReportManager.fail(
                    "Unable to verify checkout overview page: "
                            + e.getMessage());

            throw e;
        }
    }

    // =========================================================
    // Finish Order
    // =========================================================

    /**
     * Clicks Finish button.
     */
    public void clickFinish() {

        ExtentReportManager.info(
                "Click Finish button");

        try {

            ElementUtils.click(finishButton);

            log.info(
                    "Finish button clicked successfully");

            ExtentReportManager.pass(
                    "Finish button clicked successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to click Finish button",
                    e);

            ExtentReportManager.fail(
                    "Unable to click Finish button: "
                            + e.getMessage());

            throw e;
        }
    }
}