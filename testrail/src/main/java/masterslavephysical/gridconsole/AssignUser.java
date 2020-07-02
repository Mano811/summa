package masterslavephysical.gridconsole;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class AssignUser  {

//	private TargetEnvironment targetEnvironment;
//	StepLib stepLib = new StepLib();
	WebDriver driver = null;

//	@org.testng.annotations.Factory(dataProvider = "targetEnvironment")
//	public AssignUser(TargetEnvironment targetEnvironment) {
//
//		this.targetEnvironment = targetEnvironment;
//
//	}

	@BeforeClass()
	public void init() throws Throwable {

		try {

//			DesiredCapabilities capabilities = new CapabilityFactory().Create(targetEnvironment, "Assignuserflow");
//			driver = stepLib.getStepWebDriver(capabilities);
			
			Proxy proxy=new Proxy();
			proxy.setHttpProxy("165.225.104.32:9400");
			proxy.setSslProxy("165.225.104.32:9400");
			DesiredCapabilities cap=DesiredCapabilities.chrome();
//			 cap.setVersion("75.0.3770.80");
			 cap.setCapability(CapabilityType.PROXY, proxy);
			 
			 driver=new RemoteWebDriver(new URL("http://10.100.97.152:30692/wd/hub"),cap);

			//String url = "http://ec2-54-165-62-138.compute-1.amazonaws.com:5002/login";
			 String url = "http://sbioneline.com/";
			System.out.println("*********************Before the get****************");

			driver.get(url);
			System.out.println("*********************After the get****************");

			// driver.manage().window().maximize();
			Thread.sleep(1360);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	@Description("Loading up the website and maximising the screen")
	@Test(priority = 1)
	@Step("Leading up the website and maximising the screen")
	public void Loadup() throws Throwable {

		driver.manage().window().maximize();

		System.out.println("*********************Inside the Loadfunctionality ****************");
		Thread.sleep(4000);

	}

	@Description("Assigning the user flow")
	@Test(priority = 2)
	@Step("Assigning the user flow ")
	public void Assignusr() throws Throwable {

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
		WebElement role = driver
				.findElement(By.xpath("/html/body/app-root/app-login-multi-role/div/div/div[3]/div/select"));
		Select dropdown = new Select(role);
		// dropdown.selectByVisibleText("Account Manager");

		dropdown.selectByValue("2");
		Thread.sleep(5400);
		driver.findElement(By.xpath("/html/body/app-root/app-login-multi-role/div/div/div[3]/div/button")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"side-main-menu\"]/li[4]/a")).click(); // clicking the user
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/app-root/app-ah-users/div/div/div[1]/div[2]/button")).click();// clicking
																												// assignuser

		Thread.sleep(1000);

		// Module starts

		WebElement autoOptions = driver.findElement(By.xpath("//*[@id=\"projects\"]"));
		autoOptions.sendKeys("IOS NEW");

		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);

		List<WebElement> optionsToSelect = driver.findElements(By.className("mat-option-text"));

		for (WebElement suggest : optionsToSelect) {

			System.out.println(suggest.getText());
			if (suggest.getText().equalsIgnoreCase("IOS NEW")) {
				suggest.click();
				break;
			}

		}

		// Module ends

		WebElement usr = driver.findElement(By.xpath("//*[@id=\"mat-input-1\"]")); //
		usr.sendKeys("Sam");

		driver.manage().timeouts().implicitlyWait(150000, TimeUnit.MILLISECONDS);

		List<WebElement> optionssel = driver.findElements(By.className("mat-option-text"));

		for (WebElement sugg : optionssel) {

			System.out.println(sugg.getText());
			if (sugg.getText().equalsIgnoreCase("Sam")) {
				sugg.click();
				break;
			}

		}

		// End

		WebElement last = driver
				.findElement(By.xpath("/html/body/app-root/app-ah-assign-user/div/div/form/div/div/div[3]/select"));
		Select drpdwn = new Select(last);
		drpdwn.selectByValue("0: Object");

		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/app-root/app-ah-assign-user/div/div/button[1]")).click();

		Thread.sleep(2000);

		driver.switchTo().alert().accept();

		Thread.sleep(5000);

	}

	@Description("Deactivate the user flow ")
	@Test(priority = 3)
	@Step("Deactivate the user flow ")
	public void Deactivateusr() throws Throwable {

		driver.findElement(By.xpath("/html/body/app-root/app-ah-users/div/div/div[2]/table/tbody/tr[2]/td[6]/button[2]/i")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();

		Thread.sleep(4000);
		
		
		driver.quit();
	}

	

}
