package KDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileSheet {
	// method takes three parameters
	// 1. file path
	// 2. file name
	// 3. sheet name in the excel file
	// returns the sheet from the given excel file
	static public Sheet getExcelSheet(String filePath, String fileName,
			String sheetName) throws IOException {
		// Create a object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook keywordWorkbook = null;
		// Find the file extension by spliting file name in substing and getting
		// only extension name
		String fileExtension = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtension.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			keywordWorkbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtension.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			keywordWorkbook = new HSSFWorkbook(inputStream);
		}
		// Read sheet inside the workbook by its name
		Sheet keyWordSheet = keywordWorkbook.getSheet(sheetName);
		keyWordSheet.
		return keyWordSheet;
	}
	
	
	public static String[][] readXLSX(String filePath, String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		org.apache.poi.ss.usermodel.Workbook wb = null;
		try {
			File file = new File(filePath + "\\" + fileName);
			FileInputStream fs = new FileInputStream(file);
			// .xls
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {
				// If it is xlsx file then create object of XSSFWorkbook class
				wb = new XSSFWorkbook(fs);
			} else if (fileName.substring(fileName.indexOf(".")).equals(".xls")) {
				// If it is xls file then create object of HSSFWorkbook class
				wb = new HSSFWorkbook(fs);
			}

			org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet(sheetName);

			int totalNoOfRows = sh.getPhysicalNumberOfRows();
			int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
			
			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows - 1; i++) {
				for (int j = 0; j <= totalNoOfCols - 1; j++) {
					sh.getRow(i).getCell(j).setCellType(1);
					arrayExcelData[i - 1][j] = sh.getRow(i).getCell(j)
							.getStringCellValue().toString();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData() ");
			e.printStackTrace();
		}
		return arrayExcelData;
	}

}