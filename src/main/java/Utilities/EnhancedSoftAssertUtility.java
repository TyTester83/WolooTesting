package Utilities;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

/**
 * Enhanced SoftAssertValidationUtility with Extent Report integration
 * Captures assertion failures for detailed reporting while allowing test continuation
 */
public class EnhancedSoftAssertUtility {
    
    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();
    private static ThreadLocal<StringBuilder> failureLog = new ThreadLocal<>();

    /**
     * Initialize SoftAssert for current test thread
     */
    public static void initSoftAssert() {
        softAssert.set(new SoftAssert());
        failureLog.set(new StringBuilder());
        ExtentReportManager.logInfo("Soft Assertions initialized for thread: " + Thread.currentThread().getName());
    }

    /**
     * Get current SoftAssert instance
     */
    public static SoftAssert getSoftAssert() {
        if (softAssert.get() == null) {
            initSoftAssert();
        }
        return softAssert.get();
    }

    /**
     * Verify element is displayed with soft assertion and logging
     */
    public static void verifyElementVisible(WebElement element, String message) {
        try {
            boolean isDisplayed = element.isDisplayed();
            getSoftAssert().assertTrue(isDisplayed, message);
            if (isDisplayed) {
                ExtentReportManager.logPass(message + " - Element is visible");
            } else {
                String failureMsg = message + " - Element is NOT visible";
                ExtentReportManager.logWarning(failureMsg);
                recordFailure(failureMsg);
            }
        } catch (Exception e) {
            String failureMsg = message + " - Exception: " + e.getMessage();
            getSoftAssert().fail(failureMsg);
            ExtentReportManager.logWarning(failureMsg);
            recordFailure(failureMsg);
        }
    }

    /**
     * Verify element text with soft assertion and logging
     */
    public static void verifyElementText(WebElement element, String expectedText, String message) {
        try {
            String actualText = element.getText();
            boolean isMatch = actualText.equals(expectedText);
            getSoftAssert().assertEquals(actualText, expectedText, message);
            if (isMatch) {
                ExtentReportManager.logPass(message + " - Text verified: '" + expectedText + "'");
            } else {
                String failureMsg = message + " - Expected: '" + expectedText + "' but got: '" + actualText + "'";
                ExtentReportManager.logWarning(failureMsg);
                recordFailure(failureMsg);
            }
        } catch (Exception e) {
            String failureMsg = message + " - Exception: " + e.getMessage();
            getSoftAssert().fail(failureMsg);
            ExtentReportManager.logWarning(failureMsg);
            recordFailure(failureMsg);
        }
    }

    /**
     * Verify element contains text with soft assertion and logging
     */
    public static void verifyElementContainsText(WebElement element, String expectedText, String message) {
        try {
            String actualText = element.getText();
            boolean isContains = actualText.contains(expectedText);
            getSoftAssert().assertTrue(isContains, 
                message + " - Expected text '" + expectedText + "' not found in '" + actualText + "'");
            if (isContains) {
                ExtentReportManager.logPass(message + " - Text '" + expectedText + "' found in element");
            } else {
                String failureMsg = message + " - Text '" + expectedText + "' NOT found in '" + actualText + "'";
                ExtentReportManager.logWarning(failureMsg);
                recordFailure(failureMsg);
            }
        } catch (Exception e) {
            String failureMsg = message + " - Exception: " + e.getMessage();
            getSoftAssert().fail(failureMsg);
            ExtentReportManager.logWarning(failureMsg);
            recordFailure(failureMsg);
        }
    }

    /**
     * Verify boolean condition with soft assertion and logging
     */
    public static void verifyTrue(boolean condition, String message) {
        getSoftAssert().assertTrue(condition, message);
        if (condition) {
            ExtentReportManager.logPass(message + " - Condition verified as TRUE");
        } else {
            ExtentReportManager.logWarning(message + " - Condition is FALSE");
            recordFailure(message + " - Condition is FALSE");
        }
    }

    /**
     * Verify false condition with soft assertion and logging
     */
    public static void verifyFalse(boolean condition, String message) {
        getSoftAssert().assertFalse(condition, message);
        if (!condition) {
            ExtentReportManager.logPass(message + " - Condition verified as FALSE");
        } else {
            ExtentReportManager.logWarning(message + " - Condition is TRUE");
            recordFailure(message + " - Condition is TRUE");
        }
    }

    /**
     * Verify two values are equal with soft assertion and logging
     */
    public static void verifyEquals(Object actual, Object expected, String message) {
        getSoftAssert().assertEquals(actual, expected, message);
        if (actual != null && actual.equals(expected)) {
            ExtentReportManager.logPass(message + " - Values match: '" + expected + "'");
        } else {
            String failureMsg = message + " - Expected: '" + expected + "' but got: '" + actual + "'";
            ExtentReportManager.logWarning(failureMsg);
            recordFailure(failureMsg);
        }
    }

    /**
     * Record failure for summary logging
     */
    private static void recordFailure(String failureMessage) {
        if (failureLog.get() != null) {
            failureLog.get().append("- ").append(failureMessage).append("\n");
        }
    }

    /**
     * Assert all collected assertions and log results to report
     */
    public static void assertAll() {
        try {
            StringBuilder failuresSummary = failureLog.get();
            if (failuresSummary != null && failuresSummary.length() > 0) {
                ExtentReportManager.logWarning("Soft Assertion Failures Summary:\n" + failuresSummary.toString());
            }
            getSoftAssert().assertAll();
            ExtentReportManager.logPass("All soft assertions passed");
        } catch (AssertionError e) {
            ExtentReportManager.logError("Soft assertion failed: " + e.getMessage(), e, null);
            throw e;
        }
    }

    /**
     * Clear soft assertions for current thread
     */
    public static void clearAssertions() {
        if (softAssert.get() != null) {
            softAssert.remove();
        }
        if (failureLog.get() != null) {
            failureLog.remove();
        }
    }

    /**
     * Get count of failures
     */
    public static int getFailureCount() {
        if (failureLog.get() != null) {
            return failureLog.get().toString().split("\n").length - 1;
        }
        return 0;
    }
}
