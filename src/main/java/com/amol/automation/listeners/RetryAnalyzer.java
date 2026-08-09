package com.amol.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Provides retry functionality for failed TestNG tests.
 *
 * A failed test can be automatically re-executed up to the configured maximum
 * retry count.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;

	private static final int MAX_RETRY_COUNT = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < MAX_RETRY_COUNT) {

			retryCount++;

			return true;
		}

		return false;
	}
}