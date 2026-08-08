package com.amol.automation.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.amol.automation.actions.ProductActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.utils.LoggerUtils;

public class ProductTest extends BaseTest {

    private static final Logger log =
            LoggerUtils.getLogger(ProductTest.class);

    @Test(
            description = "Verify product add to cart with valid user",
            groups = {"smoke", "regression"}
    )
    public void verifyProductAddToCart() {

        log.info("TEST STARTED : Verify Product Add To Cart");

        // =====================================================
        // Create Parent Extent Test
        // =====================================================

   

        // =====================================================
        // Test Data
        // =====================================================

        String username = "standard_user";
        String password = "secret_sauce";

        // =====================================================
        // Execute Business Flow
        // =====================================================

        ProductActions productActions =
                new ProductActions();

        productActions.verifyProductAddToCart(
                username,
                password
        );

        // =====================================================
        // Test Completed
        // =====================================================


    }
}
