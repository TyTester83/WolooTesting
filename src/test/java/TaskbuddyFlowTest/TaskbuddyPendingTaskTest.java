package TaskbuddyFlowTest;

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

public class TaskbuddyPendingTaskTest extends BaseClass {
	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		common.buddyPendingTaskTab().validatePendingTask(facilityName, taskTime);
		wait.until(ExpectedConditions.elementToBeClickable(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); // defect in the application
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}
}
