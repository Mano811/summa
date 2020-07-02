package masterslavephysical.gridconsole;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class Testcr {

	WebDriver driver = null;

	@Description("Checks the AccountsPage")
	@Test
	@Step("Checks the AccountsPage ")
	public void Accountspagecheck() throws Throwable {
		
		System.out.println("Started the testcase 02  *******");
		
		try 
		{
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("165.225.104.40:9400");
		proxy.setSslProxy("165.225.104.40:9400");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		//http://10.100.97.35:31403/wd/hub
		// Used : http://ec2-18-221-176-55.us-east-2.compute.amazonaws.com:30966/wd/hub
        cap.setCapability(CapabilityType.PROXY, proxy);
		driver = new RemoteWebDriver(
				new URL("http://10.100.97.35:31403/wd/hub"), cap);
		
		Thread.sleep(20000);
		String url = "https://www.google.com/";
		System.out.println("*********************Before the get****************");

		driver.get(url);
		System.out.println("*********************After the get****************");
		Thread.sleep(1360);
		
		// starting the test

		driver.manage().window().maximize();

		
		
		
		 driver.close();
		driver.quit();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("the error is ************");
			e.printStackTrace();
		}

	}

}
