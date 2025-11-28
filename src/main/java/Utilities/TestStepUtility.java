package Utilities;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

/**
 * TestStepUtility provides wrapped methods for common test operations
 * Each method logs its action to the extent report for detailed step-level reporting
 * Automatically captures screenshots on failure
 */
public class TestStepUtility {

    private AppiumDriver driver;

    public TestStepUtility(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Wait for element to be visible with step logging
     */
    public WebElement waitForElementVisible(WebElement element, int timeoutSeconds, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            ExtentReportManager.logPass(stepDescription + " - Element is visible", driver, true);
            return visibleElement;
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Failed: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable with step logging
     */
    public WebElement waitForElementClickable(WebElement element, int timeoutSeconds, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            ExtentReportManager.logPass(stepDescription + " - Element is clickable", driver, true);
            return clickableElement;
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Failed: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Click element with step logging
     */
    public void clickElement(WebElement element, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            ExtentReportManager.logPass(stepDescription + " - Element clicked successfully", driver, true);
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Failed to click: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Send keys to element with step logging
     */
    public void sendKeys(WebElement element, String text, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            element.clear();
            element.sendKeys(text);
            ExtentReportManager.logPass(stepDescription + " - Text '" + text + "' entered successfully", driver, true);
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Failed to send keys: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Verify element is displayed with step logging
     */
    public void verifyElementDisplayed(WebElement element, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            if (!element.isDisplayed()) {
                throw new AssertionError("Element is not displayed");
            }
            ExtentReportManager.logPass(stepDescription + " - Element is displayed", driver, true);
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Failed: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Verify element text with step logging
     */
    public void verifyElementText(WebElement element, String expectedText, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            String actualText = element.getText();
            if (!actualText.equals(expectedText)) {
                throw new AssertionError("Expected: " + expectedText + " but got: " + actualText);
            }
            ExtentReportManager.logPass(stepDescription + " - Text verified: '" + expectedText + "'", driver, true);
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Text verification failed: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Verify element contains text with step logging
     */
    public void verifyElementContainsText(WebElement element, String expectedText, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            String actualText = element.getText();
            if (!actualText.contains(expectedText)) {
                throw new AssertionError("Text '" + expectedText + "' not found in: " + actualText);
            }
            ExtentReportManager.logPass(stepDescription + " - Text '" + expectedText + "' verified in element", driver, true);
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Text verification failed: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Get element text with step logging
     */
    public String getElementText(WebElement element, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            String text = element.getText();
            ExtentReportManager.logPass(stepDescription + " - Retrieved text: '" + text + "'", driver, false);
            return text;
        } catch (Exception e) {
            ExtentReportManager.logFail(stepDescription + " - Failed to get text: " + e.getMessage(), driver);
            throw e;
        }
    }

    /**
     * Wait and sleep with step logging
     */
    public void waitForSeconds(int seconds, String stepDescription) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription + " - Waiting for " + seconds + " seconds");
            Thread.sleep(seconds * 1000L);
            ExtentReportManager.logPass(stepDescription + " - Wait completed", driver, false);
        } catch (InterruptedException e) {
            ExtentReportManager.logWarning(stepDescription + " - Wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Assert custom condition with step logging
     */
    public void assertCondition(boolean condition, String stepDescription, String failureMessage) {
        try {
            ExtentReportManager.logInfo("Step: " + stepDescription);
            if (!condition) {
                throw new AssertionError(failureMessage);
            }
            ExtentReportManager.logPass(stepDescription + " - Assertion passed", driver, true);
        } catch (AssertionError e) {
            ExtentReportManager.logFail(stepDescription + " - " + e.getMessage(), driver);
            throw e;
        }
    }
}
