package com.amol.automation.constants;

/**
 * FrameworkConstants
 *
 * Contains common constants used throughout the framework.
 */
public final class FrameworkConstants {

	private FrameworkConstants() {

	}

	// Application

	public static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

	// Reports

	public static final String REPORT_FOLDER = System.getProperty("user.dir") + "/reports/";

	public static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/screenshots/";

	// Test Data

	public static final String TEST_DATA_FOLDER = "src/main/resources/testdata/";

	public static final String EXCEL_FILE = TEST_DATA_FOLDER + "LoginData.xlsx";

	public static final String LOGIN_SHEET = "LoginData";

	public static final String USERS_JSON = TEST_DATA_FOLDER + "Users.json";

	public static final String PRODUCT_JSON =
            TEST_DATA_FOLDER + "ProductData.json";
}
