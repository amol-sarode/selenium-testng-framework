package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.driver.DriverManager;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object for SauceDemo Login Page.
 *
 * Responsibilities:
 * - Store locators
 * - Perform UI operations
 * - Return UI state
 * - Report actual UI actions/results
 *
 * Assertions belong to Test layer.
 */
public class LoginPage {

    private static final Logger log =
            LoggerUtils.getLogger(LoginPage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By txtUsername =
            By.id("user-name");

    private final By txtPassword =
            By.id("password");

    private final By btnLogin =
            By.id("login-button");

    private final By errorMessage =
            By.xpath("//h3[@data-test='error']");

    // =========================================================
    // Username
    // =========================================================

    public void enterUsername(String username) {

        validateValue(username, "Username");

        ExtentReportManager.info(
                "Entering username");

        try {

            ElementUtils.enterText(
                    txtUsername,
                    username);

            log.info("Username entered successfully");

            ExtentReportManager.pass(
                    "Username entered successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to enter username",
                    e);

            ExtentReportManager.fail(
                    "Unable to enter username");

            throw e;
        }
    }

    // =========================================================
    // Password
    // =========================================================

    public void enterPassword(String password) {

        validateValue(password, "Password");

        ExtentReportManager.info(
                "Entering password");

        try {

            ElementUtils.enterText(
                    txtPassword,
                    password);

            log.info("Password entered successfully");

            ExtentReportManager.pass(
                    "Password entered successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to enter password",
                    e);

            ExtentReportManager.fail(
                    "Unable to enter password");

            throw e;
        }
    }

    // =========================================================
    // Login
    // =========================================================

    public void clickLogin() {

        ExtentReportManager.info(
                "Clicking Login button");

        try {

            ElementUtils.click(btnLogin);

            log.info("Login button clicked successfully");

            ExtentReportManager.pass(
                    "Login button clicked successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to click Login button",
                    e);

            ExtentReportManager.fail(
                    "Unable to click Login button");

            throw e;
        }
    }

    // =========================================================
    // Verify Successful Login
    // =========================================================

    /**
     * Returns the actual page title after login.
     *
     * No assertion is performed here.
     */
    public String getPageTitle() {

        String actualTitle =
                DriverManager.getDriver().getTitle();

        log.info(
                "Actual page title after login: {}",
                actualTitle);

        ExtentReportManager.info(
                "Actual page title after login: "
                        + actualTitle);

        return actualTitle;
    }

    // =========================================================
    // Login Error
    // =========================================================

    public boolean isErrorMessageDisplayed() {

        try {

            boolean displayed =
                    ElementUtils.isDisplayed(
                            errorMessage);

            log.info(
                    "Login error displayed: {}",
                    displayed);

            return displayed;

        } catch (Exception e) {

            log.error(
                    "Unable to verify login error message",
                    e);

            throw e;
        }
    }

    // =========================================================
    // Get Login Error
    // =========================================================

    public String getErrorMessage() {

        String message =
                ElementUtils.getText(
                        errorMessage);

        log.info(
                "Actual login error message: {}",
                message);

        ExtentReportManager.info(
                "Actual login error message: "
                        + message);

        return message;
    }

    // =========================================================
    // Validation
    // =========================================================

    private void validateValue(
            String value,
            String fieldName) {

        if (value == null ||
                value.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    fieldName +
                    " cannot be null or empty");
        }
    }
}