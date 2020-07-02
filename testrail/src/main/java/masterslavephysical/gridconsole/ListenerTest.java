package masterslavephysical.gridconsole;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest<T> implements ITestListener {

	public void onTestStart(ITestResult result) {

		System.out.println(result.getName()+" test case started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("The name of the testcase passed is :"+result.getName());

	}

	public void onTestFailure(ITestResult result) {
		
		
		/*Class clazz = result.getTestClass().getRealClass();
		Field field;
		try {
			field = clazz.getDeclaredField("driver");
			WebDriver driver = (WebDriver)field.get(result.getInstance());
			//String url = driver.getCurrentUrl();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
			Field[] fl = result.getInstance().getClass().getFields();
			//fl.equals(WebDriver);
			WebDriver driver = null;
			try {
				for(Field fn : fl) {
					String classnam = fn.getType().getName();
					if (classnam.equals("org.openqa.selenium.WebDriver")) {
						driver = (WebDriver)fn.get(result.getInstance());
						//org.openqa.selenium.WebDriver new_name = (org.openqa.selenium.WebDriver) fn.getType();
						
					};
					
					
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// do something with 'objAsType'
		
		
		
		/*Object Name = className.getClass();
		T t = (T) castObject(class3, className);
		String name2 = Name.getClass().getCanonicalName();
		String Name3 = "TestCases";
		Package packageName = Name.getClass().getPackage();
		java.lang.reflect.Method[] method = Name.getClass().getMethods();
		WebDriver driver = ((TestCases) result.getInstance()).getDriver();*/
		System.out.println("The name of the testcase failed is :"+result.getName());
	}
	
	private <T> T castObject(Class<T> clazz, Object object) {
		  return (T) object;
		}

	public void onTestSkipped(ITestResult result) {
		System.out.println("The name of the testcase Skipped is :"+result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}


}
