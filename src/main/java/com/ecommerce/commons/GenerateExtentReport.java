package com.ecommerce.commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GenerateExtentReport {

	public static ExtentReports getReport() {
		
			ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + "//reports//index.html");
			extentSparkReporter.config().setDocumentTitle("Cool Reports");
			extentSparkReporter.config().setReportName("Charan Automation Reports");

			ExtentReports extentReport = new ExtentReports();
			extentReport.attachReporter(extentSparkReporter);

			extentReport.setSystemInfo(" Automation Tester", "Charan");

		return extentReport;

	}

}
