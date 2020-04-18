package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	
	String home = "http://automationpractice.com/index.php";
	
	WebDriver driver;
	WebDriverWait wait;

	String url1 = "";
	String url2 = "";
	
	//Iz padajuceg menija "woman" biramo summer dresses 
	public void womenDresses(WebDriver driver) {

		driver.get(home);

		Actions ac = new Actions(driver);
		WebElement element = driver.findElement(By.cssSelector("a[title=\"Women\"] "));
		ac.moveToElement(element).perform();

		driver.findElement(By.cssSelector(".sf-menu>li:nth-child(1)>ul>li:nth-child(2)>ul>li:nth-child(3)>a")).click();

		url1 = driver.getCurrentUrl();
	}
	
	//proveravamo dali se nalazimo na stranici summer dresses
	public void compare(WebDriver driver) {

		wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.category-name")));

		WebElement element = driver.findElement(By.cssSelector("span.category-name"));

		String sDresses = "Summer Dresses";
		Assert.assertEquals(sDresses, element.getText());

	}
	//Iz padajuceg menija "dresses" biramo summer dresses
	public void SummerDresses(WebDriver driver) {

		driver.navigate().to(home);

		WebElement element = driver.findElement(By.cssSelector("ul.sf-menu>li:nth-child(2)>a"));
		Actions ac = new Actions(driver);
		ac.moveToElement(element).perform();

		driver.findElement(By.cssSelector("ul.sf-menu>li:nth-child(2)>ul>li:nth-child(3)>a")).click();

		url2 = driver.getCurrentUrl();

	}
	
	//proveravamo dali obe opcije summer dresses iz padajucih menija vode na istu stranicu
	public void Url(WebDriver driver) {

		Assert.assertEquals(url1, url2);
	}

}
