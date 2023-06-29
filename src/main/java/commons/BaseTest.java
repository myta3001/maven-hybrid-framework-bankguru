package commons;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import PageObjects.bankGuru.DashboardPageObject;
import PageObjects.bankGuru.DeleteAccountPageObject;
import PageObjects.bankGuru.DeleteCustomerPageObject;
import PageObjects.bankGuru.EditAccountPageObject;
import PageObjects.bankGuru.EditCustomerPageObject;
import PageObjects.bankGuru.NewAccountPageObject;
import PageObjects.bankGuru.NewCustomerPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import reportConfig.ExtentTestManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String pageUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case H_FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--headless");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case H_CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			System.out.println("The browser name is incorrect.");
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.manage().window().maximize();
		driver.get(pageUrl);
		return driver;
	}

	protected Boolean verifyTrue(Boolean condition) {
		Boolean casePass = true;
		try {
			Assert.assertTrue(condition);
			setLogPASS("---------------------------PASSED---------------------------");
		} catch (Throwable e) {
			casePass = false;

			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(Status.FAIL, "<p style=\"color:red !important;\">" + e.toString() + "</p>", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

			ITestResult result = Reporter.getCurrentTestResult();

			VerificationFailures.getFailures().addFailureForTest(result, e);
			result.setThrowable(e);
		}
		return casePass;
	}

	protected Boolean verifyFalse(Boolean condition) {
		Boolean casePass = true;
		try {
			Assert.assertFalse(condition);
			setLogPASS("---------------------------PASSED---------------------------");
		} catch (Throwable e) {
			casePass = false;

			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(Status.FAIL, "<p style=\"color:red !important;\">" + e.toString() + "</p>", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

			ITestResult result = Reporter.getCurrentTestResult();

			VerificationFailures.getFailures().addFailureForTest(result, e);
			result.setThrowable(e);
		}
		return casePass;
	}

	protected Boolean verifyEquals(Object actual, Object expected) {
		Boolean casePass = true;
		try {
			Assert.assertEquals(actual, expected);
			setLogPASS("---------------------------PASSED---------------------------");
		} catch (Throwable e) {
			casePass = false;

			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(Status.FAIL, "<p style=\"color:red !important;\">" + e.toString() + "</p>", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			ITestResult result = Reporter.getCurrentTestResult();

			VerificationFailures.getFailures().addFailureForTest(result, e);
			result.setThrowable(e);
		}
		return casePass;
	}

	protected void startLog(String methodName, String desc) {
		ExtentTestManager.startTest(methodName, desc);
	}
	
	protected void setLogINFO(String detail) {
		ExtentTestManager.getTest().log(Status.INFO, detail);
	}

	protected void setLogPASS(String detail) {
		ExtentTestManager.getTest().log(Status.PASS, detail);		
	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected DashboardPageObject navigateToDashboardPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_DASHBOARD_LINK);
		return PageGeneratorManager.getDashboardPage(driver);
	}
	
	protected NewCustomerPageObject navigateToNewCustomerPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_NEW_CUSTOMER_LINK);
		return PageGeneratorManager.getNewCustomerPage(driver);
	}
	
	protected EditCustomerPageObject navigateToEditCustomerPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_EDIT_CUSTOMER_LINK);
		return PageGeneratorManager.getEditCustomerPage(driver);
	}
	
	protected DeleteCustomerPageObject navigateToDeleteCustomerPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_DELETE_CUSTOMER_LINK);
		return PageGeneratorManager.getDeleteCustomerPage(driver);
	}
	
	protected NewAccountPageObject navigateToNewAccountPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_NEW_ACCOUNT_LINK);
		return PageGeneratorManager.getNewAccountPage(driver);
	}
	
	protected EditAccountPageObject navigateToEditAccountPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_EDIT_ACCOUNT_LINK);
		return PageGeneratorManager.getEditAccountPage(driver);
	}
	
	protected DeleteAccountPageObject navigateToDeleteAccountPage(WebDriver driver) {
		driver.get(GlobalConstants.BANK_GURU_DELETE_ACCOUNT_LINK);
		return PageGeneratorManager.getDeleteAccountPage(driver);
	}
	
	protected String getSubString(String origin, int subStringLength) {
		return origin.substring(0, subStringLength - 1);
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		String cmd = "";
		try {
			String osName = GlobalConstants.OS_NAME.toLowerCase();

			String driverInstanceName = driver.toString().toLowerCase();
			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriveer*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.WARNING, e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
