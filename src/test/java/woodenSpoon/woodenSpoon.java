package woodenSpoon;

import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class woodenSpoon {
	WebDriver driver;
	List<WebElement> titles;
	List<WebElement> prices;

	@BeforeMethod
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=wooden+spoon");
	}

	@Test (priority = 0)
	public void titles() {
		titles = driver.findElements(By.xpath("//h2[@class='a-size-base s-inline  s-access-title  a-text-normal']"));
		System.out.println("title size: " + titles.size());
		for (WebElement each : titles) {
			System.out.println(each.getText());
		}
	}

	@Test(priority = 1)
	public void prices() {
		prices = driver.findElements(By.xpath("//span[@class='sx-price sx-price-large']"));
		System.out.println("prices size: " +prices.size());
		for (WebElement price : prices) {
			System.out.println(price.getText());
		}
	}

	@Test(priority = 2)
	public void sizes() {
		for (int i = 0; i < titles.size(); i++) {
			System.out.println("Title: " + titles.get(i).getText());
			System.out.println("Price: " + prices.get(i).getText());
			System.out.println("---------------------------------");
		}
		
    List<WebElement> wholeItems = driver.findElements(By.xpath("//div[@class='s-item-container']"));
		
		System.out.println("wholeItems.size():" + wholeItems.size());
		
		
		for (int i = 0; i < wholeItems.size(); i++) {
			if(wholeItems.get(i).getText().isEmpty()) continue;
			
			String desXpath = "(//div[@class='s-item-container'])[" + (i+1) + "]//h2";
			String priceXpath = "(//div[@class='s-item-container'])[" + (i+1) + "]//span[@class='sx-price sx-price-large']";
			
			System.out.println(driver.findElement(By.xpath(desXpath)).getText());
			System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
			System.out.println("------------");
			
		}
	}

		

	
@AfterClass
public void tearDownClass() throws InterruptedException {
	Thread.sleep(2000);
	driver.close();

}
}
