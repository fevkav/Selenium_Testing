package pageobjects;

import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.ConfigFileReader;
import config.WebDriverConfig;

/**
 * The entire page object representing the login page of Cetecom Cert, which is
 * the super class of all page objects and has to be instantiate for all page at
 * first.
 * 
 * @author fkavalci
 *
 */
public class Page {

	private WebDriver driver;

	/**
	 * the menu item 'role/user' displayed in the top of the menubar. NOTE: this
	 * WebElement is usually not a component of the cert login page.
	 */
	@FindBy(xpath = "//div[@class='v-menubar v-widget user-menu v-menubar-user-menu']")
	WebElement userMenu;

	/**
	 * the textfield input for the username in the login form.
	 */
	@FindBy(id = "username")
	private WebElement usernameTextInput;

	/**
	 * the textfield input for the password in the login form.
	 */
	@FindBy(id = "password")
	private WebElement passwordTextInput;

	@FindBy(xpath = "//div[@role = 'button']")
	private WebElement loginButton;

	/**
	 * Contains all menu item webelements.
	 */
	@FindBy(className = "valo-menu-item-caption")
	List<WebElement> menuItems;

	private By logoutLocator = By.xpath("//*[@class='v-menubar-menuitem-caption' and text()='Log Out']");

	/**
	 * Does a HTTP GET operation to the url given in the properties file,
	 * initialises the webelements and checks if all webelements are found.
	 * 
	 */
	public Page() {

		driver = WebDriverConfig.getDriver();

		navigate();

		PageFactory.initElements(driver, this);

		elementsAllDisplayed();

	}

	void navigate() {
		driver.get(ConfigFileReader.getURL());
	}

	/**
	 * 
	 * @return true, if all specified webelements in this page object are found.
	 */
	public boolean elementsAllDisplayed() {

		try {
			return usernameTextInput.isDisplayed() && passwordTextInput.isDisplayed()
					&& loginButton.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	/**
	 * fills the username and password text field of the login form and submits it
	 * with the given credentials in the properties file. default role cetecom\admin
	 * 
	 * @return a new page object representing the homepage.
	 */
	public HomePage loginDefault() {

		usernameTextInput.sendKeys(ConfigFileReader.getUsername());
		passwordTextInput.sendKeys(new String(Base64.getDecoder().decode(ConfigFileReader.getPassword())));

		loginButton.click();

		return new HomePage();

	}

	/**
	 * Logs with the given username and the default password given in the
	 * config.properties file.
	 * 
	 * @param username
	 * @return a new page object representing the homepage.
	 */
	public HomePage loginWithUsername(String username) {
		usernameTextInput.sendKeys(username);
		passwordTextInput.sendKeys(new String(Base64.getDecoder().decode(ConfigFileReader.getPassword())));

		loginButton.click();

		return new HomePage();

	}

	/**
	 * helpful for tests with other users.
	 * 
	 * @param username the username of the Cetecom cert user.
	 */
	public void setUsername(String username) {
		usernameTextInput.sendKeys(username);
	}

	/**
	 * fills the password text field in the login form with the given password in
	 * the properties file. Note: the password has to be Base64 encoded.
	 */
	public void setDefaultPassword() {
		passwordTextInput.sendKeys(new String(Base64.getDecoder().decode(ConfigFileReader.getPassword())));
	}

	/**
	 * a simple click on the login submit button.
	 * 
	 * @return a new page object representing the homepage.
	 */
	public HomePage submit() {
		loginButton.click();
		return new HomePage();
	}

	/**
	 * 
	 * @return the current title of the page.
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 
	 * @return the current url of the page.
	 */
	public String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Logs the current user out by clicking on the user menu and then on the 'Log
	 * Out' link. Includes a ' explicit wait' to wait for the login page gets
	 * loaded.
	 * 
	 * @return a new page object (login)
	 */
	public Page logout() {

		userMenu.click();

		WebDriverConfig.getDriver().findElement(logoutLocator).click();

		WebDriverConfig.waitForElement(usernameTextInput);

		return new Page();
	}

	/**
	 * 
	 * @return the current role text displayed on the top of the menubar.
	 */
	public String getUserRole() {
		return userMenu.getText();
	}

	/**
	 * 
	 * @return all found menu items.
	 */
	public List<WebElement> getMenuItems() {
		return menuItems;
	}

}
