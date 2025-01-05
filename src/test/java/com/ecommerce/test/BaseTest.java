package com.ecommerce.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ecommerce.pages.EcomLandingPage;

public class BaseTest {

	static WebDriver driver;
	public EcomLandingPage ecomLandingPage;

	public void initializeBrowser() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resource//resources//GlobalProperties.properties");

		prop.load(fis);

		switch (prop.getProperty("browserName")) {
		case ("Chrome"):
			driver = new ChromeDriver();
			break;
		case ("Firefox"):
			driver = new FirefoxDriver();
			break;
		case ("Edge"):
			driver = new EdgeDriver();
			break;
		}

	}
	
	public static String getScreenshot(String testcase) {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src, new File(
					System.getProperty("user.dir") + "//src//test//resource//TestResults//" + testcase + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return System.getProperty("user.dir") + "//src//test//resource//TestResults//" + testcase + ".png";
	}

	@BeforeMethod
	public WebDriver launchApplication() throws IOException {
		initializeBrowser();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		ecomLandingPage = new EcomLandingPage(driver);
		return driver;
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

}
