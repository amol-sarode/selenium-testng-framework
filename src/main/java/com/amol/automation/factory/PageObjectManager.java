package com.amol.automation.factory;

import com.amol.automation.pages.CartPage;
import com.amol.automation.pages.CheckoutCompletePage;
import com.amol.automation.pages.CheckoutOverviewPage;
import com.amol.automation.pages.CheckoutPage;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.pages.ProductPage;

/**
 * Central manager for Page Objects.
 *
 * Responsibilities:
 * - Maintain Page Objects using ThreadLocal
 * - Provide thread-safe Page Object access
 * - Prevent sharing Page Objects during parallel execution
 * - Cleanup ThreadLocal references after test execution
 */
public final class PageObjectManager {

    private PageObjectManager() {
        // Prevent object creation
    }

    // =========================================================
    // ThreadLocal Page Objects
    // =========================================================

    private static final ThreadLocal<LoginPage> LOGIN_PAGE =
            ThreadLocal.withInitial(LoginPage::new);

    private static final ThreadLocal<HomePage> HOME_PAGE =
            ThreadLocal.withInitial(HomePage::new);

    private static final ThreadLocal<ProductPage> PRODUCT_PAGE =
            ThreadLocal.withInitial(ProductPage::new);

    private static final ThreadLocal<CartPage> CART_PAGE =
            ThreadLocal.withInitial(CartPage::new);

    private static final ThreadLocal<CheckoutPage> CHECKOUT_PAGE =
            ThreadLocal.withInitial(CheckoutPage::new);

    private static final ThreadLocal<CheckoutOverviewPage> CHECKOUT_OVERVIEW_PAGE =
            ThreadLocal.withInitial(CheckoutOverviewPage::new);

    private static final ThreadLocal<CheckoutCompletePage> CHECKOUT_COMPLETE_PAGE =
            ThreadLocal.withInitial(CheckoutCompletePage::new);

    // =========================================================
    // Page Object Access
    // =========================================================

    /**
     * Returns LoginPage for current execution thread.
     */
    public static LoginPage getLoginPage() {

        return LOGIN_PAGE.get();
    }

    /**
     * Returns HomePage for current execution thread.
     */
    public static HomePage getHomePage() {

        return HOME_PAGE.get();
    }

    /**
     * Returns ProductPage for current execution thread.
     */
    public static ProductPage getProductPage() {

        return PRODUCT_PAGE.get();
    }

    /**
     * Returns CartPage for current execution thread.
     */
    public static CartPage getCartPage() {

        return CART_PAGE.get();
    }

    /**
     * Returns CheckoutPage for current execution thread.
     */
    public static CheckoutPage getCheckoutPage() {

        return CHECKOUT_PAGE.get();
    }

    /**
     * Returns CheckoutOverviewPage for current execution thread.
     */
    public static CheckoutOverviewPage getCheckoutOverviewPage() {

        return CHECKOUT_OVERVIEW_PAGE.get();
    }

    /**
     * Returns CheckoutCompletePage for current execution thread.
     */
    public static CheckoutCompletePage getCheckoutCompletePage() {

        return CHECKOUT_COMPLETE_PAGE.get();
    }

    // =========================================================
    // Cleanup
    // =========================================================

    /**
     * Removes all Page Objects associated with
     * the current execution thread.
     *
     * Prevents:
     * - ThreadLocal memory leaks
     * - Stale Page Object references
     * - Cross-test object reuse
     */
    public static void unload() {

        LOGIN_PAGE.remove();
        HOME_PAGE.remove();
        PRODUCT_PAGE.remove();
        CART_PAGE.remove();
        CHECKOUT_PAGE.remove();
        CHECKOUT_OVERVIEW_PAGE.remove();
        CHECKOUT_COMPLETE_PAGE.remove();
    }
}