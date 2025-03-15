package my.domain.name;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/my/domain/name/feature",
    glue = {"my.domain.name.steps", "my.domain.name.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class TestRunner {
    // This class will be empty
    // It's used as a hook for Cucumber tests
}
