package KDF;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import framework.*;

public class TransactionView {
	WebDriver driver;
	String sheetname = "transactionView";
	Sheet KDTexcelSheet;
	String strDateFormat = "dd_MM_yyyy_HH_mm_ss";
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
	
	@DataProvider(name = "dataForTest")
	public Object[][] getTransactionData() {
		return (ExcelFileSheet.readXLSX("test\\resources\\da"
				+ "ta","DDT.xlsx",sheetname));
	}
	
	@Test(dataProvider = "dataForTest", priority=6)
	public void transactionTest(String date, String voucher) throws Exception {
		parameters.put("date", date);
		parameters.put("voucher", voucher);

		KDTExecuter.executeTest(driver, sheetname, parameters);
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));

	}
}