package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.GlobalConstants;

public class ExtentManager {
	public static ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.EXTENT_REPORT + "ExtentReport.html");
		reporter.config().setReportName("BankGuru HTML Report");
		reporter.config().setDocumentTitle("BankGuru HTML Report");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Reporter", "Alex Tran");
		extentReports.setSystemInfo("Project", "Bank Guru Automation Testing");
		extentReports.setSystemInfo("Course", "Automation Testing Trainning");
		extentReports.setSystemInfo("JDK Version", GlobalConstants.JAVA_VERSION);
		extentReports.setSystemInfo("Operation System", GlobalConstants.OS_NAME);
		extentReports.setSystemInfo("Operation System Version", GlobalConstants.OS_VERSION);
		return extentReports;
	}
}
