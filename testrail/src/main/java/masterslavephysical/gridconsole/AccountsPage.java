// Chrome browser .New website testing : Opens up the site and checks the login . This is a login fail scenario. It also verifies the contents in the pop up

package masterslavephysical.gridconsole;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tep.listener.TestListener;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

@Listeners(TestListener.class)
public class AccountsPage {

//	private TargetEnvironment targetEnvironment;
//	StepLib stepLib = new StepLib();
	WebDriver driver = null;
//
//	@org.testng.annotations.Factory(dataProvider = "targetEnvironment")
//	public AccountsPage(TargetEnvironment targetEnvironment) {
//
//		this.targetEnvironment = targetEnvironment;
//
//	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

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

//			DesiredCapabilities capabilities = new CapabilityFactory().Create(targetEnvironment, "AccountPage");
//			System.out.println("---------------"+capabilities.getCapability("version"));
//			driver = stepLib.getStepWebDriver(capabilities);
			
			
//			SocketAddress addr = new InetSocketAddress("165.225.104.40", 10223);
//			Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
			
			
			Proxy proxy=new Proxy();
//			proxy.setHttpProxy("165.225.104.40:9400");
//			proxy.setSslProxy("165.225.104.40:9400");
			
			proxy.setHttpProxy("172.27.66.50:9400");
			proxy.setSslProxy("172.27.66.50:9400");
			
			//String idpUrl = getIdpAuthUrl(prop, decryptedURL);
			
			
			
			//proxy.setSocksUsername("136965@ust-global.com");
           // proxy.setSocksPassword("Company_3");
			DesiredCapabilities cap=DesiredCapabilities.chrome();
//			 cap.setVersion("75.0.3770.80");
			
			
			 
			// cap.setPlatform(Platform.WIN10);
			// cap.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
			 //cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			 cap.setCapability(CapabilityType.PROXY, proxy);
			// cap.setCapability("idleTimeout", 20);
			 //cap.setCapability(ForSeleniumServer.PROXY_PAC, "http://pac.zscaler.net/ust-global.com/ust.pac");
			// cap.setCapability(ForSeleniumServer.AVOIDING_PROXY, true);
//			 cap.setCapability(ForSeleniumServer.PROXYING_EVERYTHING, false);
			 
			// driver=new RemoteWebDriver(new URL("http://10.100.97.152:30305/wd/hub"),cap);
			 driver=new RemoteWebDriver(new URL("http://172.27.66.9:32389/wd/hub"),cap);
			 

			 //driver.manage().addCookie(cookie);
			//String url = "http://ec2-54-165-62-138.compute-1.amazonaws.com:5002/login";
			
			//Proxy proxy = null;
			//if (url.contains("https")) {
				//proxy = new Proxy(Type.HTTP, new InetSocketAddress("gateway.zscaler.net", 9400));
				
			//} else {
			//	proxy = new Proxy(Type.HTTP, new InetSocketAddress("gateway.zscaler.net", 9400));
			//}
			
			 String url = "http://automationpractice.com/index.php";
			//driver.get("https://136965@ust-global.com:Company_3@idp.ust-global.com/adfs/ls/idpinitiatedsignon.aspx");
			// driver.navigate().to(url);
			System.out.println("*********************Before the get****************");
			
			driver.get(url);
			System.out.println("*********************After the get****************");
	//		driver.manage().addCookie(cookie);
			// driver.manage().window().maximize();
			Thread.sleep(1360);
		} catch (Throwable e) {
			
			e.printStackTrace();
			driver.quit();
		}

	}

	@Description("Checks the AccountsPage")
	@Test
	@Step("Checks the AccountsPage ")
	public void Accountspagecheck() throws Exception {

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
