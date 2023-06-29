package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.com.bankGuru.DeleteCustomerPageUI;

public class DeleteCustomerPageObject extends BasePage {
	private WebDriver driver;

	public DeleteCustomerPageObject(WebDriver driver) {
		driver = this.driver;
	}

	public Boolean isPageTitleDisplayed(WebDriver driver, String title) {
		waitForElementVisible(driver, DeleteCustomerPageUI.DELETE_CUSTOMER_PAGE_TITLE, title);
		return isElementDisplayed(driver, DeleteCustomerPageUI.DELETE_CUSTOMER_PAGE_TITLE, title);
	}

	public Boolean isCustomerIDNotExist(WebDriver driver) {
		return getAlertText(driver).toLowerCase().contains("customer does not exist");
	}
}
