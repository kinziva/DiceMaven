package com.PomDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.POM.AllOrders;
import com.POM.ProductsPage;
import com.POM.WebOrdersLoginPage;

public class WebOrdersTests {
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrders allOrdersPage;
	ProductsPage productsPage;
	String userId = "Tester";
	String password = "test";

	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
	}

	@Test(description = "Verify labels and tab links are displayed", priority = 1)
	public void labelsVerication() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");

		// loginPage.userName.sendKeys(userId);
		// loginPage.password.sendKeys(password);
		// loginPage.loginButton.click();

		loginPage.login(userId, password);

		allOrdersPage = new AllOrders(driver);
		assertTrue(allOrdersPage.webOrders.isDisplayed(), "Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List Of All Orders label is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), "viewAllOrders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "orderTab is not displayed");
	}

	/*
	 * Step 1. Navigate to loginpage Step 2. Assert that you are on loginpage Step
	 * 3. Login using valid credentials Step 4. Click on view all products Step 5.
	 * Verify following products are displayed: MyMoney FamilyAlbum ScreenSaver Step
	 * 6. Verify prices and discounts : MyMoney $100 8% FamilyAlbum $80 15%
	 * ScreenSaver $20 10%
	 */
	@Test(description = "Verify default Products and prices")
	public void availableProductsTest() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrders(driver);
		allOrdersPage.viewAllProducts.click();
		productsPage = new ProductsPage(driver);

		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<>();

		// for each Lambda
		productsPage.productNames.forEach(each -> actProducts.add(each.getText()));

		// for (WebElement productTitle : productsPage.productNames) {
		// actProducts.add(productTitle.getText());
		// }
		assertEquals(actProducts, expProducts);

		for (WebElement row : productsPage.productsRows) {

			System.out.println("text" +row.getText());
			String[] prodData = row.getText().split(" ");
			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;
			}
		}
	}

	// logout after each test
	//@AfterMethod
	public void logout() {
		allOrdersPage.logout();
	}

	//@AfterClass
	public void tearDown() {
		driver.quit();
	}
}