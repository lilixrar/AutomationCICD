@tag
Feature: Purchase an item from E-commerce Website
  I want to use this template for my feature file

# Gherkin syntax, front-end, plain english

Background: 			# pre-requisite for the scenario
Given User lands on Ecommerce Page

  @Regression		# custom-made tag for choosing which tests to run at TestRunner
  Scenario Outline: Happy Path of Purchasing an Order
    Given User is logged-in with username <userName> and password <password>
    When User added a product <productName> to Cart
    And User checkout product <productName> and submit the order
    Then "Thankyou for the order." message is displayed on Confirmation Page

    Examples: 
      | userName  									| password 						|	productName			|
      | orangefatcat11@nuggets.com 	| Orangef@ttie1 			|	ZARA COAT 3			|			
#      | lilibells@ro.com 						| IamPrincess00! 			| ADIDAS ORIGINAL	|
      
      
      
