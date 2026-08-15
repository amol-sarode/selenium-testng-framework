package com.amol.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.actions.EndToEndActions;
import com.amol.automation.actions.LoginActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.factory.ActionObjectManager;
import com.amol.automation.listeners.RetryAnalyzer;

/**
 * End-to-end test for SauceDemo purchase functionality.
 *
 * Flow:
 * Login -> Products -> Add Product -> Cart
 * -> Checkout -> Complete Order -> Back To Products
 *
 * Architecture:
 *
 * Test
 *   -> LoginActions
 *   -> EndToEndActions
 *
 * Assertions remain in Test layer.
 * Business operations remain in Action layer.
 * UI operations remain in Page layer.
 */
public class EndToEndTest extends BaseTest {

    private final LoginActions loginActions =
            ActionObjectManager.getLoginActions();

    private final EndToEndActions endToEndActions =
            new EndToEndActions();

    @Test(
        description = "Verify complete SauceDemo purchase flow with valid user",
        groups = {"smoke", "regression"},
        retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyEndToEndPurchaseFlow() {

        // =====================================================
        // Test Data
        // =====================================================

        String username = "standard_user";
        String password = "secret_sauce";

        String firstName = "Amol";
        String lastName = "Sarode";
        String postalCode = "382481";

        String productName = "Sauce Labs Backpack";

        // =====================================================
        // Login
        // =====================================================

        loginActions.login(username, password);

        // =====================================================
        // Verify Successful Login
        // =====================================================

        String productsTitle =
                endToEndActions.getProductsPageTitle();

        Assert.assertEquals(
                productsTitle,
                "Products",
                "Login successful but Products page was not displayed"
        );

        // =====================================================
        // Add Product
        // =====================================================

        endToEndActions.addProduct(productName);

        // =====================================================
        // Cart Count
        // =====================================================

        Assert.assertEquals(
                endToEndActions.getCartCount(),
                "1",
                "Cart count is not updated correctly"
        );

        // =====================================================
        // Open Cart
        // =====================================================

        endToEndActions.openCart();

        // =====================================================
        // Cart Verification
        // =====================================================

        Assert.assertEquals(
                endToEndActions.getCartTitle(),
                "Products",
                "Cart page title mismatch"
        );

        Assert.assertEquals(
                endToEndActions.getCartProductName(),
                productName,
                "Incorrect product found in cart"
        );

        // =====================================================
        // Checkout
        // =====================================================

        endToEndActions.proceedToCheckout();

        Assert.assertEquals(
                endToEndActions.getCheckoutTitle(),
                "Your Cart",
                "Checkout information page mismatch"
        );

        // =====================================================
        // Checkout Information
        // =====================================================

        endToEndActions.enterCheckoutInformation(
                firstName,
                lastName,
                postalCode
        );

        endToEndActions.continueCheckout();

        // =====================================================
        // Checkout Overview
        // =====================================================

        Assert.assertEquals(
                endToEndActions.getOverviewTitle(),
                "Checkout: Overview",
                "Checkout overview page mismatch"
        );

        // =====================================================
        // Complete Order
        // =====================================================

        endToEndActions.finishOrder();

        Assert.assertEquals(
                endToEndActions.getCompletePageTitle(),
                "Checkout: Complete!",
                "Checkout complete page title mismatch"
        );

        Assert.assertEquals(
                endToEndActions.getThankYouMessage(),
                "Thank you for your order!",
                "Order confirmation message mismatch"
        );

        // =====================================================
        // Back To Products
        // =====================================================

        endToEndActions.backToProducts();

        Assert.assertEquals(
                endToEndActions.getProductsPageTitle(),
                "Products",
                "User is not redirected to Products page"
        );
    }
}

