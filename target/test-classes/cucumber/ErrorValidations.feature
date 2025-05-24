
@tag
Feature: Error Validation at Login

  @ErrorValidation
  Scenario Outline: Check Invalid Login on Landing Page
    Given User lands on Ecommerce Page
    When User is logged-in with username <userName> and password <password>
    Then "Incorrect email or password." message is displayed on Landing Page

	Examples: 
	      | userName  									| password 							|
	      | orangefatcat11@nuggets.com 	| Orangef@ttie1@@! 			|		
																				#wrong password^