package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;

import com.amol.automation.factory.ActionObjectManager;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.pages.HomePage;
import com.amol.automation.pages.ProductPage;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Business actions for Product functionality.
 *
 * Responsibilities:
 * - Perform product-related business operations
 * - Coordinate LoginActions where required
 * - Create high-level business reporting nodes
 * - Return UI state to Test layer
 *
 * Assertions remain in Test layer.
 *
 * Reporting flow:
 *
 * Test
 *   -> Extent Test Title
 *
 * Action
 *   -> createNode()
 *
 * Page Object
 *   -> info()
 *   -> pass()
 *   -> fail()
 *
 * Listener
 *   -> final PASS / FAIL
 */
public class ProductActions {

    private static final Logger log =
            LoggerUtils.getLogger(ProductActions.class);

    private final HomePage homePage;
    private final ProductPage productPage;
    private final LoginActions loginActions;

    // =========================================================
    // Constructor
    // =========================================================

    public ProductActions() {

        homePage =
                PageObjectManager.getHomePage();

        productPage =
                PageObjectManager.getProductPage();

        loginActions =
                ActionObjectManager.getLoginActions();
    }

    // =========================================================
    // Login
    // =========================================================

    /**
     * Performs login operation.
     *
     * LoginActions is responsible for creating
     * the "Login" business node.
     */
    public void login(
            String username,
            String password) {

        log.info("Starting login");

        loginActions.login(
                username,
                password);

        log.info("Login completed");
    }

    // =========================================================
    // Product Page
    // =========================================================

    /**
     * Checks whether Products page is displayed.
     *
     * Assertions remain in Test layer.
     *
     * @return true if Products page is displayed
     */
    public boolean isHomePageDisplayed() {

        log.info(
                "Checking Products page");

        return homePage.isHomePageDisplayed();
    }

    /**
     * Gets Products page title.
     *
     * @return Products page title
     */
    public String getProductsPageTitle() {

        log.info(
                "Getting Products page title");

        return productPage.getProductsPageTitle();
    }

    /**
     * Adds specified product to cart.
     *
     * Creates business-level reporting node.
     *
     * @param productName product name
     */
    public void addProduct(
            String productName) {

        ExtentReportManager.createNode(
                "Add Product");

        log.info(
                "Adding product : {}",
                productName);

        productPage.addProduct(
                productName);

        log.info(
                "Product added : {}",
                productName);
    }

    /**
     * Gets cart item count.
     *
     * @return cart count
     */
    public String getCartCount() {

        log.info(
                "Getting cart count");

        return productPage.getCartCount();
    }

    /**
     * Opens shopping cart.
     */
    public void openCart() {

        ExtentReportManager.createNode(
                "Open Cart");

        log.info(
                "Opening cart");

        productPage.clickCart();
    }

    // =========================================================
    // Complete Product Flow
    // =========================================================

    /**
     * Executes complete product add-to-cart business flow.
     *
     * Assertions remain in the Test layer.
     *
     * @param username username
     * @param password password
     * @param productName product to add
     */
    public void verifyProductAddToCart(
            String username,
            String password,
            String productName) {

        login(
                username,
                password);

        addProduct(
                productName);

        log.info(
                "Product add-to-cart flow completed");
    }
}