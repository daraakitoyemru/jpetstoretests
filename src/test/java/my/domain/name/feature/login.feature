@login
Feature: Test JPetStore Login
 
  Scenario Outline: Testing valid login
    Given I open Chrome browser
    And I go to JPetStore sign in page
    When I enter valid "<username>" and "<password>" combination and press login button
    Then I should be able to login successfully

    Examples: 
      | username    | password    |
      | ashley    | 12345678 |
      | quality2    | 12345678 |
      
  Scenario Outline: Testing invalid login
   Given I open Chrome browser
   And I go to JPetStore sign in page
   When I enter invalid "<username>" and "<password>" combination and press login button
   Then I should not be able to login
   	
    Examples: 
      | username    | password    |
      | ashley    | wrongpass |
      | quality2    | 1234 |
      
  Scenario Outline: Testing blank username
    Given I open Chrome browser
    And I go to JPetStore sign in page
    When I enter blank "<username>" and valid "<password>" combination and press login button
    Then I should not be able to login
   	
    Examples: 
      | username    | password    |
      | blank    | 12345678 |
      | blank    | 12345678 |
      
	Scenario Outline: Testing blank password
		Given I open Chrome browser
    And I go to JPetStore sign in page
    When I enter "<username>" and blank "<password>" combination and press login button
    Then I should not be able to login
   	
    Examples: 
      | username    | password    |
      | ashley    | blank |
      | quality2    | blank |