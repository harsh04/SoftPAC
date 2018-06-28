package KDF;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import framework.*;

public class MembershipRegistrationIndividual {
	WebDriver driver;
	String sheetname = "MemRegIndividual";
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
	
	@Test(dataProvider = "dataForTest", priority=1)
	public void registrationTest(String type, String accType, String fName, String mName, String lName, String gender, String date, String religion, String occupation, String landAcre, String caste, String nationality, String otherName, String motherName,String panNum,String	voterID, String	driving,String passport,String aadhar,String address1,String address2,String village,String	state,String pinNum,String	landline,String	mobile,String email,String	income,String education,String maritalStatus,String	natureActivity,String	accNum,String	imagePhoto,String	imageSig,String	notification) throws Exception {
		parameters.put("type", type);
		parameters.put("accType", accType);
		parameters.put("firstName", fName);
		parameters.put("middleName", mName);
		parameters.put("lastName", lName);
		parameters.put("gender", gender);
		parameters.put("date", date);
		parameters.put("religion", religion);
		parameters.put("occupation", occupation);
		parameters.put("landacre", landAcre);
		parameters.put("caste", caste);
		parameters.put("nationality", nationality );
		parameters.put("otherName", otherName);
		parameters.put("motherName", motherName);
		parameters.put("panNum",panNum);
		parameters.put("voterID",voterID);
		parameters.put("driving",driving);
		parameters.put("passport",passport);
		parameters.put("aadhar",aadhar);
		parameters.put("address1",address1);
		parameters.put("address2",address2);
		parameters.put("village",village);
		parameters.put("state",state);
		parameters.put("pinNum",pinNum);
		parameters.put("landline",landline);
		parameters.put("mobile",mobile);
		parameters.put("email",email);
		parameters.put("income",income);
		parameters.put("education",education);
		parameters.put("maritalStatus",maritalStatus);
		parameters.put("natureActivity",natureActivity);
		parameters.put("accNum",accNum);
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
