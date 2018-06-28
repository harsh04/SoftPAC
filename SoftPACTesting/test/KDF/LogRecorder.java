package KDF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.IllegalFormatException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LogRecorder {
	private static String[] header = {"TestCase", "DDT Record Number", "Keyword", "Object locator","Object Type","Result"};
	static String workingDir;
	static String fileName;
	static String sheetName;
	
	
	
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
    
    static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new File(workingDir+"//"+fileName));

        // Get Sheet at index 0
        Sheet sheet = workbook.getSheet(sheetName);

        // Get Row at index 1
        Row row = sheet.getRow(1);
        
        // Get the Cell at index 2 from the above row
        Cell cell = row.getCell(2);

        // Create the cell if it doesn't exist
        if (cell == null)
            cell = row.createCell(2);

        // Update the cell's value
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("Updated Value");

        // Write the output to the file
        FileOutputStream fileOut = new FileOutputStream(workingDir+"//"+fileName);
        workbook.write(fileOut);
        fileOut.close();
    }
}
