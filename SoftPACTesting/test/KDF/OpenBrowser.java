package KDF;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class OpenBrowser {

	private WebDriver driver;
	static String driverPath = "test\\resources\\drivers\\";

	public OpenBrowser(String browserType) {

		setDriver(browserType);

	}

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType) {
		switch (browserType) {
		case "CHROME":
			driver = initChromeDriver();
			break;
		case "EDGE":
			driver = initEdgeDriver();
			break;
		case "FIREFOX":
			driver = initFirefoxDriver();
			break;
		case "IE":
			driver = initIEDriver();
			break;

		case "HEADLESS":
			driver = initHeadLessDriver();
			break;

		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver();
		}
	}

	private static WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver", driverPath
				+ "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver initEdgeDriver() {
		System.setProperty("webdriver.edge.driver", driverPath
				+ "MicrosoftWebDriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver initIEDriver() {
		System.setProperty("webdriver.ie.driver", driverPath
				+ "IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver initFirefoxDriver() {
		WebDriver driver;
		try {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		} catch (WebDriverException e) {
			File pathToBinary = new File(
					"C:\\Users\\LTI.INFVA07207\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			driver = new FirefoxDriver(ffBinary, firefoxProfile);
			driver.manage().window().maximize();
			return driver;
		}
		
	}

	private static WebDriver initHeadLessDriver() {
		// WebDriver driver = new HtmlUnitDriver();
		// driver.manage().window().maximize();
		System.out
				.println("browser : headless"
						+ " is not implemented due to incorrect libs, Launching Firefox as browser of choice..");
		WebDriver driver = initFirefoxDriver();
		return driver;
	}

	public void initializeTestDriverSetup(String browserType) {
		try {
			setDriver(browserType);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
