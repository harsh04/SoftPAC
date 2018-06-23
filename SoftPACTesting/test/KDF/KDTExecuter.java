package KDF;

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
							framework.performAction(row.getCell(1).toString(),
									row.getCell(2).toString(), row.getCell(3)
											.toString(), row.getCell(4)
											.toString(), row.getCell(5)
											.toString(), parameters);

						} catch (Exception e) {
							System.out.println("fail = go for debug");
							// e.printStackTrace();
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
