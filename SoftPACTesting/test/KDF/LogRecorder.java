package KDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.IllegalFormatException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LogRecorder {
	private static String[] header = {"TestCase", "DDT data", "Keyword", "Object locator","Object Type","Status"};
	static String workingDir;
	static String fileName;
	static String sheetName;
	static int rowNumber = 0;
	
	
	
    public static void CreateExcel() throws IOException, IllegalFormatException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        // Create a Sheet
        Sheet sheet = workbook.createSheet(sheetName);

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < header.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(headerCellStyle);
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(workingDir+"//"+fileName);
        workbook.write(fileOut);
        fileOut.close();
    }
    
    static void modifyExistingWorkbook(String testCase, String recordNum, String keyword, String locator, String locatorType, String result) throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new FileInputStream(workingDir+"//"+fileName));

        // Get Sheet at index 0
        Sheet sheet = workbook.getSheet(sheetName);
        sheet.createRow(rowNumber);
        // Get Row at index 1
        Row row = sheet.getRow(rowNumber);
        Cell cell_0 = row.createCell(0);
        Cell cell_1 = row.createCell(1);
        Cell cell_2 = row.createCell(2);
        Cell cell_3 = row.createCell(3);
        Cell cell_4 = row.createCell(4);
        Cell cell_5 = row.createCell(5);
        
        
        cell_0.setCellValue(testCase);
        cell_1.setCellValue(recordNum);
        cell_2.setCellValue(keyword);
        cell_3.setCellValue(locator);
        cell_4.setCellValue(locatorType);
        cell_5.setCellValue(result);
        
        // Write the output to the file
        FileOutputStream fileOut = new FileOutputStream(workingDir+"//"+fileName);
        workbook.write(fileOut);
        fileOut.close();
    }
}
