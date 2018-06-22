package KDF;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * this is the excel executor goes row by row of excel reads the keywords in
 * excel sheet executes one by one
 *
 */

public class Membership_Registration {
	WebDriver driver;
	Sheet excelSheet;
	String strDateFormat = "dd_MM_yyyy_HH_mm";
	SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	java.util.Date date = new java.util.Date();
	
	
	@BeforeTest
	public void setUp() throws Exception {
		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "+ sdf.format(date));	
	}

	@Test
	public void test() throws Exception {
		Framework framework = new Framework(driver);
		excelSheet = ExcelFileSheet.getExcelSheet("test\\resources\\data", "KDT.xlsx","Membership");
		
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
		System.out.println(rowCount);
		for (int i = 1; i < rowCount; i++) {
			Row row = excelSheet.getRow(i);
			if (row.getCell(0).toString().length() == 0) {
				if(row.getCell(1).toString().length() > 0){
					try {
						framework.performAction(row.getCell(1).toString(), row
								.getCell(2).toString(), row.getCell(3).toString(),
								row.getCell(4).toString(), row.getCell(5).toString());
						
					} catch (Exception e) {
						System.out.println("fail =" + e.getMessage());
					}
				}
			}
			else {
				System.out.println("New Testcase-> " + row.getCell(0).toString()
						+ " Started");
				framework = new Framework(driver);
			}
		}
	}

	@AfterTest
	public void tearDown() {
		
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));
		
	}
}