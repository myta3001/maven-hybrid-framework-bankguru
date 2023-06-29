package com.bankguru.scripts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Get_System_Account_And_Create_Customer;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.LoginPageObject;
import PageObjects.bankGuru.NewAccountPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class New_Account extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private NewAccountPageObject newAccountPage;
	private String username, password, customerID, initialDeposit, accountType;
	private String includeNumbericText, includeSpecialCharText, includeFirstBlankSpace, includeBlankSpace;

	@BeforeClass
	@Parameters("browser")
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);

		username = Get_System_Account_And_Create_Customer.username;
		password = Get_System_Account_And_Create_Customer.password;
		customerID = Get_System_Account_And_Create_Customer.customerID;
		initialDeposit = "5000";
		accountType = "Current"; // Savings

		includeNumbericText = "name123";
		includeSpecialCharText = "1234@796%&";
		includeFirstBlankSpace = " 423454";
		includeBlankSpace = "123 67";

		startLog("Pre_Condition - New_Customer", "Prepare enviroment and data.");

		setLogINFO("Pre_Condition - Step_01: Navigate to Login page");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		setLogINFO("Pre_Condition - Step_02: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

		setLogINFO("Pre_Condition - Step_03: Verify access to dashboard page successfully");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));

		setLogINFO("Pre_Condition - Step_04: Navigate to New Account page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

		setLogINFO("Pre_Condition - Step_05: Check if exsiting Ads popup and close it.");
		newAccountPage.closeAdsPopup(driver);

		setLogINFO("Pre_Condition - Step_06: Verify New Account page is displayed");
		verifyTrue(newAccountPage.isPageTitleDisplayed(driver));
	}

	@Test
	public void New_Account_01_CustomerID_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating CustomerID textbox empty");

		setLogINFO("New_Account_01 - Step_01: Click to CustomerID textbox");
		newAccountPage.clickToElementByName(driver, "cusid");

		setLogINFO("New_Account_01 - Step_02: press TAB key to focus next field");
		newAccountPage.pressTabToElementByName(driver, "cusid");

		setLogINFO("New_Account_01 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "cusid"), "Customer ID is required");
	}

	@Test
	public void New_Account_02_Input_Not_Numberic_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating CustomerID textbox with data input");

		setLogINFO("New_Account_02 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_02 - Step_02: Input to CustomerID textbox including numberic name: \"" + includeNumbericText + "\"");
		newAccountPage.inputToElementByName(driver, includeNumbericText, "cusid");

		setLogINFO("New_Account_02 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "cusid"), "Characters are not allowed");
	}

	@Test
	public void New_Account_03_Input_Special_Char_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("New_Account_03 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_03 - Step_02: Input to CustomerID textbox including special character: \"" + includeSpecialCharText + "\"");
		newAccountPage.inputToElementByName(driver, includeSpecialCharText, "cusid");

		setLogINFO("New_Account_03 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "cusid"), "Special characters are not allowed");
	}

	@Test
	public void New_Account_04_Input_First_Blank_Space_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("New_Account_04 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_04 - Step_02: Input to CustomerID textbox including first blank space character: \"" + includeFirstBlankSpace + "\"");
		newAccountPage.inputToElementByName(driver, includeFirstBlankSpace, "cusid");

		setLogINFO("New_Account_04 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "cusid"), "First character can not have space");
	}

	@Test
	public void New_Account_05_Input_Including_Space_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("New_Account_05 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_05 - Step_02: Input to CustomerID textbox including blank space character: \"" + includeBlankSpace + "\"");
		newAccountPage.inputToElementByName(driver, includeBlankSpace, "cusid");

		setLogINFO("New_Account_05 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "cusid"), "Characters are not allowed");
	}

	@Test
	public void New_Account_06_Initial_Deposit_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating Initial Deposit textbox empty");

		setLogINFO("New_Account_06 - Step_01: Click to Initial Deposit textbox");
		newAccountPage.clickToElementByName(driver, "inideposit");

		setLogINFO("New_Account_06 - Step_02: press TAB key to focus next field");
		newAccountPage.pressTabToElementByName(driver, "inideposit");

		setLogINFO("New_Account_06 - Step_03: Verify validation message for Initial Deposit textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "inideposit"), "Initial Deposit must not be blank");
	}

	@Test
	public void New_Account_07_Input_Not_Numberic_To_Initial_Deposit_Textbox(Method method) {
		startLog(method.getName(), "Verify validating Initial Deposit textbox with data input");

		setLogINFO("New_Account_07 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_07 - Step_02: Input to Initial Deposit textbox including numberic name: \"" + includeNumbericText + "\"");
		newAccountPage.inputToElementByName(driver, includeNumbericText, "inideposit");

		setLogINFO("New_Account_07 - Step_03: Verify validation message for Initial Deposit textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "inideposit"), "Characters are not allowed");
	}

	@Test
	public void New_Account_08_Input_Special_Char_To_Initial_Deposit_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Initial Deposit with data input");

		setLogINFO("New_Account_08 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_08 - Step_02: Input to Initial Deposit textbox including special character: \"" + includeSpecialCharText + "\"");
		newAccountPage.inputToElementByName(driver, includeSpecialCharText, "inideposit");

		setLogINFO("New_Account_08 - Step_03: Verify validation message for Initial Deposit textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "inideposit"), "Special characters are not allowed");
	}

	@Test
	public void New_Account_09_Input_First_Blank_Space_To_Initial_Deposit_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Initial Deposit with data input");

		setLogINFO("New_Account_09 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_09 - Step_02: Input to Initial Deposit textbox including first blank space character: \"" + includeFirstBlankSpace + "\"");
		newAccountPage.inputToElementByName(driver, includeFirstBlankSpace, "inideposit");

		setLogINFO("New_Account_09 - Step_03: Verify validation message for Initial Deposit textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "inideposit"), "First character can not have space");
	}

	@Test
	public void New_Account_10_Input_Including_Space_To_Initial_Deposit_Textbox(Method method) {
		startLog(method.getName(), "Verify validating Initial Deposit textbox with data input");

		setLogINFO("New_Account_10 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_10 - Step_02: Input to Initial Deposit textbox including blank space character: \"" + includeBlankSpace + "\"");
		newAccountPage.inputToElementByName(driver, includeBlankSpace, "inideposit");

		setLogINFO("New_Account_10 - Step_03: Verify validation message for Initial Deposit textbox");
		verifyEquals(newAccountPage.getValidateMessageByElementName(driver, "inideposit"), "Characters are not allowed");
	}

	@Test
	public void New_Account_11_Input_Valid_Info_To_Create_New_Account(Method method) {
		startLog(method.getName(), "Input valid information into New Account form and submit");

		setLogINFO("New_Account_11 - Step_01: Refresh page");
		newAccountPage.refreshCurrentPage(driver);

		setLogINFO("New_Account_11 - Step_02: Input to Customer ID textbox with: \"" + customerID + "\"");
		newAccountPage.inputToElementByName(driver, customerID, "cusid");

		setLogINFO("New_Account_11 - Step_03: Select Account Type textbox with value: \"" + accountType + "\"");
		newAccountPage.selectItemInDropdownByName(driver, accountType, "selaccount");

		setLogINFO("New_Account_11 - Step_04: Input to Initial Deposit textbox with: \"" + initialDeposit + "\"");
		newAccountPage.inputToElementByName(driver, initialDeposit, "inideposit");

		setLogINFO("New_Account_11 - Step_05: Click to Submit button");
		newAccountPage.clickToElementByValue(driver, "submit");

		setLogINFO("New_Account_11 - Step_06: Verify Registed Success message is displayed");
		verifyTrue(newAccountPage.isSuccessMessageDisplayed(driver));

		String accountID = newAccountPage.getNextCellValueByText(driver, "Account ID");
		setLogINFO("New_Account_11 - Step_07: Store ID \"" + accountID + "\" to account ID varible for later using.");
		Get_System_Account_And_Create_Customer.accountID = accountID;

		setLogINFO("New_Account_11 - Step_08: Verify registed Customer ID");
		verifyEquals(newAccountPage.getNextCellValueByText(driver, "Customer ID"), customerID);

		setLogINFO("New_Account_11 - Step_09: Verify registed Account Type");
		verifyEquals(newAccountPage.getNextCellValueByText(driver, "Account Type"), accountType);

		setLogINFO("New_Account_11 - Step_10: Verify registed Initial Deposit");
		verifyEquals(newAccountPage.getNextCellValueByText(driver, "Current Amount"), initialDeposit);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
