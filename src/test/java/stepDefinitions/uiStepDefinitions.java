package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.BasePageObject;
import pageObjects.HomePage;
import pageObjects.SupportPage;

public class uiStepDefinitions  {
	WebDriver driver;
	HomePage homepage; 


	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sabar\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		this.driver =driver;
		//System.out.println("driver value is ******"+driver);
		homepage = new HomePage(driver);

	}

	@After
	public void tearDown() {
		//System.out.println("driver value is &&&&&&&"+driver);
		driver.quit();
	}


	@Given("Login to reqres Home Page {string} {string}")
	public void login_to_reqres_home_page(String url, String expectedPageTitle) throws Exception {
		// Write code here that turns the phrase aboveinto concrete actions

		homepage.navigateToHomePage(url);
		assertEquals(expectedPageTitle,homepage.getHomePageTitle());
	}

	@And("Validate if different request types are displayed {string}")
	public void validate_if_different_request_types_are_displayed(String expectedRequest) {
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(expectedRequest.split(", ")));
		//System.out.println("Araay listy names ..."+names);
		assertEquals(names,homepage.getDifferentRequests());
	} 

	@And("Navigate to Single user not found request")
	public void navigate_to_single_user_not_found_request() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		homepage.clickOnSingleUserNotFoundAPI();
	}

	@When("validate the request and response requestURL {string} {string}")
	public void validate_the_request_and_response_request_url(String requestURL, String responseCode) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(requestURL,homepage.getSingleUserNotFoundAPIRequestText());
		assertEquals(responseCode,homepage.getSingleUserNotFoundAPIResponseText());
	}

	@And("Click on support reqRes link")
	public void click_on_support_req_res_link() throws Exception {
		homepage.clickOnSupportReqresLink();

	}

	@When("verify one time Support Option {string}")
	public void verify_one_time_support_option(String string) throws Exception {
		assertEquals(string,homepage.getOneTimePaymentText());

	}

	@And("verify Monthly Support Option  {string}")
	public void verify_monthly_support_option(String string) throws Exception {
		assertEquals(string,homepage.getMonthlyPaymentText());

	}

	@And("validate the Upgrade button")
	public void validate_the_upgrade_button() throws Exception {
		assertTrue(homepage.verifyUpgradeLink());
	}



}
