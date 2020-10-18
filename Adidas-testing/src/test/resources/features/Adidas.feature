Feature: Web automated checks over our DEMO ONLINE
Description: As a user, I want to check the navigation to Application and check for the API.


@functionalUI
Scenario: Web automated checks over our DEMO ONLINE
Given the user launches the application
And validate the category list
| Phones | Laptops | Monitors |
When user navigates Laptop link
And user navigates Sony Laptop "Sony vaio i5"
And user click Add to cart button
And user Accept pop up confirmation
And user click Home button
And user navigates Laptop link
And user navigates Dell Laptop "Dell i7 8gb"
And user click Add to cart button
And user Accept pop up confirmation
And user click Cart button
And user Delete Dell i7 8gb
And user click place order button
And user fills out the data in detail section
      | Name | Country   | City | CreditCard | Month | Year |
      | Vivek Narula   | India | Gurgoan | 1612161216121612 | 08 | 2024 |
And user click Purchase button
Then verify the details "Amount: 790 USD"
And click OK button

@functionalAPI1
Scenario: Validating the response of Status API for Available Pets
Given status api is available
When user request for available pets
Then response status of pets should be "available"

@functionalAPI
Scenario: Validating the response of Post API to add pet
Given Api to add pet is available
When user post a request to add pet
Then check the pet is added to the store by calling the get Api Find pet by Id

@functionalAPI
Scenario: Validating the response of Post API to update status
Given Api to update pet is available
When user post a request to update pet status
Then check the status is updated to the store by calling the get Api Find pet by Id

