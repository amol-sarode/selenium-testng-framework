package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

public class CartPage {

    private static final Logger log =
            LoggerUtils.getLogger(CartPage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By cartTitle =
            By.cssSelector(".title");

    private final By productName =
            By.cssSelector(".inventory_item_name");

    private final By checkoutButton =
            By.id("checkout");

    // =========================================================
    // Cart Title
    // =========================================================

    public String getCartTitle() {

        ExtentReportManager.info(
                "Get Cart page title");

        String title =
                ElementUtils.getText(cartTitle);

        log.info(
                "Cart page title : {}",
                title);

        return title;
    }

    // =========================================================
    // Product Name
    // =========================================================

    public String getProductName() {

        ExtentReportManager.info(
                "Get product name from Cart");

        String name =
                ElementUtils.getText(productName);

        log.info(
                "Cart product : {}",
                name);

        return name;
    }

    // =========================================================
    // Checkout
    // =========================================================

    public void clickCheckout() {

        ExtentReportManager.info(
                "Click Checkout button");

        try {

            /*
             * Wait until the Cart page is actually displayed
             * before clicking Checkout.
             */
            ElementUtils.getText(cartTitle);

            /*
             * Checkout button is on Cart page.
             */
            ElementUtils.click(checkoutButton);

            log.info(
                    "Checkout button clicked successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to click Checkout button",
                    e);

            ExtentReportManager.fail(
                    "Unable to click Checkout button");

            throw e;
        }
    }
}

