package my.domain.name.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;
import my.domain.name.config.WebDriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewAcct {

  private WebDriver driver;

  public CreateNewAcct() {
    this.driver = WebDriverConfig.getDriver();
  }

  @Given("I am on the new account page")
  public void i_am_on_the_new_account_page() {
    driver.get("http://35.239.23.197:8080/jpetstore/");
    driver.findElement(By.linkText("Enter the Store")).click();

    driver.findElement(By.linkText("Sign In")).click();
    driver.findElement(By.linkText("Register Now!")).click();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I enter valid user id {string}")
  public void i_enter_valid_user_id(String id) {
    WebElement usernameField = driver.findElement(
      By.cssSelector("[name='username']")
    );
    usernameField.sendKeys(id);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I enter valid password {string}")
  public void i_enter_valid_password(String pwd) {
    WebElement passwordField = driver.findElement(
      By.cssSelector("[name='password']")
    );
    passwordField.sendKeys(pwd);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I enter valid confirm password {string}")
  public void i_enter_valid_confirm_password(String pwd) {
    WebElement confirmPasswordField = driver.findElement(
      By.cssSelector("[name='repeatedPassword']")
    );
    confirmPasswordField.sendKeys(pwd);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I enter account information")
  public void i_enter_account_information() {
    driver.findElement(By.name("account.firstName")).sendKeys("test");
    driver.findElement(By.name("account.lastName")).sendKeys("user");
    driver.findElement(By.name("account.email")).sendKeys("test@mail.com");
    driver.findElement(By.name("account.phone")).sendKeys("1234567890");
    driver.findElement(By.name("account.address1")).sendKeys("123 Test St");
    driver.findElement(By.name("account.city")).sendKeys("Calgary");
    driver.findElement(By.name("account.state")).sendKeys("AB");
    driver.findElement(By.name("account.zip")).sendKeys("12345");
    driver.findElement(By.name("account.country")).sendKeys("CAN");

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I save my account information")
  public void i_save_my_account_information() {
    driver.findElement(By.name("newAccount")).submit();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I enter my newly created user id {string}")
  public void i_enter_my_newly_created_user_id(String userId) {
    WebElement usernameField = driver.findElement(By.name("username"));
    usernameField.clear();
    usernameField.sendKeys(userId);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @When("I click submit")
  public void i_click_submit() {
    driver.findElement(By.cssSelector("[name='signon']")).submit();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see welcome message for {string}")
  public void i_should_see_welcome_message_for(String id) {
    String pageSource = driver.getPageSource();
    Assert.assertTrue(
      "Welcome message should contain user id",
      pageSource.contains("Welcome " + id) || pageSource.contains(id)
    );

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Then("I should see an error message")
  public void i_should_see_an_error_message() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Check for error message
    String pageSource = driver.getPageSource();
    Assert.assertTrue(
      "Error message is displayed",
      pageSource.contains("already exists")
    );

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
