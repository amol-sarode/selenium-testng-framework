package com.amol.automation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Logger;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Repository class responsible for Customer database operations.
 *
 * Responsibilities: - Execute SQL queries - Handle JDBC objects - Return
 * database results - Report database operation details
 *
 * Assertions must not be added here.
 */
public class CustomerRepository {

	private static final Logger log = LoggerUtils.getLogger(CustomerRepository.class);

	private static final String CREATE_CUSTOMER_QUERY = "INSERT INTO Customers "
			+ "(FirstName, LastName, Email, Phone, City, CustomerStatus) " + "VALUES (?, ?, ?, ?, ?, ?)";

	private static final String GET_CUSTOMER_QUERY = "SELECT FirstName, LastName " + "FROM Customers "
			+ "WHERE CustomerId = ?";

	private static final String UPDATE_CUSTOMER_QUERY = "UPDATE Customers " + "SET Email = ?, UpdatedDate = GETDATE() "
			+ "WHERE CustomerId = ?";

	private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM Customers " + "WHERE CustomerId = ?";

	/**
	 * Creates a new customer.
	 *
	 * @return generated CustomerId
	 * @throws SQLException if database operation fails
	 */
	public int createCustomer(String firstName, String lastName, String email, String phone, String city,
			String customerStatus) throws SQLException {

		ExtentReportManager.info("Creating customer: " + firstName + " " + lastName);

		log.info("Creating customer: {} {}", firstName, lastName);

		try (Connection connection = DatabaseConnectionManager.getConnection();

				PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER_QUERY,
						Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, firstName);
			log.info("Customer first name is : ", firstName);
			ExtentReportManager.info("Customer first name is : " + firstName);
			statement.setString(2, lastName);
			log.info("Customer last name is : ", lastName);
			ExtentReportManager.info("Customer last name is : " + lastName);
			statement.setString(3, email);
			log.info("Customer email id is : ", email);
			ExtentReportManager.info("Customer email id is : " + email);
			statement.setString(4, phone);
			log.info("Customer phone number is : ", phone);
			ExtentReportManager.info("Customer phone number is : " + phone);
			statement.setString(5, city);
			log.info("Customer city is : ", city);
			ExtentReportManager.info("Customer city is : " + city);
			statement.setString(6, customerStatus);
			log.info("Customer status is : ", customerStatus);
			ExtentReportManager.info("Customer status is : " + customerStatus);


			int rowsInserted = statement.executeUpdate();
			
			log.info("Total rows inserted : ", rowsInserted);
			ExtentReportManager.info("Total rows inserted : " + rowsInserted);


			if (rowsInserted == 0) {

				ExtentReportManager.fail("Customer was not inserted");

				throw new SQLException("Customer creation failed. No row inserted.");
			}

			try (ResultSet resultSet = statement.getGeneratedKeys()) {

				if (resultSet.next()) {

					int customerId = resultSet.getInt(1);

					log.info("Customer created successfully. CustomerId: {}", customerId);
					ExtentReportManager.pass("Customer created successfully. CustomerId: " + customerId);

					return customerId;
				}
			}

			ExtentReportManager.fail("Customer created but generated CustomerId was not returned");

			throw new SQLException("Customer created but generated CustomerId was not returned.");

		} catch (SQLException e) {

			log.error("Unable to create customer", e);

			ExtentReportManager.fail("Unable to create customer: " + e.getMessage());

			throw e;
		}
	}

	/**
	 * Retrieves customer name using CustomerId.
	 *
	 * @return customer full name or null when not found
	 * @throws SQLException if database operation fails
	 */
	public String getCustomerName(int customerId) throws SQLException {

		ExtentReportManager.info("Reading customer. CustomerId: " + customerId);

		log.info("Reading customer. CustomerId: {}", customerId);

		try (Connection connection = DatabaseConnectionManager.getConnection();

				PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_QUERY)) {

			statement.setInt(1, customerId);

			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {

					String firstName = resultSet.getString("FirstName");

					String lastName = resultSet.getString("LastName");

					String customerName = firstName + " " + lastName;

					log.info("Customer found: {}", customerName);

					ExtentReportManager.pass("Customer found successfully: " + customerName);

					return customerName;
				}

				log.info("Customer not found. CustomerId: {}", customerId);

				ExtentReportManager.info("Customer not found. CustomerId: " + customerId);

				return null;
			}

		} catch (SQLException e) {

			log.error("Unable to read customer. CustomerId: {}", customerId, e);

			ExtentReportManager.fail("Unable to read customer: " + e.getMessage());

			throw e;
		}
	}

	/**
	 * Updates customer email.
	 *
	 * @return number of rows updated
	 * @throws SQLException if database operation fails
	 */
	public int updateCustomerEmail(int customerId, String email) throws SQLException {

		ExtentReportManager.info("Updating customer email. CustomerId: " + customerId);

		log.info("Updating customer email. CustomerId: {}", customerId);

		try (Connection connection = DatabaseConnectionManager.getConnection();

				PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY)) {

			statement.setString(1, email);
			statement.setInt(2, customerId);

			int rowsUpdated = statement.executeUpdate();

			if (rowsUpdated == 1) {

				log.info("Customer email updated successfully. CustomerId: {}", customerId);

				ExtentReportManager.pass("Customer email updated successfully. CustomerId: " + customerId);

			} else {

				log.info("No customer updated. CustomerId: {}", customerId);

				ExtentReportManager.info("No customer updated. CustomerId: " + customerId);
			}

			return rowsUpdated;

		} catch (SQLException e) {

			log.error("Unable to update customer. CustomerId: {}", customerId, e);

			ExtentReportManager.fail("Unable to update customer: " + e.getMessage());

			throw e;
		}
	}

	/**
	 * Deletes customer using CustomerId.
	 *
	 * @return number of rows deleted
	 * @throws SQLException if database operation fails
	 */
	public int deleteCustomer(int customerId) throws SQLException {

		ExtentReportManager.info("Deleting customer. CustomerId: " + customerId);

		log.info("Deleting customer. CustomerId: {}", customerId);

		try (Connection connection = DatabaseConnectionManager.getConnection();

				PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_QUERY)) {

			statement.setInt(1, customerId);

			int rowsDeleted = statement.executeUpdate();

			if (rowsDeleted == 1) {

				log.info("Customer deleted successfully. CustomerId: {}", customerId);

				ExtentReportManager.pass("Customer deleted successfully. CustomerId: " + customerId);

			} else {

				log.info("No customer deleted. CustomerId: {}", customerId);

				ExtentReportManager.info("No customer deleted. CustomerId: " + customerId);
			}

			return rowsDeleted;

		} catch (SQLException e) {

			log.error("Unable to delete customer. CustomerId: {}", customerId, e);

			ExtentReportManager.fail("Unable to delete customer: " + e.getMessage());

			throw e;
		}
	}
}