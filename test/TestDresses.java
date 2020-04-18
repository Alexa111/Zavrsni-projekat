package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page_object.SummerDresses;

public class TestDresses {

	SummerDresses sd = new SummerDresses();

	WebDriver driver;

	@BeforeClass

	public void go() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test(priority = 1)

	public void getDresses() {

		sd.buyDresses(driver);

	}

	@Test(priority = 2)

	public void checkCart() {

		sd.cart(driver);

	}
	
	@Test (priority = 3)
	
	public void log() {
		
		sd.logIn(driver);
		
	}

	@AfterClass

	public void close() {

		driver.close();

	}

}
