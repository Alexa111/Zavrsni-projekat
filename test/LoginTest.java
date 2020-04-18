package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page_object.LogIn;

public class LoginTest {

	LogIn log = new LogIn();

	WebDriver driver;

	@BeforeClass

	public void go() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test(priority = 1)

	public void goLog() {

		log.go(driver);

	}

	@Test(priority = 2)

	public void logAll() throws IOException {

		for (int i = 0; i < 30; i++) {

			log.logMembers(driver);

		}
	}

	@AfterClass

	public void close() {

		driver.close();

	}

}