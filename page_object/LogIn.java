package page_object;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LogIn {

	public static int red = 1;

	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;

	WebDriver driver;
	WebDriverWait wait;

	//odlazimo na stranu za logovanje
	public void go(WebDriver driver) {

		driver.get("http://automationpractice.com/index.php");

		driver.findElement(By.className("login")).click();
	}

	//metoda kojom logujemo vec registrovane korisnike iz excel fajla
	public void logMembers(WebDriver driver) throws IOException {

		fis = new FileInputStream("src/main/resources/Data.xlsx");

		wb = new XSSFWorkbook(fis);

		sheet = wb.getSheetAt(0);

		Row row = sheet.getRow(red);

		Cell cell = row.getCell(0);

		String email = cell.getStringCellValue();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		driver.findElement(By.id("email")).sendKeys(email);

		Cell cell3 = row.getCell(3);

		String password = cell3.getStringCellValue();

		driver.findElement(By.id("passwd")).sendKeys(password);

		driver.findElement(By.id("SubmitLogin")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account")));

		Cell cName = row.getCell(1);
		String name = cName.getStringCellValue();

		Cell cLast = row.getCell(2);
		String last = cLast.getStringCellValue();

		String expected = name + " " + last;

		WebElement element = driver.findElement(By.cssSelector(".account>span"));
		String actual = element.getText();

		//proveravamo dali je korisnik ulogovan, poredici ime i prezime
		Assert.assertEquals(actual, expected);

		driver.findElement(By.className("logout")).click();
		red++;
	}

}
