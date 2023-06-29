package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.com.bankGuru.EditCustomerPageUI;

public class EditCustomerPageObject extends BasePage {
	private WebDriver driver;

	public EditCustomerPageObject(WebDriver driver) {
		driver = this.driver;
	}

	public Boolean isPageTitleDisplayed(WebDriver driver, String title) {
		waitForElementVisible(driver, EditCustomerPageUI.EDIT_CUSTOMER_PAGE_TITLE, title);
		return isElementDisplayed(driver, EditCustomerPageUI.EDIT_CUSTOMER_PAGE_TITLE, title);
	}
	
	public Boolean isSuccessMessageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, EditCustomerPageUI.SUCCESS_EDIT_MESSAGE);
		return isElementDisplayed(driver, EditCustomerPageUI.SUCCESS_EDIT_MESSAGE);
	}

	public Boolean isCustomerIDNotExist(WebDriver driver) {
		return getAlertText(driver).toLowerCase().contains("customer does not exist");
	}
}
