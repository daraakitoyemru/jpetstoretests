package my.domain.name;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/java/my/domain/name/feature",
  glue = { "my.domain.name.steps", "my.domain.name.hooks" },
  plugin = { "pretty", "html:target/cucumber-reports" },
  tags = "@create-account or @change-password or @login",
  monochrome = true
)
public class TestRunner {}
