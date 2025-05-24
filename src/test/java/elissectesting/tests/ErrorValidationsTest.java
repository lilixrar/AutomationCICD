package elissectesting.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import elissectesting.TestComponents.BaseTest;
import elissectesting.TestComponents.Retry;
import elissectesting.pageobjects.CartPage;
import elissectesting.pageobjects.CheckoutPage;
import elissectesting.pageobjects.ProductCatalogue;
import elissectesting.pageobjects.ThankyouPage;

public class ErrorValidationsTest extends BaseTest {
	
    String userName = "orangefatcat11@nuggets.com";
	String password = "Orangef@ttie1";
    String wrongPassword = "Orangef@ttie!!!";
	String productName = "ZARA COAT 3";
	
	 @Test (groups= {"ErrorHandling"}, retryAnalyzer = Retry.class) // "retryAnalyzer" is manually placed on suspiciously flaky Tests
	 public void loginChecking() throws IOException, InterruptedException {
		    
			landingPage.loginApp(userName, wrongPassword);
			Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password!@@", "\n ~~~Did Not Match!!~~~\n");
			
		    }
	 
	 @Test
	 public void productChecking() throws IOException, InterruptedException {
		    
		    ProductCatalogue productCat = landingPage.loginApp(userName, password); 
		    List<WebElement> products = productCat.getProductList();
		    
		    productCat.addProductToCart(productName);
		    CartPage cartPage = productCat.clickTheCart();
		    
		    boolean matchResult = cartPage.checkItemsCart(productName);
		    Assert.assertTrue(matchResult);	

		    }

}

