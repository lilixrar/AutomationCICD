package elissectesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import elissectesting.AbstractComponents.AbstractComponent;

public class ThankyouPage extends AbstractComponent {

	WebDriver driver;

	public ThankyouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement thankyouMessage;

	public String confirmThankYou() {
		return thankyouMessage.getText();

	}

}
