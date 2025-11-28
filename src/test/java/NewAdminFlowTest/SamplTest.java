package NewAdminFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.ListenerImpUtility;
import io.appium.java_client.AppiumBy;
@Listeners(ListenerImpUtility.class)
public class SamplTest extends BaseClass {

	@Test(groups = "regression", priority = 0)
	@Parameters("rowNumber")
	public void TC01_addNew_ClassicFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String taskBuddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		common.adminDashboard().adminLogin(adminMobNo);
		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
//		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
//	
//		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
//		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
//		common.adminDashboard().newFacilityDashboardUIValidation(facilityName, taskBuddyName, adminType, pendingTaskCount);
//		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTaskbuddyDropdown())).click();
//		Thread.sleep(3000);
//		WebElement actualTaskbuddy = driver.findElement(
//				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]"));
//		Thread.sleep(1000);
//		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
//		Thread.sleep(3000);
//		common.adminDashboard().taskEfficiencyCalculation();

	}
	@Test(groups = "regression", priority = 1)
	@Parameters("rowNumber")
	public void TC02_addNew_ClassicFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String taskBuddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//		common.adminDashboard().adminLogin(adminMobNo);
		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
	}
}
