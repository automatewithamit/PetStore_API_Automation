package api.petstore.utilities;

import api.petstore.reporting.ExtentReportManager;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static String[] getUserNameColumnValues(String fileName, String sheetName, String columnName) {
        ExtentReportManager.getTest().log(Status.INFO, "Starting test with File: " + fileName + ", " + sheetName + ", " + columnName);
        List<String> columnValues = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + fileName);
            }

            DataFormatter formatter = new DataFormatter();
            int columnIndex = -1;
            Row headerRow = sheet.getRow(0);

            // Find the column index based on column name
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                if (formatter.formatCellValue(headerRow.getCell(i)).equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column " + columnName + " does not exist in sheet " + sheetName);
            }

            // Iterate over the rows and add the cell values to the list
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String cellValue = formatter.formatCellValue(row.getCell(columnIndex));
                    columnValues.add(cellValue);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return columnValues.toArray(new String[0]);
    }
    public static String getCellValue(String fileName, String sheetName, int row, int column) {
        String cellValue = "";
        try (FileInputStream file = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + fileName);
            }

            Row sheetRow = sheet.getRow(row);
            if (sheetRow == null) {
                throw new IllegalArgumentException("Row " + row + " does not exist in sheet " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();
            cellValue = formatter.formatCellValue(sheetRow.getCell(column));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellValue;
    }


    public static Object[][] getExcelData(String filePath, String sheetName) {
        ExtentReportManager.getTest().log(Status.INFO, "Starting test with File: " + filePath + ", " + sheetName);
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<Object> rowData = new ArrayList<>();
                row.forEach(cell -> rowData.add(formatter.formatCellValue(cell)));
                data.add(rowData.toArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }
}
