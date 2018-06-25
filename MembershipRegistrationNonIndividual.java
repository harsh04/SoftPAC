package KDF;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * this is the excel executor goes row by row of excel reads the keywords in
 * excel sheet executes one by one
 *
 */

public class MembershipRegistrationNonIndividual {
	WebDriver driver;
	String sheetname = "MembRegNonInd";
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
	
	@Test(dataProvider = "dataForTest")


		public void registrationTest(String accType, String fName, String HoldName1, String HoldName2, String Op1, String Op2, String constitution, String docdetail, String docName, String issueAuthority, String PlaceOfIssue, String RegNo, String Date, String pan, String add1, String add2, String village, String state, String pin, String landline, String mobile, String email, String income, String education, String activity, String Member_id, String image,String mem_id,String image1) throws Exception {
		    parameters.put("accType", accType);
			parameters.put("Name", fName);
			parameters.put("HoldName1", HoldName1);
			parameters.put("HoldName2", HoldName2);
			parameters.put("Op1", Op1);
			parameters.put("Op2", Op2);
			parameters.put("Constitution", constitution);
			parameters.put("DocDetail", docdetail);
			parameters.put("DocName", docName);
			parameters.put("issueAuthority", issueAuthority);
			parameters.put("PlaceofIssue", PlaceOfIssue );
			parameters.put("RegNo", RegNo);
			parameters.put("Date", Date);
			parameters.put("Pan", pan);
			parameters.put("Address1", add1);
			parameters.put("Address2", add2);
			parameters.put("village", village);
			parameters.put("state", state);
			parameters.put("pin", pin);
			parameters.put("landline", landline);
			parameters.put("mobile", mobile);
			parameters.put("email", email);
			parameters.put("income", income);
			parameters.put("education", education);
			parameters.put("activity", activity);
			parameters.put("Member_id", Member_id);
			parameters.put("photo_image", image);
			parameters.put("mem_id", mem_id);
			parameters.put("signature_image", image1);
		KDTExecuter.executeTest(driver, sheetname, parameters);
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));
		
	}
}
