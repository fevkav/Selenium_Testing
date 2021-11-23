package pageobjects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.ConfigFileReader;
import config.WebDriverConfig;
import operations.CoreOperations;

/**
 * General tests which are relevant on every page, e.g. logout, change role, click on a menu item.
 * @author fkavalci
 *
 */
public class PageTest {
	
	private HomePage homepage;	
	
	@Before
	public void setUp() {
		homepage = new Page().loginDefault();
	}
	
	@After
	public void tearDown() {

		WebDriverConfig.closeDriver();

	}
	
	@Test
	public void logoutTest() {
		
		Page loginPage = homepage.logout();
		
		assertThat(loginPage.getUrl(), equalTo(ConfigFileReader.getURL()));
		assertThat(loginPage.elementsAllDisplayed(), is(true));
		
	}
	
	@Test
	public void checkMenuItemsAdmin() {
				
		List<String> expectedMenuItems = Arrays.asList("Home", "Countries", "Frequencies", "Requirement Schemes", 
				"Label Requirements", "Products", "Contacts", "Accounts & Roles");
		
		List<String> actualMenuItems = CoreOperations.getInnerTextsOfAllElements(homepage.getMenuItems());
				
		assertThat(actualMenuItems, is(expectedMenuItems));		
		
	}
	
	@Test 
	public void logoutAndLoginAndcheckMenuItemsSystemAdmin() {
		
		List<String> expectedMenuItems = Arrays.asList("Home", "Countries", "Frequencies", "Requirement Schemes", 
				"Label Requirements", "Tenants", "Accounts & Roles");
		
		Page loginPage = homepage.logout();		
		loginPage.setUsername("system\\admin");
		loginPage.setDefaultPassword();				
		HomePage newHomepage = loginPage.submit();	
				
		assertThat(expectedMenuItems, is(CoreOperations.getInnerTextsOfAllElements(newHomepage.getMenuItems())));
	}
	
	@Test
	public void getUserRoleAdmin() {
		assertThat(homepage.getUserRole(), is(equalTo("Admin")));
	}
		
}
