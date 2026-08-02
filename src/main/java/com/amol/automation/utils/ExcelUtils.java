package com.amol.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {

	private ExcelUtils() {

	}

	private static final Logger log = LoggerUtils.getLogger(ExcelUtils.class);


	public static Object[][] getTestData(String filePath, String sheetName) {


		log.info("Reading Excel file : {}", filePath);


		try (FileInputStream fis = new FileInputStream(new File(filePath));

				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {


			XSSFSheet sheet = workbook.getSheet(sheetName);


			if (sheet == null) {

				throw new RuntimeException("Sheet '" + sheetName + "' not found.");

			}


			DataFormatter formatter = new DataFormatter();


			Row headerRow = sheet.getRow(0);


			if (headerRow == null) {

				throw new RuntimeException("Excel header row is missing");

			}


			int cols = headerRow.getPhysicalNumberOfCells();


			int lastRow = sheet.getLastRowNum();


			int validRows = 0;


			// Count only rows having username data
			for (int i = 1; i <= lastRow; i++) {


				Row row = sheet.getRow(i);


				if (row != null
						&& row.getCell(0) != null
						&& !formatter.formatCellValue(row.getCell(0)).trim().isEmpty()) {


					validRows++;

				}

			}



			Object[][] data = new Object[validRows][cols];


			int index = 0;


			for (int i = 1; i <= lastRow; i++) {


				Row row = sheet.getRow(i);


				if (row != null
						&& row.getCell(0) != null
						&& !formatter.formatCellValue(row.getCell(0)).trim().isEmpty()) {



					for (int j = 0; j < cols; j++) {


						data[index][j] =
								formatter.formatCellValue(row.getCell(j));

					}


					index++;

				}

			}



			log.info("Excel data loaded successfully. Total records : {}", validRows);


			return data;



		} catch (IOException e) {


			log.error("Unable to read Excel file", e);


			throw new RuntimeException("Unable to read Excel file : " + filePath, e);

		}

	}

}