package pageobjects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import config.WebDriverConfig;

public class LoginTest {

	private Page loginPage;

	@Before
	public void setUp() {
		loginPage = new Page();
	}

	@After
	public void tearDown() {
		WebDriverConfig.closeDriver();
	}

	@Test
	public void elementsAllDisplayedTest() {
		assertTrue(loginPage.elementsAllDisplayed());
	}

	@Test(expected = TimeoutException.class)
	public void loginWithWrongUsername() {

		loginPage.setUsername("wrongUsername");
		loginPage.setDefaultPassword();		
		loginPage.submit();
	}
	
	@Test
	public void loginDefaultTest() {
		
		HomePage homePage = loginPage.loginDefault();
		
		assertFalse(loginPage.elementsAllDisplayed());
				
		assertTrue(homePage.elementsAllDisplayed());
		
	}
			
	@Test
	public void customLoginTest() {
				
		loginPage.setUsername("system\\admin");
		loginPage.setDefaultPassword();		
		
		loginPage.submit();
				
		assertFalse(loginPage.elementsAllDisplayed());		
	}
	
		
}
