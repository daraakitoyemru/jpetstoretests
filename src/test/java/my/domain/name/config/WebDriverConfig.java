package my.domain.name.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Configuration class for WebDriver setup
 */
public class WebDriverConfig {

  private static WebDriver driver;

  /**
   * Initialize and get the WebDriver instance
   * @return WebDriver instance
   */
  public static WebDriver getDriver() {
    if (driver == null) {
      try {
        // Setup ChromeDriver using WebDriverManager with automatic version detection
        WebDriverManager.chromedriver().browserVersion("latest").setup();

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        // Uncomment the line below if you need to run tests in headless mode
        // options.addArguments("--headless=new");

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);

        // Configure timeouts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
      } catch (Exception e) {
        System.err.println(
          "Error initializing ChromeDriver: " + e.getMessage()
        );
        e.printStackTrace();
        throw e;
      }
    }
    return driver;
  }

  /**
   * Close the WebDriver instance
   */
  public static void quitDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
