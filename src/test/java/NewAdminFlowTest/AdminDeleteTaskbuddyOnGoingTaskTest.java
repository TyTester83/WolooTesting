package NewAdminFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AdminDeleteTaskbuddyOnGoingTaskTest extends BaseClass {

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_taskbuddyOngoingTaskUpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");

		common.adminDashboard().adminLogin(adminMobNo);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);
		String actualOngoingTaskCount = common.adminDashboard().getOnGoingTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualOngoingTaskCount.contains("1"), actualOngoingTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 2)
	@Parameters("rowNumber")
	public void Tc02_adminDeleteTaskbuddyOngoingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);

		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(buddyName);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(buddyName);

		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		common.taskPage().deleteTaskTimings(facilityName, taskTime1, taskTime2);

		WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.ImageView[contains(@content-desc,'" + facilityName + "') and contains(@content-desc,'"
						+ taskTime1 + "') and contains(@content-desc,'" + taskTime2 + "')]/parent::android.view.View"));
		Assert.assertTrue(expFacilityEle.isDisplayed(), "Task should be display");
		for (int i = 0; i < 5; i++) {
			try {
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			} catch (Exception ex) {
				System.out.println("BACK key failed too: " + ex.getMessage());
			}
			Thread.sleep(500);
		}
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

}
