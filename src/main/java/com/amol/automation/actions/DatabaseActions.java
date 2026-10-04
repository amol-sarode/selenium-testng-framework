package com.amol.automation.actions;

import org.apache.logging.log4j.Logger;

import com.amol.automation.database.CustomerRepository;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;

/**
 * Business actions for Database functionality.
 *
 * Responsibilities: - Perform database business operations - Coordinate
 * database repository - Create high-level Extent reporting nodes - Return
 * database results to Test layer
 *
 * Assertions belong to the Test layer.
 *
 * Reporting Flow:
 *
 * Test | v DatabaseActions | +--> CREATE Customer | +--> READ Customer | +-->
 * UPDATE Customer | +--> DELETE Customer | v CustomerRepository
 */
public class DatabaseActions {

	private static final Logger log = LoggerUtils.getLogger(DatabaseActions.class);

	private final CustomerRepository customerRepository;

	public DatabaseActions() {
		customerRepository = new CustomerRepository();
	}

	/**
	 * Creates a new customer.
	 *
	 * @return generated customer ID
	 * @throws Exception if customer creation fails
	 */
	public int createCustomer(String firstName, String lastName, String email, String phone, String city,
			String customerStatus) throws Exception {

		ExtentReportManager.createNode("Create Customer");

		log.info("===== Customer CREATE Process Started =====");

		try {

			int customerId = customerRepository.createCustomer(firstName, lastName, email, phone, city, customerStatus);

			log.info("Customer created successfully. CustomerId: {}", customerId);

			log.info("===== Customer CREATE Process Completed =====");

			return customerId;

		} catch (Exception e) {

			log.error("Customer CREATE process failed", e);

			throw e;
		}
	}

	/**
	 * Reads customer details using customer ID.
	 *
	 * @return customer full name, or null when customer is not found
	 * @throws Exception if customer retrieval fails
	 */
	public String getCustomerName(int customerId) throws Exception {

		ExtentReportManager.createNode("Read Customer");

		log.info("===== Customer READ Process Started. CustomerId: {} =====", customerId);

		try {

			String customerName = customerRepository.getCustomerName(customerId);

			log.info("Customer READ process completed. CustomerId: {}", customerId);

			return customerName;

		} catch (Exception e) {

			log.error("Customer READ process failed. CustomerId: {}", customerId, e);

			throw e;
		}
	}

	/**
	 * Updates customer email.
	 *
	 * @return number of rows updated
	 * @throws Exception if customer update fails
	 */
	public int updateCustomerEmail(int customerId, String email) throws Exception {

		ExtentReportManager.createNode("Update Customer");

		log.info("===== Customer UPDATE Process Started. CustomerId: {} =====", customerId);

		try {

			int rowsUpdated = customerRepository.updateCustomerEmail(customerId, email);

			log.info("Customer UPDATE process completed. CustomerId: {}, Rows Updated: {}", customerId, rowsUpdated);

			return rowsUpdated;

		} catch (Exception e) {

			log.error("Customer UPDATE process failed. CustomerId: {}", customerId, e);

			throw e;
		}
	}

	/**
	 * Deletes customer using customer ID.
	 *
	 * @return number of rows deleted
	 * @throws Exception if customer deletion fails
	 */
	public int deleteCustomer(int customerId) throws Exception {

		ExtentReportManager.createNode("Delete Customer");

		log.info("===== Customer DELETE Process Started. CustomerId: {} =====", customerId);

		try {

			int rowsDeleted = customerRepository.deleteCustomer(customerId);

			log.info("Customer DELETE process completed. CustomerId: {}, Rows Deleted: {}", customerId, rowsDeleted);

			return rowsDeleted;

		} catch (Exception e) {

			log.error("Customer DELETE process failed. CustomerId: {}", customerId, e);

			throw e;
		}
	}
}