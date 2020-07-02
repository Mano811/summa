// Chrome browser .New website testing : Opens up the site and checks the login . This is a login fail scenario. It also verifies the contents in the pop up

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
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class ContentValidation {

//	private TargetEnvironment targetEnvironment;
//	StepLib stepLib = new StepLib();
	WebDriver driver = null;

//	@org.testng.annotations.Factory(dataProvider = "targetEnvironment")
//	public ContentValidation(TargetEnvironment targetEnvironment) {
//
//		this.targetEnvironment = targetEnvironment;
//
//	}

	@BeforeClass()
	public void init() throws Throwable {

		try {

			/*
			 * DesiredCapabilities capabilities = new DesiredCapabilities();
			 * 
			 * capabilities.setCapability("targetEnvironment", this.targetEnvironment);
			 * 
			 * stepLib.setJobId(TestListener.jobId); stepLib.setRunId(TestListener.runId);
			 * 
			 * driver = stepLib.getDriver(capabilities, "Test_01_TEP_InternshipApp");
			 */

//			DesiredCapabilities capabilities = new CapabilityFactory().Create(targetEnvironment,
//					"Login");
//			driver = stepLib.getStepWebDriver(capabilities);
			
			Proxy proxy=new Proxy();
			proxy.setHttpProxy("165.225.104.32:9400");
			proxy.setSslProxy("165.225.104.32:9400");
			DesiredCapabilities cap=DesiredCapabilities.chrome();
//			 cap.setVersion("75.0.3770.80");
//			 cap.setCapability(CapabilityType.PROXY, proxy);
			 
			 driver=new RemoteWebDriver(new URL("http://10.100.97.152:30692/wd/hub"),cap);

			String url = "http://ec2-54-165-62-138.compute-1.amazonaws.com:5002/login";
			System.out.println("*********************Before the get****************");

			driver.get(url);
			System.out.println("*********************After the get****************");

			// driver.manage().window().maximize();
			Thread.sleep(1360);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	@Description("Checks if the application starts up and validates the content")
	@Test
	@Step("Checks if the application starts up and validates the content ")
	public void LoginToApplicationAndCheckContent() throws Throwable {

		driver.manage().window().maximize();

		WebElement email = driver.findElement(By.xpath("//*[@id='exampleInputEmail1']"));
		email.clear();
		email.sendKeys("infinity@test.com");
		Thread.sleep(200);
		WebElement password = driver.findElement(By.xpath("//*[@id='passwordInput']"));
		password.clear();
		password.sendKeys("1234");
		Thread.sleep(200);
		
		driver.findElement(By.xpath("/html/body/app-root/app-login-page/div/div/div[3]/form/div[3]/button[1]")).click();
		Thread.sleep(5400);
		String expected=driver.findElement(By.xpath("/html/body/app-root/app-login-multi-role/div/div/div[3]/div/h2")).getText();
		
		System.out.println("The expected is"+expected);
		if(expected!="kkk")
		{
	        Assert.fail ("wrong content");

		}
		
		driver.quit();

	}

}
