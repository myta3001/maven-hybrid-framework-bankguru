package com.bankguru.scripts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Get_System_Account_And_Create_Customer;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.LoginPageObject;
import PageObjects.bankGuru.NewCustomerPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class New_Customer extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private NewCustomerPageObject newCustomerPage;
	private String username, password, fullTextData, fullNumbericText, includeNumbericText, lessCharacterPIN, includeSpecialCharText, includeFirstBlankSpaceText, includeBlankSpaceNumberic, emailIncludeSpace;
	private String customerName, customerGender, customerBirthDay, customerBirthMonth, customerBirthYear, customerAddress, customerCity, customerState, customerPIN, customerMobile, customerEmail, customerPassword;

	@BeforeClass
	@Parameters("browser")
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);

		username = Get_System_Account_And_Create_Customer.username;
		password = Get_System_Account_And_Create_Customer.password;

		fullTextData = "testData";
		fullNumbericText = "123456";
		includeNumbericText = "name123";
		lessCharacterPIN = "63453";
		includeSpecialCharText = "test@data%&";
		includeFirstBlankSpaceText = " test data";
		includeBlankSpaceNumberic = "123 67";
		emailIncludeSpace = " alex tran3301@gmail.com";

		customerName = "Alex Tran";
		customerGender = "female";

		customerBirthDay = "30";
		customerBirthMonth = "09";
		customerBirthYear = "2002";

		customerAddress = "Ha noi\nHai Ba Trung\nDong Da\nViet Nam";
		customerCity = "Hanoi";
		customerState = "Hanoi";
		customerPIN = "123456";
		customerMobile = "0987328723";
		customerEmail = "alextran" + generateFakeNumber() + "@gmail.com";
		customerPassword = "abcdef123";

		startLog("Pre_Condition - New_Customer", "Prepare enviroment and data.");

		setLogINFO("Pre_Condition - Step_01: Navigate to Login page");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		setLogINFO("Pre_Condition - Step_02: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

		setLogINFO("Pre_Condition - Step_03: Verify access to dashboard page successfully");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));
	}

	@Test
	public void New_Customer_01_Name_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating textbox Name empty");
		
		setLogINFO("New_Customer_01 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_01 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_01 - Step_03: Click to textbox Name");
		newCustomerPage.clickToElementByName(driver, "name");

		setLogINFO("New_Customer_01 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "name");

		setLogINFO("New_Customer_01 - Step_05: Verify validation message for Name textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "name"), "Customer name must not be blank");
	}

	@Test
	public void New_Customer_02_Input_Numberic_To_Name_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Name with data input");
		
		setLogINFO("New_Customer_02 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_02 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_02 - Step_03: Input to Name textbox with numberic: \"" + fullNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, fullNumbericText, "name");

		setLogINFO("New_Customer_02 - Step_04: Verify validation message for Name field");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "name"), "Numbers are not allowed");

		setLogINFO("New_Customer_02 - Step_05: Input to Name textbox including numberic name: \"" + includeNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, includeNumbericText, "name");

		setLogINFO("New_Customer_02 - Step_06: Verify validation message for Name textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "name"), "Numbers are not allowed");
	}

	@Test
	public void New_Customer_03_Input_Special_Char_To_Name_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Name with data input");
		
		setLogINFO("New_Customer_03 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_03 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_03 - Step_03: Input to Name textbox including special character: \"" + includeSpecialCharText + "\"");
		newCustomerPage.inputToElementByName(driver, includeSpecialCharText, "name");

		setLogINFO("New_Customer_03 - Step_04: Verify validation message for Name textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "name"), "Special characters are not allowed");
	}

	@Test
	public void New_Customer_04_Input_First_Blank_Space_To_Name_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Name with data input");
		
		setLogINFO("New_Customer_04 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_04 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_04 - Step_03: Input to Name textbox including first blank space character: \"" + includeFirstBlankSpaceText + "\"");
		newCustomerPage.inputToElementByName(driver, includeFirstBlankSpaceText, "name");

		setLogINFO("New_Customer_04 - Step_04: Verify validation message for Name textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "name"), "First character can not have space");
	}

	@Test
	public void New_Customer_05_Address_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating textbox Address empty");
		
		setLogINFO("New_Customer_05 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_05 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_05 - Step_03: Click to textbox Address");
		newCustomerPage.clickToElementByName(driver, "addr");

		setLogINFO("New_Customer_05 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "addr");

		setLogINFO("New_Customer_05 - Step_05: Verify validation message for Address textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "addr"), "Address Field must not be blank");
	}

	@Test
	public void New_Customer_06_Input_First_Blank_Space_To_Address_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Address with data input");
		
		setLogINFO("New_Customer_06 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_06 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_06 - Step_03: Input to Address textbox including first blank space character: \"" + includeFirstBlankSpaceText + "\"");
		newCustomerPage.inputToElementByName(driver, includeFirstBlankSpaceText, "addr");

		setLogINFO("New_Customer_06 - Step_04: Verify validation message for Address textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "addr"), "First character can not have space");
	}

	@Test
	public void New_Customer_07_City_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating City textbox empty");
		
		setLogINFO("New_Customer_07 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_07 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_07 - Step_03: Click to City textbox");
		newCustomerPage.clickToElementByName(driver, "city");

		setLogINFO("New_Customer_07 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "city");

		setLogINFO("New_Customer_07 - Step_05: Verify validation message for City textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "city"), "City Field must not be blank");
	}

	@Test
	public void New_Customer_08_Input_Numberic_To_City_Textbox(Method method) {
		startLog(method.getName(), "Verify validating City textbox with data input");
		
		setLogINFO("New_Customer_08 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_08 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_08 - Step_03: Input to City textbox with numberic: \"" + fullNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, fullNumbericText, "city");

		setLogINFO("New_Customer_08 - Step_04: Verify validation message for City field");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "city"), "Numbers are not allowed");

		setLogINFO("New_Customer_08 - Step_05: Input to City textbox including numberic name: \"" + includeNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, includeNumbericText, "city");

		setLogINFO("New_Customer_08 - Step_06: Verify validation message for City textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "city"), "Numbers are not allowed");
	}

	@Test
	public void New_Customer_09_Input_Special_Char_To_City_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox City with data input");
		
		setLogINFO("New_Customer_09 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_09 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_09 - Step_03: Input to City textbox including special character: \"" + includeSpecialCharText + "\"");
		newCustomerPage.inputToElementByName(driver, includeSpecialCharText, "city");

		setLogINFO("New_Customer_09 - Step_04: Verify validation message for City textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "city"), "Special characters are not allowed");
	}

	@Test
	public void New_Customer_10_Input_First_Blank_Space_To_City_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox City with data input");
		
		setLogINFO("New_Customer_10 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_10 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_10 - Step_03: Input to City textbox including first blank space character: \"" + includeFirstBlankSpaceText + "\"");
		newCustomerPage.inputToElementByName(driver, includeFirstBlankSpaceText, "city");

		setLogINFO("New_Customer_10 - Step_04: Verify validation message for City textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "city"), "First character can not have space");
	}

	@Test
	public void New_Customer_11_State_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating State textbox empty");
		
		setLogINFO("New_Customer_11 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_11 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_11 - Step_03: Click to State textbox");
		newCustomerPage.clickToElementByName(driver, "state");

		setLogINFO("New_Customer_11 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "state");

		setLogINFO("New_Customer_11 - Step_05: Verify validation message for State textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "state"), "State must not be blank");
	}

	@Test
	public void New_Customer_12_Input_Numberic_To_State_Textbox(Method method) {
		startLog(method.getName(), "Verify validating State textbox with data input");
		
		setLogINFO("New_Customer_12 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_12 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_12 - Step_03: Input to State textbox with numberic: \"" + fullNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, fullNumbericText, "state");

		setLogINFO("New_Customer_12 - Step_04: Verify validation message for State field");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "state"), "Numbers are not allowed");

		setLogINFO("New_Customer_12 - Step_05: Input to State textbox including numberic name: \"" + includeNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, includeNumbericText, "state");

		setLogINFO("New_Customer_12 - Step_06: Verify validation message for State textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "state"), "Numbers are not allowed");
	}

	@Test
	public void New_Customer_13_Input_Special_Char_To_State_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox State with data input");
		
		setLogINFO("New_Customer_13 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_13 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_13 - Step_03: Input to State textbox including special character: \"" + includeSpecialCharText + "\"");
		newCustomerPage.inputToElementByName(driver, includeSpecialCharText, "state");

		setLogINFO("New_Customer_13 - Step_04: Verify validation message for State textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "state"), "Special characters are not allowed");
	}

	@Test
	public void New_Customer_14_Input_First_Blank_Space_To_State_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox State with data input");
		
		setLogINFO("New_Customer_14 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_14 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_14 - Step_03: Input to State textbox including first blank space character: \"" + includeFirstBlankSpaceText + "\"");
		newCustomerPage.inputToElementByName(driver, includeFirstBlankSpaceText, "state");

		setLogINFO("New_Customer_14 - Step_04: Verify validation message for State textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "state"), "First character can not have space");
	}

	@Test
	public void New_Customer_15_Input_Text_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating PIN textbox with data input");
		
		setLogINFO("New_Customer_15 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_15 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_15 - Step_03: Input to PIN textbox with text only: \"" + fullTextData + "\"");
		newCustomerPage.inputToElementByName(driver, fullTextData, "pinno");

		setLogINFO("New_Customer_15 - Step_04: Verify validation message for PIN field");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Characters are not allowed");

		setLogINFO("New_Customer_15 - Step_05: Input to PIN textbox including numberic text: \"" + includeNumbericText + "\"");
		newCustomerPage.inputToElementByName(driver, includeNumbericText, "pinno");

		setLogINFO("New_Customer_15 - Step_06: Verify validation message for PIN textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Characters are not allowed");
	}

	@Test
	public void New_Customer_16_PIN_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating PIN textbox empty");
		
		setLogINFO("New_Customer_16 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_16 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_16 - Step_03: Click to PIN textbox");
		newCustomerPage.clickToElementByName(driver, "pinno");

		setLogINFO("New_Customer_16 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "pinno");

		setLogINFO("New_Customer_16 - Step_05: Verify validation message for PIN textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "PIN Code must not be blank");
	}

	@Test
	public void New_Customer_17_Input_Limited_Numberic_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating PIN textbox with data input");
		
		setLogINFO("New_Customer_17 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_17 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_17 - Step_03: Input to PIN textbox with less than 6 numberic: \"" + lessCharacterPIN + "\"");
		newCustomerPage.inputToElementByName(driver, lessCharacterPIN, "pinno");

		setLogINFO("New_Customer_17 - Step_04: Verify validation message for PIN field");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "PIN Code must have 6 Digits");
	}

	@Test
	public void New_Customer_18_Input_Special_Char_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox PIN with data input");
		
		setLogINFO("New_Customer_18 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_18 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_18 - Step_03: Input to PIN textbox including special character: \"" + includeSpecialCharText + "\"");
		newCustomerPage.inputToElementByName(driver, includeSpecialCharText, "pinno");

		setLogINFO("New_Customer_18 - Step_04: Verify validation message for PIN textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Special characters are not allowed");
	}

	@Test
	public void New_Customer_19_Input_First_Blank_Space_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox PIN with data input");
		
		setLogINFO("New_Customer_19 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_19 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_19 - Step_03: Input to PIN textbox including first blank space character: \"" + includeFirstBlankSpaceText + "\"");
		newCustomerPage.inputToElementByName(driver, includeFirstBlankSpaceText, "pinno");

		setLogINFO("New_Customer_19 - Step_04: Verify validation message for PIN textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "First character can not have space");
	}

	@Test
	public void New_Customer_20_Input_Including_Space_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox PIN with data input");
		
		setLogINFO("New_Customer_20 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_20 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_20 - Step_03: Input to PIN textbox including blank space character: \"" + includeBlankSpaceNumberic + "\"");
		newCustomerPage.inputToElementByName(driver, includeBlankSpaceNumberic, "pinno");

		setLogINFO("New_Customer_20 - Step_04: Verify validation message for PIN textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Characters are not allowed");
	}

	@Test
	public void New_Customer_21_Mobile_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating Mobile textbox empty");
		
		setLogINFO("New_Customer_21 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_21 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_21 - Step_03: Click to Mobile textbox");
		newCustomerPage.clickToElementByName(driver, "telephoneno");

		setLogINFO("New_Customer_21 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "telephoneno");

		setLogINFO("New_Customer_21 - Step_05: Verify validation message for Mobile textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "telephoneno"), "Mobile no must not be blank");
	}

	@Test
	public void New_Customer_22_Input_First_Blank_Space_To_Mobile_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Mobile with data input");
		
		setLogINFO("New_Customer_22 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_22 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_22 - Step_03: Input to Mobile textbox including first blank space character: \"" + includeFirstBlankSpaceText + "\"");
		newCustomerPage.inputToElementByName(driver, includeFirstBlankSpaceText, "telephoneno");

		setLogINFO("New_Customer_22 - Step_04: Verify validation message for Mobile textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "telephoneno"), "First character can not have space");
	}

	@Test
	public void New_Customer_23_Input_Including_Space_To_Mobile_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Mobile with data input");
		
		setLogINFO("New_Customer_23 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_23 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_23 - Step_03: Input to Mobile textbox including blank space character: \"" + includeBlankSpaceNumberic + "\"");
		newCustomerPage.inputToElementByName(driver, includeBlankSpaceNumberic, "telephoneno");

		setLogINFO("New_Customer_23 - Step_04: Verify validation message for Mobile textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "telephoneno"), "Characters are not allowed");
	}

	@Test
	public void New_Customer_24_Input_Special_Char_To_Mobile_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Mobile with data input");
		
		setLogINFO("New_Customer_24 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_24 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_24 - Step_03: Input to Mobile textbox including special character: \"" + includeSpecialCharText + "\"");
		newCustomerPage.inputToElementByName(driver, includeSpecialCharText, "telephoneno");

		setLogINFO("New_Customer_24 - Step_04: Verify validation message for Mobile textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "telephoneno"), "Special characters are not allowed");
	}

	@Test
	public void New_Customer_25_Email_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating Email textbox empty");
		
		setLogINFO("New_Customer_25 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_25 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_25 - Step_03: Click to Email textbox");
		newCustomerPage.clickToElementByName(driver, "emailid");

		setLogINFO("New_Customer_25 - Step_04: press TAB key to focus next field");
		newCustomerPage.pressTabToElementByName(driver, "emailid");

		setLogINFO("New_Customer_25 - Step_05: Verify validation message for Email textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "emailid"), "Email-ID must not be blank");
	}

	@Test(dataProvider = "invalid_email")
	public void New_Customer_26_Input_Invalid_To_Email_Textbox(String invalid_email, Method method) {
		startLog(method.getName(), "Verify validating textbox Email with data input");
		
		setLogINFO("New_Customer_26 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_26 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_26 - Step_03: Input to Email textbox with an invalid email: \"" + invalid_email + "\"");
		newCustomerPage.inputToElementByName(driver, invalid_email, "emailid");

		setLogINFO("New_Customer_26 - Step_04: Verify validation message for Email textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "emailid"), "Email-ID is not valid");
	}

	@Test
	public void New_Customer_27_Input_First_Blank_Space_To_Email_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Email with data input");
		
		setLogINFO("New_Customer_27 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_27 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_27 - Step_03: Input to Email textbox including blank space character: \"" + emailIncludeSpace + "\"");
		newCustomerPage.inputToElementByName(driver, emailIncludeSpace, "emailid");

		setLogINFO("New_Customer_27 - Step_04: Verify validation message for Email textbox");
		verifyEquals(newCustomerPage.getValidateMessageByElementName(driver, "emailid"), "First character can not have space");
	}

	@Test
	public void New_Customer_28_Input_Valid_Info_To_New_Customer_Form(Method method) {
		startLog(method.getName(), "Input valid information into New Customer form and submit");
		
		setLogINFO("New_Customer_28 - Step_01: Navigate to New Customer Page");
		newCustomerPage = navigateToNewCustomerPage(driver);

		setLogINFO("New_Customer_28 - Step_02: Check if exsiting Ads popup and close it.");
		newCustomerPage.closeAdsPopup(driver);

		setLogINFO("New_Customer_28 - Step_03: Input to Name textbox with value: \"" + customerName + "\"");
		newCustomerPage.inputToElementByName(driver, customerName, "name");

		setLogINFO("New_Customer_28 - Step_04: Choose Gender checkbox with value: \"" + customerGender + "\"");
		newCustomerPage.clickToElementByValue(driver, customerGender);

		setLogINFO("New_Customer_28 - Step_05: Input to Date of Birth textbox with value: \"" + customerBirthDay + "-" + customerBirthMonth + "-" + customerBirthYear + "\"");
		newCustomerPage.inputToDatePickerByName(driver, customerBirthDay, customerBirthMonth, customerBirthYear, "dob");

		setLogINFO("New_Customer_28 - Step_06: Input to Name textbox with value: \"" + customerAddress + "\"");
		newCustomerPage.inputToElementByName(driver, customerAddress, "addr");

		setLogINFO("New_Customer_28 - Step_07: Input to Name textbox with value: \"" + customerCity + "\"");
		newCustomerPage.inputToElementByName(driver, customerCity, "city");

		setLogINFO("New_Customer_28 - Step_08: Input to Name textbox with value: \"" + customerState + "\"");
		newCustomerPage.inputToElementByName(driver, customerState, "state");

		setLogINFO("New_Customer_28 - Step_09: Input to Name textbox with value: \"" + customerPIN + "\"");
		newCustomerPage.inputToElementByName(driver, customerPIN, "pinno");

		setLogINFO("New_Customer_28 - Step_10: Input to Name textbox with value: \"" + customerMobile + "\"");
		newCustomerPage.inputToElementByName(driver, customerMobile, "telephoneno");

		setLogINFO("New_Customer_28 - Step_11: Input to Name textbox with value: \"" + customerEmail + "\"");
		newCustomerPage.inputToElementByName(driver, customerEmail, "emailid");

		setLogINFO("New_Customer_28 - Step_12: Input to Name textbox with value: \"" + customerPassword + "\"");
		newCustomerPage.inputToElementByName(driver, customerPassword, "password");

		setLogINFO("New_Customer_28 - Step_13: Click to Submit button");
		newCustomerPage.clickToElementByValue(driver, "Submit");
//		sleepInSecond(2);
//		newCustomerPage.acceptAlert(driver);
		setLogINFO("New_Customer_28 - Step_14: Verify Registed Success message is displayed");
		verifyTrue(newCustomerPage.isSuccessMessageDisplayed(driver));

		setLogINFO("New_Customer_28 - Step_15: Store new customerID into statis variable for using later");
		Get_System_Account_And_Create_Customer.customerID = newCustomerPage.getNextCellValueByText(driver, "Customer ID");

		setLogINFO("New_Customer_28 - Step_16: Verify registed Customer Name");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Customer Name"), customerName);

		setLogINFO("New_Customer_28 - Step_17: Verify registed Gender");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Gender"), customerGender);

		setLogINFO("New_Customer_28 - Step_18: Verify registed Birthdate");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Birthdate"), customerBirthYear + "-" + customerBirthMonth + "-" + customerBirthDay);

		String registedAddress = customerAddress.replaceAll("\n", " ");
		setLogINFO("New_Customer_28 - Step_19: Verify registed Address");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Address"), registedAddress);

		setLogINFO("New_Customer_28 - Step_20: Verify registed City");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "City"), customerCity);

		setLogINFO("New_Customer_28 - Step_21: Verify registed State");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "State"), customerState);

		setLogINFO("New_Customer_28 - Step_22: Verify registed Pin");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Pin"), customerPIN);

		setLogINFO("New_Customer_28 - Step_23: Verify registed Mobile No.");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Mobile No."), customerMobile);

		setLogINFO("New_Customer_28 - Step_24: Verify registed Email");
		verifyEquals(newCustomerPage.getNextCellValueByText(driver, "Email"), customerEmail);
	}

	@DataProvider(name = "invalid_email")
	public Object[][] IncorrectFormatEmail() {
		return new Object[][] { { "alex" }, { "alex3001@" }, { "alex3100@gmail" }, { "alex3010gmail.com" } };
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
