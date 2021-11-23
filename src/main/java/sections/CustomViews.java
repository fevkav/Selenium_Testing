package sections;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.WebDriverConfig;

/**
 * This page object represents the 'custom views' card slot on the home page. 
 * @author fkavalci
 *
 */
public class CustomViews {
	
	/**
	 * The headline of this custom views card slot. Should be 'Custom Views'.
	 */
	@FindBy(xpath="//*[contains(text(), 'Custom Views')]")
	private WebElement cardHeader;
	
	/**
	 * The different register links in the custom views card slot.
	 */
	@FindBy(xpath="//div[@class='v-captiontext']")
	private List<WebElement> captions;
	
	public CustomViews() {
		PageFactory.initElements(WebDriverConfig.getDriver(), this);
	}
	
	public String getCardHeader() {
		return cardHeader.getText();
	}
	
	public List<WebElement> getCaptions() {
		return captions;
	}

}
