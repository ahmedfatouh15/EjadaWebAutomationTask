@tag
Feature: Purchase items from the site

	Background:
		Given User is On the Login Screen
  	When The User log in using "standard_user" and "secret_sauce"
  	Then The User should login successfully
  	
  @purchase
  Scenario: User add items to cart
    When user sort the items by the most Expensive
    And add the first two items to cart
    And navigate to the cart page
    Then the items should appear in the cart page
    When the user checkouts
    Then checkout page should open
    When the user submits the form using firstName,lastName and 51411
    Then overview page should open
    And the money amount should be correct
    And the url is correct
    When the user finishes the order
    Then thank you and order confirm messages should appear