package com.amol.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.actions.LoginActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.constants.FrameworkConstants;
import com.amol.automation.utils.ExcelUtils;

public class LoginTest extends BaseTest {

    private final LoginActions loginActions = new LoginActions();

    // =========================================================
    // Valid Login
    // =========================================================

    @Test(
        description = "Verify login with valid user",
        groups = { "smoke", "regression" }
    )
    public void verifyLogin() {

        String[] loginData = ExcelUtils.getRowData(
                FrameworkConstants.LOGIN_EXCEL_FILE,
                FrameworkConstants.LOGIN_SHEET,
                1
        );

        // Excel:
        // [0] = Row number
        // [1] = Username
        // [2] = Password
        // [3] = Expected Error Message

        String username = loginData[1];
        String password = loginData[2];

        loginActions.login(username, password);
    }

    // =========================================================
    // Invalid Login
    // =========================================================

    @Test(
        description = "Verify login with invalid credentials",
        groups = { "negative", "regression" }
    )
    public void verifyInvalidLogin() {

        String[] loginData = ExcelUtils.getRowData(
                FrameworkConstants.LOGIN_EXCEL_FILE,
                FrameworkConstants.LOGIN_SHEET,
                3
        );

        String username = loginData[1];
        String password = loginData[2];
        String expectedErrorMessage = loginData[3];

        loginActions.login(username, password);

        String actualErrorMessage = loginActions.getErrorMessage();

        Assert.assertEquals(
                actualErrorMessage,
                expectedErrorMessage,
                "Login error message does not match expected message"
        );
    }
}