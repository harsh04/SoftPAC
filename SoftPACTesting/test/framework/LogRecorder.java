package framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.IllegalFormatException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LogRecorder {
	private static String[] header = { "TestCase", "DDT data", "Keyword",
			"Object locator", "Object Type", "Status" };
	public static String workingDir;
	public static String fileName;
	public static String sheetName;
	public static int rowNumber = 0;

	public static void CreateExcel() throws IOException, IllegalFormatException {
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for
												// generating `.xls` file

		// Create a Sheet
		Sheet sheet = workbook.createSheet(sheetName);

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLACK.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		headerCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		headerCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < header.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < header.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(workingDir + "//"
				+ fileName);
		workbook.write(fileOut);
		fileOut.close();
	}

	static void modifyExistingWorkbook(String testCase, String recordData,
			String keyword, String locator, String locatorType, String result)
			throws InvalidFormatException, IOException {
		// Obtain a workbook from the excel file
		Workbook workbook = WorkbookFactory.create(new FileInputStream(
				workingDir + "//" + fileName));

		// Get Sheet at index 0
		Sheet sheet = workbook.getSheet(sheetName);
		sheet.createRow(rowNumber);
		// Get Row
		Row row = sheet.getRow(rowNumber);

		Cell cell_0 = row.createCell(0);
		Cell cell_1 = row.createCell(1);
		Cell cell_2 = row.createCell(2);
		Cell cell_3 = row.createCell(3);
		Cell cell_4 = row.createCell(4);
		Cell cell_5 = row.createCell(5);

		// styling
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		borderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		CellStyle greenBackgroundStyle = workbook.createCellStyle();
		greenBackgroundStyle.setBorderBottom(CellStyle.BORDER_THIN);
		greenBackgroundStyle.setBottomBorderColor(IndexedColors.BLACK
				.getIndex());
		greenBackgroundStyle.setBorderLeft(CellStyle.BORDER_THIN);
		greenBackgroundStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		greenBackgroundStyle.setBorderRight(CellStyle.BORDER_THIN);
		greenBackgroundStyle
				.setRightBorderColor(IndexedColors.BLACK.getIndex());
		greenBackgroundStyle.setBorderTop(CellStyle.BORDER_THIN);
		greenBackgroundStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		Font fontGreen = workbook.createFont();
		fontGreen.setColor(IndexedColors.GREEN.getIndex());
		greenBackgroundStyle.setFont(fontGreen);

		CellStyle redBackgroundStyle = workbook.createCellStyle();
		redBackgroundStyle.setBorderBottom(CellStyle.BORDER_THIN);
		redBackgroundStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		redBackgroundStyle.setBorderLeft(CellStyle.BORDER_THIN);
		redBackgroundStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		redBackgroundStyle.setBorderRight(CellStyle.BORDER_THIN);
		redBackgroundStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		redBackgroundStyle.setBorderTop(CellStyle.BORDER_THIN);
		redBackgroundStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		Font fontRed = workbook.createFont();
		fontRed.setColor(IndexedColors.RED.getIndex());
		redBackgroundStyle.setFont(fontRed);

		if (testCase.length() > 0) {
			cell_0.setCellValue(testCase);
			cell_0.setCellStyle(borderStyle);
		}

		if (recordData.length() > 0) {
			cell_1.setCellValue(recordData);
			cell_1.setCellStyle(borderStyle);
		}

		cell_2.setCellValue(keyword);
		cell_2.setCellStyle(borderStyle);

		cell_3.setCellValue(locator);
		cell_3.setCellStyle(borderStyle);

		cell_4.setCellValue(locatorType);
		cell_4.setCellStyle(borderStyle);

		if (result.contains("Pass")) {
			cell_5.setCellValue(result);
			cell_5.setCellStyle(greenBackgroundStyle);
		} else {
			if (result.length() > 0) {
				cell_5.setCellValue(result);
				cell_5.setCellStyle(redBackgroundStyle);
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < header.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to the file
		FileOutputStream fileOut = new FileOutputStream(workingDir + "//"
				+ fileName);
		workbook.write(fileOut);
		fileOut.close();
	}
}
