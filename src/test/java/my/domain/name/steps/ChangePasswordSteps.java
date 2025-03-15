package my.domain.name.steps;

import io.cucumber.java.en.*;
import my.domain.name.config.WebDriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangePasswordSteps {

  WebDriver driver;

  public ChangePasswordSteps() {
    this.driver = WebDriverConfig.getDriver();
  }

  @Given("I am logged into JPetStore")
  public void i_am_logged_into_jpetstore() {
    // Navigate to JPetStore login page
    driver.get(
      "http://35.239.23.197:8080/jpetstore/actions/Account.action?signonForm="
    );

    // Find username and password fields
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));

    // Enter "quality1" and "12345678"
    usernameField.clear();
    usernameField.sendKeys(""); // Clear any autofill
    usernameField.clear(); // Clear again to be sure
    usernameField.sendKeys("quality1"); // Uses correct username

    passwordField.clear();
    passwordField.sendKeys(""); // Clear any autofill
    passwordField.clear(); // Clear again to be sure
    passwordField.sendKeys("12345678"); // Uses correct password

    // Click the sign-on button
    driver.findElement(By.name("signon")).click();

    // Wait for login to complete
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Verify we're on the correct page
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(
      "URL should contain Catalog.action",
      currentUrl.contains("Catalog.action")
    );
  }

  @When("I navigate to the my account page")
  public void i_navigate_to_account_page() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // Verify we can find the My Account link
    WebElement accountLink = driver.findElement(By.linkText("My Account"));
    Assert.assertTrue(
      "My Account link should be clickable",
      accountLink.isDisplayed() && accountLink.isEnabled()
    );
    accountLink.click();
  }

  @When("I enter a new password {string}")
  public void i_enter_new_password(String newPassword) {
    WebElement newPasswordField = driver.findElement(By.name("password"));
    newPasswordField.clear(); // Clear field before entering new password
    newPasswordField.sendKeys(""); // Clear any autofill
    newPasswordField.clear(); // Clear again to be sure
    newPasswordField.sendKeys(newPassword);
  }

  @When("I confirm the new password {string}")
  public void i_confirm_new_password(String newPassword) {
    WebElement confirmPasswordField = driver.findElement(
      By.name("repeatedPassword")
    );
    confirmPasswordField.clear(); // Clear field before entering new password
    confirmPasswordField.sendKeys(""); // Clear any autofill
    confirmPasswordField.clear(); // Clear again to be sure
    confirmPasswordField.sendKeys(newPassword);
  }

  @When("I save account information")
  public void i_save_account_information() {
    WebElement saveButton = driver.findElement(By.name("editAccount"));
    saveButton.click();
  }

  @Then("I should be directed to home page")
  public void i_should_be_redirected_to_home_page() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    String expectedUrl =
      "http://35.239.23.197:8080/jpetstore/actions/Catalog.action";
    Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
  }

  @Then("I should be able to log in with the new password {string}")
  public void i_should_be_able_to_login_with_new_password(String newPassword) {
    // Logout first
    driver.get(
      "http://35.239.23.197:8080/jpetstore/actions/Account.action?signoff="
    );

    // Navigate to login page
    driver.get(
      "http://35.239.23.197:8080/jpetstore/actions/Account.action?signonForm="
    );

    // Find username and password fields
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));

    // Enter "quality1" and new password
    usernameField.clear();
    usernameField.sendKeys(""); // Clear any autofill
    usernameField.clear(); // Clear again to be sure
    usernameField.sendKeys("quality1"); // Uses correct username

    passwordField.clear();
    passwordField.sendKeys(""); // Clear any autofill
    passwordField.clear(); // Clear again to be sure
    passwordField.sendKeys(newPassword); // Uses the changed password

    // Click the sign-on button
    driver.findElement(By.name("signon")).click();

    // Wait for the homepage to load
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Verify we're on the correct page
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(
      "URL should contain Catalog.action",
      currentUrl.contains("Catalog.action")
    );

    // Check if the "Sign Out" button exists (proving that login worked)
    WebElement signOutButton = driver.findElement(By.linkText("Sign Out"));
    Assert.assertTrue(signOutButton.isDisplayed());

    driver.quit();
  }
}
