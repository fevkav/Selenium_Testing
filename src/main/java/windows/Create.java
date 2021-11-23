package windows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.WebDriverConfig;

public class Create {

	@FindBy(xpath="//span[contains(text(), 'Save')]")
	private WebElement saveButton;
	
	@FindBy(xpath="//span[contains(text(), 'Cancel')]")
	private WebElement cancelButton;

	@FindBy(className="v-window-closebox")
	private WebElement closeBox;
	
	
	
	public Create() {
		PageFactory.initElements(WebDriverConfig.getDriver(), this);
	}

	public void save() {
		
	}

	public void cancel() {

	}

	public void close() {

	}

}
