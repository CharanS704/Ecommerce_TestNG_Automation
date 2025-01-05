package com.ecommerce.Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility{
	
	
	public static void getScreenshot(String testCase, WebDriver driver) throws IOException {
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//src//test//resource//TestResults//"+testCase+".png"));
	}

}
