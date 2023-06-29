package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class EditAccountPageObject extends BasePage {
	private WebDriver driver;

	public EditAccountPageObject(WebDriver driver) {
		driver = this.driver;
	}
	
	public Boolean isAccountIDNotExist(WebDriver driver) {
		return getAlertText(driver).toLowerCase().contains("account does not exist");
	}
}
