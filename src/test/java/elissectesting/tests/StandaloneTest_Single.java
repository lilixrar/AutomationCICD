package elissectesting.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import elissectesting.pageobjects.CartPage;
import elissectesting.pageobjects.CheckoutPage;
import elissectesting.pageobjects.LandingPage;
import elissectesting.pageobjects.ProductCatalogue;
import elissectesting.pageobjects.ThankyouPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest_Single {
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		String userName = "orangefatcat11@nuggets.com";
		String password = "Orangef@ttie1";
		String productName = "ZARA COAT 3";

		LandingPage landingpage = new LandingPage(driver);
		landingpage.goToPage();
		ProductCatalogue productCat = landingpage.loginApp(userName, password);
		List<WebElement> products = productCat.getProductList();

		productCat.addProductToCart(productName);
		CartPage cartPage = productCat.clickTheCart();

		boolean matchResult = cartPage.checkItemsCart(productName);
		Assert.assertTrue(matchResult);
		CheckoutPage checkOutPage = cartPage.clickCheckOut();

		checkOutPage.chooseCountry("France");
		ThankyouPage thankyouPage = checkOutPage.submitOrder();
		String thankYouMsg = thankyouPage.confirmThankYou();
		Assert.assertTrue(thankYouMsg.equalsIgnoreCase("Thankyou for the order."), "\n ~~Did Not Match~~ \n");

		driver.quit();

	}

}
