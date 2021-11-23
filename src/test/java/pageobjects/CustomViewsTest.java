package pageobjects;

import static operations.CoreOperations.getElementByInnerText;
import static operations.CoreOperations.getInnerTextsOfAllElements;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import config.WebDriverConfig;
import operations.CoreOperations;
import sections.CustomViews;

public class CustomViewsTest {

	
	private HomePage homePage;
	private CustomViews customViews;
	
	@Before
	public void setUp() {
		homePage = new Page().loginDefault();		
		customViews = homePage.getCustomViews();		
	}
	
	@After
	public void tearDown() {
		WebDriverConfig.closeDriver();
	}

	@Test
	public void checkCustomViewsHeader() {		
		assertThat(customViews.getCardHeader(), equalTo(" Custom Views"));
	}
	
	@Test
	public void checkCaptionsTextsCetecomAdmin() {
		
		List<String> expectedCaptions = Arrays.asList("Countries", "Frequencies", "Requirement Schemes", 
				"Label Requirements", "Products", "Contacts", "Accounts & Roles");		
		
		assertThat(getInnerTextsOfAllElements(customViews.getCaptions()), is(expectedCaptions));
	}

	@Test
	public void clickOnProductsRegisterInCustomViews() {
		
		WebElement captionProducts = getElementByInnerText(customViews.getCaptions(), "Products");
		
		// a normal WebElement.click() throws ElementNotVisibleException
		CoreOperations.clickJS(captionProducts);
		
		
		
		
	}
	
	
	
	

}
