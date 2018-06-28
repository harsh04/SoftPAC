package framework;

import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

public class KDTExecuter {
	
	public static void executeTest(WebDriver driver, String sheetname,
		
		HashMap<String, String> parameters) {
		Framework framework = new Framework(driver);
		Sheet KDTexcelSheet;
		
		try {
			KDTexcelSheet = ExcelFileSheet.getExcelSheet(
					"test\\resources\\data", "KDT.xlsx", sheetname);

			int KDTRowCount = KDTexcelSheet.getLastRowNum()
					- KDTexcelSheet.getFirstRowNum();

			for (int i = 1; i < KDTRowCount; i++) {
				Row row = KDTexcelSheet.getRow(i);
				if (row.getCell(0).toString().length() == 0) {
					if (row.getCell(1).toString().length() > 0) {
						try {
							String value = ExcelFileSheet.getCellValueAsString(row.getCell(4));
							if(value.contains("{{")){
								String colName = value.replace("{{", "");
								colName = colName.replace("}}", "");				
								value = parameters.get(colName);
							}
							framework.performAction(ExcelFileSheet.getCellValueAsString(row.getCell(1)),
									ExcelFileSheet.getCellValueAsString(row.getCell(2)),
									ExcelFileSheet.getCellValueAsString(row.getCell(3)),
									value,
									ExcelFileSheet.getCellValueAsString(row.getCell(5)));

						} catch (Exception e) {
							System.out.println("fail = its time to debug");
							// e.printStackTrace();
						}
					}else{
						LogRecorder.rowNumber++;
						if(Framework.testCaseStatus){
							LogRecorder.modifyExistingWorkbook("", "", "", "", "", "Complete Test Case Passed");
						}else{
							LogRecorder.modifyExistingWorkbook("", "", "", "", "", "Test Case Failed");
						}
					}
				} else {
					System.out.println("New Testcase-> "
							+ row.getCell(0).toString() + " Started");
					
					LogRecorder.rowNumber++;
					LogRecorder.modifyExistingWorkbook("", "", "", "", "", "");
					LogRecorder.rowNumber++;
					LogRecorder.modifyExistingWorkbook(row.getCell(0)
							.toString(), parameters.toString(), "", "", "", "");

				}
			}
		} catch (IOException e1) {
			System.out.println("Unable to read excel file");
		} catch (InvalidFormatException e) {
			System.out.println("Invalid file name");
		}
	}
}
