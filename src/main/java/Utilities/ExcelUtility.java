package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	private static final String EXCEL_PATH = "./src/test/resources/TestData.xlsx";

	// Read a specific row as a Map (header -> cell value)
	public Map<String, String> readRowAsMap(String sheetName, int rowNum)
			throws EncryptedDocumentException, IOException {

		Map<String, String> rowData = new LinkedHashMap<>();

		try (FileInputStream fis = new FileInputStream(EXCEL_PATH); Workbook wb = WorkbookFactory.create(fis)) {

			Sheet sheet = wb.getSheet(sheetName);
			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(rowNum);

			if (headerRow == null || dataRow == null) {
				throw new RuntimeException("Header or Data row is missing in the sheet.");
			}

			int lastCellNum = headerRow.getLastCellNum();
			for (int i = 0; i < lastCellNum; i++) {
				String key = headerRow.getCell(i).toString().trim();
				String value = (dataRow.getCell(i) != null) ? dataRow.getCell(i).toString().trim() : "";
				rowData.put(key, value);
			}
		}

		return rowData;
	}

	public String readDataFromExcel(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fisE = new FileInputStream(EXCEL_PATH);

		Workbook wb = WorkbookFactory.create(fisE);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String cell = row.getCell(cellNum).getStringCellValue();

		return cell;
	}

	public void writeDataToExcel(String sheetName, int rowNum, int cellNum, String data)
			throws EncryptedDocumentException, IOException {

		FileInputStream fisE = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(fisE);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		row.createCell(cellNum, CellType.STRING).setCellValue(data);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		wb.close();

	}

	public static Object[][] readExcelAsDataProvider(String filePath, String sheetName) {
		Object[][] data = null;
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			data = new Object[rowCount - 1][colCount]; // Skipping header

			for (int i = 1; i < rowCount; i++) { // Start from 1 (skip header)
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = getCellValue(cell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private static Object getCellValue(Cell cell) {
		if (cell == null)
			return "";

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return cell.getNumericCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		default:
			return "";

		}
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
	    try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
	         Workbook wb = WorkbookFactory.create(fis)) {
	        Sheet sheet = wb.getSheet(sheetName);
	        return sheet.getLastRowNum(); // Actual number of rows add +1
	    }
	}

	public int getLastCellCount(String sheetName, int rowIndex) throws EncryptedDocumentException, IOException {

		try (FileInputStream fis = new FileInputStream(EXCEL_PATH); Workbook wb = WorkbookFactory.create(fis)) {

			Sheet sheet = wb.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet '" + sheetName + "' not found");
			}

			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				throw new IllegalArgumentException("Row " + rowIndex + " not found in sheet '" + sheetName + "'");
			}

			return row.getLastCellNum(); // Returns number of cells
		}
	}
}
