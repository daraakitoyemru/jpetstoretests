@create-account
Feature: Create New Account
  As a user
  I want to be able to click the link to register my account
  Add my user information
  Add my account information
  So I can login with my newly created account

  Scenario Outline: Successful Account Creation
    Given I am on the new account page
    When I enter valid user id "<userId>"
    And I enter valid password "<password>"
    And I enter valid confirm password "<password>"
    And I enter account information
    When I save my account information
    And I enter my newly created user id "<userId>"
    And I enter valid password "<password>"
    When I click submit
    Then I should see welcome message for "<userId>"

    Examples: 
      | userId      | password |
      | testuser123 | pass123  |
      | newuser456  | pass456  |
      
  Scenario Outline: Attempt to Create Account with Existing User ID
    Given I am on the new account page
    When I enter valid user id "<existingUserId>"
    And I enter valid password "<password>"
    And I enter valid confirm password "<password>"
    And I enter account information
    When I save my account information
    Then I should see an error message

    Examples: 
      | existingUserId | password |
      | quality2          | 12345678     |
    
  Scenario Outline: Attempt to Create Account without Filling Account Information
    Given I am on the new account page
    When I enter valid user id "<userId>"
    And I enter valid password "<password>"
    And I enter valid confirm password "<password>"
    When I save my account information
    Then I should see validation error message

    Examples: 
      | userId      | password |
      | newuser123  | pass123  |
