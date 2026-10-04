package com.amol.automation.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amol.automation.actions.DatabaseActions;
import com.amol.automation.base.BaseTest;
import com.amol.automation.utils.LoggerUtils;

/**
 * Customer CRUD database tests.
 *
 * Test Responsibilities: - Call business actions - Validate expected results
 * using assertions
 *
 * Architecture:
 *
 * Test | v DatabaseActions | v CustomerRepository | v DatabaseConnectionManager
 */
public class CustomerCrudTest extends BaseTest {

	private static final Logger log = LoggerUtils.getLogger(CustomerCrudTest.class);

	private final DatabaseActions databaseActions = new DatabaseActions();

	private static int customerId;

	private static final String FIRST_NAME = "Amol";
	private static final String LAST_NAME = "Sarode";
	private static final String EMAIL = "amol.sarode@test.com";
	private static final String PHONE = "9876543210";
	private static final String CITY = "Ahmedabad";
	private static final String CUSTOMER_STATUS = "ACTIVE";
	private static final String UPDATED_EMAIL = "amol.sarode.updated@test.com";

	/**
	 * Verify customer creation.
	 */
	@Test(description = "Verify customer creation", priority = 1)
	public void verifyCreateCustomer() throws Exception {

		log.info("===== CREATE Customer Test Started =====");

		customerId = databaseActions.createCustomer(FIRST_NAME, LAST_NAME, EMAIL, PHONE, CITY, CUSTOMER_STATUS);

		Assert.assertTrue(customerId > 0,
				"Customer should be created and generated CustomerId should be greater than zero");

		log.info("Customer created successfully. CustomerId: {}", customerId);

		log.info("===== CREATE Customer Test Completed =====");
	}

	/**
	 * Verify customer retrieval.
	 */
	@Test(description = "Verify customer retrieval", dependsOnMethods = "verifyCreateCustomer")
	public void verifyReadCustomer() throws Exception {

		log.info("===== READ Customer Test Started =====");

		String customerName = databaseActions.getCustomerName(customerId);

		Assert.assertNotNull(customerName, "Customer should exist in database");

		Assert.assertEquals(customerName, FIRST_NAME + " " + LAST_NAME, "Customer name does not match expected value");

		log.info("Customer retrieved successfully: {}", customerName);

		log.info("===== READ Customer Test Completed =====");
	}

	/**
	 * Verify customer email update.
	 */
	@Test(description = "Verify customer email update", dependsOnMethods = "verifyReadCustomer")
	public void verifyUpdateCustomer() throws Exception {

		log.info("===== UPDATE Customer Test Started =====");

		int rowsUpdated = databaseActions.updateCustomerEmail(customerId, UPDATED_EMAIL);

		Assert.assertEquals(rowsUpdated, 1, "Exactly one customer record should be updated");

		log.info("Customer email updated successfully. CustomerId: {}", customerId);

		log.info("===== UPDATE Customer Test Completed =====");
	}

	/**
	 * Verify customer deletion.
	 */
	@Test(description = "Verify customer deletion", dependsOnMethods = "verifyUpdateCustomer")
	public void verifyDeleteCustomer() throws Exception {

		log.info("===== DELETE Customer Test Started =====");

		int rowsDeleted = databaseActions.deleteCustomer(customerId);

		Assert.assertEquals(rowsDeleted, 1, "Exactly one customer record should be deleted");

		log.info("Customer deleted successfully. CustomerId: {}", customerId);

		log.info("===== DELETE Customer Test Completed =====");
	}
}