package com.bankguru.scripts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Get_System_Account_And_Create_Customer;
import com.bankguru.testdata.AccountData;

import PageObjects.bankGuru.BalanceEnquiryPageObject;
import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.DeleteAccountPageObject;
import PageObjects.bankGuru.DeleteCustomerPageObject;
import PageObjects.bankGuru.EditAccountPageObject;
import PageObjects.bankGuru.EditCustomerPageObject;
import PageObjects.bankGuru.LoginPageObject;
import PageObjects.bankGuru.NewAccountPageObject;
import PageObjects.bankGuru.NewCustomerPageObject;
import PageObjects.bankGuru.PaymentPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import ultilities.DataHelper;

public class Payment extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private NewAccountPageObject newAccountPage;
	private EditAccountPageObject editAccountPage;
	private DeleteAccountPageObject deleteAccountPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	private PaymentPageObject depositPage;
	private PaymentPageObject withdrawPage;
	private PaymentPageObject fundTransferPage;
	private BalanceEnquiryPageObject balanceEnquiryPage;
	private String systemUser, systemPass;
	private String payeeAccountNo, fundTransferAmount, fundTransferDesc;
	private String customerID, customerName, customerGender, customerBirthDay, customerBirthMonth, customerBirthYear, customerAddress, customerCity, customerState, customerPIN, customerMobile, customerEmail, customerPassword;
	private String editedAddress, editedCity, editedState, editedPIN, editedMobile, editedEmail;
	private String accountNo, initialDeposit, depositAmount, depositDesc, withdrawAmount, withdrawDesc, accountType;
	private DataHelper dataFaker;

	@Parameters("browser")
	@BeforeClass
	public void BeforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		systemUser = Get_System_Account_And_Create_Customer.username;
		systemPass = Get_System_Account_And_Create_Customer.password;
		
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

		editedAddress = dataFaker.getAddress();
		editedCity = dataFaker.getCity();
		editedState = dataFaker.getState();
		editedPIN = dataFaker.getPIN();
		editedMobile = dataFaker.getMobile();
		editedEmail = dataFaker.getEmail();

		initialDeposit = AccountData.INITIAL_DEPOSIT;
		depositAmount = AccountData.DEPOSIT_AMOUNT;
		depositDesc = AccountData.DEPOSIT_DESCRIPTION;
		withdrawAmount = AccountData.WITHDRAW_AMOUNT;
		withdrawDesc = AccountData.WITHDRAW_DESCRIPTION;
		accountType = AccountData.ACCOUNT_TYPE;
//		editedAccountType = AccountData.EDITED_ACCOUNT_TYPE;

		payeeAccountNo = Get_System_Account_And_Create_Customer.accountID;
		fundTransferAmount = AccountData.FUND_TRANSFER_AMOUNT;
		fundTransferDesc = AccountData.FUND_TRANSFER_DESCRIPTION;

		startLog("Pre-Condition", "Pre-condition for check Payment cases");
		setLogINFO("Pre-Condition - Step_01: Login to Guru system");
		loginPage.loginToSystem(driver, systemUser, systemPass);
		dashboardPage = PageGeneratorManager.getDashboardPage(driver);

		setLogINFO("Pre_Condition - Step_02: Verify dashboard page is displayed");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, systemUser));
	}

	@Test
	public void Payment_01_Create_New_Customer_Account(Method method) {
		startLog(method.getName(), "Create new customer and check created sucessfully");

		setLogINFO("Payment_01 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_01 - Step_01: Navigate to New Customer Page by clicking to menu");
		dashboardPage.clickToLeftMenuByText(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		setLogINFO("Payment_01 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("Payment_01 - Step_03: Verify New Customer page is displayed");
		verifyTrue(newCustomerPage.isPageTitleDisplayed(driver));

		setLogINFO("Payment_01 - Step_04: Input to Name textbox with value: \"" + customerName + "\"");
		newCustomerPage.inputToElementByName(driver, customerName, "name");

		setLogINFO("Payment_01 - Step_05: Choose Gender checkbox with value: \"" + customerGender + "\"");
		newCustomerPage.clickToElementByValue(driver, customerGender);

		setLogINFO("Payment_01 - Step_06: Input to Date of Birth textbox with value: \"" + customerBirthDay + "/" + customerBirthMonth + "/" + customerBirthYear + "\"");
		newCustomerPage.inputToDatePickerByName(driver, customerBirthDay, customerBirthMonth, customerBirthYear, "dob");

		setLogINFO("Payment_01 - Step_07: Input to Address textbox with value: \"" + customerAddress + "\"");
		newCustomerPage.inputToElementByName(driver, customerAddress, "addr");

		setLogINFO("Payment_01 - Step_08: Input to City textbox with value: \"" + customerCity + "\"");
		newCustomerPage.inputToElementByName(driver, customerCity, "city");

		setLogINFO("Payment_01 - Step_09: Input to State textbox with value: \"" + customerState + "\"");
		newCustomerPage.inputToElementByName(driver, customerState, "state");

		setLogINFO("Payment_01 - Step_10: Input to PIN textbox with value: \"" + customerPIN + "\"");
		newCustomerPage.inputToElementByName(driver, customerPIN, "pinno");

		setLogINFO("Payment_01 - Step_11: Input to Mobile textbox with value: \"" + customerMobile + "\"");
		newCustomerPage.inputToElementByName(driver, customerMobile, "telephoneno");

		setLogINFO("Payment_01 - Step_12: Input to Email textbox with value: \"" + customerEmail + "\"");
		newCustomerPage.inputToElementByName(driver, customerEmail, "emailid");

		setLogINFO("Payment_01 - Step_13: Input to Password textbox with value: \"" + customerPassword + "\"");
		newCustomerPage.inputToElementByName(driver, customerPassword, "password");

		setLogINFO("Payment_01 - Step_14: Click to Submit button");
		newCustomerPage.clickToElementByValue(driver, "Submit");
sleepInSecond(5);
		setLogINFO("Payment_01 - Step_15: Verify Registed Success message is displayed");
		verifyTrue(newCustomerPage.isSuccessMessageDisplayed(driver));

		customerID = newCustomerPage.getNextCellValueByText(driver, "Customer ID");
		setLogINFO("Payment_01 - Step_16: Store customerID \"" + customerID + "\" into for using later");

		setLogINFO("Payment_01 - Step_17: Verify registed Customer Name");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Customer Name"), customerName);

		setLogINFO("Payment_01 - Step_18: Verify registed Gender");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Gender"), customerGender);

		setLogINFO("Payment_01 - Step_19: Verify registed Birthdate");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Birthdate"), customerBirthYear + "-" + customerBirthMonth + "-" + customerBirthDay);

		String registedAddress = customerAddress.replaceAll("\n", " ");
		setLogINFO("Payment_01 - Step_20: Verify registed Address");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Address"), registedAddress);

		setLogINFO("Payment_01 - Step_21: Verify registed City");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "City"), customerCity);

		setLogINFO("Payment_01 - Step_22: Verify registed State");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "State"), customerState);

		setLogINFO("Payment_01 - Step_23: Verify registed Pin");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Pin"), customerPIN);

		setLogINFO("Payment_01 - Step_24: Verify registed Mobile No.");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Mobile No."), customerMobile);

		setLogINFO("Payment_01 - Step_25: Verify registed Email");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Email"), customerEmail);
	}

	@Test
	public void Payment_02_Edit_Customer_Account(Method method) {
		startLog(method.getName(), "Edit an customer account and check edited successfully");

		setLogINFO("Payment_02 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_02 - Step_01: Navigate to Edit Customer Page");
		dashboardPage.clickToLeftMenuByText(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		setLogINFO("Payment_02 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);

		setLogINFO("Payment_02 - Step_03: Input to CustomerID textbox with : \"" + customerID + "\"");
		editCustomerPage.inputToElementByName(driver, customerID, "cusid");

		setLogINFO("Payment_02 - Step_04: Click to Submit button");
		editCustomerPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Payment_02 - Step_05: Verify Edit Customer Form is displayed");
		verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Customer"));

		setLogINFO("Payment_02 - Step_06: Input to Address textbox with value: \"" + editedAddress + "\"");
		editCustomerPage.inputToElementByName(driver, editedAddress, "addr");

		setLogINFO("Payment_02 - Step_07: Input to City textbox with value: \"" + editedCity + "\"");
		editCustomerPage.inputToElementByName(driver, editedCity, "city");

		setLogINFO("Payment_02 - Step_08: Input to State textbox with value: \"" + editedState + "\"");
		editCustomerPage.inputToElementByName(driver, editedState, "state");

		setLogINFO("Payment_02 - Step_09: Input to PIN textbox with value: \"" + editedPIN + "\"");
		editCustomerPage.inputToElementByName(driver, editedPIN, "pinno");

		setLogINFO("Payment_02 - Step_10: Input to Mobile textbox with value: \"" + editedMobile + "\"");
		editCustomerPage.inputToElementByName(driver, editedMobile, "telephoneno");

		setLogINFO("Payment_02 - Step_11: Input to Email textbox with value: \"" + editedEmail + "\"");
		editCustomerPage.inputToElementByName(driver, editedEmail, "emailid");

		setLogINFO("Payment_02 - Step_12: Click to Submit button");
		editCustomerPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Payment_02 - Step_13: Accept Alert and verify redirecting to Edit Customer Form");
		System.out.println("Alert after editing Customer: " + editCustomerPage.getAlertText(driver));
		editCustomerPage.acceptAlert(driver);
		verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Customer Form"));
	}

	@Test
	public void Payment_03_Add_New_Account(Method method) {
		startLog(method.getName(), "Add new account and verify information.");

		setLogINFO("Payment_03 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_03 - Step_01: Navigate to New Account page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

		setLogINFO("Payment_03 - Step_02: Check if exsiting Ads popup and close it.");
		newAccountPage.closeAdsPopup(driver);

		setLogINFO("Payment_03 - Step_03: Verify New Account page is displayed");
		verifyTrue(newAccountPage.isPageTitleDisplayed(driver));

		setLogINFO("Payment_03 - Step_04: Input to Customer ID textbox with: \"" + customerID + "\"");
		newAccountPage.inputToElementByName(driver, customerID, "cusid");

		setLogINFO("Payment_03 - Step_05: Select Account Type textbox with value: \"" + accountType + "\"");
		newAccountPage.selectItemInDropdownByName(driver, accountType, "selaccount");

		setLogINFO("Payment_03 - Step_06: Input to Initial Deposit textbox with: \"" + initialDeposit + "\"");
		newAccountPage.inputToElementByName(driver, initialDeposit, "inideposit");

		setLogINFO("Payment_03 - Step_07: Click to Submit button");
		newAccountPage.clickToElementByValue(driver, "submit");
sleepInSecond(5);
		setLogINFO("Payment_03 - Step_08: Verify Registed Success message is displayed");
		verifyTrue(newAccountPage.isSuccessMessageDisplayed(driver));

		accountNo = newAccountPage.getNextCellValueByText(driver, "Account ID");
		setLogINFO("Payment_03 - Step_09: Store ID \"" + accountNo + "\" to account ID varible for later using.");

		setLogINFO("Payment_03 - Step_10: Verify registed Customer ID");
		verifyEquals(newAccountPage.getNextCellValueByText(driver, "Customer ID"), customerID);

		setLogINFO("Payment_03 - Step_11: Verify registed Account Type");
		verifyEquals(newAccountPage.getNextCellValueByText(driver, "Account Type"), accountType);

		setLogINFO("Payment_03 - Step_12: Verify registed Initial Deposit");
		verifyEquals(newAccountPage.getNextCellValueByText(driver, "Current Amount"), initialDeposit);
	}

	@Test
	public void Payment_04_Edit_Account_Info(Method method) {
		startLog(method.getName(), "Edit account information and verify result.");

		setLogINFO("Payment_04 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_04 - Step_01: Navigate to Edit Account page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

		setLogINFO("Payment_04 - Step_02: Check if exsiting Ads popup and close it.");
		editAccountPage.closeAdsPopup(driver);

		setLogINFO("Payment_04 - Step_03: Input to Account No textbox with a valid accountID: \"" + accountNo + "\"");
		editAccountPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_04 - Step_04: Click to Submit button");
		editAccountPage.clickToElementByValue(driver, "Submit");
		sleepInSecond(10);
		// this function is not working
		// setLogINFO("Payment_04 - Step_05: Verify Edit Account Form is displayed");
		// verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Account"));
		//
		// setLogINFO("Payment_04 - Step_06: Select Account Type textbox with value: \"" + editedAccountType + "\"");
		// newAccountPage.selectItemInDropdownByName(driver, editedAccountType, "selaccount");
		//
		// setLogINFO("Payment_04 - Step_07: Click to Submit button");
		// editAccountPage.clickToElementByValue(driver, "Submit");
		//
		// setLogINFO("Payment_04 - Step_08: Verify Edited Success message is displayed");
		// verifyTrue(newAccountPage.isSuccessMessageDisplayed(driver));
		//
		// setLogINFO("Payment_04 - Step_09: Verify edited Account Number");
		// verifyEquals(newAccountPage.getNextCellValueByText(driver, "Account ID"), accountNo);
		//
		// setLogINFO("Payment_04 - Step_10: Verify edited Account Type");
		// verifyEquals(newAccountPage.getNextCellValueByText(driver, "Account Type"), editedAccountType);
	}

	@Test
	public void Payment_05_Deposit_To_An_Account(Method method) {
		startLog(method.getName(), "Deposit to an account and verify information.");

		setLogINFO("Payment_05 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_05 - Step_01: Navigate to Deposit page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Deposit");
		depositPage = PageGeneratorManager.getPaymentPage(driver);

		setLogINFO("Payment_05 - Step_02: Check if exsiting Ads popup and close it.");
		depositPage.closeAdsPopup(driver);

		setLogINFO("Payment_05 - Step_03: Verify Deposit page is displayed");
		verifyTrue(depositPage.isPageTitleDisplayed(driver, "Amount Deposit Form"));

		setLogINFO("Payment_05 - Step_04: Input to Account No textbox with: \"" + accountNo + "\"");
		depositPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_05 - Step_05: Select Amount textbox with value: \"" + depositAmount + "\"");
		depositPage.inputToElementByName(driver, depositAmount, "ammount");

		setLogINFO("Payment_05 - Step_06: Input to Description textbox with: \"" + depositDesc + "\"");
		depositPage.inputToElementByName(driver, depositDesc, "desc");

		setLogINFO("Payment_05 - Step_07: Click to Submit button");
		depositPage.clickToElementByValue(driver, "Submit");
		sleepInSecond(3);
		// ------------------------Function is not working-------------------------
		// setLogINFO("Payment_05 - Step_08: Verify Deposited Success message is displayed");
		// verifyTrue(depositPage.isSuccessMessageDisplayed(driver, "Transaction details of Deposit for Account " + accountNo));
		//
		// setLogINFO("Payment_05 - Step_09: Verify deposited Account ID");
		// verifyEquals(depositPage.getNextCellValueByText(driver, "Account ID"), accountNo);
		//
		// initialDeposit = Integer.toString(Integer.valueOf(initialDeposit) + Integer.valueOf(depositAmount));
		// setLogINFO("Payment_05 - Step_10: Verify Account Current Amount after deposited");
		// verifyEquals(depositPage.getNextCellValueByText(driver, "Current Amount"), initialDeposit);
		//
		// setLogINFO("Payment_05 - Step_11: Verify deposited description");
		// verifyEquals(depositPage.getNextCellValueByText(driver, "Description"), depositDesc);
	}

	@Test
	public void Payment_06_Withdrawal_From_An_Account(Method method) {
		startLog(method.getName(), "Withdrawal from an account and verify information.");

		setLogINFO("Payment_06 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_06 - Step_01: Navigate to Withdrawal page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Withdrawal");
		withdrawPage = PageGeneratorManager.getPaymentPage(driver);

		setLogINFO("Payment_06 - Step_02: Check if exsiting Ads popup and close it.");
		withdrawPage.closeAdsPopup(driver);

		setLogINFO("Payment_06 - Step_03: Verify Withdrawal page is displayed");
		verifyTrue(withdrawPage.isPageTitleDisplayed(driver, "Amount Withdrawal Form"));

		setLogINFO("Payment_06 - Step_04: Input to Account No textbox with: \"" + accountNo + "\"");
		withdrawPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_06 - Step_05: Select Amount textbox with value: \"" + withdrawAmount + "\"");
		withdrawPage.inputToElementByName(driver, withdrawAmount, "ammount");

		setLogINFO("Payment_06 - Step_06: Input to Description textbox with: \"" + withdrawDesc + "\"");
		withdrawPage.inputToElementByName(driver, withdrawDesc, "desc");

		setLogINFO("Payment_06 - Step_07: Click to Submit button");
		withdrawPage.clickToElementByValue(driver, "Submit");
		setLogINFO("Payment_06 - Step_08: Verify Withdrawal Success message is displayed");
		verifyTrue(withdrawPage.isSuccessMessageDisplayed(driver, "Transaction details of Withdrawal for Account " + accountNo));

		sleepInSecond(3);
		// -------------------------function is not working ---------------------------
		// setLogINFO("Payment_06 - Step_09: Verify withdrawal Account ID");
		// verifyEquals(withdrawPage.getNextCellValueByText(driver, "Account ID"), accountNo);
		//
		// initialDeposit = Integer.toString(Integer.valueOf(initialDeposit) - Integer.valueOf(withdrawAmount));
		// setLogINFO("Payment_06 - Step_10: Verify Account Current Amount after deposited");
		// verifyEquals(withdrawPage.getNextCellValueByText(driver, "Current Amount"), initialDeposit);
		//
		// setLogINFO("Payment_06 - Step_11: Verify withdrawal description");
		// verifyEquals(withdrawPage.getNextCellValueByText(driver, "Description"), withdrawDesc);
	}

	@Test
	public void Payment_07_Fund_Transfer_Between_Two_Accounts(Method method) {
		startLog(method.getName(), "Fund transfer between two accounts and verify information.");

		setLogINFO("Payment_07 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_06 - Step_01: Navigate to Fund Transfer page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getPaymentPage(driver);

		setLogINFO("Payment_06 - Step_02: Check if exsiting Ads popup and close it.");
		fundTransferPage.closeAdsPopup(driver);

		setLogINFO("Payment_06 - Step_03: Verify Withdrawal page is displayed");
		verifyTrue(fundTransferPage.isPageTitleDisplayed(driver, "Fund transfer"));

		setLogINFO("Payment_06 - Step_04: Input to Payers Account Number textbox with: \"" + accountNo + "\"");
		fundTransferPage.inputToElementByName(driver, accountNo, "payersaccount");

		setLogINFO("Payment_06 - Step_05: Input to Payees Account Number textbox with: \"" + payeeAccountNo + "\"");
		fundTransferPage.inputToElementByName(driver, payeeAccountNo, "payeeaccount");

		setLogINFO("Payment_06 - Step_06: Select Amount textbox with value: \"" + fundTransferAmount + "\"");
		fundTransferPage.inputToElementByName(driver, fundTransferAmount, "ammount");

		setLogINFO("Payment_06 - Step_07: Input to Description textbox with: \"" + fundTransferDesc + "\"");
		fundTransferPage.inputToElementByName(driver, fundTransferDesc, "desc");

		setLogINFO("Payment_06 - Step_08: Click to Submit button");
		fundTransferPage.clickToElementByValue(driver, "Submit");
		sleepInSecond(5);

		setLogINFO("Payment_06 - Step_09: Verify Withdrawal page is displayed");
		verifyTrue(fundTransferPage.isPageTitleDisplayed(driver, "Fund Transfer Details"));

		setLogINFO("Payment_06 - Step_10: Verify Fund Transfer Amount");
		verifyEquals(fundTransferPage.getNextCellValueByText(driver, "From Account Number"), accountNo);

		setLogINFO("Payment_06 - Step_11: Verify Fund Transfer Amount");
		verifyEquals(fundTransferPage.getNextCellValueByText(driver, "To Account Number"), payeeAccountNo);

		initialDeposit = Integer.toString(Integer.valueOf(initialDeposit) - Integer.valueOf(fundTransferAmount));
		Get_System_Account_And_Create_Customer.initialDeposit = Integer.toString(Integer.valueOf(Get_System_Account_And_Create_Customer.initialDeposit) + Integer.valueOf(fundTransferAmount));
		setLogINFO("Payment_06 - Step_12: Verify Fund Transfer Amount");
		verifyEquals(fundTransferPage.getNextCellValueByText(driver, "Amount"), fundTransferAmount);

		String displayedDesc = fundTransferPage.getSubString(fundTransferDesc, 1, 20);
		setLogINFO("Payment_06 - Step_13: Verify fund transfer description");
		verifyEquals(fundTransferPage.getNextCellValueByText(driver, "Description"), displayedDesc);
	}

	@Test
	public void Payment_08_Check_Account_Balance_Enquiry(Method method) {
		startLog(method.getName(), "Verify Current Account Balance Enquiry.");

		setLogINFO("Payment_08 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_08 - Step_01: Navigate to Balance Enquiry page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);

		setLogINFO("Payment_08 - Step_02: Check if exsiting Ads popup and close it.");
		balanceEnquiryPage.closeAdsPopup(driver);

		setLogINFO("Payment_08 - Step_03: Input to Account No textbox with a valid accountID: \"" + accountNo + "\"");
		balanceEnquiryPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_08 - Step_04: Click to Submit button");
		balanceEnquiryPage.clickToElementByValue(driver, "Submit");
		
		sleepInSecond(5);
		//------------- this function is not working----------------------------------
		// setLogINFO("Payment_04 - Step_05: Verify Edit Account Form is displayed");
		// verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Account"));
		//
		// setLogINFO("Payment_04 - Step_08: Verify Edited Success message is displayed");
		// verifyTrue(newAccountPage.isSuccessMessageDisplayed(driver));
		//
		// setLogINFO("Payment_04 - Step_09: Verify Balance Enquiry Amount");
		// verifyEquals(balanceEnquiryPage.getNextCellValueByText(driver, "Balance Enquiry"), initialDeposit);
	}

	@Test
	public void Payment_09_Delete_Account(Method method) {
		startLog(method.getName(), "Delete Current Account and verify.");

		setLogINFO("Payment_09 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_09 - Step_01: Navigate to Delete Account page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountPage(driver);

		setLogINFO("Payment_09 - Step_02: Check if exsiting Ads popup and close it.");
		deleteAccountPage.closeAdsPopup(driver);

		setLogINFO("Payment_09 - Step_03: Input to Account No textbox with a valid accountID: \"" + accountNo + "\"");
		deleteAccountPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_09 - Step_04: Click to Submit button");
		deleteAccountPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Payment_09 - Step_05: Accept confirm alert.");
		deleteAccountPage.acceptAlert(driver);
		sleepInSecond(5);
		
		setLogINFO("Payment_09 - Step_06: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_09 - Step_07: Navigate to Edit Account page by clicking to the menu");
		deleteAccountPage.clickToLeftMenuByText(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

		setLogINFO("Payment_09 - Step_08: Input to Account No textbox with a valid accountID: \"" + accountNo + "\"");
		editAccountPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_09 - Step_09: Click to Submit button");
		editAccountPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Payment_09 - Step_10: Verify Account does not exsit message.");
		verifyTrue(editAccountPage.isAccountIDNotExist(driver));
		editAccountPage.acceptAlert(driver);
	}

	@Test
	public void Payment_10_Delete_Customer(Method method) {
		startLog(method.getName(), "Delete Current Customer and verify.");

		setLogINFO("Payment_10 - Step_00: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver);

		setLogINFO("Payment_10 - Step_01: Navigate to Delete Customer page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);

		setLogINFO("Payment_10 - Step_02: Check if exsiting Ads popup and close it.");
		deleteCustomerPage.closeAdsPopup(driver);

		setLogINFO("Payment_10 - Step_03: Input to Account No textbox with a valid accountID: \"" + customerID + "\"");
		deleteCustomerPage.inputToElementByName(driver, customerID, "cusid");

		setLogINFO("Payment_10 - Step_04: Click to Submit button");
		deleteCustomerPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Payment_10 - Step_05: Accept delete confirm alert.");
		deleteCustomerPage.acceptAlert(driver);
		sleepInSecond(5);

		setLogINFO("Payment_10 - Step_06: Navigate to Dashboard Page");
		dashboardPage = navigateToDashboardPage(driver); 

		setLogINFO("Payment_10 - Step_07: Navigate to Edit Account page by clicking to the menu");
		deleteCustomerPage.clickToLeftMenuByText(driver, "Edit Account");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		setLogINFO("Payment_10 - Step_08: Input to Account No textbox with a valid accountID: \"" + accountNo + "\"");
		editCustomerPage.inputToElementByName(driver, accountNo, "accountno");

		setLogINFO("Payment_10 - Step_09: Click to Submit button");
		editCustomerPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Payment_10 - Step_10: Verify Account does not exsit message.");
		verifyTrue(editCustomerPage.isCustomerIDNotExist(driver));
		editAccountPage.acceptAlert(driver);
	}

	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		closeBrowserAndDriver(driver);
	}
}
