package elissectesting.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import elissectesting.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	 	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;		
		PageFactory.initElements(driver, this);			
	}
	
	 
	@FindBy(css=".cartSection h3") private List<WebElement> cartProducts; 
	@FindBy(css=".totalRow button") WebElement checkOutBtn;

	 	By checkOut = By.cssSelector(".totalRow button");


	 	public boolean checkItemsCart (String productName) {
		boolean match = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName)); 
 		return match;
	}
	
	public CheckoutPage clickCheckOut() {
		waitForElementClickable(checkOut);
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}

}
