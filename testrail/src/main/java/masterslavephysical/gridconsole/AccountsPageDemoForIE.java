// Chrome browser .New website testing : Opens up the site and checks the login . This is a login fail scenario. It also verifies the contents in the pop up

package masterslavephysical.gridconsole;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class AccountsPageDemoForIE {

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


	@BeforeClass()
	public void init() throws Throwable {

		try {

			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setBrowserName("internet explorer");
		    //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		    //capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			//capabilities.setVersion("11");
			capabilities.setPlatform(Platform.WINDOWS);
			
			
			 
			// cap.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
			 //cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			 //cap.setCapability(CapabilityType.PROXY, proxy);
			 //cap.setCapability(ForSeleniumServer.PROXY_PAC, "http://pac.zscaler.net/ust-global.com/ust.pac");
			// cap.setCapability(ForSeleniumServer.AVOIDING_PROXY, true);
//			 cap.setCapability(ForSeleniumServer.PROXYING_EVERYTHING, false);
			 
			 driver=new RemoteWebDriver(new URL("http://10.100.97.55:4445/wd/hub"),capabilities);
			//driver=new RemoteWebDriver(new URL("http://168.62.222.186:4444/wd/hub"),capabilities);
			
			

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
		}

	}

	@Description("Checks the AccountsPage")
	@Test
	@Step("Checks the AccountsPage ")
	public void Accountspagecheck() throws Throwable {

		driver.manage().window().maximize();

        String content=driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).getText();
        
        System.out.println("Content is:::::::::"+content);
    
        if(content.contains("WOMEN"))
        {
         System.out.println("Correct message");
        }
        else
        {
         Assert.fail ("Unexpected Content . Was expecting WOMEN but Got :"+content);
        }


	}

}
