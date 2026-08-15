package com.amol.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for reading test data from Excel files.
 *
 * Responsibilities:
 * - Read complete test data
 * - Read individual data rows
 * - Validate Excel files and sheets
 * - Convert Excel values to String
 */
public final class ExcelUtils {

    private ExcelUtils() {
        // Prevent object creation
    }

    private static final Logger log =
            LoggerUtils.getLogger(ExcelUtils.class);

    // =========================================================
    // Complete Test Data
    // =========================================================

    /**
     * Reads all valid test data rows from an Excel sheet.
     *
     * Row 0 is treated as the header row.
     *
     * @param filePath  Excel file path
     * @param sheetName Excel sheet name
     * @return Excel data as Object[][]
     */
    public static Object[][] getTestData(
            String filePath,
            String sheetName) {

        validateFilePath(filePath);
        validateSheetName(sheetName);

        Path excelFile =
                Paths.get(filePath);

        validateFileExists(excelFile);

        log.info(
                "Reading Excel test data. File: {}, Sheet: {}",
                filePath,
                sheetName);

        try (InputStream inputStream =
                     Files.newInputStream(excelFile);
             XSSFWorkbook workbook =
                     new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet =
                    getSheet(workbook, sheetName);

            Row headerRow =
                    getHeaderRow(sheet, sheetName);

            int columnCount =
                    headerRow.getLastCellNum();

            if (columnCount <= 0) {
                throw new IllegalStateException(
                        "Excel sheet does not contain columns: "
                                + sheetName);
            }

            DataFormatter formatter =
                    new DataFormatter();

            int lastRow =
                    sheet.getLastRowNum();

            int validRowCount = 0;

            for (int rowIndex = 1;
                 rowIndex <= lastRow;
                 rowIndex++) {

                if (isValidRow(
                        sheet.getRow(rowIndex),
                        formatter)) {

                    validRowCount++;
                }
            }

            Object[][] testData =
                    new Object[validRowCount][columnCount];

            int dataIndex = 0;

            for (int rowIndex = 1;
                 rowIndex <= lastRow;
                 rowIndex++) {

                Row row =
                        sheet.getRow(rowIndex);

                if (!isValidRow(row, formatter)) {
                    continue;
                }

                for (int columnIndex = 0;
                     columnIndex < columnCount;
                     columnIndex++) {

                    testData[dataIndex][columnIndex] =
                            getCellValue(
                                    row,
                                    columnIndex,
                                    formatter);
                }

                dataIndex++;
            }

            log.info(
                    "Excel test data loaded successfully. "
                            + "Records: {}, Columns: {}",
                    validRowCount,
                    columnCount);

            return testData;

        } catch (IOException e) {

            log.error(
                    "Unable to read Excel file: {}",
                    filePath,
                    e);

            throw new IllegalStateException(
                    "Unable to read Excel file: "
                            + filePath,
                    e);
        }
    }

    // =========================================================
    // Single Row
    // =========================================================

    /**
     * Reads one specific data row.
     *
     * Row 0 = header
     * Row 1 = first data row
     *
     * @param filePath  Excel file path
     * @param sheetName Excel sheet name
     * @param dataRow   data row number
     * @return row data
     */
    public static String[] getRowData(
            String filePath,
            String sheetName,
            int dataRow) {

        validateFilePath(filePath);
        validateSheetName(sheetName);

        if (dataRow < 1) {
            throw new IllegalArgumentException(
                    "Data row must be greater than or equal to 1");
        }

        Path excelFile =
                Paths.get(filePath);

        validateFileExists(excelFile);

        log.info(
                "Reading Excel row. File: {}, Sheet: {}, Row: {}",
                filePath,
                sheetName,
                dataRow);

        try (InputStream inputStream =
                     Files.newInputStream(excelFile);
             XSSFWorkbook workbook =
                     new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet =
                    getSheet(workbook, sheetName);

            Row headerRow =
                    getHeaderRow(sheet, sheetName);

            int columnCount =
                    headerRow.getLastCellNum();

            if (dataRow > sheet.getLastRowNum()) {
                throw new IllegalArgumentException(
                        "Excel data row does not exist: "
                                + dataRow);
            }

            Row row =
                    sheet.getRow(dataRow);

            DataFormatter formatter =
                    new DataFormatter();

            if (!isValidRow(row, formatter)) {
                throw new IllegalStateException(
                        "Excel data row is empty: "
                                + dataRow);
            }

            String[] rowData =
                    new String[columnCount];

            for (int columnIndex = 0;
                 columnIndex < columnCount;
                 columnIndex++) {

                rowData[columnIndex] =
                        getCellValue(
                                row,
                                columnIndex,
                                formatter);
            }

            log.info(
                    "Excel row loaded successfully: {}",
                    dataRow);

            return rowData;

        } catch (IOException e) {

            log.error(
                    "Unable to read Excel file: {}",
                    filePath,
                    e);

            throw new IllegalStateException(
                    "Unable to read Excel file: "
                            + filePath,
                    e);
        }
    }

    // =========================================================
    // Sheet
    // =========================================================

    private static XSSFSheet getSheet(
            XSSFWorkbook workbook,
            String sheetName) {

        XSSFSheet sheet =
                workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException(
                    "Excel sheet not found: "
                            + sheetName);
        }

        return sheet;
    }

    // =========================================================
    // Header
    // =========================================================

    private static Row getHeaderRow(
            XSSFSheet sheet,
            String sheetName) {

        Row headerRow =
                sheet.getRow(0);

        if (headerRow == null) {
            throw new IllegalStateException(
                    "Excel header row is missing: "
                            + sheetName);
        }

        return headerRow;
    }

    // =========================================================
    // Cell Value
    // =========================================================

    private static String getCellValue(
            Row row,
            int columnIndex,
            DataFormatter formatter) {

        if (row == null ||
                row.getCell(columnIndex) == null) {

            return "";
        }

        return formatter
                .formatCellValue(
                        row.getCell(columnIndex))
                .trim();
    }

    // =========================================================
    // Row Validation
    // =========================================================

    private static boolean isValidRow(
            Row row,
            DataFormatter formatter) {

        if (row == null ||
                row.getCell(0) == null) {

            return false;
        }

        return !formatter
                .formatCellValue(
                        row.getCell(0))
                .trim()
                .isEmpty();
    }

    // =========================================================
    // File Validation
    // =========================================================

    private static void validateFileExists(
            Path filePath) {

        if (!Files.exists(filePath)) {

            throw new IllegalArgumentException(
                    "Excel file not found: "
                            + filePath);
        }

        if (!Files.isRegularFile(filePath)) {

            throw new IllegalArgumentException(
                    "Excel path is not a valid file: "
                            + filePath);
        }
    }

    // =========================================================
    // Input Validation
    // =========================================================

    private static void validateFilePath(
            String filePath) {

        if (filePath == null ||
                filePath.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Excel file path cannot be null or empty");
        }
    }

    private static void validateSheetName(
            String sheetName) {

        if (sheetName == null ||
                sheetName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Excel sheet name cannot be null or empty");
        }
    }
}