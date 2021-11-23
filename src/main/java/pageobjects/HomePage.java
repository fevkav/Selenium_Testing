package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.ConfigFileReader;
import config.WebDriverConfig;
import operations.CoreOperations;
import sections.CustomViews;

public class HomePage extends Page {


		
	private static final String URL_SUFFIX = "#!HomeView";

	/**
	 * @see CustomViews
	 */
	private CustomViews customViews;
	
	@FindBy(xpath="//div[contains(text(),'Home')]")
	private WebElement header;
		
	
	public HomePage() {
		super();
		customViews = new CustomViews();
	}

	@Override
	public boolean elementsAllDisplayed() {
				
		for (WebElement menuItem : menuItems) {
			if(!menuItem.isDisplayed())			
				return false;
		}
		
		WebDriverConfig.waitForElement(header);
		return header.isDisplayed();
	}
	
	@Override
	void navigate() {
		WebDriverConfig.getDriver().get(ConfigFileReader.getURL() + URL_SUFFIX);
	}

	public CustomViews getCustomViews() {
		return customViews;
	}

	
	
	
	
	

}
