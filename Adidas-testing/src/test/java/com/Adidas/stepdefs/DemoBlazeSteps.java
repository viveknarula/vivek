package com.Adidas.stepdefs;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.Adidas.testobjects.DemoBlazePage_TO;
import com.Adidas.hooks.Hooks;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DemoBlazeSteps {
	WebDriver driver = Hooks.driver;
	DemoBlazePage_TO demoBlazePage_TO = new DemoBlazePage_TO(driver);

	@And("^validate the category list$")
	public void validateCategoryList(List<String> expectedList) {
		System.out.println("Expected list" + expectedList);
		List<String> alist = new ArrayList<String>(expectedList);
		demoBlazePage_TO.verifyCategoryList(alist);
	}

	@When("^user navigates Laptop link$")
	public void user_navigates_laptop_link() throws InterruptedException {
		demoBlazePage_TO.clickLaptopButton();
	}

	@And("^user navigates Sony Laptop \"([^\"]*)\"$")
	public void clickSonyLaptopLink(String args) throws InterruptedException {
		demoBlazePage_TO.clickSonyLaptopLink(args);
	}

	@And("^user click Add to cart button$")
	public void clickAddCartButton() throws InterruptedException {
		demoBlazePage_TO.clickAddCartButton();
	}

	@And("^user click Cart button$")
	public void clickCartButton() throws InterruptedException {
		demoBlazePage_TO.clickCartButton();
	}

	@And("^user click place order button$")
	public void clickPlaceOrderButton() throws InterruptedException {
		demoBlazePage_TO.clickPlaceOrderButton();
	}

	@And("^user Accept pop up confirmation$")
	public void acceptPopUp() {
		demoBlazePage_TO.acceptPopUp();
	}

	@And("^user click Home button$")
	public void clickHomeButton() throws InterruptedException {
		demoBlazePage_TO.clickHomeButton();
	}

	@And("^user navigates Dell Laptop \"([^\"]*)\"$")
	public void clickDellLaptopLink(String args) throws InterruptedException {
		demoBlazePage_TO.clickDellLaptopLink(args);
	}

	@And("^user Delete Dell i7 8gb$")
	public void deleteDellLaptop() throws InterruptedException {
		demoBlazePage_TO.deleteDellLaptopLink();
	}

	@And("^user fills out the data in detail section$")
	public void enterPersonalDetails(DataTable formData) throws InterruptedException {
		List<List<String>> formDataRow = formData.raw();
		demoBlazePage_TO.enterPersonalDetails(formDataRow);
	}

	@And("^user click Purchase button$")
	public void clickPurchaseButton() throws InterruptedException {
		demoBlazePage_TO.clickPurchaseButton();
	}

	@Then("^verify the details \"([^\"]*)\"$")
	public void verfiyAmountDetails(String expected) {
		demoBlazePage_TO.verfiyAmountDetails(expected);
	}

	@And("^click OK button$")
	public void clickOkButton() throws InterruptedException {
		demoBlazePage_TO.clickOkButton();
	}

	@Given("^status api is available$")
	public void status_api_is_available() throws Throwable {
		demoBlazePage_TO.createStatusApiUrl();
	}

	@When("^user request for available pets$")
	public void user_request_for_available_pets() throws Throwable {
		demoBlazePage_TO.callStatusApi();
	}

	@Then("^response status of pets should be \"([^\"]*)\"$")
	public void response_status_of_pets_should_be_available(String status) throws Throwable {
		demoBlazePage_TO.checkStatusAvaliable(status);
	}

	@Given("^Api to add pet is available$")
	public void api_to_add_is_available() throws Throwable {
		demoBlazePage_TO.createPostApi();
	}

	@When("^user post a request to add pet$")
	public void user_post_a_request_to_add_pet() throws Throwable {
		demoBlazePage_TO.callPostApi();
	}

	@Then("^check the pet is added to the store by calling the get Api Find pet by Id$")
	public void check_the_pet_is_added_to_the_store() throws Throwable {
		demoBlazePage_TO.checkPetAdded();
	}

	@Given("^Api to update pet is available$")
	public void api_to_update_pet_is_available() throws Throwable {
		demoBlazePage_TO.createUpdateStatusAPI();
	}

	@When("^user post a request to update pet status$")
	public void user_post_a_request_to_update_pet_status() throws Throwable {
		demoBlazePage_TO.callUpdateStatusAPI();
	}

	@Then("^check the status is updated to the store by calling the get Api Find pet by Id$")
	public void check_the_status_is_updated_to_the_store_by_calling_the_get_api_find_pet_by_id() throws Throwable {
		demoBlazePage_TO.checkStatusAfterUpdate();
	}

}
