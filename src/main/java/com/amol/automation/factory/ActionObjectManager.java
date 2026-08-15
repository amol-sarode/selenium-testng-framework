package com.amol.automation.factory;

import com.amol.automation.actions.EndToEndActions;
import com.amol.automation.actions.LoginActions;
import com.amol.automation.actions.ProductActions;

/**
 * Central manager for Action Objects.
 *
 * Responsibilities:
 * - Maintain Action Objects using ThreadLocal
 * - Provide thread-safe Action Object access
 * - Prevent sharing Action Objects during parallel execution
 * - Cleanup ThreadLocal references after test execution
 */
public final class ActionObjectManager {

    private ActionObjectManager() {
        // Prevent object creation
    }

    // =========================================================
    // ThreadLocal Action Objects
    // =========================================================

    private static final ThreadLocal<LoginActions> LOGIN_ACTIONS =
            ThreadLocal.withInitial(LoginActions::new);

    private static final ThreadLocal<ProductActions> PRODUCT_ACTIONS =
            ThreadLocal.withInitial(ProductActions::new);

    private static final ThreadLocal<EndToEndActions> END_TO_END_ACTIONS =
            ThreadLocal.withInitial(EndToEndActions::new);

    // =========================================================
    // Action Object Access
    // =========================================================

    /**
     * Returns LoginActions for current execution thread.
     */
    public static LoginActions getLoginActions() {

        return LOGIN_ACTIONS.get();
    }

    /**
     * Returns ProductActions for current execution thread.
     */
    public static ProductActions getProductActions() {

        return PRODUCT_ACTIONS.get();
    }

    /**
     * Returns EndToEndActions for current execution thread.
     */
    public static EndToEndActions getEndToEndActions() {

        return END_TO_END_ACTIONS.get();
    }

    // =========================================================
    // Cleanup
    // =========================================================

    /**
     * Removes all Action Objects associated
     * with the current execution thread.
     *
     * Prevents:
     * - ThreadLocal memory leaks
     * - Stale Action Object references
     * - Cross-test object reuse
     */
    public static void unload() {

        LOGIN_ACTIONS.remove();
        PRODUCT_ACTIONS.remove();
        END_TO_END_ACTIONS.remove();
    }
}
