package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features = "FeatureFiles/BookFlightTicket.feature", glue="StepDefinitions", dryRun = false, 
plugin = {"pretty", "html:TestReports/BookFlightTicketTest.html"})
public class BookFlightTicketTest {

}
