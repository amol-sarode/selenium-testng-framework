package com.amol.automation.factory;

import com.amol.automation.pages.CartPage;
import com.amol.automation.pages.CheckoutCompletePage;
import com.amol.automation.pages.CheckoutOverviewPage;
import com.amol.automation.pages.CheckoutPage;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.LoginPage;
import com.amol.automation.pages.ProductPage;

public final class PageObjectManager {

	private PageObjectManager() {
	}

	private static final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();

	private static final ThreadLocal<HomePage> homePage = new ThreadLocal<>();

	private static final ThreadLocal<ProductPage> productPage = new ThreadLocal<>();

	private static final ThreadLocal<CartPage> cartPage = new ThreadLocal<>();

	private static final ThreadLocal<CheckoutPage> checkoutPage = new ThreadLocal<>();

	private static final ThreadLocal<CheckoutOverviewPage> checkoutOverviewPage = new ThreadLocal<>();

	private static final ThreadLocal<CheckoutCompletePage> checkoutCompletePage = new ThreadLocal<>();

	public static LoginPage getLoginPage() {

		if (loginPage.get() == null) {

			loginPage.set(new LoginPage());
		}

		return loginPage.get();
	}

	public static HomePage getHomePage() {

		if (homePage.get() == null) {

			homePage.set(new HomePage());
		}

		return homePage.get();
	}

	public static ProductPage getProductPage() {

		if (productPage.get() == null) {

			productPage.set(new ProductPage());
		}

		return productPage.get();
	}

	public static CartPage getCartPage() {

		if (cartPage.get() == null) {

			cartPage.set(new CartPage());
		}

		return cartPage.get();
	}

	public static CheckoutPage getCheckoutPage() {

		if (checkoutPage.get() == null) {

			checkoutPage.set(new CheckoutPage());
		}

		return checkoutPage.get();
	}

	public static CheckoutOverviewPage getCheckoutOverviewPage() {

		if (checkoutOverviewPage.get() == null) {

			checkoutOverviewPage.set(new CheckoutOverviewPage());
		}

		return checkoutOverviewPage.get();
	}

	public static CheckoutCompletePage getCheckoutCompletePage() {

		if (checkoutCompletePage.get() == null) {

			checkoutCompletePage.set(new CheckoutCompletePage());
		}

		return checkoutCompletePage.get();
	}

	public static void unload() {

		loginPage.remove();
		homePage.remove();
		productPage.remove();
		cartPage.remove();
		checkoutPage.remove();
		checkoutOverviewPage.remove();
		checkoutCompletePage.remove();
	}
}