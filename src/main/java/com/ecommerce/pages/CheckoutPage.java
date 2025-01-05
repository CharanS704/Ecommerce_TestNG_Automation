package com.ecommerce.pages;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	AbstractComponents abstractComponents;

	public CheckoutPage(WebDriver driver, String value) {
		super(driver, value);
		PageFactory.initElements(driver, this);
		abstractComponents = new AbstractComponents(driver, value);

	}

	@FindBy(xpath = "//input[@class='input txt text-validated' and @placeholder='Select Country']")
	WebElement countryInputBox;

	@FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> countryList;

	@FindBy(id = "toast-container")
	WebElement toastElement;

	@FindBy(xpath = "//span[contains(text(),'Total')]")
	WebElement totalAmount;

	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	WebElement placeOrderButton;

	@FindBy(xpath = "//td[@class='box']//h1[@class='hero-primary']/parent::td/parent::tr/parent::tbody/tr")
	List<WebElement> orderConfirmationDetails;

	@FindBy(xpath = "//tr[@class='ng-star-inserted']/td/label")
	List<WebElement> orderConfirmationIds;

	public String enterCountry(String countryName) {

		abstractComponents.waitForElementToBeClickable(countryInputBox);
		countryInputBox.click();
		countryInputBox.sendKeys(countryName);

		abstractComponents.waitForElementsToAppear(countryList);
		Optional<WebElement> countryElement = countryList.stream()
				.filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst();
		if (countryElement.isPresent())
			countryElement.get().click();
		else
			System.out.println(
					"The provided country: '" + countryList + "' is not available in the system to be selected!");

		abstractComponents.waitForElementToBeClickable(placeOrderButton);
		placeOrderButton.click();

		abstractComponents.waitForElementToAppear(toastElement);
		return toastElement.getText();

	}

	public List<String> fetchOrderDetailsFromConfirmationPage() {
		
		abstractComponents.scrollWindowTo(0, 0);
		String OrderStatusMessage = orderConfirmationDetails.get(0).getText();
		
		List<String> orderDetails = orderConfirmationIds.stream().map(id -> id.getText()).collect(Collectors.toList());
		orderDetails.add(0,OrderStatusMessage);
		return orderDetails;

	}

}
