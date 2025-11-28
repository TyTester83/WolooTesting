package TaskMasterFlowTest;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class SampleTest extends BaseClass {
	@Test
	public void Tc01_searchProductTest() throws InterruptedException {
		String adminMobNo = "9511599511";

		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));

		WebElement categories = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Categories\"]"));
		Assert.assertTrue(categories.isDisplayed(), categories + "There is no Categories displayed");

		WebElement searchField = driver.findElement(AppiumBy.xpath(
				"(//android.view.View[@content-desc=\"Categories\"]/ancestor::android.view.View)[4]/descendant::android.view.View[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(searchField)).click();

		WebElement searchDetailField = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));
		Assert.assertTrue(searchDetailField.isDisplayed(), searchDetailField + "expected field is not displayed");

		wait.until(ExpectedConditions.elementToBeClickable(searchDetailField)).click();
		searchDetailField.clear();
		searchDetailField.sendKeys("Glass Cleaner Stick");

		Thread.sleep(2000);
//		WebElement searchLayout=driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.LinearLayout"));
//		GenericUtility.tapUsingCoordinatePercentage(driver, searchLayout, 44.0, 12.0);

		String productName = "Glass Cleaner Stick";
		WebElement productDropdown = driver
				.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='" + productName + "']"));
		productDropdown.click();

		try {
			if (driver instanceof AndroidDriver) {
				((AndroidDriver) driver).hideKeyboard();
			}
		} catch (Exception e) {
			System.out.println("hideKeyboard() failed, trying BACK key as fallback...");
		}

		Thread.sleep(2000);
		WebElement product = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'"
				+ productName + "')]/child::android.view.View[@content-desc=\"Add\"]"));
		Assert.assertTrue(product.isDisplayed(), product + "expected Product is not displayed");
		wait.until(ExpectedConditions.elementToBeClickable(product)).click();
		Thread.sleep(2000);
		String expectedQunatity = "1";
		WebElement actualQuantity = driver
				.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + expectedQunatity + "']"));
		Assert.assertEquals(actualQuantity.isDisplayed(), true);
	}

}
