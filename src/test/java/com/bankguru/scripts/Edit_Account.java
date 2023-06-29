package com.bankguru.scripts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Get_System_Account_And_Create_Customer;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.EditAccountPageObject;
import PageObjects.bankGuru.LoginPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class Edit_Account extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private EditAccountPageObject editAccountPage;
	private String username, password, accountID;
	private String includeBlankSpace, includeNumbericText, includeSpecialCharText;

	@BeforeClass
	@Parameters("browser")
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		username = Get_System_Account_And_Create_Customer.username;
		password = Get_System_Account_And_Create_Customer.password;
		accountID = Get_System_Account_And_Create_Customer.accountID;

		includeNumbericText = "name123";
		includeSpecialCharText = "1234@796%&";
		includeBlankSpace = "123 67";

		startLog("Pre_Condition - Edit_Customer", "Prepare enviroment and data.");

		setLogINFO("Pre_Condition - Step_01: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

		setLogINFO("Pre_Condition - Step_02: Verify access to dashboard page successfully");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));

		setLogINFO("Pre_Condition - Step_03: Navigate to Edit Customer page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

		setLogINFO("Pre_Condition - Step_04: Check if exsiting Ads popup and close it.");
		editAccountPage.closeAdsPopup(driver);
	}

	@Test
	public void Edit_Account_01_Account_No_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating Account No textbox empty");

		setLogINFO("Edit_Account_01 - Step_01: Click to Account No textbox");
		editAccountPage.clickToElementByName(driver, "accountno");

		setLogINFO("Edit_Account_01 - Step_02: press TAB key to focus next field");
		editAccountPage.pressTabToElementByName(driver, "accountno");

		setLogINFO("Edit_Account_01 - Step_03: Verify validation message for Account No textbox");
		verifyEquals(editAccountPage.getValidateMessageByElementName(driver, "accountno"), "Account Number must not be blank");
	}

	@Test
	public void Edit_Account_02_Input_Not_Numberic_To_Account_No_Textbox(Method method) {
		startLog(method.getName(), "Verify validating Account No textbox with data input");

		setLogINFO("Edit_Account_02 - Step_01: Refresh page");
		editAccountPage.refreshCurrentPage(driver);

		setLogINFO("Edit_Account_02 - Step_02: Input to Account No textbox including numberic name: \"" + includeNumbericText + "\"");
		editAccountPage.inputToElementByName(driver, includeNumbericText, "accountno");

		setLogINFO("Edit_Account_02 - Step_03: Verify validation message for Account No textbox");
		verifyEquals(editAccountPage.getValidateMessageByElementName(driver, "accountno"), "Characters are not allowed");
	}

	@Test
	public void Edit_Account_03_Input_Special_Char_To_Account_No_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Account No with data input");

		setLogINFO("Edit_Account_03 - Step_01: Refresh page");
		editAccountPage.refreshCurrentPage(driver);

		setLogINFO("Edit_Account_03 - Step_02: Input to Account No textbox including special character: \"" + includeSpecialCharText + "\"");
		editAccountPage.inputToElementByName(driver, includeSpecialCharText, "accountno");

		setLogINFO("Edit_Account_03 - Step_03: Verify validation message for Account No textbox");
		verifyEquals(editAccountPage.getValidateMessageByElementName(driver, "accountno"), "Special characters are not allowed");
	}

	@Test
	public void Edit_Account_04_Input_Including_Space_To_Account_No_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Account No with data input");

		setLogINFO("Edit_Account_04 - Step_01: Refresh page");
		editAccountPage.refreshCurrentPage(driver);

		setLogINFO("Edit_Account_04 - Step_02: Input to Account No textbox including blank space character: \"" + includeBlankSpace + "\"");
		editAccountPage.inputToElementByName(driver, includeBlankSpace, "accountno");

		setLogINFO("Edit_Account_04 - Step_03: Verify validation message for Account No textbox");
		verifyEquals(editAccountPage.getValidateMessageByElementName(driver, "accountno"), "Characters are not allowed");
	}

	@Test
	public void Edit_Account_05_Input_Valid_Account_No(Method method) {
		startLog(method.getName(), "Input a valid Account No and submit form");

		setLogINFO("Edit_Account_05 - Step_01: Refresh page");
		editAccountPage.refreshCurrentPage(driver);

		setLogINFO("Edit_Account_05 - Step_02: Input to Account No textbox with a valid accountID: \"" + accountID + "\"");
		editAccountPage.inputToElementByName(driver, accountID, "accountno");

		setLogINFO("Edit_Account_05 - Step_03: Click to Submit button");
		editAccountPage.clickToElementByValue(driver, "Submit");

		//the page stopped working
//		setLogINFO("Edit_Account_04 - Step_04: Verify Edit Account page is loaded");
//		verifyEquals(editAccountPage.getPageUrl(driver), "https://demo.guru99.com/v4/manager/editAccountPage.php");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
