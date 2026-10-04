package com.amol.automation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

/**
 * Manages database connections.
 *
 * Responsibilities: - Load JDBC driver - Create database connection - Close
 * database connection when explicitly required
 *
 * This class does not create Extent nodes. The Action layer owns the
 * business-level Extent node.
 */
public final class DatabaseConnectionManager {

	private static final Logger log = LoggerUtils.getLogger(DatabaseConnectionManager.class);

	private static final String DRIVER = ConfigReader.getInstance().getProperty("db.driver");

	private static final String URL = ConfigReader.getInstance().getProperty("db.url");

	private static final String USERNAME = ConfigReader.getInstance().getProperty("db.username");

	private static final String PASSWORD = ConfigReader.getInstance().getProperty("db.password");

	private DatabaseConnectionManager() {
		// Prevent object creation
	}

	/**
	 * Creates and returns a database connection.
	 *
	 * @return active database connection
	 * @throws SQLException when connection cannot be established
	 */
	public static Connection getConnection() throws SQLException {

		ExtentReportManager.info("Initializing database connection");

		log.info("Initializing database connection");

		try {

			Class.forName(DRIVER);

			log.debug("SQL Server JDBC Driver loaded successfully");

			ExtentReportManager.info("SQL Server JDBC Driver loaded successfully");

		} catch (ClassNotFoundException e) {

			log.error("SQL Server JDBC Driver not found", e);

			ExtentReportManager.fail("SQL Server JDBC Driver not found");

			throw new SQLException("SQL Server JDBC Driver not found.", e);
		}

		try {

			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			log.info("Database connection established successfully");

			ExtentReportManager.pass("Database connection established successfully");

			return connection;

		} catch (SQLException e) {

			log.error("Failed to establish database connection", e);

			ExtentReportManager.fail("Failed to establish database connection: " + e.getMessage());

			throw e;
		}
	}

	/**
	 * Closes an existing database connection.
	 *
	 * @param connection database connection
	 */
	public static void closeConnection(Connection connection) {

		ExtentReportManager.info("Closing database connection");

		if (connection == null) {

			log.info("Database connection is already null");

			ExtentReportManager.info("Database connection is already null");

			return;
		}

		try {

			connection.close();

			log.info("Database connection closed successfully");

			ExtentReportManager.info("Database connection closed successfully");

		} catch (SQLException e) {

			log.error("Failed to close database connection", e);

			ExtentReportManager.fail("Failed to close database connection: " + e.getMessage());
		}
	}
}