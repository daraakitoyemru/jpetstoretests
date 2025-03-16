package my.domain.name.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import my.domain.name.config.WebDriverConfig;

// note: claude ai was used for this file for simple setup

/**
 * Hooks for WebDriver setup and teardown
 */
public class WebDriverHooks {

  /**
   * Setup WebDriver before each scenario
   */
  @Before
  public void setupWebDriver() {
    // Initialize WebDriver
    WebDriverConfig.getDriver();
  }

  /**
   * Quit WebDriver after each scenario
   */
  @After
  public void tearDownWebDriver() {
    // Quit WebDriver
    WebDriverConfig.quitDriver();
  }
}
