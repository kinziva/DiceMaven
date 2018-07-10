package com.PomDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.POM.AllOrders;
import com.POM.Orders;
import com.POM.ProductsPage;
import com.POM.WebOrdersLoginPage;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class OrdersPageTest {
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrders allOrdersPage;
	Orders ordersPage;

	Faker faker;
	Select select;
	
	String quantity, discount, name, street, city, state, zip, cardNumber;

	String userId = "Tester";
	String password = "test";

	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

		faker = new Faker();
		quantity = "" + faker.number().numberBetween(0, 1000);
		discount = "" + faker.number().numberBetween(0, 80);
		cardNumber = "" + faker.number().numberBetween(0, 100000000) + faker.number().numberBetween(0, 100000000);
		name = faker.name().firstName() + " " + faker.name().lastName();
		street = faker.address().buildingNumber() + " " + faker.address().streetAddress();
		state = faker.address().state();
		city = faker.address().cityName();
		state = faker.address().state();
		zip = faker.address().zipCode().substring(0, 5);
	}

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
		ordersPage = new Orders(driver);
		allOrdersPage = new AllOrders(driver);

	}

	@Test(description = "Verify labels and tab links are displayed", priority = 1)
	public void labelsVerication() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");

	}

	@Test(description = "Verify order added", priority = 2)
	public void placeOrder() throws InterruptedException {
		loginPage.login(userId, password);
		allOrdersPage.orderTab.click();
		Thread.sleep(500);
		assertEquals(ordersPage.ordersTitle.getText(), "Order");
		select = new Select(ordersPage.products);
		select.selectByIndex(1);
		
		ordersPage.quantity.clear();
		ordersPage.quantity.sendKeys(quantity);
		ordersPage.discount.clear();
		ordersPage.discount.sendKeys(discount);
		ordersPage.calculate.click();
		ordersPage.customerName.sendKeys(name);
		ordersPage.street.sendKeys(street);
		ordersPage.city.sendKeys(city);
		ordersPage.state.sendKeys(state);
		ordersPage.zip.sendKeys(zip);
		ordersPage.cardRadio.click();
		ordersPage.cardNo.sendKeys(cardNumber);
		ordersPage.expDate.sendKeys("12/18");
		ordersPage.process.click();
		
		assertEquals(ordersPage.orderPlaced.getText(), "New order has been successfully added.");

		allOrdersPage.viewAllOrders.click();

		String firstRow = driver.findElement(By.xpath("//table[@id= 'ctl00_MainContent_orderGrid']//tr[2]")).getText();
		System.out.println(firstRow);
		System.out.println(" Actual Name: " + allOrdersPage.customerNameOnTable.getText());
		System.out.println(" Expected Name: " + name);
		assertEquals(allOrdersPage.customerNameOnTable.getText(), name);
	}

	// logout after each test
	@AfterMethod
	public void logout() {
		// allOrdersPage.logout();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}