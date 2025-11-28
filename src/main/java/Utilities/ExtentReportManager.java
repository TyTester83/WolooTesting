package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.AppiumDriver;

/**
 * ExtentReportManager handles all extent report operations for step-level logging
 * Captures each test step, screenshot on failure, and maintains thread-safe report instances
 */
public class ExtentReportManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<ExtentReports> report = new ThreadLocal<>();
    private static ThreadLocal<String> testMethodName = new ThreadLocal<>();
    private static final String REPORT_PATH = "./AdvanceReport/";
    private static ExtentSparkReporter sparkReporter;

    /**
     * Store the test method name for use in report naming
     */
    public static void setTestMethodName(String methodName) {
        testMethodName.set(methodName);
    }

    /**
     * Get the stored test method name
     */
    public static String getTestMethodName() {
        return testMethodName.get();
    }

    /**
     * Initialize the Extent Report with dynamic timestamp and test method name
     */
    public static synchronized void initReport() {
        if (report.get() == null) {
            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());
            // Build report filename with timestamp
            String reportFileName = "report_" + timeStamp + ".html";
            String reportPath = REPORT_PATH + reportFileName;

            // Ensure directory exists
            File reportDir = new File(REPORT_PATH);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("WHMS Automation Test Report");
            sparkReporter.config().setReportName("TASKMASTER - Automated Test Report");
            sparkReporter.config().setTheme(Theme.DARK);

            ExtentReports extentReport = new ExtentReports();
            extentReport.attachReporter(sparkReporter);
            extentReport.setSystemInfo("OS", System.getProperty("os.name"));
            extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReport.setSystemInfo("Automation Framework", "Appium + TestNG + ExtentReports 5.0.9");

            report.set(extentReport);
            System.out.println("[ExtentReport] Report initialized at: " + reportPath);
        }
    }

    /**
     * Create a new test instance for the current test method with the actual method name
     */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = report.get().createTest(testName, description);
        test.set(extentTest);
        setTestMethodName(testName);  // Store method name for report reference
        // Also set the ExtentTest in UtilityClassObject for other utilities/listeners
        try {
            Utilities.UtilityClassObject.setTest(extentTest);
        } catch (Throwable t) {
            System.out.println("[ExtentReport] Warning: could not set UtilityClassObject test: " + t.getMessage());
        }
        return extentTest;
    }

    /**
     * Get the current test instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Log an informational step
     */
    public static void logInfo(String stepDescription) {
        if (test.get() != null) {
            test.get().log(Status.INFO, stepDescription);
            System.out.println("[STEP INFO] " + stepDescription);
        }
    }

    /**
     * Log a passing step with optional screenshot
     */
    public static void logPass(String stepDescription, AppiumDriver driver, boolean captureScreenshot) {
        if (test.get() != null) {
            test.get().log(Status.PASS, stepDescription);
            if (captureScreenshot && driver != null) {
                captureScreenshot(driver, "PASS");
            }
            System.out.println("[STEP PASS] " + stepDescription);
        }
    }

    /**
     * Log a passing step
     */
    public static void logPass(String stepDescription) {
        logPass(stepDescription, null, false);
    }

    /**
     * Log a failing step with automatic screenshot
     */
    public static void logFail(String stepDescription, AppiumDriver driver) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, stepDescription);
            if (driver != null) {
                captureScreenshot(driver, "FAIL");
            }
            System.out.println("[STEP FAIL] " + stepDescription);
        }
    }

    /**
     * Log a warning step
     */
    public static void logWarning(String stepDescription) {
        if (test.get() != null) {
            test.get().log(Status.WARNING, stepDescription);
            System.out.println("[STEP WARNING] " + stepDescription);
        }
    }

    /**
     * Log test execution error with exception details
     */
    public static void logError(String stepDescription, Throwable throwable, AppiumDriver driver) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, stepDescription + " | Exception: " + throwable.getMessage());
            test.get().log(Status.FAIL, throwable);
            if (driver != null) {
                captureScreenshot(driver, "ERROR");
            }
            System.out.println("[STEP ERROR] " + stepDescription);
        }
    }

    /**
     * Capture screenshot and attach to report
     */
    public static void captureScreenshot(AppiumDriver driver, String screenshotName) {
        try {
            if (test.get() != null && driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Image = ts.getScreenshotAs(OutputType.BASE64);
                String timestamp = new SimpleDateFormat("HH_mm_ss").format(new Date());
                String finalScreenshotName = screenshotName + "_" + timestamp;
                test.get().addScreenCaptureFromBase64String(base64Image, finalScreenshotName);
            }
        } catch (Exception e) {
            System.out.println("[WARNING] Failed to capture screenshot: " + e.getMessage());
        }
    }

    /**
     * Capture screenshot with custom label
     */
    public static void captureScreenshot(AppiumDriver driver, String label, String description) {
        try {
            if (test.get() != null && driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Image = ts.getScreenshotAs(OutputType.BASE64);
                test.get().log(Status.INFO, description);
                test.get().addScreenCaptureFromBase64String(base64Image, label);
            }
        } catch (Exception e) {
            System.out.println("[WARNING] Failed to capture screenshot: " + e.getMessage());
        }
    }

    /**
     * Mark test as PASS in the report
     */
    public static void markTestPassed(String message) {
        if (test.get() != null) {
            test.get().log(Status.PASS, message);
            System.out.println("[TEST RESULT] PASSED - " + message);
        }
    }

    /**
     * Mark test as FAIL in the report
     */
    public static void markTestFailed(String message, AppiumDriver driver) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, message);
            if (driver != null) {
                captureScreenshot(driver, "TEST_FAILED");
            }
            System.out.println("[TEST RESULT] FAILED - " + message);
        }
    }

    /**
     * Mark test as SKIPPED in the report
     */
    public static void markTestSkipped(String message) {
        if (test.get() != null) {
            test.get().log(Status.SKIP, message);
            System.out.println("[TEST RESULT] SKIPPED - " + message);
        }
    }

    /**
     * Flush and close the report
     */
    public static synchronized void flushReport() {
        if (report.get() != null) {
            report.get().flush();
            System.out.println("[ExtentReport] Report flushed successfully.");
            report.remove();
            test.remove();
            testMethodName.remove();
        }
    }

    /**
     * Remove test thread-local
     */
    public static void removeTest() {
        test.remove();
        try {
            Utilities.UtilityClassObject.setTest(null);
        } catch (Throwable t) {
            // ignore
        }
        testMethodName.remove();
    }
}
