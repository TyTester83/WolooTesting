package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import io.appium.java_client.AppiumBy;

public class NewUserPurchasePremiumSubscriptionThrough_RenewItLinkTest extends BaseClass {

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_newUserTrytoPurchasingNewFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 1;
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();

		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.newUserRenewPopup().newUserWarningPopup();
		wait.until(ExpectedConditions.visibilityOf(common.newFacilitypage().getFacilityButton()));
		gUtil.safeClick(common.newFacilitypage().getBackButton(), 2, driver);

		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 2)
	@Parameters("rowNumber")
	public void Tc02_newUser_Tap_RenewItLink_Purchasing_PremiumSubscriptionTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 1;
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String subscriptionPlan = data.get("Subscription Plan");
		String paymentBank = data.get("Bank");
		String taskBuddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		gUtil.safeClick(common.adminDashboard().getRenewLink(), 3, driver);

		common.paynowPage().tapPaymentPlan(subscriptionPlan);
		common.paymentOptionsPage().paymentUIValidation();
		gUtil.safeClick(common.paymentOptionsPage().getNetBankButton(), 3, driver);
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));

		common.iotDashboardPage().iotAirQualityUIValidation();
		common.iotDashboardPage().usageUIValidation();
		common.iotDashboardPage().taskAuditUIValidation();
		gUtil.safeClick(common.iotDashboardPage().getTaskbuddyDropdown(), 3, driver);
//		wait.until(ExpectedConditions.elementToBeClickable(common.iotDashboardPage().getTaskbuddyDropdown())).click();

		Thread.sleep(3000);
		WebElement actualTaskbuddy = driver.findElement(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]"));
//		Thread.sleep(1000);
//		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		gUtil.safeClick(actualTaskbuddy, 2, driver);
		common.iotDashboardPage().iOtTaskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

}
