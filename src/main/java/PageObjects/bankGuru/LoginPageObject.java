package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.commons.BasePageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		driver = this.driver;
	}

	public DashboardPageObject loginToSystem(WebDriver driver, String username, String password) {
		waitForElementVisible(driver, pageUIs.commons.BasePageUI.ELEMENT_BY_NAME, "uid");
		sendkeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, username, "uid");

		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, "password");
		sendkeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, password, "password");

		waitForElementClickable(driver, BasePageUI.ELEMENT_BY_NAME, "btnLogin");
		clickToElement(driver, BasePageUI.ELEMENT_BY_NAME, "btnLogin");
		
		return PageGeneratorManager.getDashboardPage(driver);
	}

}
