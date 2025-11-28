package SupervisorFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import io.appium.java_client.AppiumBy;

public class SupervisorClosureofRequestedTaskTest extends BaseClass {
	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_approveBuddyRequestedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskName = data.get("Task Name");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.taskClosureTab().tapRequestedTaskDetailsButton(buddyName, taskTime);

		List<String> taskNames = new ArrayList<String>();
		taskNames.add(taskName);
		common.approvalPage().validateAndApproveCompletedTask(taskNames);
		common.completedTaskTab().validateCompletedTaskIsDisplayed(buddyName, taskTime);
		Thread.sleep(2000);
		utility.notification();
		WebElement taskApprovedEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"Task Approved by Supervisor\")]"));

		Assert.assertTrue(taskApprovedEle.isDisplayed(), "PN is not generated for rejected task ");
		wait.until(ExpectedConditions.visibilityOf(taskApprovedEle));
		Thread.sleep(1000);
		taskApprovedEle.click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 2)
	@Parameters("rowNumber")
	public void Tc02_completedTaskUpdatedInSupervisors_JanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskCount = "1";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();
		String actualCompletedTaskCount = common.janitorDetailPage().getCompletedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualCompletedTaskCount.contains(taskCount),
				actualCompletedTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
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

	@Test(groups = "regression",priority=4)
	@Parameters("rowNumber")
	public void Tc04_supervisorRejectBuddyRequestedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");
		String buddyName = data.get("TaskBuddy Name");
		String taskName = data.get("Task Name");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.taskClosureTab().tapRequestedTaskDetailsButton(buddyName, taskTime);

		List<String> taskNames = new ArrayList<String>();
		taskNames.add(taskName);
		common.approvalPage().rejectTheTask(taskNames);
		common.supevisorRejectedTab().validateRejectedTaskIsVisible(buddyName, taskTime);

		Thread.sleep(2000);
		utility.notification();
		WebElement taskRejectedEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"TASK REJECTED BY Supervisor\")]"));

		Assert.assertTrue(taskRejectedEle.isDisplayed(), "PN is not generated for rejected task ");
		wait.until(ExpectedConditions.visibilityOf(taskRejectedEle));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(taskRejectedEle)).click();

		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);

		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression",priority=5)
	@Parameters("rowNumber")
	public void Tc05_verifySupervisorRejectedTask_UpdatedInJanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskCount = "1";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);

		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();

		String actualRejectedTaskCount = common.janitorDetailPage().getRejectedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualRejectedTaskCount.contains(taskCount),
				actualRejectedTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);

		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

}
