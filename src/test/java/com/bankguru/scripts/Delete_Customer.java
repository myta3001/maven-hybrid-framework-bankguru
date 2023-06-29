package com.bankguru.scripts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Get_System_Account_And_Create_Customer;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.DeleteCustomerPageObject;
import PageObjects.bankGuru.LoginPageObject;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class Delete_Customer extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	private String username, password, customerID;
	private String includeNumbericText, includeSpecialCharText, includeFirstBlankSpace, includeBlankSpace;

	@Parameters("browser")
	@BeforeClass
	public void BeforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANK_GURU_LOGIN_LINK);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		username = Get_System_Account_And_Create_Customer.username;
		password = Get_System_Account_And_Create_Customer.password;
		customerID = Get_System_Account_And_Create_Customer.customerID;

		includeNumbericText = "name123";
		includeSpecialCharText = "1234@796%&";
		includeFirstBlankSpace = " 423454";
		includeBlankSpace = "123 67";

		startLog("Pre_Condition - Delete_Customer", "Prepare enviroment and data.");

		setLogINFO("Pre_Condition - Step_01: Login to Bank Guru System with account: \"" + username + "/" + password + "\"");
		dashboardPage = loginPage.loginToSystem(driver, username, password);

		setLogINFO("Pre_Condition - Step_02: Verify access to dashboard page successfully");
		verifyTrue(dashboardPage.isIdInfoDisplayed(driver, username));

		setLogINFO("Pre_Condition - Step_03: Navigate to Delete Customer page by clicking to the menu");
		dashboardPage.clickToLeftMenuByText(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);

		setLogINFO("Pre_Condition - Step_04: Check if exsiting Ads popup and close it.");
		deleteCustomerPage.closeAdsPopup(driver);
	}

	@Test
	public void Delete_Customer_01_CustomerID_Textbox_Empty(Method method) {
		startLog(method.getName(), "Verify validating CustomerID textbox empty");

		setLogINFO("Delete_Customer_01 - Step_01: Click to CustomerID textbox");
		deleteCustomerPage.clickToElementByName(driver, "cusid");

		setLogINFO("Delete_Customer_01 - Step_02: press TAB key to focus next field");
		deleteCustomerPage.pressTabToElementByName(driver, "cusid");

		setLogINFO("Delete_Customer_01 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(deleteCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Customer ID is required");
	}

	@Test
	public void Delete_Customer_02_Input_Not_Numberic_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating CustomerID textbox with data input");

		setLogINFO("Delete_Customer_02 - Step_01: Refresh page");
		deleteCustomerPage.refreshCurrentPage(driver);

		setLogINFO("Delete_Customer_02 - Step_02: Input to CustomerID textbox including numberic name: \"" + includeNumbericText + "\"");
		deleteCustomerPage.inputToElementByName(driver, includeNumbericText, "cusid");

		setLogINFO("Delete_Customer_02 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(deleteCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Characters are not allowed");
	}

	@Test
	public void Delete_Customer_03_Input_Special_Char_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("Delete_Customer_03 - Step_01: Refresh page");
		deleteCustomerPage.refreshCurrentPage(driver);

		setLogINFO("Delete_Customer_03 - Step_02: Input to CustomerID textbox including special character: \"" + includeSpecialCharText + "\"");
		deleteCustomerPage.inputToElementByName(driver, includeSpecialCharText, "cusid");

		setLogINFO("Delete_Customer_03 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(deleteCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Special characters are not allowed");
	}

	@Test
	public void Delete_Customer_04_Input_First_Blank_Space_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("Delete_Customer_04 - Step_01: Refresh page");
		deleteCustomerPage.refreshCurrentPage(driver);

		setLogINFO("Delete_Customer_04 - Step_02: Input to CustomerID textbox including first blank space character: \"" + includeFirstBlankSpace + "\"");
		deleteCustomerPage.inputToElementByName(driver, includeFirstBlankSpace, "cusid");

		setLogINFO("Delete_Customer_04 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(deleteCustomerPage.getValidateMessageByElementName(driver, "cusid"), "First character can not have space");
	}

	@Test
	public void Delete_Customer_05_Input_Including_Space_To_CustomerID_Textbox(Method method) {
		startLog(method.getName(), "Verify validating textbox CustomerID with data input");

		setLogINFO("Delete_Customer_05 - Step_01: Refresh page");
		deleteCustomerPage.refreshCurrentPage(driver);

		setLogINFO("Delete_Customer_05 - Step_02: Input to CustomerID textbox including blank space character: \"" + includeBlankSpace + "\"");
		deleteCustomerPage.inputToElementByName(driver, includeBlankSpace, "cusid");

		setLogINFO("Delete_Customer_05 - Step_03: Verify validation message for CustomerID textbox");
		verifyEquals(deleteCustomerPage.getValidateMessageByElementName(driver, "cusid"), "Characters are not allowed");
	}

	@Test
	public void Delete_Customer_06_Input_Valid_CustomerID(Method method) {
		startLog(method.getName(), "Input a valid Customer ID and submit form");

		setLogINFO("Delete_Customer_06 - Step_01: Refresh page");
		deleteCustomerPage.refreshCurrentPage(driver);

		setLogINFO("Delete_Customer_06 - Step_02: Input to CustomerID textbox with a valid CustomerID: \"" + customerID + "\"");
		deleteCustomerPage.inputToElementByName(driver, customerID, "cusid");

		setLogINFO("Delete_Customer_06 - Step_03: Click to Submit button");
		deleteCustomerPage.clickToElementByValue(driver, "Submit");

		setLogINFO("Delete_Customer_06 - Step_04: Accept the browser Alert");
		deleteCustomerPage.acceptAlert(driver);
		deleteCustomerPage.acceptAlert(driver);

		setLogINFO("Delete_Customer_06 - Step_05: Try to delete the customerID again and verify the alert");
		deleteCustomerPage.inputToElementByName(driver, customerID, "cusid");
		deleteCustomerPage.clickToElementByValue(driver, "Submit");
		deleteCustomerPage.acceptAlert(driver);
		
		verifyTrue(deleteCustomerPage.isCustomerIDNotExist(driver));
		deleteCustomerPage.acceptAlert(driver);
	}

	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		closeBrowserAndDriver(driver);
	}
}
