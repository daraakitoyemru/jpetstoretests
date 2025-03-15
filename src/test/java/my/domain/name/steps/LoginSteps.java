package my.domain.name.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import my.domain.name.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSteps {

  private WebDriver driver;

  public LoginSteps() {
    this.driver = WebDriverConfig.getDriver();
  }

  @Given("^I open Chrome browser$")
  public void i_open_Chrome_browser() throws Throwable {
    driver.get("http://35.239.23.197:8080/jpetstore/");
  }

  @Given("I go to JPetStore sign in page")
  public void i_go_to_jpetstore_sign_in_page() {
    driver.get(
      "http://35.239.23.197:8080/jpetstore/actions/Account.action?signonForm="
    );
  }

  @When(
    "^I enter valid \"([^\"]*)\" and \"([^\"]*)\" combination and press login button$"
  )
  public void i_enter_valid_and_combination(String arg1, String arg2)
    throws Throwable {
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));
    
    usernameField.clear();
    usernameField.sendKeys(""); // Clear any autofill
    usernameField.clear(); // Clear again to be sure
    usernameField.sendKeys(arg1);
    
    passwordField.clear();
    passwordField.sendKeys(""); // Clear any autofill
    passwordField.clear(); // Clear again to be sure
    passwordField.sendKeys(arg2);
    
    driver.findElement(By.name("signon")).submit();
  }

  @Then("^I should be able to login successfully$")
  public void i_should_be_able_to_login_successfully() throws Throwable {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Check for welcome message instead of Sign Out link
    String pageSource = driver.getPageSource();
    boolean isLoggedIn = pageSource.contains("Welcome");
    
    assertTrue("User should be logged in successfully", isLoggedIn);
  }

  @When(
    "^I enter invalid \"([^\"]*)\" and \"([^\"]*)\" combination and press login button$"
  )
  public void i_should_not_be_able_to_login(String arg1, String arg2)
    throws Throwable {
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));
    
    usernameField.clear();
    usernameField.sendKeys(""); // Clear any autofill
    usernameField.clear(); // Clear again to be sure
    usernameField.sendKeys(arg1);
    
    passwordField.clear();
    passwordField.sendKeys(""); // Clear any autofill
    passwordField.clear(); // Clear again to be sure
    passwordField.sendKeys(arg2);
    
    driver.findElement(By.name("signon")).submit();
  }

  @Then("I should not be able to login")
  public void i_should_not_be_able_to_login() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    String pageSource = driver.getPageSource();
    boolean hasErrorMessage = pageSource.contains(
      "Invalid username or password. Signon failed."
    );

    assertTrue("Error message should be displayed", hasErrorMessage);
  }

  @When(
    "I enter blank \"([^\"]*)\" and \"([^\"]*)\" combination and press login button$"
  )
  public void i_enter_blank_information(String arg1, String arg2)
    throws Throwable {
    WebElement usernameField = driver.findElement(By.name("username"));
    WebElement passwordField = driver.findElement(By.name("password"));
    
    usernameField.clear();
    usernameField.sendKeys(""); // Clear any autofill
    usernameField.clear(); // Clear again to be sure
    
    passwordField.clear();
    passwordField.sendKeys(""); // Clear any autofill
    passwordField.clear(); // Clear again to be sure
    
    driver.findElement(By.name("signon")).click();
  }
}
