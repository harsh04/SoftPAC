package Grid;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class AlertTestNg {
	WebDriver driver;
  @Test
  public void f() {
	  
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
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver","test\\resources\\drivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://the-internet.herokuapp.com/javascript_alerts");
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
