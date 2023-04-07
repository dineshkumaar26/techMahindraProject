package pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePageObject{
	//WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	//BasePageObject basePageObject = new BasePageObject(driver);


	@FindBy(xpath = "//div[@id='pageWrapper']//h1")
	WebElement accountsSummaryTitle;

	@FindBy(xpath = "//*[@data-key=\"try-link\"]")
	List < WebElement > getDifferentRequests;

	@FindBy(xpath = "//*[@data-id=\"users-single-not-found\"]")
	WebElement singleUserNotFoundLink;

	@FindBy(xpath = "//*[@class=\"url\"]")
	WebElement singleUserNotFoundAPIRequestText;

	@FindBy(xpath = "//*[@class=\"response-code bad\"]")
	WebElement singleUserNotFoundAPIResponseText;
	
	@FindBy(xpath = "//*[@href=\"#support-heading\"]")
	WebElement supportReqresLink;
	
	@FindBy(xpath = "//*[@for=\"supportOneTime\"]")
	WebElement oneTimePaymentText;

	@FindBy(xpath = "//*[@for=\"supportRecurring\"]")
	WebElement monthlyPaymentText;

	@FindBy(xpath = "//*[@id=\"trigger-pro\"]")
	WebElement upgradeLink;
	
	

	public String getHomePageTitle() throws Exception {
		System.out.println("Page title is ..."+getPageTitle());
		return getPageTitle();
	}

	public void navigateToHomePage(String url) {
		// TODO Auto-generated method stub
		loadPage(url);
	}
	
	

	public ArrayList<String> getDifferentRequests() {
		// TODO Auto-generated method stub
		ArrayList<String> requestlist= new ArrayList<String>();

		for(WebElement request : getDifferentRequests) {
			requestlist.add(request.getText());
		}
		System.out.println("request list is ....."+requestlist);
		return requestlist;

	}

	public void clickOnSingleUserNotFoundAPI() throws Exception {
		windowScroll(1000);		
		waitForClickableElement(10,singleUserNotFoundLink);
		click(singleUserNotFoundLink);
	}

	public String getSingleUserNotFoundAPIRequestText() throws Exception {
		waitForClickableElement(10,singleUserNotFoundAPIRequestText);
		return getText(singleUserNotFoundAPIRequestText);
	}
	public String getSingleUserNotFoundAPIResponseText() throws Exception {
		return getText(singleUserNotFoundAPIResponseText);
	}
	
	public void clickOnSupportReqresLink() throws Exception {
		windowScroll(1000);		
		waitForClickableElement(10,supportReqresLink);
		click(supportReqresLink);
	}
	
	public String getOneTimePaymentText() throws Exception {
		waitForClickableElement(10,oneTimePaymentText);
		return getText(oneTimePaymentText);
	}

	public String getMonthlyPaymentText() throws Exception {
		waitForClickableElement(10,monthlyPaymentText);
		return getText(monthlyPaymentText);
	}

	public boolean verifyUpgradeLink() throws Exception {
		return isElementVisible(upgradeLink, 2);
	}
	
}

