package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object for SauceDemo Home / Products Page.
 *
 * Responsibilities:
 * - Store page locators
 * - Perform UI operations
 * - Report INFO/PASS/FAIL
 * - Return UI state
 *
 * Assertions do NOT belong here.
 */
public class HomePage {

    private static final Logger log =
            LoggerUtils.getLogger(HomePage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By pageTitle =
            By.className("title");

    private final By menuButton =
            By.id("react-burger-menu-btn");

    // =========================================================
    // Home Page Visibility
    // =========================================================

    public boolean isHomePageDisplayed() {

        ExtentReportManager.info(
                "Verify Products page is displayed");

        try {

            boolean displayed =
                    ElementUtils.isDisplayed(
                            pageTitle);

            if (displayed) {

                log.info(
                        "Products page is displayed");

                ExtentReportManager.pass(
                        "Products page is displayed successfully");

            } else {

                log.warn(
                        "Products page is not displayed");

                ExtentReportManager.fail(
                        "Products page is not displayed");
            }

            return displayed;

        } catch (Exception e) {

            log.error(
                    "Unable to verify Products page visibility",
                    e);

            ExtentReportManager.fail(
                    "Unable to verify Products page visibility: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Page Title
    // =========================================================

    public String getHomePageTitle() {

        ExtentReportManager.info(
                "Get Products page title");

        try {

            String title =
                    ElementUtils.getText(
                            pageTitle);

            log.info(
                    "Products page title : {}",
                    title);

            ExtentReportManager.pass(
                    "Products page title retrieved successfully");

            return title;

        } catch (Exception e) {

            log.error(
                    "Unable to get Products page title",
                    e);

            ExtentReportManager.fail(
                    "Unable to get Products page title: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Menu Visibility
    // =========================================================

    public boolean isMenuDisplayed() {

        ExtentReportManager.info(
                "Verify menu button is displayed");

        try {

            boolean displayed =
                    ElementUtils.isDisplayed(
                            menuButton);

            if (displayed) {

                log.info(
                        "Menu button is displayed");

                ExtentReportManager.pass(
                        "Menu button is displayed successfully");

            } else {

                log.warn(
                        "Menu button is not displayed");

                ExtentReportManager.fail(
                        "Menu button is not displayed");
            }

            return displayed;

        } catch (Exception e) {

            log.error(
                    "Unable to verify menu button visibility",
                    e);

            ExtentReportManager.fail(
                    "Unable to verify menu button visibility: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Exception Message
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