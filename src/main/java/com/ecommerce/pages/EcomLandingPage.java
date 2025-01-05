package com.ecommerce.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.commons.GenerateExtentReport;

public class EcomLandingPage extends AbstractComponents {

	static AbstractComponents abstractComponents;
	static WebDriver driver;
	static ExtentReports extentReport;

	public EcomLandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		abstractComponents = new AbstractComponents(driver);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(id = "toast-container")
	WebElement toastElement;

	public ProductCatalogPage loginToApp() {

		abstractComponents.waitForElementToAppear(userEmail);
		userEmail.sendKeys("charan1234@gmail.com");

		abstractComponents.waitForElementToAppear(password);
		password.sendKeys("Charan1234");

		abstractComponents.waitForElementToBeClickable(submit);
		submit.click();

		return new ProductCatalogPage(driver, "getJavaScriptExecutor");

	}
	
	
	public String fetchMessage() {
		
		abstractComponents.waitForElementToAppear(userEmail);
		userEmail.sendKeys("charan1234@gmail.com");

		abstractComponents.waitForElementToAppear(password);
		password.sendKeys("Charan");

		abstractComponents.waitForElementToBeClickable(submit);
		submit.click();
		
		abstractComponents.waitForElementToAppear(toastElement);
		return toastElement.getText();
	}

}
