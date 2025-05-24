package elissectesting.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import elissectesting.pageobjects.CartPage;
import elissectesting.pageobjects.OrderHist;

public class AbstractComponent { 	
	
	
	WebDriver driver;
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']") WebElement cartButton; 
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']") WebElement ordersButton;

	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementClickable (By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForElementDisappear (WebElement elem) throws InterruptedException {
		Thread.sleep(1000);
		
	}
	
	public void waitToAppear (WebElement elem) throws InterruptedException {
		Thread.sleep(1000);

	}
	
	public CartPage clickTheCart() {			
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderHist clickOrderHist() {			
		ordersButton.click();
		OrderHist orderHistPage = new OrderHist(driver);
		return orderHistPage;
	}
	

}
