package KDF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Framework {

	WebDriver driver;
	OpenBrowser browserobj;
	static String[][] ddtData;
	static Row ddtHead;
	static int ddtRows = 0;
	List<String> ddtHeader = new ArrayList<String>();
	
	
	static HashMap<String, String> variables = new HashMap<String, String>();

	public Framework(WebDriver driver) {
		this.driver = driver;
	}

	public void performAction(String operation, String objectName, 
			String objectType, String value, String var, HashMap<String, String> param) throws Exception {
		
		System.out.println("Command : " + operation);
		String status = "failed";
		
		switch (operation.toUpperCase()) {
			
			case "OPENBROWSER":
				System.out.println("Opening : " + value);
				browserobj = new OpenBrowser(value);
				driver = browserobj.getDriver();
				status = "Passed";
				break;
				
			case "CLOSEBROWSER":
				driver.close();
				status = "Passed";
				break;
				
			case "GOTOURL":
				// Get url of application
				System.out.println("Site : " + value);
				driver.get(value);
				status = "Passed";
				break;
				
			case "WAITUNTIL":
				WebDriverWait wait = new WebDriverWait(driver, 10);
				try{
					wait.until(ExpectedConditions.visibilityOfElementLocated(this.getByObject(objectName, objectType)));
					status = "Passed";
				}catch(Throwable e){
					System.out.println("Object not found");
				}
				break;
				
			case "STORE":
				variables.put(var, driver.findElement(this.getByObject(objectName, objectType)).getText());
				status = "Passed";
				break;
				
			case "VERIFYTITLE":
				try{
					Assert.assertEquals(driver.getTitle(), variables.get(value));
					status = "Passed";
				}catch(Throwable e){
					status = "Title does not match";
				}
				break;
				
			case "VERIFYTEXTONPAGE":
				System.out.println("Value : "+value);
				try{
					Assert.assertTrue(driver.getPageSource().contains(value));
					status = "Passed";
				}catch(Throwable e){
					status = "Text not found!";
				}
				break;
				
			case "CLICK":
				// Perform click
				System.out.println("On : " + objectName);
				driver.findElement(this.getByObject(objectName, objectType)).click();
				status = "Passed";
				break;
				
			case "HOVER":
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(this.getByObject(objectName, objectType))).click().perform();
				status = "Passed";
				break;
				
			case "SETTEXT":
				if(value.contains("{{")){
					String colName = value.replace("{{", "");
					colName = colName.replace("}}", "");				
					System.out.println("In : " + objectName);
					System.out.println("Value : " + param.get(colName));
					driver.findElement(this.getByObject(objectName, objectType)).sendKeys(param.get(colName));
					status = "Passed";
				}else{
					System.out.println("In : " + objectName);
					System.out.println("Value : " + value);
					driver.findElement(this.getByObject(objectName, objectType)).sendKeys(value);
					status = "Passed";
				}			
				break;
			
			case "DROPDOWN":
				List<WebElement> dropdown= driver.findElements(this.getByObject(objectName, objectType));
				for (WebElement drop : dropdown) {
					if(drop.getText().equalsIgnoreCase(value)){
						drop.click();
						System.out.println("Value : " +drop.getText());
						status = "Passed";
					}
				}
				break;
				
			case "SELECT":
					Select dropDown = new Select(driver.findElement(this.getByObject(objectName, objectType)));
					dropDown.selectByValue(value);
					System.out.println("Value : " +value);
					status = "Passed";
				break;
				
			case "GETTEXT":
				// Get text of an element
				System.out.println("Read From : " + objectName);
				String str = driver.findElement(
						this.getByObject(objectName, objectType)).getText();
				System.out.println(str);
				status = "Passed";
				break;
				
			case "TIMEOUT":
				// Get url of application
				System.out.println("For : " + value);
				float sleeptime = Float.parseFloat(value);
				Thread.sleep((long) (sleeptime) * 1000);
				status = "Passed";
				break;
				
			default:
				break;
		}
		System.out.println("Status : " + status);
		System.out.println();
		LogRecorder.rowNumber++;
		LogRecorder.modifyExistingWorkbook("","",operation,objectName,objectType,status);
	}

	/**
	 * Find element BY using object type and value * @param objectName
	 * 
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getByObject(String objectName, String objectType)
			throws Exception {
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(objectName);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(objectName);
		}
		// find by id
		else if (objectType.equalsIgnoreCase("ID")) {
			return By.id(objectName);
		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {
			return By.name(objectName);
		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(objectName);
		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {
			return By.linkText(objectName);
		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
			return By.partialLinkText(objectName);
		} else {
			throw new Exception("Wrong object type");
		}
	}
}