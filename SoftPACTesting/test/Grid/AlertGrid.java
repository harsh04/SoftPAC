package Grid;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AlertGrid {

	static WebDriver driver = null;

	@Parameters({ "browser", "port" })
	@BeforeMethod
	public void beforeTest(String browser, String port) {
		System.out.println("Browser : "+browser);
		System.out.println("Port : "+port);
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
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
	}

	@Test
	public void alert() throws InterruptedException {
		driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(1) > button")).click();
		driver.switchTo().alert().accept();
		String mytext = driver.findElement(By.id("result")).getText();
	    System.out.println(mytext);
	    
	    //use this in testNG
	    Assert.assertEquals("You successfuly clicked an alert", mytext);
	    
	    
	    driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(2) > button")).click();
		driver.switchTo().alert().accept();
		String mytext1 = driver.findElement(By.id("result")).getText();
	    System.out.println(mytext1);
	    
	    driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(2) > button")).click();
		driver.switchTo().alert().dismiss();
		String mytext3 = driver.findElement(By.id("result")).getText();
	    System.out.println(mytext3);
	    
	    driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(3) > button")).click();
		driver.switchTo().alert().sendKeys("deepanshu");
		driver.switchTo().alert().accept();
		String mytext4 = driver.findElement(By.id("result")).getText();
	    System.out.println(mytext4);
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
