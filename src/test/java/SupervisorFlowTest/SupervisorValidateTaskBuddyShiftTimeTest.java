package SupervisorFlowTest;

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

public class SupervisorValidateTaskBuddyShiftTimeTest extends BaseClass {

	@Test(groups = "regression")
	@Parameters("rowNumber")
	public void Tc0_shiftInTimingValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String adminMobNo = data.get("Admin Mobile No");
		String registrationPts = "2010";
		String adminType = data.get("Admin Type");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		String shiftStartTime = common.buddyDashboardPage().tapShiftInButton();
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName);
		common.buddyProfilePage().wolooPointValidation(registrationPts);
		common.buddyProfilePage().tapLogout();
		Thread.sleep(5000);
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.supervisorDashboardPage().getClusterImage().click();
		common.clusterPage().clusterValidation(adminMobNo);

		common.janitorListPage().janitorsClusterValidation(buddyName, taskbuddyMobNo, "Present");
		common.janitorDetailPage().janitorsDetailValidation(buddyName, taskbuddyMobNo);
		common.janitorDetailPage().janitorsShiftTimeValidattion(shiftStartTime);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(common.janitorListPage().getBackButton())).click();
		wait.until(ExpectedConditions.visibilityOf(common.clusterPage().getClusterText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}
}
