package com.amol.automation.dataprovider;

import org.testng.annotations.DataProvider;

import com.amol.automation.constants.FrameworkConstants;
import com.amol.automation.utils.ExcelUtils;

/**
 * Provides test data for automation tests.
 */
public final class TestDataProvider {

	private TestDataProvider() {

	}

	/**
     * Provides login data from Excel.
     */
    @DataProvider(name = "loginData")
    public static Object[][] loginData() {

        return ExcelUtils.getTestData(
                FrameworkConstants.EXCEL_FILE,
                FrameworkConstants.LOGIN_SHEET
        );
    }
}
