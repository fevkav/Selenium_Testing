package windows;

import org.openqa.selenium.WebElement;

import operations.CoreOperations;

public class CreateNewProductFamily extends Create{
	
	private WebElement familyNameTextInput;
	
	private WebElement customerNameTextInput;
		
	public void setFamilyName(String familyName) {
		CoreOperations.fillTextInput(familyNameTextInput, familyName);				
	}
	
	public void setCustomerName(String customerName) {
		CoreOperations.fillTextInput(customerNameTextInput, customerName);
	}
	
}
