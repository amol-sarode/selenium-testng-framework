
package com.amol.automation.api;

import org.apache.logging.log4j.Logger;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApiClient {

	private static final Logger logger = LoggerUtils.getLogger(BookingApiClient.class);

	// =========================================================
	// Create Booking
	// =========================================================

	public Response createBooking(BookingRequest request) {

		ExtentReportManager.createNode("Create Booking API");

		logger.info("Sending POST request to /booking");

		ExtentReportManager.info("Sending POST request to /booking");

		Response response = given().spec(ApiSpecification.getSpec()).log().all().body(request).when().post("/booking")
				.then().log().all().extract().response();

		int statusCode = response.getStatusCode();

		logger.info("Create Booking response status: {}", statusCode);

		ExtentReportManager.info("Response status: " + statusCode);

		if (statusCode >= 200 && statusCode < 300) {

			logger.info("Booking API request completed successfully");

			ExtentReportManager.pass("Booking API returned a successful HTTP status");

		} else {

			logger.error("Booking API failed. HTTP status: {}. Response: {}", statusCode, response.asPrettyString());

			ExtentReportManager.fail(
					"Booking API failed. HTTP status: " + statusCode + ". Response: " + response.asPrettyString());
		}

		return response;
	}

	// =========================================================
	// Get Booking
	// =========================================================

	public Response getBooking(int bookingId) {

		logger.info("Sending GET request for booking ID: {}", bookingId);

		ExtentReportManager.info("Sending GET request for booking ID: " + bookingId);

		Response response = given().spec(ApiSpecification.getSpec()).log().all().when().get("/booking/" + bookingId)
				.then().log().all().extract().response();

		logger.info("Get Booking response status: {}", response.getStatusCode());

		return response;
	}
}
