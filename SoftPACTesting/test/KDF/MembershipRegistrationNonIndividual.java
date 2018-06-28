package KDF;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import framework.*;

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
	
	@Test(dataProvider = "dataForTest", priority=2)
	public void memberRegistrationNonIndividualTest(String custType, String	accType, String	name, String	name1, String	name2, String	operator1, String	operator2, String	contitution, String	docDesc,String docName, String	authority, String	issuePlace, String	registerNum, String	docDate, String	panNum, String	Address1,String  Address2, String	village, String	state, String	pin, String	landline, String	mob, String	email, String	income, String 	education, String	activity, String accNum, String imagePhoto, String imageSig, String notification) throws Exception {
		parameters.put("custType", custType);
		parameters.put("accType", accType);
		parameters.put("name", name);
		parameters.put("name1", name1);
		parameters.put("name2", name2);
		parameters.put("operator1",operator1 );
		parameters.put("operator2",operator2 );
		parameters.put("contitution", contitution);
		parameters.put("docDesc", docDesc);
		parameters.put("docName", docName);
		parameters.put("authority", authority);
		parameters.put("issuePlace", issuePlace);
		parameters.put("registerNum", registerNum);
		parameters.put("docDate", docDate);
		parameters.put("panNum", panNum);
		parameters.put("Address1", Address1);
		parameters.put("Address2", Address2);
		parameters.put("village", village);
		parameters.put("state", state);
		parameters.put("pin", pin);
		parameters.put("landline", landline);
		parameters.put("mob", mob);
		parameters.put("email", email);
		parameters.put("income", income);
		parameters.put("education", education);
		parameters.put("activity", activity);
		parameters.put("accNum", accNum);
		parameters.put("imagePhoto", imagePhoto);
		parameters.put("imageSig", imageSig);	
		parameters.put("notification", notification);
		
		KDTExecuter.executeTest(driver, sheetname, parameters);
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("\n\nExecution Log - End Time - "+sdf.format(date));
		
	}
}