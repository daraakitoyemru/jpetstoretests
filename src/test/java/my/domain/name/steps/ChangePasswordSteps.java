package my.domain.name.steps;

import io.cucumber.java.en.*;
import java.time.Duration;
import my.domain.name.config.WebDriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// xpath: https://www.w3schools.com/xml/xpath_syntax.asp
//expected conditions creds: https://www.browserstack.com/guide/expectedconditions-in-selenium

public class ChangePasswordSteps {

  WebDriver driver;

  public ChangePasswordSteps() {
    this.driver = WebDriverConfig.getDriver();
  }

  private void resetPassword(String newPassword) {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement accountLink = wait.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//a[contains(text(),'My Account')]")
      )
    );
    accountLink.click();

    WebElement newPasswordField = driver.findElement(By.name("password"));
    newPasswordField.clear();
    newPasswordField.sendKeys(newPassword);

    WebElement confirmPasswordField = driver.findElement(
      By.name("repeatedPassword")
    );
    confirmPasswordField.clear();
    confirmPasswordField.sendKeys(newPassword);

    WebElement saveButton = driver.findElement(By.name("editAccount"));
    saveButton.click();
  }

  @Given("I reset the password to the default before testing")
  public void i_reset_the_password_to_default() {
    try {
      loginToJPetStore("quality1", "newpass123");
    } catch (Exception e) {}

    resetPassword("12345678");
  }

  @Given("I am logged into JPetStore")
  public void i_am_logged_into_jpetstore() {
    try {
      loginToJPetStore("quality1", "12345678");
    } catch (RuntimeException e) {
      if (
        e.getMessage().contains("Login failed. Invalid username or password")
      ) {
        loginToJPetStore("quality1", "newpass123");
      } else {
        throw e;
      }
    }
  }

  private void loginToJPetStore(String username, String password) {
    driver.get(
      "http://35.239.23.197:8080/jpetstore/actions/Account.action?signonForm="
    );

    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));

    usernameField.clear();
    usernameField.sendKeys(username);

    passwordField.clear();
    passwordField.sendKeys(Keys.CONTROL + "a");
    passwordField.sendKeys(Keys.BACK_SPACE);
    passwordField.sendKeys(password);

    driver.findElement(By.name("signon")).click();

    WebDriverWait wait = new WebDriverWait(driver, 10);

    try {
      WebElement errorMessage = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//*[contains(text(),'Invalid username or password')]")
        )
      );

      throw new RuntimeException("Login failed. Invalid username or password");
    } catch (org.openqa.selenium.TimeoutException e) {}

    wait.until(ExpectedConditions.urlContains("Catalog.action"));
  }

  @When("I navigate to the my account page")
  public void i_navigate_to_account_page() {
    WebDriverWait wait = new WebDriverWait(driver, 15);

    try {
      WebElement accountLink = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//a[contains(text(),'My Account')]")
        )
      );
      accountLink.click();
    } catch (Exception e) {
      throw new RuntimeException("My account not found");
    }
  }

  @When("I enter a new password {string}")
  public void i_enter_new_password(String newPassword) {
    WebElement newPasswordField = driver.findElement(By.name("password"));
    newPasswordField.clear();
    newPasswordField.sendKeys(newPassword);
  }

  @When("I confirm the new password {string}")
  public void i_confirm_new_password(String newPassword) {
    WebElement confirmPasswordField = driver.findElement(
      By.name("repeatedPassword")
    );
    confirmPasswordField.clear();
    confirmPasswordField.sendKeys(newPassword);
  }

  @When("I save account information")
  public void i_save_account_information() {
    WebElement saveButton = driver.findElement(By.name("editAccount"));
    saveButton.click();
  }

  @Then("I should be directed to home page")
  public void i_should_be_redirected_to_home_page() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.urlContains("Catalog.action"));

    String expectedUrl =
      "http://35.239.23.197:8080/jpetstore/actions/Catalog.action";
    Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
  }

  @Then("I should be able to log in with the new password {string}")
  public void i_should_be_able_to_login_with_new_password(String newPassword) {
    loginToJPetStore("quality1", newPassword);

    WebElement signOutButton = driver.findElement(
      By.xpath("//a[contains(text(),'Sign Out')]")
    );
    Assert.assertTrue(signOutButton.isDisplayed());
  }

  @When("I confirm with a different password {string}")
  public void i_confirm_with_a_different_password(String confirmPassword) {
    WebElement confirmPasswordField = driver.findElement(
      By.name("repeatedPassword")
    );
    confirmPasswordField.clear();
    confirmPasswordField.sendKeys(confirmPassword);
  }

  @When("I attempt to save account information")
  public void i_attempt_to_save_account_information() {
    WebElement saveButton = driver.findElement(By.name("editAccount"));
    saveButton.click();
  }

  @Then("I should see an error message about mismatched passwords")
  public void i_should_see_an_error_message_about_mismatched_passwords() {
    WebDriverWait wait = new WebDriverWait(driver, 5);

    WebElement errorMessage = wait.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//*[contains(text(),'Password fields do not match')]")
      )
    );
    Assert.assertTrue(errorMessage.isDisplayed());
  }
}
