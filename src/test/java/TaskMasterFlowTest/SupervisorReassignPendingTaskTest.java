package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SupervisorReassignPendingTaskTest extends BaseClass{
	@Test(groups = "regression",priority = 1)
	@Parameters("rowNumber")
	public void Tc01_supervisorReassignBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String taskReassignTime = data.get("Task Time2");
		String reassignTaskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");
		String adminType = data.get("Admin Type");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.pendingTaskTab().tapPendingTaskReassignButton(taskTime, buddyName);

		common.searchJanitorsPage().tapReassignByName_MobileNo(adminMobNo, facilityName, buddyName, taskbuddyMobNo);
		common.schedulePage().reassignTasktoJanitor(taskReassignTime);

		common.pendingTaskTab().reAssignTaskValidation(facilityName, reassignTaskTime, buddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}
	@Test(groups = "regression",priority = 2)
	@Parameters("rowNumber")
	public void Tc02_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time2")+"-"+data.get("Task End Time2");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();

		common.buddyPendingTaskTab().validatePendingTask(facilityName, taskTime);
		common.buddyDashboardPage().getProfileIconImage().click();
//		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); defect in the application
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}
	@Test(groups = "regression",priority = 3)
	@Parameters("rowNumber")
	public void Tc03_adminDeleteTaskbuddyAcceptedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = data.get("Task Name");
		String startTaskTime = data.get("Task Time2");
		String endTaskTime = data.get("Task End Time2");
		String buddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";

		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));

		try {
			if (common.adminDashboard().getRenewLink().isDisplayed()) {
				System.out.println("New user will not get Popup");
			}

		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
		}

		common.adminDashboard().getAddFacilityButton().click();
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
		common.taskPage().taskTimingValidation(facilityName, facilityType, taskName, taskTime1, taskTime2);

//		WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(
//				"//android.widget.ImageView[contains(@content-desc,'" + facilityName + "') and contains(@content-desc,'"
//						+ taskTime1 + "') and contains(@content-desc,'" + taskTime2 + "')]/parent::android.view.View"));
//		Assert.assertTrue(expFacilityEle.isDisplayed(), "Task should be display");
		for (int i = 0; i < 5; i++) {
			try {
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			} catch (Exception ex) {
				System.out.println("BACK key failed too: " + ex.getMessage());
			}
			Thread.sleep(500);
		}

		try {
			if (common.adminDashboard().getRenewLink().isDisplayed()) {
				System.out.println("New user will not get Popup");
			}

		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
		}

		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

}
