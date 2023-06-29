package commons;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import pageUIs.commons.BasePageUI;
import reportConfig.ExtentTestManager;

public class BasePage {

	private long longTimeDuration = GlobalConstants.LONG_TIMEOUT;
	private Duration longTimeout = Duration.ofSeconds(longTimeDuration);
	private long shortTimeDuration = GlobalConstants.SHORT_TIMEOUT;
	private Duration shortTimeout = Duration.ofSeconds(shortTimeDuration);

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		Alert isAlertDisplayed = explicitWait.until(ExpectedConditions.alertIsPresent());
		explicitWait = new WebDriverWait(driver, longTimeout);
		return isAlertDisplayed;
	}

	public void acceptAlert(WebDriver driver) {
		try {
			waitForAlertPresence(driver).accept();
		} catch (Throwable e) {
			ExtentTestManager.getTest().log(Status.INFO, "<p style=\"color:yellow !important;\">" + e.toString() + "</p>");
		}
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String textToAlert) {
		waitForAlertPresence(driver).sendKeys(textToAlert);
	}

	protected void switchWindowById(WebDriver driver, String windowId) {
		driver.switchTo().window(windowId);
	}

	protected void switchWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> windowIdSet = driver.getWindowHandles();

		for (String windowId : windowIdSet) {
			driver.switchTo().window(windowId);
			if (getPageTitle(driver).equals(windowTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String windowParentId) {
		Set<String> windowIdSet = driver.getWindowHandles();
		for (String windowId : windowIdSet) {

			if (!windowId.equals(windowParentId)) {
				driver.close();
			}
			driver.switchTo().window(windowId);
		}
		driver.switchTo().window(windowParentId);
	}

	private By getByLocator(String locator, String... dynamicLocatorValues) {
		String locatorType = locator.substring(0, locator.indexOf("=")).strip().toLowerCase();
		String locatorPath = locator.substring(locator.indexOf("=") + 1, locator.length()).strip();

		By byElement = null;

		switch (locatorType) {
		case "xpath":
			byElement = By.xpath(String.format(locatorPath, (Object[]) dynamicLocatorValues));
			break;
		case "css":
			byElement = By.cssSelector(String.format(locatorPath, (Object[]) dynamicLocatorValues));
			break;
		case "id":
			byElement = By.id(String.format(locatorPath, (Object[]) dynamicLocatorValues));
			break;
		case "class":
			byElement = By.className(String.format(locatorPath, (Object[]) dynamicLocatorValues));
			break;
		case "name":
			byElement = By.name(String.format(locatorPath, (Object[]) dynamicLocatorValues));
			break;
		default:
			throw new RuntimeException("The locator string is invalid format.");
		}
		return byElement;
	}

	private WebElement getWebElement(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebElement element = null;
		try {
			element = driver.findElement(getByLocator(locator, dynamicLocatorValues));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return element;
	}

	protected List<WebElement> getListWebElements(WebDriver driver, String locator, String... dynamicLocatorValues) {
		return driver.findElements(getByLocator(locator, dynamicLocatorValues));
	}

	public void clickToElement(WebDriver driver, String locator, String... dynamicLocatorValues) {
		getWebElement(driver, locator, dynamicLocatorValues).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locator, String textToElement, String... dynamicLocator) {
		WebElement element = getWebElement(driver, locator, dynamicLocator);
		element.clear();
		element.sendKeys(textToElement);
	}

	public String getElementText(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, locator, dynamicLocator).getText();
	}

	protected List<String> getAllElementText(WebDriver driver, String locator, String... dynamicLocator) {
		List<String> allElementText = new ArrayList<String>();
		List<WebElement> allElements = getListWebElements(driver, locator, dynamicLocator);

		for (WebElement element : allElements) {
			allElementText.add(element.getText());
		}
		return allElementText;
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, locator, dynamicLocator));
		select.selectByVisibleText(textItem);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locator, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, locator, dynamicLocator));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDrodownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	protected List<WebElement> waitForElementPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String itemText) {
		clickToElement(driver, parentLocator);
		List<WebElement> dropdownItems = waitForElementPresence(driver, childLocator);
		for (WebElement item : dropdownItems) {
			if (item.getText().trim().equals(itemText)) {
				clickToElementByJs(driver, item);
				break;
			}
		}
	}

	protected String getELementAttribute(WebDriver driver, String locator, String attributeName, String... dynamicLocator) {
		return getWebElement(driver, locator, dynamicLocator).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	protected String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locator, String... dynamicLocator) {
		return getListWebElements(driver, locator, dynamicLocator).size();
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locator, String... dynamicLocator) {
		WebElement element = getWebElement(driver, locator, dynamicLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, locator, dynamicLocator).isDisplayed();
	}

	private void overrideGlobalTimeout(WebDriver driver, Duration timeout) {
		driver.manage().timeouts().implicitlyWait(timeout);
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicLocator) {
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElements(driver, locator, dynamicLocator);
		overrideGlobalTimeout(driver, longTimeout);

		if ((elements.size() == 0) || (elements.size() > 0 && !elements.get(0).isDisplayed())) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	protected boolean isElementSeleted(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	protected void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locator, String... dynamicLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator, dynamicLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicLocator) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator, dynamicLocator), key).perform();
	}

	protected void clickToElementByJs(WebDriver driver, WebElement item) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", item);
	}

	protected void clickToElementByJs(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red;");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute(" + attributeName + ");", getWebElement(driver, locator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	protected boolean isImageLoaded(WebDriver driver, String locator, String... dynamicLocatorValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator, dynamicLocatorValues));
	}

	protected void waitForElementVisible(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator, dynamicLocatorValues)));
		} catch (Throwable e) {
			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(Status.FAIL, "<p style=\"color:red !important;\">" + e.toString() + "</p>", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			ITestResult result = Reporter.getCurrentTestResult();

			VerificationFailures.getFailures().addFailureForTest(result, e);
			result.setThrowable(e);
		}
	}

	protected void waitForAllElementVisible(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator, dynamicLocatorValues)));
	}

	protected void waitForAllElementPresence(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator, dynamicLocatorValues)));
	}

	protected void waitForElementPresence(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator, dynamicLocatorValues)));
	}

	protected void waitForElementInvisible(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator, dynamicLocatorValues)));
		overrideGlobalTimeout(driver, longTimeout);
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
		overrideGlobalTimeout(driver, longTimeout);
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... dynamicLocatorValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator, dynamicLocatorValues)));
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String fileName : fileNames) {
			fullFileName += GlobalConstants.UPLOAD_FILE_FOLDER + fileName + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	protected void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getBrowserName(WebDriver driver) {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		return cap.getBrowserName().toLowerCase();
	}

	public void inputToElementByJs(WebDriver driver, String value, String elementName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].value = arguments[1];", getWebElement(driver, BasePageUI.ELEMENT_BY_NAME, elementName), value);
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(2);
	}

	public void clickToLeftMenuByText(WebDriver driver, String menuName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LEFT_MENU_BY_TEXT, menuName);
		clickToElement(driver, BasePageUI.DYNAMIC_LEFT_MENU_BY_TEXT, menuName);
	}

	public void inputToElementByName(WebDriver driver, String content, String nameValue) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
		sendkeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, content, nameValue);
	}

	public void selectItemInDropdownByName(WebDriver driver, String itemText, String nameValue) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
		selectItemInDefaultDropdown(driver, BasePageUI.ELEMENT_BY_NAME, itemText, nameValue);
	}

	public void sendKeysToElementByName(WebDriver driver, String content, String nameValue) {
		sendkeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, content, nameValue);
		WebElement element = getWebElement(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
		element.sendKeys(content);
	}

	public void clickToElementByName(WebDriver driver, String nameValue) {
		waitForElementClickable(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
		clickToElement(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
	}

	public void clickToElementByValue(WebDriver driver, String value) {
		if (value == "male") {
			value = "m";
		} else if (value == "female") {
			value = "f";
		}
		waitForElementClickable(driver, BasePageUI.ELEMENT_BY_VALUE, value);
		clickToElement(driver, BasePageUI.ELEMENT_BY_VALUE, value);
	}

	/**
	 * get text of element by name attribute
	 * 
	 * @param driver
	 * @param name
	 * @return String
	 */
	public String getElementTextByName(WebDriver driver, String name) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, name);
		return getElementText(driver, BasePageUI.ELEMENT_BY_NAME, name);
	}

	/**
	 * Get value of the next/right to a cell with text of referenced cell
	 * 
	 * @param driver
	 * @param cellText: text in the left/label cell
	 * @return String
	 */
	public String getNextCellValueByText(WebDriver driver, String cellText) {
		waitForElementVisible(driver, BasePageUI.NEXT_CELL_BY_TEXT, cellText);
		return getElementText(driver, BasePageUI.NEXT_CELL_BY_TEXT, cellText);
	}

	/**
	 * input to Date Picker field by field name attribute
	 * 
	 * @param driver
	 * @param day
	 * @param month
	 * @param year
	 * @param fieldName
	 */
	public void inputToDatePickerByName(WebDriver driver, String day, String month, String year, String fieldName) {
		String date = year + "-" + month + "-" + day;

		if (getBrowserName(driver).contains("firefox")) {
			inputToElementByName(driver, date, fieldName);
		} else {
			sendKeysToElementByName(driver, day, fieldName);
			sleepInSecond(1);
			sendKeysToElementByName(driver, month, fieldName);
			sleepInSecond(1);
			pressRightNarrowToElementByName(driver, fieldName);
			sleepInSecond(1);
			sendKeysToElementByName(driver, year, fieldName);
		}
	}

	public void pressRightNarrowToElementByName(WebDriver driver, String nameValue) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
		pressKeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, Keys.ARROW_RIGHT, nameValue);
	}

	public void pressTabToElementByName(WebDriver driver, String nameValue) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_BY_NAME, nameValue);
		pressKeyToElement(driver, BasePageUI.ELEMENT_BY_NAME, Keys.TAB, nameValue);
	}

	public String getValidateMessageByElementName(WebDriver driver, String nameValue) {
		waitForElementVisible(driver, BasePageUI.ELEMENT_VALIDATE_MESSAGE_BY_NAME, nameValue);
		return getElementText(driver, BasePageUI.ELEMENT_VALIDATE_MESSAGE_BY_NAME, nameValue);
	}

	public void closeAdsPopup(WebDriver driver) {

		if (!isElementUndisplayed(driver, BasePageUI.ADS_IFRAME)) {
			switchToIframe(driver, BasePageUI.ADS_IFRAME);

			if (isElementUndisplayed(driver, BasePageUI.CLOSE_ADS_IFRAME_BUTTON)) {

				if (!isElementUndisplayed(driver, BasePageUI.ADS_SUB_IFRAME)) {
					switchToIframe(driver, BasePageUI.ADS_SUB_IFRAME);

					if (!isElementUndisplayed(driver, BasePageUI.CLOSE_ADS_SUB_IFRAME_BUTTON)) {
						clickToElement(driver, BasePageUI.CLOSE_ADS_SUB_IFRAME_BUTTON);
					}
				}
			} else {
				clickToElement(driver, BasePageUI.CLOSE_ADS_IFRAME_BUTTON);
			}
			switchToDefaultContent(driver);
		}
	}

	public String getSubString(String original, int startIndex, int stringLength) {
		return original.substring(startIndex - 1, stringLength + startIndex - 1);
	}

}
