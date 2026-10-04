package com.amol.automation.tests;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.database.DatabaseConnectionManager;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

public class DatabaseConnectionTest {

	private static final Logger log = LoggerUtils.getLogger(DatabaseConnectionTest.class);

	@Test(description = "Verify SQL Server database connection")
	public void verifyDatabaseConnection() {

		Connection connection = null;

		ExtentReportManager.info("Verifying SQL Server database connection");

		try {

			log.info("Establishing SQL Server database connection");

			connection = DatabaseConnectionManager.getConnection();

			Assert.assertNotNull(connection, "Database connection should not be null.");

			Assert.assertFalse(connection.isClosed(), "Database connection should be open.");

			log.info("Database connection verified successfully");

			ExtentReportManager.pass("Database connection verified successfully");

		} catch (SQLException e) {

			log.error("Database connection verification failed", e);

			ExtentReportManager.fail("Database connection verification failed: " + e.getMessage());

			Assert.fail("Database connection failed: " + e.getMessage(), e);

		} finally {

			DatabaseConnectionManager.closeConnection(connection);

			ExtentReportManager.info("Database connection closed");
		}
	}
}