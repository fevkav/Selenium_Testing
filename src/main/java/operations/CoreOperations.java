package operations;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.WebDriverConfig;
import pageobjects.HomePage;
import pageobjects.Page;
import pageobjects.ProductsPage;

/**
 * This class provides methods for simple operations like clicking on a WebElement or looking for a specific WebElement in a list.
 * @author fkavalci
 *
 */
public class CoreOperations {

	/**
	 * Clicks on the given WebElement by using javaScript. Useful if you
	 * get ElementNotInteractableException.
	 * 
	 * @param webelement
	 */
	public static void clickJS(WebElement webelement) {

		((JavascriptExecutor) WebDriverConfig.getDriver()).executeScript("arguments[0].click()", webelement);
	}

	/**
	 * Returns all inner texts of a given list of WebElements.
	 * 
	 * @param webElements a list of WebElements
	 * @return a list of all the link texts
	 */
	public static List<String> getInnerTextsOfAllElements(List<WebElement> webElements) {

		LinkedList<String> linkTexts = new LinkedList<String>();

		webElements.forEach(webelement -> {
			linkTexts.add(webelement.getAttribute("innerText"));
		});

		return linkTexts;
	}

	/**
	 * Gets the webelement in <code>webElements</code> which contains the given String in his DOM innerText property.
	 * @param webElements list of WebElements where to look for.
	 * @param innerText the webelement's innerText property should contain this.
	 * @return the resulting WebElement
	 * @throws org.openqa.selenium.NoSuchElementException
	 */
	public static WebElement getElementByInnerText(List<WebElement> webElements, String innerText) {
		for (WebElement webElement : webElements) {
			if (webElement.getAttribute("innerText").contains(innerText)) {
				return webElement;
			}
		}

		throw new NoSuchElementException("Given list of webelements does not include webelement with given innerText=" + innerText);
	}
	
	public static Page clickMenuItem(Page page, String menuItemString) {
		
		WebElement menuItem = getElementByInnerText(page.getMenuItems(), menuItemString);
		
		menuItem.click();
		
		Page newPage;
		
		switch (menuItemString) {
		case "Products":
			newPage = new ProductsPage();
			break;
		default:
			newPage = new HomePage();			
			break;
		}
		
		
		WebDriverConfig.waitForElementsDisplayed(newPage);
		
		return newPage;		
		
	}
	
	public static void waitDirty() {
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fillTextInput(WebElement textInput, String keys) {
		
		if(!textInput.getTagName().equals("input") || !textInput.getAttribute("type").equals("text")) {
			throw new IllegalArgumentException("Given WebElement is not a html textinput element");
		}
		
		textInput.sendKeys(keys);
		
	}
	
	
	
	
	

}
