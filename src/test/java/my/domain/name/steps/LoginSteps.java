package my.domain.name.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.domain.name.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// xpath: https://www.w3schools.com/xml/xpath_syntax.asp
//expected conditions creds: https://www.browserstack.com/guide/expectedconditions-in-selenium

public class LoginSteps {

  private WebDriver driver;

  public LoginSteps() {
    this.driver = WebDriverConfig.getDriver();
  }

  @Given("^I open Chrome browser$")
  public void i_open_Chrome_browser() throws Throwable {
    driver.get("http://35.239.23.197:8080/jpetstore/");

    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//a[contains(text(),'Enter the Store')]")
      )
    );
  }

  @Given("I go to JPetStore sign in page")
  public void i_go_to_jpetstore_sign_in_page() {
    driver.get(
      "http://35.239.23.197:8080/jpetstore/actions/Account.action?signonForm="
    );

    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(
      ExpectedConditions.visibilityOfElementLocated(By.name("username"))
    );
  }

  @When(
    "I enter valid {string} and {string} combination and press login button"
  )
  public void i_enter_valid_and_combination(String username, String password)
    throws Throwable {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement usernameField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("username"))
    );
    WebElement passwordField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("password"))
    );

    usernameField.clear();
    usernameField.sendKeys(Keys.CONTROL + "a");
    usernameField.sendKeys(Keys.BACK_SPACE);
    usernameField.sendKeys(username);

    passwordField.clear();
    passwordField.sendKeys(Keys.CONTROL + "a");
    passwordField.sendKeys(Keys.BACK_SPACE);
    passwordField.sendKeys(password);

    WebElement loginButton = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("signon"))
    );
    loginButton.click();
  }

  @Then("^I should be able to login successfully$")
  public void i_should_be_able_to_login_successfully() throws Throwable {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    wait.until(ExpectedConditions.urlContains("Catalog.action"));

    WebElement signOutLink = wait.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//a[contains(text(),'Sign Out')]")
      )
    );

    assertTrue(
      "User should be logged in successfully",
      signOutLink.isDisplayed()
    );
  }

  @When(
    "I enter invalid {string} and {string} combination and press login button"
  )
  public void i_enter_invalid_combination(String username, String password)
    throws Throwable {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement usernameField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("username"))
    );
    WebElement passwordField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("password"))
    );

    usernameField.clear();
    usernameField.sendKeys(Keys.CONTROL + "a");
    usernameField.sendKeys(Keys.BACK_SPACE);
    usernameField.sendKeys(username);

    passwordField.clear();
    passwordField.sendKeys(Keys.CONTROL + "a");
    passwordField.sendKeys(Keys.BACK_SPACE);
    passwordField.sendKeys(password);

    WebElement loginButton = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("signon"))
    );
    loginButton.click();
  }

  @When(
    "I enter blank {string} and valid {string} combination and press login button"
  )
  public void i_enter_blank_username(String username, String password)
    throws Throwable {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement usernameField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("username"))
    );
    WebElement passwordField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("password"))
    );

    usernameField.clear();
    usernameField.sendKeys(Keys.CONTROL + "a");
    usernameField.sendKeys(Keys.BACK_SPACE);

    passwordField.clear();
    passwordField.sendKeys(Keys.CONTROL + "a");
    passwordField.sendKeys(Keys.BACK_SPACE);
    passwordField.sendKeys(password);

    WebElement loginButton = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("signon"))
    );
    loginButton.click();
  }

  @Then("I should not be able to login")
  public void i_should_not_be_able_to_login() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.name("signon")));

    String pageSource = driver.getPageSource();
    boolean hasErrorMessage =
      pageSource.contains("Invalid username or password") ||
      pageSource.contains("Signon failed") ||
      pageSource.contains("Please enter your username") ||
      pageSource.contains("Please enter your password");

    assertTrue("Error message should be displayed", hasErrorMessage);
  }

  @When(
    "I enter blank {string} and {string} combination and press login button"
  )
  public void i_enter_blank_information(String username, String password)
    throws Throwable {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement usernameField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("username"))
    );
    WebElement passwordField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("password"))
    );

    usernameField.clear();
    usernameField.sendKeys(Keys.CONTROL + "a");
    usernameField.sendKeys(Keys.BACK_SPACE);

    passwordField.clear();
    passwordField.sendKeys(Keys.CONTROL + "a");
    passwordField.sendKeys(Keys.BACK_SPACE);

    WebElement loginButton = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("signon"))
    );
    loginButton.click();
  }

  @When(
    "I enter {string} and blank {string} combination and press login button"
  )
  public void i_enter_and_blank_combination_and_press_login_button(
    String username,
    String password
  ) throws Throwable {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement usernameField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("username"))
    );
    WebElement passwordField = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("password"))
    );

    usernameField.clear();
    usernameField.sendKeys(Keys.CONTROL + "a");
    usernameField.sendKeys(Keys.BACK_SPACE);
    usernameField.sendKeys(username);

    passwordField.clear();
    passwordField.sendKeys(Keys.CONTROL + "a");
    passwordField.sendKeys(Keys.BACK_SPACE);

    WebElement loginButton = wait.until(
      ExpectedConditions.elementToBeClickable(By.name("signon"))
    );
    loginButton.click();
  }
}
