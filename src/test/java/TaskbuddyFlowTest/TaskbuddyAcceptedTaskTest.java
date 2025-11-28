package TaskbuddyFlowTest;

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
import io.appium.java_client.AppiumBy;

public class TaskbuddyAcceptedTaskTest extends BaseClass {

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_validationOf_PN_for_AcceptedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
//		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(5000);

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		buddyDashboardPage.tapShiftInButton();
		common.buddyPendingTaskTab().acceptPendingTask(facilityName, taskTime);
		Thread.sleep(2000);
		utility.notification();
		WebElement taskAcceptEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"TASK ACCEPTED BY\") and contains(@text,'"
						+ buddyName + "')]"));

		Assert.assertTrue(taskAcceptEle.isDisplayed(), "PN is not generated for accepted task ");
		wait.until(ExpectedConditions.visibilityOf(taskAcceptEle));
		Thread.sleep(1000);
		taskAcceptEle.click();

		common.buddyAcceptedTab().getAcceptedTaskTab().isDisplayed();
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);
	}
}
