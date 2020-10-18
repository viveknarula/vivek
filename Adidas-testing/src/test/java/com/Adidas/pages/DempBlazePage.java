package com.Adidas.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class DempBlazePage {

	@FindAll(@FindBy(xpath = "//*[@id='itemc']"))
	public static List<WebElement> categoryList;

	@FindBy(xpath = "//*[text()='Laptops']")
	public static WebElement Laptops;

	@FindBy(xpath = "//*[text()='Sony vaio i5']")
	public static WebElement sonyLink;

	@FindBy(xpath = "//*[text()='Add to cart']")
	public static WebElement cartAddButton;

	@FindBy(xpath = "(//*[@class='nav-link'])[1]")
	public static WebElement homeButton;

	@FindBy(xpath = "(//*[@class='nav-link'])[4]")
	public static WebElement cartButton;

	@FindBy(xpath = "//*[text()='Dell i7 8gb']")
	public static WebElement dellLink;

	@FindBy(xpath = "//td[text()='Dell i7 8gb']/following::td[2]/a")
	public static WebElement deleteDellLaptop;

	@FindBy(xpath = "//*[@class='btn btn-success']")
	public static WebElement placeOrder;

	@FindBy(xpath = "//*[@id='name']")
	public static WebElement Name;

	@FindBy(xpath = "//*[@id='country']")
	public static WebElement Country;

	@FindBy(xpath = "//*[@id='city']")
	public static WebElement City;

	@FindBy(xpath = "//*[@id='card']")
	public static WebElement creditCard;

	@FindBy(xpath = "//*[@id='month']")
	public static WebElement Month;

	@FindBy(xpath = "//*[@id='year']")
	public static WebElement Year;

	@FindBy(xpath = "//*[text()='Purchase']")
	public static WebElement Purchase;

	@FindBy(xpath = "(//*[@class='lead text-muted ']/text())[2]")
	public static WebElement Amount;

	@FindBy(xpath = "//*[@class='lead text-muted ']")
	public static WebElement iD;

	@FindBy(xpath = "//*[@class='confirm btn btn-lg btn-primary']")
	public static WebElement OK;

}
