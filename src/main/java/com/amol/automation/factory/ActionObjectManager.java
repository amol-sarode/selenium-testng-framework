package com.amol.automation.factory;

import com.amol.automation.actions.EndToEndActions;
import com.amol.automation.actions.LoginActions;
import com.amol.automation.actions.ProductActions;

/**
 * Action Object Manager.
 *
 * Responsible for creating and managing thread-safe Action Objects.
 *
 * Uses ThreadLocal to ensure each parallel test thread gets its own Action
 * Object instance.
 */
public final class ActionObjectManager {

	private ActionObjectManager() {
	}

	private static final ThreadLocal<LoginActions> loginActions = new ThreadLocal<>();

	private static final ThreadLocal<ProductActions> productActions = new ThreadLocal<>();

	private static final ThreadLocal<EndToEndActions> endToEndActions = new ThreadLocal<>();

	/**
	 * Returns the LoginActions instance for the current thread.
	 */
	public static LoginActions getLoginActions() {

		if (loginActions.get() == null) {

			loginActions.set(new LoginActions());
		}

		return loginActions.get();
	}

	/**
	 * Returns the ProductActions instance for the current thread.
	 */
	public static ProductActions getProductActions() {

		if (productActions.get() == null) {

			productActions.set(new ProductActions());
		}

		return productActions.get();
	}

	/**
	 * Returns the EndToEndActions instance for the current thread.
	 */
	public static EndToEndActions getEndToEndActions() {

		if (endToEndActions.get() == null) {

			endToEndActions.set(new EndToEndActions());
		}

		return endToEndActions.get();
	}

	/**
	 * Removes all Action Objects associated with the current execution thread.
	 *
	 * This prevents ThreadLocal memory leaks and ensures clean execution for the
	 * next test.
	 */
	public static void unload() {

		loginActions.remove();

		productActions.remove();

		endToEndActions.remove();
	}
}