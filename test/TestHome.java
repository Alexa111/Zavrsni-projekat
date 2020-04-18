package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import page_object.HomePage;

public class TestHome {

	WebDriver driver;

	HomePage home = new HomePage();

	@BeforeClass

	public void go() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test(priority = 1)

	public void homeDresses() {

		home.womenDresses(driver);
		home.compare(driver);

	}

	@Test(priority = 2)

	public void homeSummerD() {

		home.SummerDresses(driver);
		home.compare(driver);

	}

	@Test(priority = 3)

	public void compareUrl() {

		home.Url(driver);
	}

	@AfterClass

	public void close() {

		driver.close();

	}

}
