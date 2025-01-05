package com.ecommerce.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecommerce.commons.GenerateExtentReport;
import com.ecommerce.test.BaseTest;

public class CustomListeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extentReport = GenerateExtentReport.getReport();

	
	@Override
	public void onTestStart(ITestResult result) {
		test = extentReport.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		
		String screenshotPath = getScreenshot(result.getMethod().getMethodName());
		
		test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test.fail(result.getThrowable());

		String screenshotPath = getScreenshot(result.getMethod().getMethodName());
		
		test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
	
	

}
