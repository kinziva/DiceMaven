package Traslate;

import java.time.LocalDateTime;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Translate {
	

	public static void translating(List<String> languages) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		String url = "https://www.collinsdictionary.com/us/translator";
		driver.get(url);
		String keyword = "Thank you!";
		// driver.findElement(By.id("input-text")).sendKeys(keyword); // find by id
		// driver.findElement(By.xpath("//textarea[@id
		// ='input-text']")).sendKeys("hello"+ Keys.ENTER);//find by id with xpaht
		driver.findElement(By.cssSelector("textarea#input-text")).sendKeys(keyword + Keys.ENTER); // find by id with
																									// CssSelector

		for (String language : languages) {
			Select dropDown = new Select(driver.findElement(By.cssSelector("select#select-output")));
			dropDown.selectByVisibleText(language);
			driver.findElement(By.cssSelector("div.spinner")).click();
			String translated = driver.findElement(By.cssSelector("div#output-text")).getText();
			System.out.println(keyword + " in " + language + " is " + translated);
		}
			driver.close();
			System.out.println();
			System.out.println("Test COMPLITED "+ LocalDateTime.now());
		}
	}

