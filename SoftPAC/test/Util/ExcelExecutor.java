package Util;

import java.sql.Timestamp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import KDF.Framework;

/**
 * this is the excel executor goes row by row of excel reads the keywords in
 * excel sheet executes one by one
 *
 */
public class ExcelExecutor {
	WebDriver driver;
	Sheet excelSheet;

	@BeforeTest
	public void setUp() throws Exception {
		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));
	}

	@Test
	public void test() throws Exception {

		Framework getAndAct = new Framework(driver);
		excelSheet = ReadExcelFileSheet.getExcelSheet("test\\resources\\data", "TestCase.xlsx","Frameworksheet");
		
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
		
		for (int i = 1; i < rowCount; i++) {
			Row row = excelSheet.getRow(i);
			// Check if the first cell contain a value, if yes, That means it is
			// the new testcase name
			if (row.getCell(0).toString().length() == 0) {
				// Print testcase detail on console
				if(row.getCell(1).toString().length() > 0){
					// Call perform function to perform operation on UI
					try {

						getAndAct.performAction(row.getCell(1).toString(), row
								.getCell(2).toString(), row.getCell(3).toString(),
								row.getCell(4).toString(), row.getCell(5).toString());

					} catch (Exception e) {
						System.out.println("fail =" + e.getMessage());
					}// end catch
				}
			}// end if
			else {
				// Print the new testcase name when it started
				System.out.println("New Testcase->" + row.getCell(0).toString()
						+ " Started");
			}// end else
		}// end for
	}

	@AfterClass
	public void tearDown() {
		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));
	}
}