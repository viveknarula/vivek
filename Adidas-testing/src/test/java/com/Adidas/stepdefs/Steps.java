package com.Adidas.stepdefs;

import org.openqa.selenium.WebDriver;
import com.Adidas.hooks.Hooks;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;

public class Steps {
	WebDriver driver = Hooks.driver;
	Scenario scenario;
	String targetURL = Hooks.targetURL;
	// DemoBlazePage_TO demoBlazePage_TO = new DemoBlazePage_TO(driver);

	@Given("^(the user launches the application)$")
	public void launchesTheSalesApplication(String arg0) {
		driver.manage().deleteAllCookies();
		driver.get(targetURL);

	}

}
