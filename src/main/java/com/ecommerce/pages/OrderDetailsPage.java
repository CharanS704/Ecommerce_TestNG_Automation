package com.ecommerce.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.abstractComponents.AbstractComponents;

public class OrderDetailsPage extends AbstractComponents {

	AbstractComponents abstractComponents;
	static WebDriver driver;

	public OrderDetailsPage(WebDriver driver, String value) {
		super(driver, value);
		PageFactory.initElements(driver, this);
		this.driver = driver;
		abstractComponents = new AbstractComponents(driver, value);

	}

	@FindBy(xpath = "//ul[@class='cartWrap ng-star-inserted']")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(),'Checkout')]")
	WebElement checkoutButton;

	public List<String> getSortedProductsInCart() {
		abstractComponents.waitForElementsToAppear(cartProducts);

		return (cartProducts.stream().map(cartProduct -> cartProduct.findElement(By.tagName("h3")).getText()).sorted()
				.collect(Collectors.toList()));
	}

	public CheckoutPage navigateToCheckout() {

		abstractComponents.waitForElementToBeClickable(checkoutButton);
		abstractComponents.scrollToTheWebElement(checkoutButton);
		abstractComponents.clickElementUsingJavacript(checkoutButton);
		return new CheckoutPage(driver, "getJavaScriptExecutor");

	}

}
