package com.ecommerce.abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.pages.OrderDetailsPage;

public class AbstractComponents {

	static WebDriver driver;
	static WebDriverWait wait;
	static JavascriptExecutor javaScriptExecutor;

	@FindBy(xpath = "//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")
	WebElement cartIcon;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public AbstractComponents(WebDriver driver, String value) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		if (value.equals("getJavaScriptExecutor"))
			this.javaScriptExecutor = (JavascriptExecutor) driver;
	}

	public void waitForElementToAppear(WebElement webElement) {
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForElementToBeClickable(WebElement webElement) {
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitForElementToDisappear(WebElement webElement) {
		wait.until(ExpectedConditions.invisibilityOf(webElement));
	}

	public void waitForElementsToAppear(List<WebElement> webElements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
	}

	public void scrollToTheWebElement(WebElement element) {
		javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void scrollWindowTo(int x, int y) {
		javaScriptExecutor.executeScript("window.scrollTo(" + x + "," + y + ");");
	}

	public void clickElementUsingJavacript(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public OrderDetailsPage navigateToCartDetails() {
		waitForElementToAppear(cartIcon);
		scrollWindowTo(0, 0);
		waitForElementToBeClickable(cartIcon);

		cartIcon.click();
		return new OrderDetailsPage(driver, "getJavaScriptExecutor");
	}

}
