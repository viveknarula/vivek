package com.Adidas.hooks;

import java.io.IOException;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.lang3.StringUtils;
import com.Adidas.seleniumutils.Screenshot;
import com.google.gson.internal.LinkedTreeMap;
import com.Adidas.generalutils.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;
	public static String targetURL;
	public static int width;
	public static int height;
	public static String viewPort;
	public static String browserName;
	Scenario scenario;

	// ----------Before Hook-----------------------------//
	static // This block is of only use to disable the unnecessary html unit
			// browser logs.
	{
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Before
	public void beforestartUp(Scenario scenario) throws IOException {

		log.startTestScenario(scenario.getName());
		this.scenario = scenario;
		JsonParsing config = new JsonParsing();

		// variables
		// AppURL,RunMode,ExecutionId
		// are used for holding value from Jenkins if not provided the value set
		// in the config.json is used.

		String runMode = System.getProperty("RunMode");
		String executionId = System.getProperty("ExecutionID");
		targetURL = config.getApplicationURL();
		LinkedTreeMap<String, String> capabilities = (LinkedTreeMap<String, String>) config
				.getWebdriverCapabilities(runMode, executionId);
		width = Integer.parseInt(capabilities.get("width").toString());
		height = Integer.parseInt(capabilities.get("height").toString());
		viewPort = capabilities.get("viewPort").toString();
		DesiredCapabilities dc = new DesiredCapabilities();
		browserName = capabilities.get("browser");
		dc.setBrowserName(browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			String chromeOpts = config.getChromeOptions();
			if (StringUtils.isNotBlank(chromeOpts)) {
				for (String opt : StringUtils.split(chromeOpts, ',')) {
					chromeOptions.addArguments(opt);
				}
			}
			dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
		}

		if (browserName.equalsIgnoreCase("iexplore")) {
			dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setPlatform(Platform.WINDOWS);
			// dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
			// true);
		}

		if (browserName.equalsIgnoreCase("firefox")) {
			dc.firefox();
			dc.setBrowserName("firefox");
			dc.setCapability("marionette", true);
		}

		driver.manage().deleteAllCookies();
		// driver.manage().window().setSize(new Dimension(width, height));

		if (browserName.equalsIgnoreCase("iexplore"))
			Runtime.getRuntime().exec(System.getProperty("user.dir"));

		// driver.get(authURL);
	}

	// -----------After Hook-----------------------------//
	@After
	public void after(Scenario scenario) throws IOException, InterruptedException {
		log.endTestScenario(scenario.getName());
		if (scenario.isFailed()) {
			Screenshot.grabScreenshotForReport(driver, scenario);
		}
		driver.quit();
	}
}