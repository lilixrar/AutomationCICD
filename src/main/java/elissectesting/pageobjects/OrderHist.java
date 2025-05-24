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

public class OrderHist extends AbstractComponent{
	
	WebDriver driver;
	
		
	public OrderHist(WebDriver driver) {
		super(driver);
		this.driver = driver;		
		PageFactory.initElements(driver, this);			
	}
	
		@FindBy(css="tr td:nth-child(3)") private List<WebElement> orderHistList; 

		
		public boolean checkOrderItems (String productName) {
		boolean match = orderHistList.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName)); 
		return match;
	}
	

}
