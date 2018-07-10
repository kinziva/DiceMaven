package com.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Orders {
	
	public Orders(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement products;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_txtUnitPrice")
	public WebElement unitPrice;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_txtDiscount")
	public WebElement discount;
	
	@FindBy(css="ctl00_MainContent_fmwOrder_txtTotal")
	public WebElement total;
	
	@FindBy(xpath="//input[@type = 'submit']")
	public WebElement calculate;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_txtName")
	public WebElement customerName;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement street;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement city;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement state;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement zip;
	
	@FindBy(xpath="//table [@id = 'ctl00_MainContent_fmwOrder_cardList']//td")
	public WebElement cardRadio;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNo;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement expDate;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement process;
	
	@FindBy(xpath="//h2")
	public WebElement ordersTitle;
	
	@FindBy(xpath="//strong")
	public WebElement orderPlaced;
}


