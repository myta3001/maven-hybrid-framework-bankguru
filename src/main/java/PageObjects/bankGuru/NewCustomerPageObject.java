package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.com.bankGuru.NewCustomerPageUI;

public class NewCustomerPageObject extends BasePage {
	private WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		driver = this.driver;
	}
	
	public Boolean isPageTitleDisplayed(WebDriver driver) {
		waitForElementVisible(driver, NewCustomerPageUI.NEW_CUSTOMER_PAGE_TITLE);
		return isElementDisplayed(driver, NewCustomerPageUI.NEW_CUSTOMER_PAGE_TITLE);
	}

	public Boolean isSuccessMessageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, NewCustomerPageUI.SUCCESS_REGISTED_MESSAGE);
		return isElementDisplayed(driver, NewCustomerPageUI.SUCCESS_REGISTED_MESSAGE);
	}
}
