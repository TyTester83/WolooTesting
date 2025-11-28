package Utilities;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class SoftAssertValidationUtility {
	private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    // Initialize SoftAssert for each test (usually called in @BeforeMethod)
    public static void initSoftAssert() {
        softAssert.set(new SoftAssert());
    }

    // Get current SoftAssert instance
    public static SoftAssert getSoftAssert() {
        return softAssert.get();
    }

    // Generic soft assertion for element visibility
    public static void verifyElementVisible(WebElement element, String message) {
        try {
            boolean isDisplayed = element.isDisplayed();
            softAssert.get().assertTrue(isDisplayed, message);
        } catch (Exception e) {
            softAssert.get().fail(message + " - Exception occurred: " + e.getMessage());
        }
    }

    // Finalize all soft assertions
    public static void assertAll() {
        softAssert.get().assertAll();
    }

}
