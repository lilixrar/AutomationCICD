package elissectesting.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import elissectesting.TestComponents.BaseTest;
import elissectesting.pageobjects.CartPage;
import elissectesting.pageobjects.CheckoutPage;
import elissectesting.pageobjects.LandingPage;
import elissectesting.pageobjects.ProductCatalogue;
import elissectesting.pageobjects.ThankyouPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCat;
	public CheckoutPage checkOutPage;
	public ThankyouPage thankyouPage;

	@Given("User lands on Ecommerce Page")
	public void user_Lands_On_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^User is logged-in with username (.+) and password (.+)$")
	public void user_Is_Logged_In(String userName, String password) {
		productCat = landingPage.loginApp(userName, password);
	}

	@When("^User added a product (.+) to Cart$")
	public void user_Add_Item_To_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCat.getProductList();
		productCat.addProductToCart(productName);
	}

	@And("^User checkout product (.+) and submit the order$")
	public void checkout_Item_And_Submit_Order(String productName) {
		CartPage cartPage = productCat.clickTheCart();
		boolean matchResult = cartPage.checkItemsCart(productName);
		Assert.assertTrue(matchResult);
		checkOutPage = cartPage.clickCheckOut();
		checkOutPage.chooseCountry("France");
		thankyouPage = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed on Confirmation Page")
	public void thankyou_Message_Confirm_Page(String string) {
		String thankYouMsg = thankyouPage.confirmThankYou();
		Assert.assertTrue(thankYouMsg.equalsIgnoreCase(string));
		driver.quit();
	}

	@Then("{string} message is displayed on Landing Page")
	public void error_Message_Landing_Page(String string) throws InterruptedException {
		Assert.assertEquals(string, landingPage.getErrorMessage(), "\n ~~~Did Not Match!!~~~\n");
		driver.quit();
	}

}
