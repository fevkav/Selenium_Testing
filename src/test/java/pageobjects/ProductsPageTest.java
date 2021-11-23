package pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.ConfigFileReader;
import config.WebDriverConfig;
import operations.CoreOperations;

public class ProductsPageTest {

	private Page page;
	
	@Before
	public void setUp() {
		page = new Page().loginDefault();
		
	}
	
	@After
	public void tearDown() {
		WebDriverConfig.closeDriver();
	}
	
	@Test
	public void checkClickMenuItem() {
				
		page = CoreOperations.clickMenuItem(page, "Products");
				
		assertThat(page.getUrl(), is(equalTo(ConfigFileReader.getURL() + "#!ProductLayout")));
		
		
	}

}
