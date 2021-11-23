package config;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.Page;

/**
 * This class encapsulates the WebDriver object (singleton object) to configure and manage it in here, 
 * to support a better readability of the system. 
 * Methods like Getter for the WebDriver or capabilities configuration can be handled in here.
 * @author fkavalci
 *
 */
public class WebDriverConfig {	
	
	private static final int WAIT_SECONDS = 5;

	private static final int TIMEOUT_IN_SECONDS = 10;

	private static WebDriver driver = null;
	
	private WebDriverConfig() {}	

	private static void init() {		

		switch (ConfigFileReader.getDriverType()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", ConfigFileReader.getDriverPath() + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", ConfigFileReader.getDriverPath() + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", ConfigFileReader.getDriverPath() + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", ConfigFileReader.getDriverPath() + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			break;
		}
		
		driver.manage().timeouts().pageLoadTimeout(ConfigFileReader.getPageLoadTimeout(), TimeUnit.SECONDS);
		
	}
	
	/**
	 * 
	 * @return the WebDriver object
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			init();
		}
		return driver;
	}
	
	/**
	 * 
	 * @param webelementToWaitFor a WebElement object, 
	 * @return
	 */
	public static WebElement waitForElement(WebElement webelementToWaitFor) {
		
		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		return wait.until(ExpectedConditions.elementToBeClickable(webelementToWaitFor));		
		
	}

	public static void waitForElementsDisplayed(Page page) {
		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				
				return page.elementsAllDisplayed();
			}
		});
		

		
	}
	
	
	/**
	 * quits the driver object, if exists.
	 */
	public static void closeDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
