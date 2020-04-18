package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SummerDresses {

	String summerDr = "http://automationpractice.com/index.php?id_category=11&controller=category";

	WebDriver driver;

	WebDriverWait wait;
	

	/* odlazimo na stranicu sa letnjim haljinama,
	biramo prvu haljinu u plavoj boji, velicine M, 2 komada */

	public void buyDresses(WebDriver driver) {

		driver.get(summerDr);

		driver.findElement(By.cssSelector(".product_list>li:first-child>.product-container>.right-block>h5>a")).click();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("color_14")));

		driver.findElement(By.id("color_14")).click();

		driver.findElement(By.cssSelector(".button-plus")).click();

		driver.findElement(By.id("uniform-group_1")).click();

		driver.findElement(By.cssSelector("#group_1>[value=\"2\"]")).click();

		driver.findElement(By.cssSelector("[class=\"exclusive\"]")).click();

	}

	/* proveravamo dali se u korpi nalaze 2 haljine(printed summer dress)
	  u plavoj boji velecine M */
	
	public void cart(WebDriver driver) {

		String descriptionExp = "Printed Summer Dress";

		wait = new WebDriverWait(driver, 10);

		WebElement element = driver.findElement(By.id("layer_cart_product_title"));
		wait.until(ExpectedConditions.textToBePresentInElement(element, descriptionExp));

		String description = element.getText();

		WebElement element1 = driver.findElement(By.id("layer_cart_product_attributes"));

		String colorSize = (element1.getText());
		String colorSizeExp = "Blue, M";

		WebElement element2 = driver.findElement(By.id("layer_cart_product_quantity"));

		String quantity = element2.getText();
		String quantityExp = "2";

		Assert.assertEquals(description, descriptionExp);
		Assert.assertEquals(colorSize, colorSizeExp);
		Assert.assertEquals(quantity, quantityExp);

	}

	/*nakon izabranih artikala registrujemo korisnika
	 i proveravamo dali je registracija uspesna */
	
	public void logIn(WebDriver driver) {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title=\"Proceed to checkout\"]")));

		driver.findElement(By.cssSelector("[title=\"Proceed to checkout\"]")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".cart_navigation>a[title=\"Proceed to checkout\"]")));

		driver.findElement(By.cssSelector(".cart_navigation>a[title=\"Proceed to checkout\"]")).click();

		driver.findElement(By.id("email_create")).sendKeys("eddieS@email.com");

		driver.findElement(By.id("SubmitCreate")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("uniform-id_gender1")));

		driver.findElement(By.id("uniform-id_gender1")).click();

		driver.findElement(By.id("customer_firstname")).sendKeys("Eddie");
		driver.findElement(By.id("customer_lastname")).sendKeys("Scarry");
		driver.findElement(By.id("passwd")).sendKeys("54321");
		driver.findElement(By.id("days")).click();
		driver.findElement(By.cssSelector("#days>[value=\"1\"]")).click();
		driver.findElement(By.id("months")).click();
		driver.findElement(By.cssSelector("#months>[value=\"1\"]")).click();
		driver.findElement(By.id("years")).click();
		driver.findElement(By.cssSelector("#years>[value=\"2000\"]")).click();
		driver.findElement(By.id("address1")).sendKeys("South Boulevard 142");
		driver.findElement(By.id("city")).sendKeys("LA");
		driver.findElement(By.id("id_state")).click();
		driver.findElement(By.cssSelector("#uniform-id_state>#id_state>[value=\"5\"]")).click();
		driver.findElement(By.id("postcode")).sendKeys("95210");
		driver.findElement(By.id("phone_mobile")).sendKeys("+8005552526");
		driver.findElement(By.id("submitAccount")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("processAddress"))).click();

		WebElement element = driver.findElement(By.className("logout"));
		String logIn = element.getText();
		String exp = "Sign out";

		Assert.assertEquals(logIn, exp);

	}

}
