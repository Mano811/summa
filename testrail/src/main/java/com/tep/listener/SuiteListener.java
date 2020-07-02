package com.tep.listener;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.tep.core.StepLib;
import com.tep.report.AllureReport;
import com.tep.rest.Job;
import com.tep.rest.StepCore;

public class SuiteListener implements ISuiteListener{
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

		System.out.println("onStart: Fetching jobId and RunId");
		String jobId = suite.getParameter("jobId");
		String jobName = suite.getParameter("jobName");
		String projectId = suite.getParameter("projectId");
		String buildToolId = suite.getParameter("buildToolId");
		TestListener.projectId=projectId;
		if(jobName==null||projectId==null||buildToolId==null) {
			StepLib stepLib = new StepLib();
			JSONObject jsonObj = null;
		     try {
		            
		            TestListener.jobId = jobId;
		            jsonObj=stepLib.Register(TestListener.jobId);
		            TestListener.runId = (String) jsonObj.get("run_id");
		            TestListener.buildId=(String) jsonObj.get("runNo");
		            TestListener.jobName=(String) jsonObj.get("job_name");
		            
		            TestListener.isAccessibilityTesting=(Boolean) jsonObj.get("isAccessibilityTesting");
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
		} else {
			StepCore stepCore = new StepCore();
			String runId = stepCore.CreateSession(jobName, Integer.parseInt(buildToolId), null, projectId);
			TestListener.runId = runId;
			TestListener.jobName = jobName;
			TestListener.projectId=projectId;
		}
		
		
	}

	public void onFinish(ISuite suite) {

		try {
			AllureReport.GenerateReport(TestListener.buildId,TestListener.runId,TestListener.jobName);
			//VideoReport.CopyVideo(TestListener.buildId, TestListener.jobName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Calling complete API..");
		Job obj=new Job();
		obj.Complete(TestListener.runId, TestListener.passedTests, TestListener.failedTests);
	}
	
	public void LogTest()
	{
		
	}
}
