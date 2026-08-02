package com.amol.automation.reports;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Logger;

import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {


	private ExtentReportManager() {

	}


	private static ExtentReports extent;


	private static final ThreadLocal<ExtentTest> extentTest =
			new ThreadLocal<>();


	private static final Logger log =
			LoggerUtils.getLogger(ExtentReportManager.class);



	public static synchronized void initReports() {


		if (extent == null) {


			try {


				Path folder =
						Paths.get(
								System.getProperty("user.dir"),
								"reports"
						);



				Files.createDirectories(folder);



				String timestamp =
						LocalDateTime.now()
						.format(
								DateTimeFormatter.ofPattern(
										"yyyyMMdd_HHmmss"
								)
						);



				String path =
						folder.resolve(
								"AutomationReport_" 
								+ timestamp 
								+ ".html"
						)
						.toString();



				ExtentSparkReporter spark =
						new ExtentSparkReporter(path);



				spark.config()
				.setDocumentTitle(
						"Automation Test Report"
				);



				spark.config()
				.setReportName(
						"Selenium Java TestNG Framework"
				);



				extent = new ExtentReports();


				extent.attachReporter(spark);



				extent.setSystemInfo(
						"Framework",
						"Selenium + Java + TestNG"
				);



				extent.setSystemInfo(
						"Author",
						"Amol"
				);



				extent.setSystemInfo(
						"Environment",
						"QA"
				);



				log.info(
						"Extent Report Created : {}",
						path
				);


			}
			catch(Exception e) {

				log.error(
						"Extent Report initialization failed",
						e
				);

				throw new RuntimeException(e);

			}

		}

	}




	public static void createTest(String name) {


		if(extent == null) {

			initReports();

		}


		ExtentTest test =
				extent.createTest(name);



		extentTest.set(test);


		log.info(
				"Extent Test Created : {}",
				name
		);


	}





	public static ExtentTest getTest() {


		if(extentTest.get() == null) {


			ExtentTest test =
					extent.createTest(
							"Unknown Test"
					);


			extentTest.set(test);

		}



		return extentTest.get();

	}





	/*
	 * Create child node
	 */
	public static ExtentTest createNode(String nodeName) {


		ExtentTest node =
				getTest()
				.createNode(nodeName);



		return node;

	}





	public static void info(String message) {


		getTest()
		.log(
				Status.INFO,
				message
		);

	}




	public static void pass(String message) {


		getTest()
		.log(
				Status.PASS,
				message
		);

	}




	public static void fail(String message) {


		getTest()
		.log(
				Status.FAIL,
				message
		);

	}




	public static void warning(String message) {


		getTest()
		.log(
				Status.WARNING,
				message
		);

	}




	public static void skip(String message) {


		getTest()
		.log(
				Status.SKIP,
				message
		);

	}





	public static synchronized void flushReports() {


		if(extent != null) {


			extent.flush();


			log.info(
					"Extent Report flushed"
			);

		}

	}

}