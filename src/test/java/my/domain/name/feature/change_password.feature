@change_password
Feature: Change Password in JPetStore

  Scenario: Reset password before testing
    Given I reset the password to the default before testing

  Scenario: Successfully changing the password
    Given I am logged into JPetStore
    When I navigate to the my account page
    And I enter a new password "newpass123"
    And I confirm the new password "newpass123"
    And I save account information
    Then I should be directed to home page
    And I should be able to log in with the new password "newpass123"

  Scenario: User enters mismatched passwords when changing password
    Given I am logged into JPetStore
    When I navigate to the my account page
    And I enter a new password "newpass123"
    And I confirm with a different password "wrongpass123"
    And I attempt to save account information
    Then I should see an error message about mismatched passwords
