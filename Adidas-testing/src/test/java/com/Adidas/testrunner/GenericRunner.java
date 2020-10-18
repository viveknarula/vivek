package com.Adidas.testrunner;

import org.apache.log4j.BasicConfigurator;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.Adidas.stepdefs",
		"com.Adidas.hooks" }, plugin = { "pretty",
				"json:Reports/JSON/cucumber.json" }, tags = "@functionalAPI,@functionalUI")
public class GenericRunner {
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		JUnitCore.main("com.Adidas.testrunner.GenericRunner");
	}
}