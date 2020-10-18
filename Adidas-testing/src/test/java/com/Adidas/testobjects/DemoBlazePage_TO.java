package com.Adidas.testobjects;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.Adidas.generalutils.log;
import com.Adidas.pages.DempBlazePage;
import com.Adidas.seleniumutils.BasePage;
import com.Adidas.seleniumutils.Screenshot;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class DemoBlazePage_TO extends BasePage {

	public static String statusApiUrl = "";
	Response getResponse = null;
	String jsonBody = "";

	public DemoBlazePage_TO(WebDriver driver) {
		super(driver, DempBlazePage.class);
	}

	public void verifyCategoryList(List<String> expectedList) {

		List<String> actualList = getTextOfWebElement(DempBlazePage.categoryList);
		Assert.assertEquals("Product category not present", actualList, expectedList);

	}

	public void clickLaptopButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.Laptops, "Laptop button");
		click(DempBlazePage.Laptops);
	}

	public void clickSonyLaptopLink(String expected) throws InterruptedException {
		validateElementEnabled(DempBlazePage.sonyLink, "Sony button");
		validateTextOfWebElement(DempBlazePage.sonyLink, expected);
		click(DempBlazePage.sonyLink);
	}

	public void clickAddCartButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.cartAddButton, "Add to cart button");
		click(DempBlazePage.cartAddButton);
	}

	public void clickCartButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.cartButton, "Click cart button");
		click(DempBlazePage.cartButton);
	}

	public void clickPlaceOrderButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.placeOrder, "Click place order  button");
		setSleepTime(5000);
		click(DempBlazePage.placeOrder);
	}

	public void acceptPopUp() {
		acceptAlertPopUp();
	}

	public void clickHomeButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.homeButton, "Click Home button");
		click(DempBlazePage.homeButton);
	}

	public void clickDellLaptopLink(String expected) throws InterruptedException {
		validateElementEnabled(DempBlazePage.dellLink, "Dell button");
		validateTextOfWebElement(DempBlazePage.dellLink, expected);
		click(DempBlazePage.dellLink);
	}

	public void clickPurchaseButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.Purchase, "Click Purchase button");
		click(DempBlazePage.Purchase);
	}

	public void clickOkButton() throws InterruptedException {
		validateElementEnabled(DempBlazePage.OK, "Click ok button");
		click(DempBlazePage.OK);
	}

	public void verfiyAmountDetails(String expected) {
		String text = getTextOfWebElement(DempBlazePage.iD);
		log.info("Getting text from a webElement, text is: " + text);
		text = text.substring(12, 27);
		System.out.println("The actual is" + text);
		System.out.println("The expected is" + expected);
		Screenshot.saveScreenshot(driver, "PurchaseOrder", System.getProperty("user.dir") + "\\Reports");
		Assert.assertEquals("Amount verification", text, expected);
	}

	public void deleteDellLaptopLink() throws InterruptedException {
		validateElementEnabled(DempBlazePage.deleteDellLaptop, "Delete Dell Laptop");
		click(DempBlazePage.deleteDellLaptop);
	}

	public void enterPersonalDetails(List<List<String>> formData) throws InterruptedException {
		click(DempBlazePage.Name);
		enterText(DempBlazePage.Name, formData.get(1).get(0));
		setSleepTime(3000);
		enterText(DempBlazePage.Country, formData.get(1).get(1));
		setSleepTime(3000);
		enterText(DempBlazePage.City, formData.get(1).get(2));
		enterText(DempBlazePage.creditCard, formData.get(1).get(3));
		enterText(DempBlazePage.Month, formData.get(1).get(4));
		enterText(DempBlazePage.Year, formData.get(1).get(5));
	}

	public void createStatusApiUrl() {
		statusApiUrl = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";

	}

	public void callStatusApi() {
		getResponse = RestAssured.given().when().get(statusApiUrl);

	}

	public void callPostApi() throws IOException {
		jsonBody = generateStringFromResource(System.getProperty("user.dir") + "\\Properties\\" + "AddPetJson.json");
		getResponse = RestAssured.given().contentType("application/json").body(jsonBody).when().post(statusApiUrl);

	}

	public void createPostApi() {
		statusApiUrl = "https://petstore.swagger.io/v2/pet/";
	}

	public void checkStatusAvaliable(String expectedStatus) {

		int statusCode = getResponse.getStatusCode();
		log.info("The status code is " + statusCode);
		Assert.assertEquals("Correct status code returned", statusCode, 200);
		JsonPath jsonPathEvaluator = new JsonPath(getResponse.getBody().asString());
		List<String> statusList = jsonPathEvaluator.get("status");
		log.info("The response api status of all records " + statusCode);
		int count = 0;
		for (String status : statusList) {
			if (status.equals(expectedStatus))
				count += 1;
		}
		Assert.assertEquals("Check status returned", statusList.size(), count);
	}

	public void checkPetAdded() throws JSONException {
		try {
			int statusCode = getResponse.getStatusCode();
			log.info("The status code is " + statusCode);
			Assert.assertEquals("Correct status code returned", statusCode, 200);
			JsonPath jsonPathEvaluator = new JsonPath(getResponse.getBody().asString());
			int id = jsonPathEvaluator.get("id");
			log.info("The pet store added is " + id);

			statusApiUrl = statusApiUrl + id;
			callStatusApi();
			log.info("The added Json " + jsonBody);
			log.info("After added response " + getResponse.asString());
			JSONAssert.assertEquals(jsonBody, getResponse.asString(), JSONCompareMode.LENIENT);
		} catch (JSONException e) {
			log.info("Json Exception is" + e.getMessage());
		}
	}

	public void createUpdateStatusAPI() throws IOException {
		createPostApi();
		jsonBody = generateStringFromResource(System.getProperty("user.dir") + "\\Properties\\" + "AddPetJson.json");
		JsonPath jsonPathEvaluator = new JsonPath(jsonBody);
		int id = jsonPathEvaluator.get("id");
		log.info("The pet store id for updation is " + id);
		statusApiUrl = statusApiUrl + id;
	}

	public void callUpdateStatusAPI() throws JSONException {
		getResponse = (Response) RestAssured.given().contentType("application/x-www-form-urlencoded")
				.accept("application/json").formParam("status", "sold").when().post(statusApiUrl);

	}

	public void checkStatusAfterUpdate() {

		int statusCode = getResponse.getStatusCode();
		log.info("The status code is " + statusCode);
		Assert.assertEquals("Correct status code returned", statusCode, 200);
		log.info("The response is " + getResponse.asString());

		callStatusApi();
		log.info("After added response " + getResponse.asString());
		JsonPath jsonPathEvaluator1 = new JsonPath(getResponse.asString());
		String status = jsonPathEvaluator1.get("status");
		int id = jsonPathEvaluator1.get("id");
		log.info("The orderid: " + id + "updated status" + status);
		Assert.assertEquals("Validate status", status, "sold");
	}

}
