package com.amol.automation.factory;

import com.amol.automation.pages.CartPage;
import com.amol.automation.pages.CheckoutCompletePage;
import com.amol.automation.pages.CheckoutOverviewPage;
import com.amol.automation.pages.CheckoutPage;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.pages.ProductPage;

/**
 * PageObjectManager
 *
 * Responsible for creating and managing Page Object instances.
 *
 * ThreadLocal is used so that every parallel test thread receives its own Page
 * Object instances.
 */
public final class PageObjectManager {

	private PageObjectManager() {
		// Prevent object creation
	}

	// =========================================================
	// Thread-Safe Page Objects
	// =========================================================

	private static final ThreadLocal<LoginPage> LOGIN_PAGE = new ThreadLocal<>();

	private static final ThreadLocal<HomePage> HOME_PAGE = new ThreadLocal<>();

	private static final ThreadLocal<ProductPage> PRODUCT_PAGE = new ThreadLocal<>();

	private static final ThreadLocal<CartPage> CART_PAGE = new ThreadLocal<>();

	private static final ThreadLocal<CheckoutPage> CHECKOUT_PAGE = new ThreadLocal<>();

	private static final ThreadLocal<CheckoutOverviewPage> CHECKOUT_OVERVIEW_PAGE = new ThreadLocal<>();

	private static final ThreadLocal<CheckoutCompletePage> CHECKOUT_COMPLETE_PAGE = new ThreadLocal<>();

	// =========================================================
	// Login Page
	// =========================================================

	/**
	 * Returns LoginPage for the current thread.
	 */
	public static LoginPage getLoginPage() {

		if (LOGIN_PAGE.get() == null) {

			LOGIN_PAGE.set(new LoginPage());
		}

		return LOGIN_PAGE.get();
	}

	// =========================================================
	// Home Page
	// =========================================================

	/**
	 * Returns HomePage for the current thread.
	 */
	public static HomePage getHomePage() {

		if (HOME_PAGE.get() == null) {

			HOME_PAGE.set(new HomePage());
		}

		return HOME_PAGE.get();
	}

	// =========================================================
	// Product Page
	// =========================================================

	/**
	 * Returns ProductPage for the current thread.
	 */
	public static ProductPage getProductPage() {

		if (PRODUCT_PAGE.get() == null) {

			PRODUCT_PAGE.set(new ProductPage());
		}

		return PRODUCT_PAGE.get();
	}

	// =========================================================
	// Cart Page
	// =========================================================

	/**
	 * Returns CartPage for the current thread.
	 */
	public static CartPage getCartPage() {

		if (CART_PAGE.get() == null) {

			CART_PAGE.set(new CartPage());
		}

		return CART_PAGE.get();
	}

	// =========================================================
	// Checkout Page
	// =========================================================

	/**
	 * Returns CheckoutPage for the current thread.
	 */
	public static CheckoutPage getCheckoutPage() {

		if (CHECKOUT_PAGE.get() == null) {

			CHECKOUT_PAGE.set(new CheckoutPage());
		}

		return CHECKOUT_PAGE.get();
	}

	// =========================================================
	// Checkout Overview Page
	// =========================================================

	/**
	 * Returns CheckoutOverviewPage for the current thread.
	 */
	public static CheckoutOverviewPage getCheckoutOverviewPage() {

		if (CHECKOUT_OVERVIEW_PAGE.get() == null) {

			CHECKOUT_OVERVIEW_PAGE.set(new CheckoutOverviewPage());
		}

		return CHECKOUT_OVERVIEW_PAGE.get();
	}

	// =========================================================
	// Checkout Complete Page
	// =========================================================

	/**
	 * Returns CheckoutCompletePage for the current thread.
	 */
	public static CheckoutCompletePage getCheckoutCompletePage() {

		if (CHECKOUT_COMPLETE_PAGE.get() == null) {

			CHECKOUT_COMPLETE_PAGE.set(new CheckoutCompletePage());
		}

		return CHECKOUT_COMPLETE_PAGE.get();
	}

	// =========================================================
	// Cleanup
	// =========================================================

	/**
	 * Removes all Page Objects associated with the current thread.
	 *
	 * This should be called after test execution.
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