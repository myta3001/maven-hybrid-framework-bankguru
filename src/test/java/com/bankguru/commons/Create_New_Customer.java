package com.bankguru.commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.LoginPageObject;
import PageObjects.bankGuru.NewCustomerPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class Create_New_Customer extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private NewCustomerPageObject newCustomerPage;
	private String username, password;
	private String customerName, customerGender, customerDOB, customerAddress, customerCity, customerState, customerPIN, customerMobile, customerEmail, customerPassword;
	public static String customerID;

	@Parameters("browser")
	@BeforeTest
	public void Register_Account_To_Guru(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);
		
		username = Get_System_Account_And_Create_Customer.username;
		password = Get_System_Account_And_Create_Customer.password;
		
		customerName = "Alex Tran";
		customerGender = "male";
		customerDOB = "1999-02-13";
		customerAddress = "Ha noi\nHai Ba Trung\nDong Da\nViet Nam";
		customerCity = "Hanoi";
		customerState = "Hanoi";
		customerPIN = "123456";
		customerMobile = "0987328723";
		customerEmail = "alextran" + generateFakeNumber() + "@gmail.com";
		customerPassword = "abcdef123";
		
		startLog("Pre_Condition - New_Customer", "Prepare enviroment and data.");

		setLogINFO("Pre_Condition - Step_01: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

		setLogINFO("Pre_Condition - Step_02: Verify access to dashboard page successfully");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));

		setLogINFO("Pre_Condition - Step_03: Navigate to New Customer page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		setLogINFO("Pre_Condition - Step_04: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("Pre_Condition - Step_05: Verify New Customer page is displayed");
		verifyTrue(newCustomerPage.isPageTitleDisplayed(driver));
		
		setLogINFO("Pre_Condition - Step_06: Input to Name textbox with value: \"" + customerName + "\"");
		newCustomerPage.inputToElementByName(driver, customerName, "name");

		setLogINFO("Pre_Condition - Step_07: Choose Gender checkbox with value: \"" + customerGender + "\"");
		newCustomerPage.clickToElementByValue(driver, customerGender);

		setLogINFO("Pre_Condition - Step_08: Input to Date of Birth textbox with value: \"" + customerDOB + "\"");
		newCustomerPage.inputToElementByName(driver, customerDOB, "dob");

		setLogINFO("Pre_Condition - Step_09: Input to Name textbox with value: \"" + customerAddress + "\"");
		newCustomerPage.inputToElementByName(driver, customerAddress, "addr");

		setLogINFO("Pre_Condition - Step_10: Input to Name textbox with value: \"" + customerCity + "\"");
		newCustomerPage.inputToElementByName(driver, customerCity, "city");

		setLogINFO("Pre_Condition - Step_11: Input to Name textbox with value: \"" + customerState + "\"");
		newCustomerPage.inputToElementByName(driver, customerState, "state");

		setLogINFO("Pre_Condition - Step_12: Input to Name textbox with value: \"" + customerPIN + "\"");
		newCustomerPage.inputToElementByName(driver, customerPIN, "pinno");

		setLogINFO("Pre_Condition - Step_13: Input to Name textbox with value: \"" + customerMobile + "\"");
		newCustomerPage.inputToElementByName(driver, customerMobile, "telephoneno");

		setLogINFO("Pre_Condition - Step_14: Input to Name textbox with value: \"" + customerEmail + "\"");
		newCustomerPage.inputToElementByName(driver, customerEmail, "emailid");

		setLogINFO("Pre_Condition - Step_15: Input to Name textbox with value: \"" + customerPassword + "\"");
		newCustomerPage.inputToElementByName(driver, customerPassword, "password");

		setLogINFO("Pre_Condition - Step_16: Click to Submit button");
		newCustomerPage.clickToElementByValue(driver, "Submit");
		
		setLogINFO("Pre_Condition - Step_17: Verify Registed Success message is displayed");
		verifyTrue(newCustomerPage.isSuccessMessageDisplayed(driver));
		
		customerID = newCustomerPage.getNextCellValueByText(driver, "Customer ID");

		closeBrowserAndDriver(driver);
	}
}
