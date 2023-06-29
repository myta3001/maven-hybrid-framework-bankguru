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
import PageObjects.bankGuru.EditCustomerPageObject;
import PageObjects.bankGuru.LoginPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class Edit_Customer extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private EditCustomerPageObject editCustomerPage;
	private String username, password, customerID;
	private String fullTextData, fullNumbericText, includeNumbericText, lessCharacterPIN, includeSpecialCharText;
	private String customerAddress, customerCity, customerState, customerPIN, customerMobile, customerEmail;

	@BeforeClass
	@Parameters("browser")
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		username = Get_System_Account_And_Create_Customer.username;
		password = Get_System_Account_And_Create_Customer.password;
		customerID = Get_System_Account_And_Create_Customer.customerID;

		fullTextData = "testData";
		fullNumbericText = "123456";
		includeNumbericText = "name123";
		lessCharacterPIN = "63453";
		includeSpecialCharText = "test@data%&";

		customerAddress = "phuong 10, Quan Phu Nhuan";
		customerCity = "Ho Chi Minh";
		customerState = "mien Nam";
		customerPIN = "987654";
		customerMobile = "0543587537";
		customerEmail = "mytran" + generateFakeNumber() + "@hotmail.com";

		startLog("Pre_Condition - Edit_Customer", "Prepare enviroment and data.");

		setLogINFO("Pre_Condition - Step_01: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

		setLogINFO("Pre_Condition - Step_02: Verify access to dashboard page successfully");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));
	}

	@Test
	public void Edit_Customer_01_CustomerID_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating CustomerID textbox empty");
		
		setLogINFO("Edit_Customer_01 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_01 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);

		setLogINFO("Edit_Customer_01 - Step_03: Click to CustomerID textbox");
		editCustomerPage.clickToElementByName(driver, "cusid");

		setLogINFO("Edit_Customer_01 - Step_04: press TAB key to focus next field");
		editCustomerPage.pressTabToElementByName(driver, "cusid");

		setLogINFO("Edit_Customer_01 - Step_05: Verify validation message for CustomerID textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Customer ID is required");
	}

	@Test
	public void Edit_Customer_02_Input_Not_Numberic_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating CustomerID textbox with data input");

		setLogINFO("Edit_Customer_02 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_02 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);

		setLogINFO("Edit_Customer_02 - Step_03: Input to CustomerID textbox including numberic name: \"" + includeNumbericText + "\"");
		editCustomerPage.inputToElementByName(driver, includeNumbericText, "cusid");

		setLogINFO("Edit_Customer_02 - Step_04: Verify validation message for CustomerID textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Characters are not allowed");
	}

	@Test
	public void Edit_Customer_03_Input_Special_Char_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("Edit_Customer_03 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_03 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);

		setLogINFO("Edit_Customer_03 - Step_03: Input to CustomerID textbox including special character: \"" + includeSpecialCharText + "\"");
		editCustomerPage.inputToElementByName(driver, includeSpecialCharText, "cusid");

		setLogINFO("Edit_Customer_03 - Step_04: Verify validation message for CustomerID textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Special characters are not allowed");
	}

	@Test
	public void Edit_Customer_04_Input_Valid_CustomerID(Method method) {
		startLog(method.getName(), "Input a valid Customer ID and submit form");

		setLogINFO("Edit_Customer_04 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_04 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);

		setLogINFO("Edit_Customer_04 - Step_03: Input to CustomerID textbox with a valid CustomerID: \"" + customerID + "\"");
		editCustomerPage.inputToElementByName(driver, customerID, "cusid");
		
		setLogINFO("Edit_Customer_04 - Step_04: Click to Submit button");
		editCustomerPage.clickToElementByValue(driver, "Submit");
		
		setLogINFO("Edit_Customer_04 - Step_05: Verify Edit Customer page is displayed");
		verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Customer"));
	}
	
	@Test
	public void Edit_Customer_05_Address_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating textbox Address empty");
		
		setLogINFO("Edit_Customer_05 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_05 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_05 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);
		
		setLogINFO("Edit_Customer_05 - Step_04: Click to Address textbox");
		editCustomerPage.inputToElementByName(driver, "", "addr");
		
		setLogINFO("Edit_Customer_05 - Step_05: Verify validation message for Address textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "addr"), "Address Field must not be blank");
	}

	@Test
	public void Edit_Customer_06_City_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating City textbox empty");

		setLogINFO("Edit_Customer_06 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_06 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_06 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_06 - Step_04: Clear the City textbox");
		editCustomerPage.inputToElementByName(driver, "", "city");

		setLogINFO("Edit_Customer_06 - Step_05: Verify validation message for City textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "city"), "City Field must not be blank");
	}

	@Test
	public void Edit_Customer_07_Input_Numberic_To_City_Textbox(Method method) {
		startLog(method.getName(), "Verify validating City textbox with data input");

		setLogINFO("Edit_Customer_07 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_07 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_07 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_07 - Step_04: Input to City textbox with numberic: \"" + fullNumbericText + "\"");
		editCustomerPage.inputToElementByName(driver, fullNumbericText, "city");

		setLogINFO("Edit_Customer_07 - Step_05: Verify validation message for City field");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "city"), "Numbers are not allowed");

		setLogINFO("Edit_Customer_07 - Step_06: Input to City textbox including numberic name: \"" + includeNumbericText + "\"");
		editCustomerPage.inputToElementByName(driver, includeNumbericText, "city");

		setLogINFO("Edit_Customer_07 - Step_07: Verify validation message for City textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "city"), "Numbers are not allowed");
	}
	
	@Test
	public void Edit_Customer_08_Input_Special_Char_To_City_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox City with data input");

		setLogINFO("Edit_Customer_08 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_08 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_08 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_08 - Step_04: Input to City textbox including special character: \"" + includeSpecialCharText + "\"");
		editCustomerPage.inputToElementByName(driver, includeSpecialCharText, "city");

		setLogINFO("Edit_Customer_08 - Step_05: Verify validation message for City textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "city"), "Special characters are not allowed");
	}

	@Test
	public void Edit_Customer_09_State_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating State textbox empty");

		setLogINFO("Edit_Customer_09 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_09 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_09 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_09 - Step_04: Clear the State textbox");
		editCustomerPage.inputToElementByName(driver, "", "state");

		setLogINFO("Edit_Customer_09 - Step_05: Verify validation message for State textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "state"), "State must not be blank");
	}
	
	@Test
	public void Edit_Customer_10_Input_Numberic_To_State_Textbox(Method method) {
		startLog(method.getName(), "Verify validating State textbox with data input");

		setLogINFO("Edit_Customer_10 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_10 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_10 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_10 - Step_04: Input to State textbox with numberic: \"" + fullNumbericText + "\"");
		editCustomerPage.inputToElementByName(driver, fullNumbericText, "state");

		setLogINFO("Edit_Customer_10 - Step_05: Verify validation message for State field");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "state"), "Numbers are not allowed");

		setLogINFO("Edit_Customer_10 - Step_06: Input to State textbox including numberic name: \"" + includeNumbericText + "\"");
		editCustomerPage.inputToElementByName(driver, includeNumbericText, "state");

		setLogINFO("Edit_Customer_10 - Step_07: Verify validation message for State textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "state"), "Numbers are not allowed");
	}

	@Test
	public void Edit_Customer_11_Input_Special_Char_To_State_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox State with data input");

		setLogINFO("Edit_Customer_11 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_11 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_11 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_11 - Step_04: Input to State textbox including special character: \"" + includeSpecialCharText + "\"");
		editCustomerPage.inputToElementByName(driver, includeSpecialCharText, "state");

		setLogINFO("Edit_Customer_11 - Step_05: Verify validation message for State textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "state"), "Special characters are not allowed");
	}
	
	@Test
	public void Edit_Customer_12_Input_Text_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating PIN textbox with data input");

		setLogINFO("Edit_Customer_12 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_12 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_12 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_12 - Step_04: Input to PIN textbox with text only: \"" + fullTextData + "\"");
		editCustomerPage.inputToElementByName(driver, fullTextData, "pinno");

		setLogINFO("Edit_Customer_12 - Step_05: Verify validation message for PIN field");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Characters are not allowed");

		setLogINFO("Edit_Customer_12 - Step_06: Input to PIN textbox including numberic text: \"" + includeNumbericText + "\"");
		editCustomerPage.inputToElementByName(driver, includeNumbericText, "pinno");

		setLogINFO("Edit_Customer_12 - Step_07: Verify validation message for PIN textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Characters are not allowed");
	}

	@Test
	public void Edit_Customer_13_PIN_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating PIN textbox empty");

		setLogINFO("Edit_Customer_13 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_13 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_13 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);
		
		setLogINFO("Edit_Customer_13 - Step_04: Clear the PIN textbox");
		editCustomerPage.inputToElementByName(driver, "", "pinno");

		setLogINFO("Edit_Customer_13 - Step_05: Verify validation message for PIN textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "pinno"), "PIN Code must not be blank");
	}

	@Test
	public void Edit_Customer_14_Input_Limited_Numberic_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating PIN textbox with data input");

		setLogINFO("Edit_Customer_14 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_14 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_14 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_14 - Step_04: Input to PIN textbox with less than 6 numberic: \"" + lessCharacterPIN + "\"");
		editCustomerPage.inputToElementByName(driver, lessCharacterPIN, "pinno");

		setLogINFO("Edit_Customer_14 - Step_05: Verify validation message for PIN field");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "pinno"), "PIN Code must have 6 Digits");
	}

	@Test
	public void Edit_Customer_15_Input_Special_Char_To_PIN_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox PIN with data input");

		setLogINFO("Edit_Customer_15 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_15 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_15 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_15 - Step_04: Input to PIN textbox including special character: \"" + includeSpecialCharText + "\"");
		editCustomerPage.inputToElementByName(driver, includeSpecialCharText, "pinno");

		setLogINFO("Edit_Customer_15 - Step_05: Verify validation message for PIN textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "pinno"), "Special characters are not allowed");
	}

	@Test
	public void Edit_Customer_16_Mobile_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating Mobile textbox empty");

		setLogINFO("Edit_Customer_16 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_16 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_16 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_16 - Step_04: Clear the Mobile textbox");
		editCustomerPage.inputToElementByName(driver, "", "telephoneno");

		setLogINFO("Edit_Customer_16 - Step_05: Verify validation message for Mobile textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "telephoneno"), "Mobile no must not be blank");
	}

	@Test
	public void Edit_Customer_17_Input_Special_Char_To_Mobile_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox Mobile with data input");

		setLogINFO("Edit_Customer_17 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_17 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_17 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_17 - Step_04: Input to Mobile textbox including special character: \"" + includeSpecialCharText + "\"");
		editCustomerPage.inputToElementByName(driver, includeSpecialCharText, "telephoneno");

		setLogINFO("Edit_Customer_17 - Step_05: Verify validation message for Mobile textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "telephoneno"), "Special characters are not allowed");
	}

	@Test
	public void Edit_Customer_18_Email_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating Email textbox empty");

		setLogINFO("Edit_Customer_18 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_18 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_18 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_18 - Step_04: Clear the Email textbox");
		editCustomerPage.inputToElementByName(driver, "", "emailid");

		setLogINFO("Edit_Customer_18 - Step_05: Verify validation message for Email textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "emailid"), "Email-ID must not be blank");
	}

	@Test(dataProvider = "invalid_email")
	public void Edit_Customer_19_Input_Invalid_To_Email_Textbox(String invalid_email, Method method) {
		startLog(method.getName(), "Verify validating textbox Email with data input");

		setLogINFO("Edit_Customer_19 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_19 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_19 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_19 - Step_04: Input to Email textbox with an invalid email: \"" + invalid_email + "\"");
		editCustomerPage.inputToElementByName(driver, invalid_email, "emailid");

		setLogINFO("Edit_Customer_19 - Step_05: Verify validation message for Email textbox");
		verifyEquals(editCustomerPage.getValidateMessageByElementName(driver, "emailid"), "Email-ID is not valid");
	}

	@Test
	public void Edit_Customer_20_Input_Valid_Info_To_Edit_Customer_Form(Method method) {
		startLog(method.getName(), "Input valid information into New Customer form and verify");

		setLogINFO("Edit_Customer_20 - Step_01: Navigate to Edit Customer Page");
		editCustomerPage = navigateToEditCustomerPage(driver);

		setLogINFO("Edit_Customer_20 - Step_02: Check if exsiting Ads popup and close it.");
		editCustomerPage.closeAdsPopup(driver);
		
		setLogINFO("Edit_Customer_20 - Step_03: Accept the browser Alert");
		editCustomerPage.acceptAlert(driver);

		setLogINFO("Edit_Customer_20 - Step_02: Input to Address textbox with value: \"" + customerAddress + "\"");
		editCustomerPage.inputToElementByName(driver, customerAddress, "addr");

		setLogINFO("Edit_Customer_20 - Step_04: Input to City textbox with value: \"" + customerCity + "\"");
		editCustomerPage.inputToElementByName(driver, customerCity, "city");

		setLogINFO("Edit_Customer_20 - Step_05: Input to State textbox with value: \"" + customerState + "\"");
		editCustomerPage.inputToElementByName(driver, customerState, "state");

		setLogINFO("Edit_Customer_20 - Step_06: Input to PIN textbox with value: \"" + customerPIN + "\"");
		editCustomerPage.inputToElementByName(driver, customerPIN, "pinno");

		setLogINFO("Edit_Customer_20 - Step_07: Input to Mobile textbox with value: \"" + customerMobile + "\"");
		editCustomerPage.inputToElementByName(driver, customerMobile, "telephoneno");

		setLogINFO("Edit_Customer_20 - Step_08: Input to Email textbox with value: \"" + customerEmail + "\"");
		editCustomerPage.inputToElementByName(driver, customerEmail, "emailid");

		setLogINFO("Edit_Customer_20 - Step_09: Click to Submit button");
		editCustomerPage.clickToElementByValue(driver, "Submit");
		
		setLogINFO("Edit_Customer_20 - Step_10: Accept Alert and verify redirecting to Edit Customer Form");
		editCustomerPage.acceptAlert(driver);
		verifyTrue(editCustomerPage.isPageTitleDisplayed(driver, "Edit Customer Form"));
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
