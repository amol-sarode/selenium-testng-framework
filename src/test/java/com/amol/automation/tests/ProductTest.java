package com.amol.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.actions.ProductActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.listeners.RetryAnalyzer;

/**
 * Test class for SauceDemo product functionality.
 *
 * Responsibilities:
 * - Define test data
 * - Call business actions
 * - Perform test assertions
 *
 * Reporting architecture:
 *
 * @Test(description)
 *        ↓
 * TestListener creates Extent Test
 *        ↓
 * Action creates business Node
 *        ↓
 * Page Object creates INFO / PASS / FAIL logs
 *        ↓
 * Test performs assertions
 *        ↓
 * TestListener handles final PASS / FAIL + screenshot
 *
 * Assertions remain in Test layer.
 */
public class ProductTest extends BaseTest {

    private final ProductActions productActions =
            new ProductActions();

    // =========================================================
    // Verify Product Add To Cart
    // =========================================================

    @Test(
        description = "Verify product add to cart with valid user",
        groups = {
            "smoke",
            "regression"
        },
        retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyProductAddToCart() {

        // =====================================================
        // Test Data
        // =====================================================

        String username =
                "standard_user";

        String password =
                "secret_sauce";

        String productName =
                "Sauce Labs Backpack";

        // =====================================================
        // Login
        // =====================================================

        productActions.login(
                username,
                password
        );

        // =====================================================
        // Products Page Verification
        // =====================================================

        Assert.assertTrue(
                productActions.isHomePageDisplayed(),
                "Products page is not displayed after login"
        );

        Assert.assertEquals(
                productActions.getProductsPageTitle(),
                "Products",
                "Products page title mismatch"
        );

        // =====================================================
        // Add Product
        // =====================================================

        productActions.addProduct(
                productName
        );

        // =====================================================
        // Cart Verification
        // =====================================================

        Assert.assertEquals(
                productActions.getCartCount(),
                "1",
                "Cart count is not updated correctly"
        );
    }
}