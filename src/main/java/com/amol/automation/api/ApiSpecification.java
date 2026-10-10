
package com.amol.automation.api;

import org.apache.logging.log4j.Logger;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class ApiSpecification {

    private static final Logger logger =
            LoggerUtils.getLogger(ApiSpecification.class);

    private ApiSpecification() {
    }

    public static RequestSpecification getSpec() {

        String baseUrl = ConfigReader.getInstance()
                .getProperty("api.base.url");

        if (baseUrl == null || baseUrl.trim().isEmpty()) {

            String errorMessage =
                    "Property 'api.base.url' is missing in config.properties";

            logger.error(errorMessage);
            ExtentReportManager.fail(errorMessage);

            throw new IllegalStateException(errorMessage);
        }

        baseUrl = baseUrl.trim();

        logger.info("========== API Request Specification ==========");
        logger.info("Base URL: {}", baseUrl);
        logger.info("Content-Type: application/json");
        logger.info("Accept: application/json");
        logger.info("User-Agent: Mozilla/5.0");

        ExtentReportManager.info(
                "========== API Request Specification =========="
        );

        ExtentReportManager.info("Base URL: " + baseUrl);
        ExtentReportManager.info("Content-Type: application/json");
        ExtentReportManager.info("Accept: application/json");
        ExtentReportManager.info("User-Agent: Mozilla/5.0");

        RequestSpecification specification =
                new RequestSpecBuilder()
                        .setBaseUri(baseUrl)
                        .setContentType(ContentType.JSON)
                        .setAccept("application/json")
                        .addHeader("User-Agent", "Mozilla/5.0")
                        .build();

        logger.info("API Request Specification created successfully");

        ExtentReportManager.pass(
                "API Request Specification created successfully"
        );

        return specification;
    }
}
