package elissectesting.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import elissectesting.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
		
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;		
		PageFactory.initElements(driver, this);			
	}
	
	
	@FindBy(css = "[placeholder='Select Country']") private WebElement countryBox;
	@FindBy(css = ".ta-item:nth-of-type(1)") private WebElement chosenCountry;
	@FindBy(css = ".action__submit") private WebElement submitOrderBtn;


		private By listCountrySuggests = By.cssSelector(".ta-results");


		public void chooseCountry (String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryBox, countryName).build().perform();
		
		waitForElementToAppear(listCountrySuggests);
		chosenCountry.click();      
		
	}
	public ThankyouPage submitOrder() {
		submitOrderBtn.click();
		return new ThankyouPage(driver);
	}
	

}
