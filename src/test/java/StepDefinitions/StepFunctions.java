package StepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepFunctions {
	public static WebDriver driver;
	public static Properties p;
	@Given("I Open Browser with url {string}")
	public void i_open_browser_with_url(String url) throws Throwable {
		p = new Properties();
		p.load(new FileInputStream("PropertyFile/OR.properties"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
	    
	}
	
	@Then("I should see Login Page")
	public void i_should_see_login_page() {
		String loginPage=driver.findElement(By.xpath(p.getProperty("objVerifyLoginPage"))).getText();
		if(loginPage.equalsIgnoreCase("Login")) {
			Reporter.log("Valid Page", true);
		}
		else {
			Reporter.log("Invalid Page");
		}
	    
	}
	
	@When("I Enter UserName as {string}")
	public void i_enter_user_name_as(String userName) {
	    driver.findElement(By.xpath(p.getProperty("objUserName"))).sendKeys(userName);
	}
	
	@When("I Enter PassWord as {string}")
	public void i_enter_pass_word_as(String password) {
	    driver.findElement(By.xpath(p.getProperty("objPassword"))).sendKeys(password);
	}
	
	@And("I click Sign In")
	public void i_click_sign_in() {
	    driver.findElement(By.xpath(p.getProperty("objSignIn"))).click();
	}
	
	@Then("I should see User DashBoard")
	public void i_should_see_user_dash_board() throws Throwable {
	    String Actual=driver.getTitle();
	    String Exp = "Flight Reservation | Dashboard";
	    assertEquals(Actual, Exp);
	    Thread.sleep(2000);
	    TakesScreenshot ts = (TakesScreenshot)driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    File trg = new File("TestReports/ScreenShots/UserDashoard.png");
	    FileUtils.copyFile(src, trg);
	}
	
	
	
	@Then("I Should See Login Page after Logout")
	public void i_should_see_login_page_after_logout() {
		String loginPage=driver.findElement(By.xpath(p.getProperty("objVerifyLoginPage"))).getText();
		if(loginPage.equalsIgnoreCase("Login")) {
			Reporter.log("Valid Page", true);
		}
		else {
			Reporter.log("Invalid Page");
		}
	}
	
	@Then("I should see Error Message")
	public void i_should_see_error_message() {
		if(driver.findElement(By.xpath(p.getProperty("objErrorMessage"))).isDisplayed()) {
			Reporter.log("Error Message Displayed", true);
			Reporter.log(driver.findElement(By.xpath(p.getProperty("objErrorMessage"))).getText(), true);
			driver.findElement(By.xpath(p.getProperty("objDismissErrorMessage"))).click();
		}
	}
	
	@When("I Enter Flight Resrvation Date as {string}")
	public void i_enter_flight_resrvation_date_as(String FlightDate) {
	    driver.findElement(By.xpath(p.getProperty("objSearchFlightDate"))).sendKeys(FlightDate);
	}
	
	@When("I select Flight from {string}")
	public void i_select_flight_from(String FlightFrom) {
	    Select from = new Select(driver.findElement(By.xpath(p.getProperty("objFlightFrom"))));
	    from.selectByVisibleText(FlightFrom);
	}
	
	@When("I select Flight To {string}")
	public void i_select_flight_to(String FlightTo) {
	    Select to = new Select(driver.findElement(By.xpath(p.getProperty("objFlightTo"))));
	    to.selectByVisibleText(FlightTo);
	}
	
	@And("I Click Search Flights")
	public void i_click_search_flights() {
	    if(driver.findElement(By.xpath(p.getProperty("objSearchFlights"))).isEnabled()) {
	    	driver.findElement(By.xpath(p.getProperty("objSearchFlights"))).click();
	    }
	    
	    
	}

	@When("I select airline as {string} from Flight search results table")
	public void i_select_airline_as_from_flight_search_results_table(String AirlineName) throws Throwable {
		WebElement ftable;
		WebElement ftablebody;
		List<WebElement> trows;
		List<WebElement> tcols;
		String fname;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.getProperty("objFlightSearchResults"))));
		try {
		    if(driver.findElement(By.xpath(p.getProperty("objFlightSearchResults"))).isDisplayed()) {
		    	ftable = driver.findElement(By.xpath(p.getProperty("objFtable")));
				ftablebody = ftable.findElement(By.tagName("tbody"));
				trows=ftablebody.findElements(By.tagName("tr"));
				for(int i=1; i<=trows.size(); i++) {
					tcols=trows.get(i).findElements(By.tagName("td"));
					fname=tcols.get(0).getText();
					if(fname.equalsIgnoreCase(AirlineName)) {
						tcols.get(8).click();
						Thread.sleep(2000);
						break;
					}
				}
		    }
			
		} catch (Exception e) {
			if(driver.findElement(By.xpath(p.getProperty("objFlightSearchResults"))).isDisplayed()) {
		    	ftable = driver.findElement(By.xpath(p.getProperty("objFtable")));
				ftablebody = ftable.findElement(By.tagName("tbody"));
				trows=ftablebody.findElements(By.tagName("tr"));
				for(int i=1; i<=trows.size(); i++) {
					tcols=trows.get(i).findElements(By.tagName("td"));
					fname=tcols.get(0).getText();
					if(fname.equalsIgnoreCase(AirlineName)) {
						tcols.get(8).click();
						Thread.sleep(2000);
						break;
					}
				}
		    }
			
		}
		
		
	}
	
	@When("I should take Screen Shot of order information")
	public void i_should_take_screen_shot_of_order_information() throws Throwable {
	    TakesScreenshot ts = (TakesScreenshot)driver;
	    File src=ts.getScreenshotAs(OutputType.FILE);
	    File trg = new File("TestReports/ScreenShots/OrderInfo.png");
	    FileUtils.copyFile(src, trg);
	}
	
	
	@When("I Enter Passeger Name as {string}")
	public void i_enter_passeger_name_as(String passengerName) {
	    driver.findElement(By.xpath(p.getProperty("objPassengerName"))).sendKeys(passengerName);
	}
	
	@When("I Select Flight class as {string}")
	public void i_select_flight_class_as(String FlightClass) {
		if(FlightClass.equalsIgnoreCase(p.getProperty("objFirstClassTicket"))) {
			driver.findElement(By.xpath(p.getProperty("objFirstClassTicket"))).click();
		}
		else if(FlightClass.equalsIgnoreCase(p.getProperty("objBusinessClassTicket"))) {
			driver.findElement(By.xpath(p.getProperty("objBusinessClassTicket"))).click();
		}
		else {
			driver.findElement(By.xpath(p.getProperty("objEconomyClassTicket"))).click();
		}
		
	}
	
	@And("I Enter Number of Tickets as {string}")
	public void i_enter_number_of_tickets_as(String Tickets) {
	    driver.findElement(By.xpath(p.getProperty("objTickets"))).clear();
	    driver.findElement(By.xpath(p.getProperty("objTickets"))).sendKeys(Tickets);
	}
	
	@When("I Click Insert Order")
	public void i_click_insert_order() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(p.getProperty("objInsertOrder"))));
		if(driver.findElement(By.xpath(p.getProperty("objInsertOrder"))).isEnabled()) {
			driver.findElement(By.xpath(p.getProperty("objInsertOrder"))).click();
		}
	    
	}
	@Then("I should see Order Success Message")
	public void i_should_see_order_success_message() {
		if(driver.findElement(By.xpath(p.getProperty("objBookingSuccessMessage"))).isDisplayed()) {
			Reporter.log("Ticket Booked", true);
		}
	}
	
	@When("I click Logout")
	public void i_click_logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.getProperty("objUserSettings"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(p.getProperty("objUserSettings"))));
		driver.findElement(By.xpath(p.getProperty("objUserSettings"))).click();
		driver.findElement(By.xpath(p.getProperty("objUserLogout"))).click();
	}
	
	@And("I Close Browser")
	public void i_close_browser() {
	    driver.quit();
	}
	
}
