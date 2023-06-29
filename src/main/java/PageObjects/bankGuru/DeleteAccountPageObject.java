package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DeleteAccountPageObject extends BasePage {
	private WebDriver driver;

	public DeleteAccountPageObject(WebDriver driver) {
		driver = this.driver;
	}
}
