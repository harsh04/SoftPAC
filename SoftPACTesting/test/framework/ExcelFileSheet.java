package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
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

		return keyWordSheet;
	}

	public static String[][] readXLSX(String filePath, String fileName,
			String sheetName) {
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
					//get all data from ddt in string format only
					arrayExcelData[i - 1][j] = getCellValueAsString(sh.getRow(i).getCell(j));
					//System.out.println("inside ddt :"+sh.getRow(i).getCell(j).toString());
					
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData() ");
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	public static String getCellValueAsString(Cell cell) {
		String strCellValue = null;
		if (cell != null) {
			
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCellValue = cell.toString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				System.out.println("inside date :"+cell.toString());
				
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					strCellValue = dateFormat.format(cell.getDateCellValue());
					System.out.println("inside date :"+strCellValue.toString());
				} else {
					Double value = cell.getNumericCellValue();
					Long longValue = value.longValue();
					strCellValue = new String(longValue.toString());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCellValue = new String(new Boolean(
						cell.getBooleanCellValue()).toString());
				break;
			case Cell.CELL_TYPE_BLANK:
				strCellValue = "";
				break;
			default:
				System.out.println("Unable to resolve cell type");
				break;
			}
		}
		return strCellValue;
	}

}