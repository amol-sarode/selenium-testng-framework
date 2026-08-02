package com.amol.automation.dataprovider;

import org.testng.annotations.DataProvider;

import com.amol.automation.constants.FrameworkConstants;
import com.amol.automation.utils.JsonUtils;

/**
 * Provides test data for automation tests.
 */
public final class TestDataProvider {

	private TestDataProvider() {

	}



	/**
	 * Provides valid login data from JSON.
	 */
	@DataProvider(name = "validLoginData")
	public static Object[][] validLoginData() {

		return new Object[][] {

				{
					JsonUtils.getValue(
							FrameworkConstants.USERS_JSON,
							"validUser",
							"username"
					),

					JsonUtils.getValue(
							FrameworkConstants.USERS_JSON,
							"validUser",
							"password"
					)
				}

		};

	}



	/**
	 * Provides locked user login data from JSON.
	 */
	@DataProvider(name = "lockedUserData")
	public static Object[][] lockedUserData() {

		return new Object[][] {

				{
					JsonUtils.getValue(
							FrameworkConstants.USERS_JSON,
							"lockedUser",
							"username"
					),

					JsonUtils.getValue(
							FrameworkConstants.USERS_JSON,
							"lockedUser",
							"password"
					)
				}

		};

	}



	/**
	 * Provides invalid login data from JSON.
	 */
	@DataProvider(name = "invalidLoginData")
	public static Object[][] invalidLoginData() {

		return new Object[][] {

				{
					"invalid_user",
					"wrong_password"
				}

		};

	}

}