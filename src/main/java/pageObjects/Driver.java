package pageObjects;


import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Dineshkumaar R
 * @description This class will initiate driver instance
 * @retun driver
 */
public class Driver {

	public WebDriver driver;
	private DesiredCapabilities capabilities;
	protected BasePageObject basePageObject;
	private static final Proxy proxy = new Proxy();

	@SuppressWarnings("deprecation")
	public WebDriver setDriver() {
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sabar\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			basePageObject = PageFactory.initElements(driver, BasePageObject.class);
			return driver;

		}
	}


}