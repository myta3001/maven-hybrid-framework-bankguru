package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.com.bankGuru.NewAccountPageUI;

public class NewAccountPageObject extends BasePage {
	private WebDriver driver;

	public NewAccountPageObject(WebDriver driver) {
		driver = this.driver;
	}
	
	public Boolean isPageTitleDisplayed(WebDriver driver) {
		waitForElementVisible(driver, NewAccountPageUI.NEW_ACCOUNT_PAGE_TITLE);
		return isElementDisplayed(driver, NewAccountPageUI.NEW_ACCOUNT_PAGE_TITLE);
	}

	public Boolean isSuccessMessageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, NewAccountPageUI.SUCCESS_REGISTED_MESSAGE);
		return isElementDisplayed(driver, NewAccountPageUI.SUCCESS_REGISTED_MESSAGE);
	}
}
