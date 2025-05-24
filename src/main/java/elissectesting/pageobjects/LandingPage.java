package elissectesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import elissectesting.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		 		super(driver);
		 		this.driver = driver;		 		
		 		PageFactory.initElements(driver, this);			 	
		 		}
	
	 	 	 	
 	@FindBy(id="userEmail") WebElement userEmail;
	
 	@FindBy(id="userPassword") WebElement userPass;

	@FindBy(id="login") WebElement submit;
	@FindBy(css="[class*='flyInOut']") WebElement errorMessage;
	
	 	
	public ProductCatalogue loginApp(String email, String password) {	 		
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		submit.click();
		ProductCatalogue productCat = new ProductCatalogue(driver);	 		
		return productCat;
	}
	
	public String getErrorMessage() throws InterruptedException {
		waitToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goToPage() {
		driver.get("https://rahulshettyacademy.com/client"); 
	}

}
