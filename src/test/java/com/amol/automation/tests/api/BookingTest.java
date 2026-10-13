
package com.amol.automation.tests.api;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.amol.automation.api.BookingApiClient;
import com.amol.automation.api.BookingRequest;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.amol.automation.utils.PayloadUtils;

import io.restassured.response.Response;

/**
 * API test class for booking operations.
 *
 * Does not extend BaseTest because API tests should not initialize WebDriver.
 */
public class BookingTest {

	private static final Logger logger = LoggerUtils.getLogger(BookingTest.class);

	private final BookingApiClient bookingApiClient = new BookingApiClient();

	// =========================================================
	// Create Booking API
	// =========================================================

	@Test(description = "Verify Create Booking API", groups = { "api", "regression" })
	public void verifyCreateBooking() {

		logger.info("Starting Create Booking API test");

		try {

			BookingRequest request = PayloadUtils.readJson("testdata/booking-data.json", BookingRequest.class);

			Response response = bookingApiClient.createBooking(request);

			int statusCode = response.getStatusCode();

			if (statusCode != 200) {

				throw new RuntimeException("Booking creation failed. HTTP status: " + statusCode + ". Response: "
						+ response.asPrettyString());
			}

			int bookingId = response.jsonPath().getInt("bookingid");

			String actualFirstName = response.jsonPath().getString("booking.firstname");

			if (bookingId <= 0) {

				throw new RuntimeException("Booking ID must be greater than zero");
			}

			if (actualFirstName == null || !request.firstname.equals(actualFirstName)) {

				throw new RuntimeException("Booking first name does not match " + "the expected test data");
			}

			logger.info("Booking created successfully. Booking ID: {}", bookingId);

			ExtentReportManager.pass("Booking created successfully. Booking ID: " + bookingId);

		} catch (Exception e) {

			logger.error("Create Booking API test failed", e);

			try {

				ExtentReportManager.fail("Create Booking API test failed: " + e.getMessage());

			} catch (Exception reportingException) {

				logger.error("Unable to log failure in Extent Report", reportingException);
			}

			throw new RuntimeException("Create Booking API test failed", e);
		}
	}
}
