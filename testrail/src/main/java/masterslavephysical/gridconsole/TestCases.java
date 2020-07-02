package masterslavephysical.gridconsole;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerTest.class)
public class TestCases {
	WebDriver driver= null;					

	
	// Test to pass as to verify listeners .		
	@Test		
	public void Login()	{	
		
		//System.setProperty("webdriver.chrome.driver", "D:/selenium_grid/selenium_grid/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "D:/selenium_grid/selenium_grid/iedriver.exe");
		driver= new InternetExplorerDriver();
	    driver.get("http://demo.guru99.com/V4/");					
	    driver.findElement(By.name("uid")).sendKeys("mngr34926");							
	    driver.findElement(By.name("password")).sendKeys("amUpenu");							
	    driver.findElement(By.name("btnLogin")).click();					
	}		

	// Forcefully failed this test as to verify listener.		
	@Test		
	public void TestToFail()				
	{		
	    System.out.println("This method to test fail");					
	    Assert.assertTrue(false);			
	}	

}
