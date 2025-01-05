package com.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.abstractComponents.AbstractComponents;

public class ProductCatalogPage extends AbstractComponents {

	AbstractComponents abstractComponents;
	static WebDriver driver;

	public ProductCatalogPage(WebDriver driver, String value) {
		super(driver, value);
		PageFactory.initElements(driver, this);
		this.driver=driver;
		abstractComponents = new AbstractComponents(driver, value);

	}

	@FindBy(css = ".container .row div.col-lg-4")
	List<WebElement> products;

	@FindBy(id = "toast-container")
	WebElement toastElement;

	@FindBy(css = ".ng-animating")
	WebElement spinnerElement;

	@FindBy(xpath = "//button[@class='btn btn-custom']/i[@class='fa fa-shopping-cart']")
	WebElement cartButton;

	public WebElement getProductWebElementByName(String name) {
		abstractComponents.waitForElementsToAppear(products);
		return products.stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(name)).findFirst()
				.orElse(null);
	}

	public String addProductToCart(WebElement product) {
		abstractComponents.waitForElementToDisappear(toastElement);

		abstractComponents.waitForElementToAppear(
				product.findElement(By.cssSelector(".container .row div.col-lg-4 button:last-of-type")));
		abstractComponents.scrollToTheWebElement(product);
		product.findElement(By.cssSelector(".container .row div.col-lg-4 button:last-of-type")).click();

		abstractComponents.waitForElementToDisappear(spinnerElement);
		abstractComponents.waitForElementToAppear(toastElement);
		return toastElement.getText();
	}



}
