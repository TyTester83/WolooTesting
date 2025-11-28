package Utilities;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class ListenerImpUtility implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		// Called before the Suite starts - Extent Report already initialized in BaseClass.loadConfig()
		System.out.println("===== Test Suite Started: " + suite.getName() + " =====");
	}

	@Override
	public void onFinish(ISuite suite) {
		// Called after the Suite finishes - Extent Report flushed in BaseClass.stopServer()
		System.out.println("===== Test Suite Finished: " + suite.getName() + " =====");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String description = "Test Method: " + methodName;
		ExtentReportManager.createTest(methodName, description);
		System.out.println("===== Test Started: " + methodName + " =====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		ExtentReportManager.markTestPassed(methodName + " - PASSED");
		System.out.println("===== Test Passed: " + methodName + " =====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentReportManager.logError(testName + " - FAILED", result.getThrowable(), 
				UtilityClassObject.getDriver());
		System.out.println("===== Test Failed: " + testName + " =====");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		ExtentReportManager.markTestSkipped(methodName + " - SKIPPED");
		System.out.println("===== Test Skipped: " + methodName + " =====");
	}
}