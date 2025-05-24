package elissectesting.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import elissectesting.TestComponents.BaseTest;
import elissectesting.pageobjects.CartPage;
import elissectesting.pageobjects.CheckoutPage;
import elissectesting.pageobjects.OrderHist;
import elissectesting.pageobjects.ProductCatalogue;
import elissectesting.pageobjects.ThankyouPage;

public class SubmitOrderTest extends BaseTest {
	
	// This standalone test is going to have a GitHub update

	String userName = "orangefatcat11@nuggets.com";
	String password = "Orangef@ttie1";
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCat = landingPage.loginApp(input.get("userName"), input.get("password"));
		List<WebElement> products = productCat.getProductList();

		productCat.addProductToCart(input.get("productName"));
		CartPage cartPage = productCat.clickTheCart();

		boolean matchResult = cartPage.checkItemsCart(input.get("productName"));
		Assert.assertTrue(matchResult);
		CheckoutPage checkOutPage = cartPage.clickCheckOut();

		checkOutPage.chooseCountry("France");
		ThankyouPage thankyouPage = checkOutPage.submitOrder();
		String thankYouMsg = thankyouPage.confirmThankYou();
		Assert.assertTrue(thankYouMsg.equalsIgnoreCase("Thankyou for the order."), "\n ~~Did Not Match~~ \n");

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCat = landingPage.loginApp(userName, password);
		OrderHist orderHistPage = productCat.clickOrderHist();
		boolean isItDisplayed = orderHistPage.checkOrderItems(productName);
		Assert.assertTrue(isItDisplayed, "\n ~~ Item Not Found ~~ \n");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//elissctesting//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}