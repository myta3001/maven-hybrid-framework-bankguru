package reportConfig;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import commons.BaseTest;

public class ExtentReportListener extends BaseTest implements ITestListener {
	public static Boolean isTookScreenshot = false;
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", this.getDriverInstance());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed with percentage " + getTestMethodName(iTestResult));
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		if (!isTookScreenshot) {
			Object testClass = iTestResult.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriverInstance();
			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		}
	}

	public void onConfigurationFailure(ITestResult iTestResult) {
		if (!isTookScreenshot) {
			Object testClass = iTestResult.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriverInstance();
			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(Status.FAIL, "Pre_Condition Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Pre_Condition Failed");
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}
}