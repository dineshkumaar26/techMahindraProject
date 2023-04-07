package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
	private WebDriver driver;
	public static final int EXPLICIT_TIMEOUT = 5;

	public BasePageObject(WebDriver driver) {
		this.driver =driver;
	}


	// @Step("Get text from the element {element}")
	public String getText(WebElement element) throws Exception {
		String val = null;
		try {
			waitForElementToBeVisible(element, 10);
			val = element.getText();
		} catch (NoSuchElementException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return val;
	}

	protected void waitForClickableElement(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));		
	}

	public void windowScroll(int scrollrange) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, " +scrollrange+ ")");	
		//js.executeScript("window.scrollBy(0, 500)");
	}

	// @Step("Wait For Element visibility {element}")
	protected boolean waitForElementToBeVisible(WebElement element, long timeInSeconds) {
		boolean isTrue = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isTrue = true;
		} catch (NoSuchElementException e) {
			// throw e;
		} catch (Exception e) {
			// throw e;
		}
		return isTrue;
	}

	//@Step("Load the page {url}")
	public void loadPage(String url) {
		driver.manage().window().maximize();
		System.out.println("URL is ....."+url);
		driver.get(url);
	}

	// Get Current page title
	public String getPageTitle() {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
		} catch (WebDriverException e) {
			throw e;
		}
		return pageTitle;
	}


	protected void click(WebElement element) throws Exception {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	// method to verify element is displayed or not
	public Boolean isDisplayed(WebElement element) throws Exception {
		return isElementVisible(element, EXPLICIT_TIMEOUT);
	}
	
	protected boolean isElementVisible(WebElement element, int timeInSeconds) {
		boolean isTrue = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isTrue = true;
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			isTrue = false;
		} catch (Exception e) {
			isTrue = false;

		}
		return isTrue;
	}
}
