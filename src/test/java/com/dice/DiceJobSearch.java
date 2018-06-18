package com.dice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

		List<String> keywords = new ArrayList<>();
		keywords.add("SDET");
		keywords.add("Senior Sofware Development Engineer in Test");
		keywords.add("Sofware Development Engineer in Test");
		keywords.add("QA Engineer");
		keywords.add("Testing Engineer");
		keywords.add("Senior Business Data Analyst");
		keywords.add("Senior Java Developer");
		keywords.add("Data Scientist");
		keywords.add("Product Owner");
		keywords.add("Sr SDET");
		keywords.add("Sr Sofware Development Engineer in Test");
		keywords.add("Senior Data Architect");
		keywords.add("QA");
		keywords.add("IT");
		keywords.add("IT NOC Analyst");
		keywords.add("Senior SCCM Desktop Engineer");
		keywords.add("Python developer");
		keywords.add("Senior Citrix Engineer");
		keywords.add("Senior Net Developer");
		keywords.add("Java RAD Developer");

		for (int i = 0; i < keywords.size(); i++) {
			String url = "https://www.dice.com/";
			driver.get(url);
			String actualTitle = driver.getTitle();
			String expectedTitle = "Job Search for Technology Professionals | Dice.com";
			if (actualTitle.equals(expectedTitle)) {
				System.out.println("Step Passed.  Dice Homepage succesfully loaded");
			} else {
				System.out.println("Step Failed. Dice Homepage did not load succesfully");
				throw new RuntimeException("Step Fail. Dice homepage did not load succesfully");
			}

			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(keywords.get(i));
			String location = "02148";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);

			driver.findElement(By.id("findTechJobs")).click();

			String count = driver.findElement(By.id("posiCountId")).getText();
			System.out.println(count);
			int countResult = Integer.parseInt(count.replace(",", ""));
			if (countResult > 0) {
				System.out.println(
						"Keyword: " + keywords.get(i) + " search returned " + countResult + " results in " + location);
			} else {
				System.out.println("STEP FAILED");
			}
			keywords.set(i, keywords.get(i) + "-" + count);
		}

		// String keyword = "Java Developer";

		driver.close();
		System.out.println("Test Completed,  " + LocalDateTime.now());
		System.out.println("New ArrayList:");
		System.out.println(keywords);
	}
}