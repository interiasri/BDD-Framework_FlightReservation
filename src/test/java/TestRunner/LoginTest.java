package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "FeatureFiles/LoginTest.feature", glue = "StepDefinitions", dryRun = false,
plugin = {"pretty", "html:TestReports/LoginTest.html"})
public class LoginTest {

}
