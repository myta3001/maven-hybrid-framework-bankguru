package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.com.bankGuru.PaymentPageUI;

public class PaymentPageObject extends BasePage {
	private WebDriver driver;

	public PaymentPageObject(WebDriver driver) {
		driver = this.driver;
	}
	
	public Boolean isPageTitleDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, PaymentPageUI.PAYMENT_PAGE_TITLE, pageTitle);
		return isElementDisplayed(driver, PaymentPageUI.PAYMENT_PAGE_TITLE, pageTitle);
	}

	public Boolean isSuccessMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, PaymentPageUI.PAYMENT_MESSAGE, message);
		return isElementDisplayed(driver, PaymentPageUI.PAYMENT_MESSAGE, message);
	}
}
