package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.ConfigFileReader;
import config.WebDriverConfig;

public class ProductsPage extends Page{
	
	private static final String URL_SUFFIX = "#!ProductLayout";

	@FindBy(className="v-captiontext")
	private List<WebElement> captions;
	
	@FindBy(xpath="//span[@class='v-button-caption' and contains(text(),'Create')]")
	private WebElement createButton;
	
	@FindBy(xpath="//div[contains(@class,'h4') and contains(@class,'v-label')]")
	private WebElement header;
	
	
	
	
	@Override
	void navigate() {
		WebDriverConfig.getDriver().get(ConfigFileReader.getURL() + URL_SUFFIX);
	}
	
	@Override
	public boolean elementsAllDisplayed() {
		for (WebElement caption : captions) {
			if(!caption.isDisplayed())			
				return false;
		}		
		
		
		return createButton.isDisplayed() && header.isDisplayed();
	
	}
	

}
