package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads a property file to configure basic parameters like the CETECOM CERT-URL
 * or username/password and provides their getters.
 * 
 * @author fkavalci
 *
 */
public class ConfigFileReader {

	private static final String propertyFilePath = "config.properties";

	private static Properties properties = init();

	////////////////// KEYS	/////////////////////////////////////////////////////

	private static final String PASSWORD = "password";

	private static final String USERNAME = "username";
	
	private static final String PAGELOADTIMEOUT = "pageloadtimeout";

	private static final String URL = "url";

	private static final String DRIVER_PATH = "driverPath";

	private static final String DRIVER_TYPE = "driver";

	//////////////////////////////////////////////////////////////////////////////

	private static Properties init() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration File not found at relative path /" + propertyFilePath);
		}

		return properties;
	}

	/**
	 * 
	 * @return the WebDriver given in the properties file {@value #propertyFilePath}
	 *         with the key {@value #DRIVER_TYPE}.
	 *         To see the possible values see {@link WebDriverConfig}}
	 */
	public static String getDriverType() {
		return properties.getProperty(DRIVER_TYPE);
	}

	/**
	 * 
	 * @return the path of the WebDriver file given in the properties file {@value #propertyFilePath}
	 *         with the key {@value #DRIVER_PATH}
	 */
	public static String getDriverPath() {
		return properties.getProperty(DRIVER_PATH);
	}

	/**
	 * 
	 * @return the root url of Cetecom Cert given in the properties file {@value #propertyFilePath}
	 *         with the key {@value #URL}
	 */
	public static String getURL() {
		return properties.getProperty(URL);
	}

	/**
	 * 
	 * @return the timeout for a page loading process given in the properties file {@value #propertyFilePath}
	 *         with the key {@value #PAGELOADTIMEOUT}
	 */
	public static long getPageLoadTimeout() {
		return Long.parseLong(properties.getProperty(PAGELOADTIMEOUT));
	}

	/**
	 * 
	 * @return the username to use for the tests given in the properties file {@value #propertyFilePath}
	 *         with the key {@value #USERNAME}
	 */
	public static String getUsername() {
		return properties.getProperty(USERNAME);
	}

	/**
	 * 
	 * @return the password for the given username in the properties file {@value #propertyFilePath}
	 *         with the key {@value #PASSWORD}
	 */
	public static String getPassword() {
		return properties.getProperty(PASSWORD);
	}

}
