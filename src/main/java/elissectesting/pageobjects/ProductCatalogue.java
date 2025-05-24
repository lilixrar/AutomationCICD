package elissectesting.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import elissectesting.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;


	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;		
		PageFactory.initElements(driver, this);			
	}


	@FindBy(css=".mb-3") List<WebElement> products; 	
	@FindBy(css=".ng-animating") WebElement disappearingToaster;


	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toasterMain = By.id("toast-container");
	By cartBtn = By.xpath("//button[@routerlink='/dashboard/cart']");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return product;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click(); 		waitForElementToAppear(toasterMain);						
		waitToAppear(disappearingToaster);
		waitForElementDisappear(disappearingToaster);
	}




}
