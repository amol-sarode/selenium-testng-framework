package com.amol.automation.constants;

/**
 * FrameworkConstants
 *
 * Contains framework-level constants used throughout the automation framework.
 *
 * Configuration values such as URL, browser, timeout, and environment are
 * maintained in config.properties.
 */
public final class FrameworkConstants {

	private FrameworkConstants() {
		// Prevent object creation
	}

	// =========================================================
	// Configuration
	// =========================================================

	public static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

	// =========================================================
	// Reports
	// =========================================================

	public static final String REPORT_FOLDER = System.getProperty("user.dir") + "/reports/";

	// =========================================================
	// Screenshots
	// =========================================================

	public static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/screenshots/";

	// =========================================================
	// Logs
	// =========================================================

	public static final String LOG_FOLDER = System.getProperty("user.dir") + "/logs/";

	// =========================================================
	// Test Data
	// =========================================================

	public static final String TEST_DATA_FOLDER = "src/main/resources/testdata/";

	public static final String LOGIN_EXCEL_FILE = TEST_DATA_FOLDER + "LoginData.xlsx";

	public static final String LOGIN_SHEET = "LoginData";

	public static final String USERS_JSON_FILE = TEST_DATA_FOLDER + "Users.json";

	public static final String PRODUCT_JSON_FILE = TEST_DATA_FOLDER + "ProductData.json";
}