// Testcase to check the title

package masterslavephysical.gridconsole;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tep.core.CapabilityFactory;
import com.tep.core.StepLib;
import com.tep.listener.TestListener;
import com.tep.testclient.TargetEnvironment;
import com.tep.testclient.TargetEnvironmentDp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class NMV04 extends TargetEnvironmentDp {

	private TargetEnvironment targetEnvironment;
	StepLib stepLib = new StepLib();
	WebDriver driver = null;

	@org.testng.annotations.Factory(dataProvider = "targetEnvironment")
	public NMV04(TargetEnvironment targetEnvironment) {

		this.targetEnvironment = targetEnvironment;

	}

	@BeforeClass()
	public void init() throws Throwable {

		try {

			DesiredCapabilities capabilities = new CapabilityFactory().Create(targetEnvironment, "Login");
			driver = stepLib.getStepWebDriver(capabilities);

			String url = "http://automationpractice.com/index.php";
			System.out.println("*********************Before the get****************");

			driver.get(url);
			System.out.println("*********************After the get****************");

		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	@Description("No message validation :NMV04 ")
	@Test
	@Step("No message validation : NMV04")
	public void LoginToApplication() throws Throwable {

		driver.manage().window().maximize();

	    driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
	      String content=driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText();
	      
	      	Select drpitem = new Select(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")));
			drpitem.selectByVisibleText("Customer service");
			
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("customer@gmail.com");
			driver.findElement(By.xpath("//*[@id=\"id_order\"]")).sendKeys("IDHHJJSSAA");
		
			
			driver.findElement(By.xpath("//*[@id=\"submitMessage\"]/span")).click();
			String message=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText();
			
			System.out.println("The content is ::"+message);
			
			 if(message.contains("Your message cannot be empty"))
		        {
		        	System.out.println("Correct message");
		        }
		        else
		        {
		        	Assert.fail ("Unexpected Content .Getting :"+content);
		        }
	 
		driver.quit();

	}

}
