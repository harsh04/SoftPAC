package Grid;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class SeGrPortTest {

	static WebDriver driver = null;

	@Parameters({ "browser", "port" })
	@BeforeMethod
	public void beforeTest(String browser, String port) {
		// compares the value of parameter name with Firefox, if its firefox
		// then it will lauch firefox and run the script.

		if (browser.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver();
			// DesiredCapabilities.firefox();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.WIN10);
			capabilities.setVersion("ANY");
			try {
				driver = new RemoteWebDriver(new URL(port.concat("/wd/hub")),
						capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * try { driver=new RemoteWebDriver(new URL(port),capabilities); }
			 * catch (MalformedURLException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */

		} else if (browser.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver","D:\\Selenium_Training\\chromedriver_win32\\chromedriver.exe");
			// driver= new ChromeDriver();
			DesiredCapabilities capabilities1 = DesiredCapabilities.chrome();
			capabilities1.setBrowserName("chrome");
			capabilities1.setPlatform(Platform.WIN10);
			capabilities1.setVersion("ANY");
			try {
				driver = new RemoteWebDriver(new URL(port.concat("/wd/hub")),
						capabilities1);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			 * try { driver=new RemoteWebDriver(new URL(port),capabilities); }
			 * catch (MalformedURLException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		} else if (browser.equalsIgnoreCase("ie")) {
			// System.setProperty("webdriver.ie.driver","D:\\Selenium_Training\\IEDriverServer_x64_2.51.0\\IEDriverServer.exe");
			// driver= new InternetExplorerDriver();
			DesiredCapabilities capabilities2 = DesiredCapabilities
					.internetExplorer();
			capabilities2.setBrowserName("internet explorer");
			capabilities2.setPlatform(Platform.WIN10);
			capabilities2.setVersion("ANY");
			try {
				driver = new RemoteWebDriver(new URL(port.concat("/wd/hub")),
						capabilities2);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			 * 
			 * try { driver=new RemoteWebDriver(new URL(port),capabilities); }
			 * catch (MalformedURLException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		} else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		
		driver.get("https://www.wikipedia.org");
	
	}

	@Test
	public void wiki() throws InterruptedException {
		driver.manage().window().maximize();
		// Thread.sleep(5000);

		driver.findElement(By.cssSelector("strong")).click();
		// Thread.sleep(5000);
		String expectedTitle = "Wikipedia, the free encyclopedia";

		// fetch the title of the web page and save it into a string variable
		String actualTitle = driver.getTitle();

		Assert.assertEquals(expectedTitle, actualTitle);

		// Thread.sleep(20000);
	}

	@AfterMethod
	// this annotation would run once test script execution would complete
	public void afterTest() {
		try {
			driver.wait(5000);
		} catch (Exception e) {
			driver.quit();
		}
	}
}
