package elissectesting.tests;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Original_StandaloneTest_Multi {

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String userName = "orangefatcat11@nuggets.com";
		String password = "Orangef@ttie1";
		String productName = "ZARA COAT 3";

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(userName);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<String> targetProducts = Arrays.asList(productName, "ADIDAS ORIGINAL", "IPHONE 13 PRO");
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		targetProducts.forEach(targetProduct -> {
			WebElement product = products.stream()
					.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(targetProduct)).findFirst()
					.orElse(null);

			if (product != null) {
				WebElement adtucart = product.findElement(By.cssSelector(".card-body button:last-of-type"));
				wait.until(ExpectedConditions.elementToBeClickable(adtucart));
				adtucart.click();

				WebElement disappearingToaster = driver.findElement(By.cssSelector(".ng-animating"));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
				wait.until(ExpectedConditions.visibilityOf(disappearingToaster));
				wait.until(ExpectedConditions.invisibilityOf(disappearingToaster));

			}

		});

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click(); // clicking the cart

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		System.out.println("Execution done: Item " + productName + " is in Cart!");

		for (int x = 0; x < cartProducts.size(); x++) {
			Assert.assertEquals(cartProducts.get(x).getText(), targetProducts.get(x));
		}

		WebElement jsFindButton = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", jsFindButton);

		Actions a = new Actions(driver);
		WebElement country = driver.findElement(By.cssSelector("[placeholder='Select Country']"));

		a.sendKeys(country, "France").build().perform();
		WebElement chooseCountry = driver.findElement(By.cssSelector(".ta-results"));
		wait.until(ExpectedConditions.elementToBeClickable(chooseCountry));

		driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."), "\n ~~Did Not Match~~ \n");

		driver.quit();

	}

}
