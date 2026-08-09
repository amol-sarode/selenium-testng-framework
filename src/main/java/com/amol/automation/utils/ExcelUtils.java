package com.amol.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel Utility class.
 *
 * Responsible for reading test data from Excel files.
 */
public final class ExcelUtils {

	private ExcelUtils() {
	}

	private static final Logger log = LoggerUtils.getLogger(ExcelUtils.class);

	/**
	 * Reads complete test data from an Excel sheet.
	 *
	 * The first row is treated as the header row.
	 *
	 * This method can be used when full Excel data is required.
	 *
	 * @param filePath  Excel file path
	 * @param sheetName Excel sheet name
	 * @return test data as Object[][]
	 */
	public static Object[][] getTestData(String filePath, String sheetName) {

		log.info("Reading Excel test data. File: {}, Sheet: {}", filePath, sheetName);

		validateFilePath(filePath);
		validateSheetName(sheetName);

		File file = new File(filePath);

		if (!file.exists()) {
			throw new RuntimeException("Excel file not found: " + filePath);
		}

		try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new RuntimeException("Excel sheet not found: " + sheetName);
			}

			Row headerRow = sheet.getRow(0);

			if (headerRow == null) {
				throw new RuntimeException("Excel header row is missing: " + sheetName);
			}

			int columnCount = headerRow.getLastCellNum();

			if (columnCount <= 0) {
				throw new RuntimeException("Excel sheet does not contain any columns: " + sheetName);
			}

			DataFormatter formatter = new DataFormatter();

			int lastRow = sheet.getLastRowNum();
			int validRowCount = 0;

			for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {

				Row row = sheet.getRow(rowIndex);

				if (isValidRow(row, formatter)) {
					validRowCount++;
				}
			}

			if (validRowCount == 0) {

				log.warn("No valid test data found in Excel. Sheet: {}", sheetName);

				return new Object[0][columnCount];
			}

			Object[][] testData = new Object[validRowCount][columnCount];

			int dataIndex = 0;

			for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {

				Row row = sheet.getRow(rowIndex);

				if (!isValidRow(row, formatter)) {
					continue;
				}

				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

					if (row.getCell(columnIndex) != null) {

						testData[dataIndex][columnIndex] = formatter.formatCellValue(row.getCell(columnIndex)).trim();

					} else {

						testData[dataIndex][columnIndex] = "";
					}
				}

				dataIndex++;
			}

			log.info("Excel test data loaded successfully. " + "Records: {}, Columns: {}", validRowCount, columnCount);

			return testData;

		} catch (IOException e) {

			log.error("Unable to read Excel file: {}", filePath, e);

			throw new RuntimeException("Unable to read Excel file: " + filePath, e);
		}
	}

	/**
	 * Reads a specific data row from an Excel sheet.
	 *
	 * Row 0 is the header row.
	 *
	 * Therefore:
	 *
	 * row 1 = first data row row 2 = second data row row 3 = third data row
	 *
	 * @param filePath  Excel file path
	 * @param sheetName Excel sheet name
	 * @param dataRow   Excel data row number
	 * @return selected row data as String[]
	 */
	public static String[] getRowData(String filePath, String sheetName, int dataRow) {

		log.info("Reading Excel row. File: {}, Sheet: {}, Row: {}", filePath, sheetName, dataRow);

		validateFilePath(filePath);
		validateSheetName(sheetName);

		if (dataRow < 1) {
			throw new IllegalArgumentException("Data row must be greater than or equal to 1");
		}

		File file = new File(filePath);

		if (!file.exists()) {
			throw new RuntimeException("Excel file not found: " + filePath);
		}

		try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new RuntimeException("Excel sheet not found: " + sheetName);
			}

			Row headerRow = sheet.getRow(0);

			if (headerRow == null) {
				throw new RuntimeException("Excel header row is missing: " + sheetName);
			}

			int columnCount = headerRow.getLastCellNum();

			if (columnCount <= 0) {
				throw new RuntimeException("Excel sheet does not contain any columns: " + sheetName);
			}

			Row row = sheet.getRow(dataRow);

			if (row == null) {
				throw new RuntimeException("Excel data row not found: " + dataRow);
			}

			DataFormatter formatter = new DataFormatter();

			if (!isValidRow(row, formatter)) {
				throw new RuntimeException("Excel data row is empty or invalid: " + dataRow);
			}

			String[] rowData = new String[columnCount];

			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

				if (row.getCell(columnIndex) != null) {

					rowData[columnIndex] = formatter.formatCellValue(row.getCell(columnIndex)).trim();

				} else {

					rowData[columnIndex] = "";
				}
			}

			log.info("Excel row loaded successfully. " + "Row: {}, Columns: {}", dataRow, columnCount);

			return rowData;

		} catch (IOException e) {

			log.error("Unable to read Excel file: {}", filePath, e);

			throw new RuntimeException("Unable to read Excel file: " + filePath, e);
		}
	}

	/**
	 * Checks whether an Excel row contains valid data.
	 *
	 * The first column is used to determine validity.
	 */
	private static boolean isValidRow(Row row, DataFormatter formatter) {

		if (row == null || row.getCell(0) == null) {
			return false;
		}

		return !formatter.formatCellValue(row.getCell(0)).trim().isEmpty();
	}

	/**
	 * Validates Excel file path.
	 */
	private static void validateFilePath(String filePath) {

		if (filePath == null || filePath.trim().isEmpty()) {

			throw new IllegalArgumentException("Excel file path cannot be null or empty");
		}
	}

	/**
     * Validates Excel sheet name.
     */
    private static void validateSheetName(String sheetName) {

        if (sheetName == null || sheetName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Excel sheet name cannot be null or empty"
            );
        }
    }
}
