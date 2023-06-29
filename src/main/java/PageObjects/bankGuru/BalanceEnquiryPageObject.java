package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class BalanceEnquiryPageObject extends BasePage {
	private WebDriver driver;

	public BalanceEnquiryPageObject(WebDriver driver) {
		driver = this.driver;
	}
}
