package com.bankguru.commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.bankguru.testdata.AccountData;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.LoginPageObject;
import PageObjects.bankGuru.NewAccountPageObject;
import PageObjects.bankGuru.NewCustomerPageObject;
import PageObjects.bankGuru.RegisterPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import ultilities.DataHelper;

public class Get_System_Account_And_Create_Customer extends BaseTest {
	private WebDriver driver;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private NewCustomerPageObject newCustomerPage;
	private NewAccountPageObject newAccountPage;
	public static String username, password, customerID, accountID, initialDeposit;
	private String accountType, customerName, customerGender, customerBirthDay, customerBirthMonth, customerBirthYear, customerAddress, customerCity, customerState, customerPIN, customerMobile, customerEmail, customerPassword;
	private DataHelper dataFaker;
	
	@Parameters("browser")
	@BeforeTest
	public void Register_Account_To_Guru(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_REGISTER_ACCOUNT_LINK);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		dataFaker = DataHelper.getData();

		customerName = dataFaker.getFullName();
		customerGender = dataFaker.getGender();

		customerBirthDay = dataFaker.getDay();
		customerBirthMonth = dataFaker.getMonth();
		customerBirthYear = dataFaker.getYear();

		customerAddress = dataFaker.getAddress();
		customerCity = dataFaker.getCity();
		customerState = dataFaker.getState();
		customerPIN = dataFaker.getPIN();
		customerMobile = dataFaker.getMobile();
		customerEmail = dataFaker.getEmail();
		customerPassword = dataFaker.getPassword();
		
		initialDeposit = AccountData.INITIAL_DEPOSIT;
		accountType = AccountData.ACCOUNT_TYPE; // Savings

		String email = dataFaker.getEmail(); 

		startLog("Pre_Condition - Register_Account", "Register account to Guru system");
		setLogINFO("Pre_Condition - Step_01: Input email ID and submit to get new account");
		registerPage.registerNewAccount(driver, email);

		setLogINFO("Pre_Condition - Step_02: Get username and password on the screen");
		username = registerPage.getNextCellValueByText(driver, "User ID :");
		password = registerPage.getNextCellValueByText(driver, "Password :");

		setLogINFO("Pre_Condition - Step_03: Navigate to system login page");
		registerPage.openPageUrl(driver, GlobalConstants.BANK_GURU_LOGIN_LINK);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		setLogINFO("Pre_Condition - Step_04: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

//		setLogINFO("Pre_Condition - Step_05: Verify access to dashboard page successfully");
//		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));

		setLogINFO("Pre_Condition - Step_06: Navigate to New Customer page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		setLogINFO("Pre_Condition - Step_07: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

//		setLogINFO("Pre_Condition - Step_08: Verify New Customer page is displayed");
//		verifyTrue(newCustomerPage.isPageTitleDisplayed(driver));

		setLogINFO("Pre_Condition - Step_09: Input to Name textbox with value: \"" + customerName + "\"");
		newCustomerPage.inputToElementByName(driver, customerName, "name");

		setLogINFO("Pre_Condition - Step_10: Choose Gender checkbox with value: \"" + customerGender + "\"");
		newCustomerPage.clickToElementByValue(driver, customerGender);

		setLogINFO("Pre_Condition - Step_11: Input to Date of Birth textbox with value: \"" + customerBirthDay + "/" + customerBirthMonth + "/" + customerBirthYear + "\"");
		newCustomerPage.inputToDatePickerByName(driver, customerBirthDay, customerBirthMonth, customerBirthYear, "dob");

		setLogINFO("Pre_Condition - Step_12: Input to Address textbox with value: \"" + customerAddress + "\"");
		newCustomerPage.inputToElementByName(driver, customerAddress, "addr");

		setLogINFO("Pre_Condition - Step_13: Input to City textbox with value: \"" + customerCity + "\"");
		newCustomerPage.inputToElementByName(driver, customerCity, "city");

		setLogINFO("Pre_Condition - Step_14: Input to State textbox with value: \"" + customerState + "\"");
		newCustomerPage.inputToElementByName(driver, customerState, "state");

		setLogINFO("Pre_Condition - Step_15: Input to PIN textbox with value: \"" + customerPIN + "\"");
		newCustomerPage.inputToElementByName(driver, customerPIN, "pinno");

		setLogINFO("Pre_Condition - Step_16: Input to Mobile textbox with value: \"" + customerMobile + "\"");
		newCustomerPage.inputToElementByName(driver, customerMobile, "telephoneno");

		setLogINFO("Pre_Condition - Step_17: Input to Email textbox with value: \"" + customerEmail + "\"");
		newCustomerPage.inputToElementByName(driver, customerEmail, "emailid");

		setLogINFO("Pre_Condition - Step_18: Input to Password textbox with value: \"" + customerPassword + "\"");
		newCustomerPage.inputToElementByName(driver, customerPassword, "password");

		setLogINFO("Pre_Condition - Step_19: Click to Submit button");
		newCustomerPage.clickToElementByValue(driver, "Submit");

//		setLogINFO("Pre_Condition - Step_20: Verify Registed Success message is displayed");
//		verifyTrue(newCustomerPage.isSuccessMessageDisplayed(driver));

		customerID = newCustomerPage.getNextCellValueByText(driver, "Customer ID");
		setLogINFO("Pre_Condition - Step_21: Store new customerID \"" + customerID + "\" into statis variable for using later");
		
		setLogINFO("Pre_Condition - Step_22: Navigate to New Account page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

		setLogINFO("Pre_Condition - Step_23: Check if exsiting Ads popup and close it.");
		newAccountPage.closeAdsPopup(driver);

//		setLogINFO("Pre_Condition - Step_24: Verify New Account page is displayed");
//		verifyTrue(newAccountPage.isPageTitleDisplayed(driver));
		
		setLogINFO("Pre_Condition - Step_25: Input to Customer ID textbox with: \"" + customerID + "\"");
		newAccountPage.inputToElementByName(driver, customerID, "cusid");

		setLogINFO("Pre_Condition - Step_26: Select Account Type textbox with value: \"" + accountType + "\"");
		newAccountPage.selectItemInDropdownByName(driver, accountType, "selaccount");

		setLogINFO("Pre_Condition - Step_27: Input to Initial Deposit textbox with: \"" + initialDeposit + "\"");
		newAccountPage.inputToElementByName(driver, initialDeposit, "inideposit");

		setLogINFO("Pre_Condition - Step_28: Click to Submit button");
		newAccountPage.clickToElementByValue(driver, "submit");

//		setLogINFO("Pre_Condition - Step_29: Verify Registed Success message is displayed");
//		verifyTrue(newAccountPage.isSuccessMessageDisplayed(driver));

		accountID = newAccountPage.getNextCellValueByText(driver, "Account ID");
		setLogINFO("Pre_Condition - Step_30: Store ID \"" + accountID + "\" to account ID varible for later using.");

		closeBrowserAndDriver(driver);
	}
}
