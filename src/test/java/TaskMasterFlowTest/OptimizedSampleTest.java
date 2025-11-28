package TaskMasterFlowTest;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Sample optimized test class with step-level extent report logging
 * Each action/verification is logged as a separate step in the extent report
 * On failure, an automatic screenshot is captured
 */
public class OptimizedSampleTest extends BaseClass {

    @Test(description = "Search product test with step-level reporting")
    public void Tc01_searchProductTest_Optimized() {
        try {
            String adminMobNo = "9511599511";

            // Step 1: Admin login
            ExtentReportManager.logInfo("Step 1: Performing admin login with mobile number: " + adminMobNo);
            try {
                common.adminDashboard().adminLogin(adminMobNo);
                ExtentReportManager.logPass("Admin login successful", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Admin login failed: " + e.getMessage(), driver);
                throw e;
            }

            // Step 2: Wait for Store element
            ExtentReportManager.logInfo("Step 2: Waiting for Store element to be visible");
            try {
                Thread.sleep(10000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
                ExtentReportManager.logPass("Store element is visible", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Store element not found: " + e.getMessage(), driver);
                throw e;
            }

            // Step 3: Verify Categories is displayed
            ExtentReportManager.logInfo("Step 3: Verifying Categories element is displayed");
            try {
                WebElement categories = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Categories\"]"));
                Assert.assertTrue(categories.isDisplayed(), "Categories element is not displayed");
                ExtentReportManager.logPass("Categories element verified and displayed", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Categories verification failed: " + e.getMessage(), driver);
                throw e;
            }

            // Step 4: Click on search field
            ExtentReportManager.logInfo("Step 4: Clicking on search field");
            try {
                WebElement searchField = driver.findElement(AppiumBy.xpath(
                        "(//android.view.View[@content-desc=\"Categories\"]/ancestor::android.view.View)[4]/descendant::android.view.View[2]"));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(searchField)).click();
                ExtentReportManager.logPass("Search field clicked successfully", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Failed to click search field: " + e.getMessage(), driver);
                throw e;
            }

            // Step 5: Enter search text
            ExtentReportManager.logInfo("Step 5: Entering search text 'Glass Cleaner Stick'");
            try {
                WebElement searchDetailField = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));
                Assert.assertTrue(searchDetailField.isDisplayed(), "Search input field is not displayed");
                
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(searchDetailField)).click();
                searchDetailField.clear();
                searchDetailField.sendKeys("Glass Cleaner Stick");
                
                ExtentReportManager.logPass("Search text entered successfully", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Failed to enter search text: " + e.getMessage(), driver);
                throw e;
            }

            // Step 6: Select product from dropdown
            ExtentReportManager.logInfo("Step 6: Selecting product from dropdown");
            try {
                Thread.sleep(2000);
                String productName = "Glass Cleaner Stick";
                WebElement productDropdown = driver
                        .findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='" + productName + "']"));
                productDropdown.click();
                ExtentReportManager.logPass("Product '" + productName + "' selected from dropdown", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Failed to select product from dropdown: " + e.getMessage(), driver);
                throw e;
            }

            // Step 7: Hide keyboard
            ExtentReportManager.logInfo("Step 7: Hiding keyboard");
            try {
                if (driver instanceof AndroidDriver) {
                    ((AndroidDriver) driver).hideKeyboard();
                    ExtentReportManager.logPass("Keyboard hidden successfully");
                }
            } catch (Exception e) {
                ExtentReportManager.logWarning("Keyboard hiding attempt failed, continuing: " + e.getMessage());
            }

            // Step 8: Click Add button for product
            ExtentReportManager.logInfo("Step 8: Clicking Add button for product");
            try {
                Thread.sleep(2000);
                String productName = "Glass Cleaner Stick";
                WebElement product = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'"
                        + productName + "')]/child::android.view.View[@content-desc=\"Add\"]"));
                Assert.assertTrue(product.isDisplayed(), "Add button for product is not displayed");
                
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(product)).click();
                ExtentReportManager.logPass("Add button clicked successfully", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Failed to click Add button: " + e.getMessage(), driver);
                throw e;
            }

            // Step 9: Verify product quantity
            ExtentReportManager.logInfo("Step 9: Verifying product quantity is displayed");
            try {
                Thread.sleep(2000);
                String expectedQuantity = "1";
                WebElement actualQuantity = driver
                        .findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + expectedQuantity + "']"));
                Assert.assertEquals(actualQuantity.isDisplayed(), true, "Quantity verification failed");
                ExtentReportManager.logPass("Product quantity '" + expectedQuantity + "' verified successfully", driver, true);
            } catch (Exception e) {
                ExtentReportManager.logFail("Product quantity verification failed: " + e.getMessage(), driver);
                throw e;
            }

            // Test completion
            ExtentReportManager.markTestPassed("Test completed successfully - All steps executed");

        } catch (Exception e) {
            ExtentReportManager.markTestFailed("Test failed during execution: " + e.getMessage(), driver);
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }
}
