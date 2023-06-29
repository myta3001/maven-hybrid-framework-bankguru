package PageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.com.bankGuru.DashboardPageUI;

public class DashboardPageObject extends BasePage {
	private WebDriver driver;

	public DashboardPageObject(WebDriver driver) {
		driver = this.driver;
	}

	public Boolean isIdInfoDisplayed(WebDriver driver, String userID) {
		waitForElementVisible(driver, DashboardPageUI.ID_INFO_TEXT, userID);
		return isElementDisplayed(driver, DashboardPageUI.ID_INFO_TEXT, userID);
	}
}
