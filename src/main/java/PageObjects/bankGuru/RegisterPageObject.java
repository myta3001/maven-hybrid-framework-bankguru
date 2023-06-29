package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageUIs.commons.BasePageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		driver = this.driver;
	}

	public void registerNewAccount(WebDriver driver, String email) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, "emailid");
		sendkeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, email, "emailid");
		
		waitForElementClickable(driver, BasePageUI.ELEMENT_BY_NAME, "btnLogin");
		clickToElement(driver, BasePageUI.ELEMENT_BY_NAME, "btnLogin");
	}

	public LoginPageObject openLoginPage(WebDriver driver) {
		openPageUrl(driver, GlobalConstants.BANK_GURU_LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
}
