package KDF;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import framework.*;

public class CreateFdJoint {
	WebDriver driver;
	String sheetname = "CreateFdJoint";
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
	
	@Test(dataProvider = "dataForTest", priority=8)
	public void registrationTest(String memberId, String sourceAcc,String custType,String jointName, String jointRelation, String nominee,String relation, String relationType, String deposit, String year, String month, String days, String interestSlab) throws Exception {
		parameters.put("memberId", memberId);
		parameters.put("sourceAcc", sourceAcc);
		parameters.put("custType", custType);
		parameters.put("jointName", jointName);
		parameters.put("jointRelation", jointRelation);
		parameters.put("nominee", nominee);
		parameters.put("relation", relation);
		parameters.put("relationType", relationType);
		parameters.put("deposit", deposit);
		parameters.put("year", year);
		parameters.put("month", month);
		parameters.put("days", days);
		parameters.put("interestSlab", interestSlab);
		KDTExecuter.executeTest(driver, sheetname, parameters);
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));
		
	}
}
