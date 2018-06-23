package KDF;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * this is the excel executor goes row by row of excel reads the keywords in
 * excel sheet executes one by one
 *
 */

public class Login {
	WebDriver driver;
	String sheetname = "login";
	Sheet KDTexcelSheet;
	static int ddtRecordNum = 1;
	String strDateFormat = "dd_MM_yyyy_HH_mm";
	SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	java.util.Date date = new java.util.Date();
	static HashMap<String, String> parameters = new HashMap<String, String>();
	
	@BeforeTest
	public void setUp() throws Exception {
		java.util.Date date = new java.util.Date();
		System.out.println("\n Start Time - "+ sdf.format(date)+"\n\n");	
		
		LogRecorder.workingDir = "test\\resources\\data\\logRecord";
		LogRecorder.fileName = "LOG_"+sheetname+"_"+sdf.format(date)+".xlsx";
		LogRecorder.sheetName = sheetname;
		LogRecorder.CreateExcel();
		
	}
	
	@DataProvider(name = "login")
	public Object[][] getLoginData() {
		return (ExcelFileSheet.readXLSX("test\\resources\\data","DDT.xlsx",sheetname));
	}
	
	@Test(dataProvider = "login")
	public void loginTest(String user, String pass) throws Exception {
		ddtRecordNum++;
		parameters.put("username", user);
		parameters.put("password", pass);
		
		Framework framework = new Framework(driver);
		KDTexcelSheet = ExcelFileSheet.getExcelSheet("test\\resources\\data", "KDT.xlsx",sheetname);		
		int KDTRowCount = KDTexcelSheet.getLastRowNum() - KDTexcelSheet.getFirstRowNum();
		
		for (int i = 1; i < KDTRowCount; i++) {
			Row row = KDTexcelSheet.getRow(i);
			if (row.getCell(0).toString().length() == 0) {
				if(row.getCell(1).toString().length() > 0){
					try {
						framework.performAction(row.getCell(1).toString(), row
								.getCell(2).toString(), row.getCell(3).toString(),
								row.getCell(4).toString(), row.getCell(5).toString(), parameters);
						
					} catch (Exception e) {
						System.out.println("fail =" + e.getMessage());
					}
				}
			}
			else {
				System.out.println("New Testcase-> " + row.getCell(0).toString()
						+ " Started");
				
			}
		}
	}

	@AfterTest
	public void tearDown() {
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));
		
	}
}