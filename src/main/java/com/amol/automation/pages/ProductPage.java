package com.amol.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ElementUtils;
import com.amol.automation.utils.LoggerUtils;

/**
 * Page Object for SauceDemo Products Page.
 *
 * Responsibilities:
 * - Store product locators
 * - Perform product UI operations
 * - Report INFO/PASS/FAIL
 * - Return UI state
 *
 * Assertions and business decisions do NOT belong here.
 *
 * Reporting:
 *
 * Test Title
 *      |
 *      +-- Add Product
 *            +-- INFO
 *            +-- PASS
 *            +-- FAIL
 */
public class ProductPage {

    private static final Logger log =
            LoggerUtils.getLogger(ProductPage.class);

    // =========================================================
    // Locators
    // =========================================================

    private final By productsTitle =
            By.className("title");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By cartIcon =
            By.className("shopping_cart_link");

    // =========================================================
    // Products Page Title
    // =========================================================

    public String getProductsPageTitle() {

        ExtentReportManager.info(
                "Get Products page title");

        try {

            String title =
                    ElementUtils.getText(
                            productsTitle);

            log.info(
                    "Products page title retrieved : {}",
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
    // Add Product
    // =========================================================

    /**
     * Adds the specified product to cart.
     *
     * SauceDemo uses the following ID convention:
     *
     * add-to-cart-sauce-labs-backpack
     *
     * Product name:
     *
     * Sauce Labs Backpack
     */
    public void addProduct(
            String productName) {

        validateProductName(
                productName);

        ExtentReportManager.info(
                "Add product to cart : "
                        + productName);

        try {

            String productId =
                    productName
                            .trim()
                            .toLowerCase()
                            .replace(" ", "-");

            By addToCartButton =
                    By.id(
                            "add-to-cart-"
                                    + productId);

            ElementUtils.click(
                    addToCartButton);

            log.info(
                    "Product added to cart successfully : {}",
                    productName);

            ExtentReportManager.pass(
                    "Product added to cart successfully : "
                            + productName);

        } catch (Exception e) {

            log.error(
                    "Unable to add product to cart : {}",
                    productName,
                    e);

            ExtentReportManager.fail(
                    "Unable to add product to cart : "
                            + productName
                            + " - "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Cart Count
    // =========================================================

    /**
     * Returns cart count.
     *
     * If the cart is empty, SauceDemo does not display
     * the cart badge. Therefore zero is returned instead
     * of throwing an exception.
     */
    public String getCartCount() {

        ExtentReportManager.info(
                "Get cart item count");

        try {

            boolean displayed =
                    ElementUtils.isDisplayed(
                            cartBadge);

            if (!displayed) {

                log.info(
                        "Cart is empty");

                ExtentReportManager.pass(
                        "Cart item count retrieved successfully : 0");

                return "0";
            }

            String count =
                    ElementUtils.getText(
                            cartBadge);

            log.info(
                    "Cart item count : {}",
                    count);

            ExtentReportManager.pass(
                    "Cart item count retrieved successfully : "
                            + count);

            return count;

        } catch (Exception e) {

            log.error(
                    "Unable to get cart item count",
                    e);

            ExtentReportManager.fail(
                    "Unable to get cart item count: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Open Cart
    // =========================================================

    public void clickCart() {

        ExtentReportManager.info(
                "Click shopping cart");

        try {

            ElementUtils.click(
                    cartIcon);

            log.info(
                    "Shopping cart opened successfully");

            ExtentReportManager.pass(
                    "Shopping cart opened successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to open shopping cart",
                    e);

            ExtentReportManager.fail(
                    "Unable to open shopping cart: "
                            + getExceptionMessage(e));

            throw e;
        }
    }

    // =========================================================
    // Validation
    // =========================================================

    private void validateProductName(
            String productName) {

        if (productName == null ||
                productName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Product name cannot be null or empty");
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