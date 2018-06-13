package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

//Test case:
//    Title: dice job search 
//
//    Step 1. Launch browser and navigate to https://dice.com 
//      Expected: dice home page should be displayed
//
//    Step 2. Insert search keyword and location then click on
//    find tech jobs
//    Expected: -Search results page should be loaded.
//              -Page title should contain count of results , 
//              along with search keyword.
//              -Count of results should be displayed on the page.
//    ====================
//    Steps to automate:
//      -Make sure you understand what functionality is being tested 
//      and each step. What is expected, what is being tested.
//
//      If you don't understand:  Ask manual tester/person who wrote it.
//      BAs, Developers, Lead 
//
//      -Manually test it and make sure , it is passing manually.
//      And make sure no defects/bugs around it.
//      -if a test case is failing manually, it does not qualify 
//      for automation.
//
//      --> starts automating:
//      Java + Selenium + Maven + Git > Github
public class DiceJobSearch {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup(); // no need a path with maven
		WebDriver driver = new ChromeDriver();

		driver.manage().window().fullscreen(); // fullscreen in Mac
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // SET universal wait line

		String url = "https://www.dice.com/";
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step Passed.  Dice Homepage succesfully loaded");
		} else {
			System.out.println("Step Failed");
			throw new RuntimeException();
		}
		

		String keyword = "Java Developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		String location = "02148";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);

		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		int countResult = Integer.parseInt(count.replace(",", ""));
		if(countResult>0) {
			System.out.println("Keyword: " +keyword+ " search returned " + countResult+ "results in "+ location);
		}else {System.out.println("STEP FAILED");
		}
		driver.close();
		System.out.println("Test Completed" + LocalDateTime.now()); 

	}

}
