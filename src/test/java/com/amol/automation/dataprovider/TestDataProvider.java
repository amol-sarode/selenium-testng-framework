package com.amol.automation.dataprovider;

import org.testng.annotations.DataProvider;

import com.amol.automation.constants.FrameworkConstants;
import com.amol.automation.utils.ExcelUtils;

/**
 * Provides test data for automation tests.
 *
 * Test data is maintained externally in Excel and supplied to TestNG tests
 * using DataProvider.
 */
public final class TestDataProvider {

	private TestDataProvider() {
	}

	// =========================================================
	// Login Test Data
	// =========================================================

	/**
	 * Provides login credentials from Excel.
	 *
	 * Excel file and sheet name are maintained in FrameworkConstants.
	 *
	 * @return login test data
	 */
	@DataProvider(name = "loginData")
	public static Object[][] loginData() {

		return ExcelUtils.getTestData(FrameworkConstants.LOGIN_EXCEL_FILE, FrameworkConstants.LOGIN_SHEET);
	}
}