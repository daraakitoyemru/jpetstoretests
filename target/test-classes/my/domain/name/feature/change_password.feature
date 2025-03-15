@change_password
Feature: Change Password in JPetStore

  As a registered user,
  I want to change my password,
  So that I can secure my account.

  Scenario: Successfully changing the password
    Given I am logged into JPetStore
    When I navigate to the my account page
    And I enter a new password "NewPass"
    And I confirm the new password "NewPass"
    And I save account information
    Then I should be directed to home page
    And I should be able to log in with the new password "NewPass"
