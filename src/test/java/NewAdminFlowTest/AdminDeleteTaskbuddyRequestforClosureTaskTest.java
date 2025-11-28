package NewAdminFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
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

public class AdminDeleteTaskbuddyRequestforClosureTaskTest extends BaseClass {

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_taskbuddyClosureTaskUpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);
		String actualClosureTaskCount = common.adminDashboard().getClosureTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualClosureTaskCount.contains("1"), actualClosureTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 2)
	@Parameters("rowNumber")
	public void Tc2_verifyAdminDelete_BuddyRequestForClosureTaskTest(@Optional("1") String rowNumber)
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

	@Test(groups = "sanity", priority = 3)
	@Parameters("rowNumber")
	public void Tc03_validateCompletedTaskUpdatedInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");
		String taskCount = "1";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);

		String actualCompletedTaskCount = common.adminDashboard().getCompletedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualCompletedTaskCount.contains(taskCount), actualCompletedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 4)
	@Parameters("rowNumber")
	public void Tc04_verifyAdminDelete_BuddyRejectedTaskTest(@Optional("1") String rowNumber)
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		List<WebElement> elements = driver
				.findElements(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'" + facilityName
						+ "') and " + "contains(@content-desc,'" + taskTime1 + "') and contains(@content-desc,'"
						+ taskTime2 + "')]/parent::android.view.View"));

		if (elements.isEmpty()) {
			System.out.println("Task element not found, as expected.");
		} else {
			WebElement expFacilityEle = elements.get(0);
			Assert.assertFalse(expFacilityEle.isDisplayed(), "Task is unexpectedly visible.");
		}
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
