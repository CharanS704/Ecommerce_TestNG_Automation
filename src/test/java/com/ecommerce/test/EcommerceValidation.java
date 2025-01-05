package com.ecommerce.test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecommerce.Utilities.ReadExcel;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.OrderDetailsPage;
import com.ecommerce.pages.ProductCatalogPage;

public class EcommerceValidation extends BaseTest {

	@Test(dataProvider = "getData")
	public void verifyMultipleItemSelection(ArrayList<String> listOfProducts) {
		ArrayList<String> expectedProductsList = listOfProducts;

		ProductCatalogPage productCatalogPage = ecomLandingPage.loginToApp();

		for (String product : expectedProductsList) {
			WebElement productElement = productCatalogPage.getProductWebElementByName(product);
			Assert.assertTrue(product.equals(productElement.findElement(By.tagName("b")).getText()));

			String message = productCatalogPage.addProductToCart(productElement);
			Assert.assertEquals(message, "Product Added To Cart", "Message is not as expected!!");
		}

		OrderDetailsPage orderDetailsPage = productCatalogPage.navigateToCartDetails();

		List<String> actualSortedProductsList = orderDetailsPage.getSortedProductsInCart();
		Assert.assertTrue(
				(expectedProductsList.stream().sorted().collect(Collectors.toList())).equals(actualSortedProductsList),
				"Products added in the cart are not as expected!!");

		CheckoutPage checkoutPage = orderDetailsPage.navigateToCheckout();
		String message = checkoutPage.enterCountry("india");
		Assert.assertTrue("Order Placed Successfully".equals(message), "Checkout message is not as expected!");

		Assert.assertTrue("THANKYOU FOR THE ORDER."
				.equals(checkoutPage.fetchOrderDetailsFromConfirmationPage().stream().findFirst().get()));

		System.out.println("Order details: ");

		checkoutPage.fetchOrderDetailsFromConfirmationPage().forEach(details -> System.out.println(details));
	}
	
	@Test( groups = "Negative Test")
	public void verifyErrorMessage() {
		String expectedErrorMessage = "Incorrect email or password.";
		
		String actualErrorMMessage = ecomLandingPage.fetchMessage();
		Assert.assertTrue(expectedErrorMessage.equals(actualErrorMMessage),"Error message is displayed incorrect!!");
	}
	
	
	@Test
	public void verifySingleItemSelection() {

		String expectedProductsList = "IPHONE 13 PRO";

		ProductCatalogPage productCatalogPage = ecomLandingPage.loginToApp();

			WebElement productElement = productCatalogPage.getProductWebElementByName(expectedProductsList);
			Assert.assertTrue(expectedProductsList.equals(productElement.findElement(By.tagName("b")).getText()));

			String message = productCatalogPage.addProductToCart(productElement);
			Assert.assertEquals(message, "Product Added To Cart", "Message is not as expected!!");

		OrderDetailsPage orderDetailsPage = productCatalogPage.navigateToCartDetails();

		List<String> actualSortedProductsList = orderDetailsPage.getSortedProductsInCart();
		Assert.assertTrue(
				(List.of(expectedProductsList).equals(actualSortedProductsList)),"Products added in the cart are not as expected!!");

		CheckoutPage checkoutPage = orderDetailsPage.navigateToCheckout();
		String checkOutMessage = checkoutPage.enterCountry("india");
		Assert.assertTrue("Order Placed Successfully".equals(checkOutMessage), "Checkout message is not as expected!");

		Assert.assertTrue("THANKYOU FOR THE ORDER."
				.equals(checkoutPage.fetchOrderDetailsFromConfirmationPage().stream().findFirst().get()));

		System.out.println("Order details: ");

		checkoutPage.fetchOrderDetailsFromConfirmationPage().forEach(details -> System.out.println(details));
	}
	
	@DataProvider
	public Iterator<ArrayList<String>> getData() throws EncryptedDocumentException, IOException {

		ArrayList<ArrayList<String>> finalList = new ArrayList<ArrayList<String>>();
		ArrayList<String> readExcel = ReadExcel.readExcel();
		ArrayList<String> asList = null;
		for(String stringItem: readExcel) {
			String[] split = stringItem.split(",");
			asList = new ArrayList<>(Arrays.asList(split));
			System.out.println("Hashcode of arraylist: "+asList.hashCode());
			finalList.add(asList);
		}
		System.out.println("Hashcode of elements in the final list:");
		finalList.forEach(p->System.out.println(p.hashCode()));
		
		return finalList.iterator();
		
		
		
	}
	
	
	


}
