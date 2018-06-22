package KDF;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Util.OpenBrowser;

public class Framework {

	WebDriver driver;
	OpenBrowser browserobj;
	static String variable = "";

	public Framework(WebDriver driver) {
		this.driver = driver;
	}

	public void performAction(String operation, String objectName,
			String objectType, String value, String var) throws Exception {
		System.out.println("Command : " + operation);
		String status = "failed";
		switch (operation.toUpperCase()) {
		case "OPENEXCEL":
			
			break;
			
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
			
		case "STORE":
			variable = var;
			status = "Passed";
			break;
			
		case "VERIFYTITLE":
			try{
				Assert.assertEquals(driver.getTitle(), variable);
				status = "Passed";
			}catch(Throwable e){
				status = "Title does not match";
			}
			break;
			
		case "VERIFYTEXTONPAGE":
			try{
				Assert.assertTrue(driver.getPageSource().contains(variable));
				status = "Passed";
			}catch(Throwable e){
				status = "Text not found!";
			}
			break;
		case "CLICK":
			// Perform click
			System.out.println("On : " + objectName);
			driver.findElement(this.getByObject(objectName, objectType))
					.click();
			status = "Passed";
			break;
		case "SETTEXT":
			// Set text on control
			System.out.println("In : " + objectName);
			System.out.println("Value : " + value);
			driver.findElement(this.getByObject(objectName, objectType))
					.sendKeys(value);
			status = "Passed";
			break;
		case "GOTOURL":
			// Get url of application
			System.out.println("Site : " + value);
			driver.get(value);
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