package com.amol.automation.factory;

import com.amol.automation.actions.LoginActions;

/**
 * Action Object Manager.
 *
 * Responsible only for creating and managing thread-safe Action Objects.
 */
public final class ActionObjectManager {

	private ActionObjectManager() {
	}

	private static final ThreadLocal<LoginActions> loginActions = new ThreadLocal<>();

	public static LoginActions getLoginActions() {

		if (loginActions.get() == null) {

			loginActions.set(new LoginActions());
		}

		return loginActions.get();
	}

	/**
	 * Remove all Action Objects after test execution.
	 */
	public static void unload() {

		loginActions.remove();
	}
}