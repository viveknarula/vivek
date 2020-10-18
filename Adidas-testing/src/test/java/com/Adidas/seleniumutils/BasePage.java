package com.Adidas.seleniumutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Adidas.generalutils.log;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	@SuppressWarnings("unchecked")
	public BasePage(WebDriver driver, Class aClass) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, aClass);
		wait = new WebDriverWait(driver, 30);
	}

	public List<String> getTextOfWebElement(List<WebElement> webElement) {
		List<String> webElementsText = new ArrayList<String>();
		System.out.println("The size is" + webElement.size());
		for (WebElement ele : webElement)
			webElementsText.add(ele.getText().trim());
		log.info("Getting text from a webElement, text is: " + webElementsText);
		return webElementsText;
	}

	public void validateTextOfWebElement(WebElement webElement, String expected) {
		String webElementsText = webElement.getText();
		log.info("Getting text from a webElement, text is: " + webElementsText);
		Assert.assertEquals("Field Matched: ", webElementsText, expected);

	}

	public String getTextOfWebElement(WebElement webElement) {
		String webElementsText = webElement.getText();
		return webElementsText;

	}

	public void validateElementEnabled(WebElement element, String Fieldname) {
		Assert.assertTrue("Field Not Enabled : " + Fieldname, element.isEnabled());
	}

	public void click(WebElement webElement) throws InterruptedException {
		log.info("----Clicking on a webElement");
		waitElementLocated(webElement, 40000);
		webElement.click();
	}

	public boolean waitElementLocated(WebElement element, int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void acceptAlertPopUp() {

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	public void setTimeout(int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void enterText(WebElement element, String val) {
		log.info("----Clearing, then sending keys: " + val);
		// scrollToElement(element);
		element.clear();
		// waitElementLocated(element,60);
		element.sendKeys(val);
	}

	public void setSleepTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			return;
		}
	}

	public String generateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}