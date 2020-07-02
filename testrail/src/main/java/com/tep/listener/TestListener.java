package com.tep.listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	public static int passedTests = 0;
	public static int failedTests = 0;
	// monday_27thaug_start
	public static int totalTests = 0;
	int y = 0;

	ArrayList<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
	ArrayList<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
	ArrayList<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	ArrayList<ITestNGMethod> starttime = new ArrayList<ITestNGMethod>();

	public static String jobId;
	public static String runId;
	public static String buildId;
	public static String jobName;
	public static String projectId;
	public static Date startTime;
	public static Date endTime;
	public static String packageName;
	public static String testName;
	public static Boolean isAccessibilityTesting;
	public static int buildToolId;

	// This method will automatically be called if a test runs successfully
	public void onTestSuccess(ITestResult result) {

		passedTests++;

		passedtests.add(result.getMethod());
		System.out.println("method name:" + result.getMethod().getMethodName());

		// add the passed tests to the passed list

	}

	// This method will automatically be called if a test fails
	public void onTestFailure(ITestResult result) {

		failedTests++;
		Object resultIstance = result.getInstance();

		failedtests.add(result.getMethod());
		System.out.println("method name:" + result.getMethod().getMethodName());
		
		Class clazz = result.getTestClass().getRealClass();
		Field[] fieldArray = clazz.getDeclaredFields();
		//fl.equals(WebDriver);
		WebDriver driver = null;
		try {
			for(Field fn : fieldArray) {
				String classnam = fn.getType().getName();
				if (classnam.equals("org.openqa.selenium.WebDriver")) {
					fn.setAccessible(true);
					driver = (WebDriver)fn.get(result.getInstance());
					Cookie cookie = new Cookie("zaleniumTestPassed", "false");
					driver.manage().addCookie(cookie);
				
					//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
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
		
		
		

		// add the failed tests to the failed list
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		TestListener testListener= new TestListener();
        packageName = testListener.getClass().getPackage().getName();
		System.out.println("passed test are " + passedTests);
		System.out.println("failed test are " + failedTests);
		startTime = context.getStartDate();
		endTime = context.getEndDate();
		System.out.println("The end time is" + context.getEndDate());
		totalTests = passedTests + failedTests;
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// System.out.println("onTestStart: Do nothing");

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

		// starttime.add(context.getStartDate());
		// starttime.add((ITest) context.getStartDate());

	}

	public void LogTest() {

	}
}
