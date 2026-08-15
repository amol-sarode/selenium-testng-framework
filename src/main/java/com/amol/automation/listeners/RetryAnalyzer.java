package com.amol.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.amol.automation.utils.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {

        boolean retryEnabled =
                Boolean.parseBoolean(
                        getProperty("retry.enabled", "false"));

        if (!retryEnabled) {
            return false;
        }

        int maxRetryCount =
                Integer.parseInt(
                        getProperty("retry.count", "1"));

        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }

        return false;
    }

    private String getProperty(
            String key,
            String defaultValue) {

        String value =
                ConfigReader.getInstance()
                        .getProperty(key);

        return value == null
                || value.trim().isEmpty()
                        ? defaultValue
                        : value;
    }
}