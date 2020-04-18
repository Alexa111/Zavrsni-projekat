package page_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

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

public class Registration {

	public static int red = 1;

	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;

	WebDriver driver;
	WebDriverWait wait;

	// Ovom metodom odlazimo na stranicu za registraciju

	public void go(WebDriver driver) {

		driver.get("http://automationpractice.com/index.php");

		driver.findElement(By.className("login")).click();
	}

	// Metoda kojom unosimo email adresu i odlazimo na stranicu za unos ostalih podataka
	 
	public void registerMail(WebDriver driver) throws IOException {

		fis = new FileInputStream("src/main/resources/Data.xlsx");

		wb = new XSSFWorkbook(fis);

		sheet = wb.getSheetAt(0);

		Row row = sheet.getRow(red);

		Cell cell = row.getCell(0);

		String email = cell.getStringCellValue().toString();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email_create")));

		driver.findElement(By.name("email_create")).sendKeys(email);

		driver.findElement(By.id("SubmitCreate")).click();

	}

	// Metoda sa kojom izvlacimo podatke iz excela i unosimo ih u polja
	public void registerData(WebDriver driver) throws IOException {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone_mobile")));

		fis = new FileInputStream("src/main/resources/Data.xlsx");
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);

		Row row = sheet.getRow(red);

		Cell cell1 = row.getCell(1);
		String fName = cell1.getStringCellValue().toString();
		driver.findElement(By.id("customer_firstname")).sendKeys(fName);

		Cell cell2 = row.getCell(2);
		String lName = cell2.getStringCellValue().toString();
		driver.findElement(By.id("customer_lastname")).sendKeys(lName);

		Cell cell3 = row.getCell(3);
		String pass = cell3.getStringCellValue().toString();
		driver.findElement(By.id("passwd")).sendKeys(pass);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("uniform-days")));

		driver.findElement(By.id("uniform-days")).click();
		driver.findElement(By.cssSelector("#days>[value=\"1\"]")).click();
		driver.findElement(By.id("months")).click();
		driver.findElement(By.cssSelector("#months>[value=\"1\"]")).click();
		driver.findElement(By.id("years")).click();
		driver.findElement(By.cssSelector("#years>[value=\"2000\"]")).click();

		Cell cell4 = row.getCell(4);
		String address = cell4.getStringCellValue().toString();
		driver.findElement(By.id("address1")).sendKeys(address);

		Cell cell5 = row.getCell(5);
		String city = cell5.getStringCellValue().toString();
		driver.findElement(By.id("city")).sendKeys(city);

		driver.findElement(By.id("id_state")).click();
		driver.findElement(By.cssSelector("#uniform-id_state>#id_state>[value=\"5\"]")).click();

		Cell cell6 = row.getCell(6);
		double zip = cell6.getNumericCellValue();
		String zc = new DecimalFormat("#").format(zip);
		driver.findElement(By.id("postcode")).sendKeys(zc);

		Cell cell7 = row.getCell(7);
		String phone = cell7.toString();
		driver.findElement(By.id("phone_mobile")).sendKeys(phone);

		driver.findElement(By.id("submitAccount")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.className("logout")));
		WebElement element = driver.findElement(By.className("logout"));

		// proveravamo dali je korisnik ulogovan a zatim idemo na logout

		String logIn = element.getText();
		String exp = "Sign out";
		Assert.assertEquals(logIn, exp);

		element.click();
		red++;
	}

}
