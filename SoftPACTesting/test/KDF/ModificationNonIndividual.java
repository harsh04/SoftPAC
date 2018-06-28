package KDF;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import framework.*;


public class ModificationNonIndividual {
	WebDriver driver;
	String sheetname = "ModNonIndividual";
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
	public Object[][] getLoginData() {
		return (ExcelFileSheet.readXLSX("test\\resources\\data","DDT.xlsx",sheetname));
	}
	
	@Test(dataProvider = "dataForTest", priority=4)
	public void modifyNonIndividualTest(String action, String constitution,
			String docDetails, String docName, String docAuth, String docIssue,
			String docNum, String docDate, String panNum, String address1,
			String address2, String village, String state, String pin,
			String landline, String mob, String email, String income,
			String education, String activity, String photoImg, String signImg)
			throws Exception {
		
		parameters.put("action", action);
		parameters.put("constitution", constitution);
		parameters.put("docDetails", docDetails);
		parameters.put("docName", docName);
		parameters.put("docAuth", docAuth);
		parameters.put("docIssue", docIssue);
		parameters.put("docNum", docNum);
		parameters.put("docDate", docDate);
		parameters.put("panNum", panNum);
		parameters.put("address1", address1);
		parameters.put("address2", address2);
		parameters.put("village", village);
		parameters.put("state", state);
		parameters.put("pin", pin);
		parameters.put("landline", landline);
		parameters.put("mob", mob);
		parameters.put("email", email);
		parameters.put("income", income);
		parameters.put("education", education);
		parameters.put("activity", activity);
		parameters.put("photoImg", photoImg);
		parameters.put("signImg", signImg);
		
		KDTExecuter.executeTest(driver, sheetname, parameters);
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));
		
	}
}