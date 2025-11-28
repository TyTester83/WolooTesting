package TaskMasterFlowTest;

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

public class TrialUserRenewClassicExpiryTest extends BaseClass {
	@Test(groups = "regression",priority=0)
	@Parameters("rowNumber")
	public void Tc01_newUserRenewClassicExpiryTest(@Optional("10") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String paymentBank = data.get("Bank");
		String taskMasterPlan = data.get("Subscription Plan");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		common.facilityRenewPopup().tapPayNowButton();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(taskMasterPlan);

		common.paymentOptionsPage().getNetBankButton().click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
//		common.premiumPopup().isPopupVisible();
		common.adminDashboard().navigateToFacilityByName(facilityName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTaskbuddyDropdown())).click();

		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();

		common.adminDashboard().taskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);

	}
}
