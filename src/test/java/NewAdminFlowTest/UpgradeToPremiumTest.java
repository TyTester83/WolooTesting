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
import io.appium.java_client.AppiumBy;

public class UpgradeToPremiumTest extends BaseClass {
	@Test
	@Parameters("rowNumber")
	public void Tc01_upgradePremium_RenewMembership_ImmediatelyTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String renewPlan = data.get("FacilityRenewalPlan");
		String paymentBank = data.get("Bank");
		String taskBuddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Products")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));

		common.premiumPopup().tapPremiumPopup(facilityName);
		common.stinqguardPage().onboardingScreen();
		common.premiumPage().tapPremiumFacility(facilityName);
		common.renewMembershipPopup().tapRenewButton(renewPlan);
		wait.until(ExpectedConditions.visibilityOf(common.paymentOptionsPage().getNetBankButton()));
		wait.until(ExpectedConditions.elementToBeClickable(common.paymentOptionsPage().getNetBankButton())).click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.premiumPopup().isPopupVisible();
		common.adminDashboard().navigateToFacilityByName(facilityName);

		common.iotDashboardPage().iotAirQualityUIValidation();
		common.iotDashboardPage().usageUIValidation();
		common.iotDashboardPage().taskAuditUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.iotDashboardPage().getTaskbuddyDropdown())).click();

		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		common.iotDashboardPage().iOtTaskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test
	@Parameters("rowNumber")
	public void Tc02_upgradePremium_RenewMembership_LaterTest(@Optional("10") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String renewPlan = data.get("FacilityRenewalPlan");
		String paymentBank = data.get("Bank");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Products")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));

		common.premiumPopup().tapPremiumPopup(facilityName);
		common.stinqguardPage().onboardingScreen();
		common.premiumPage().tapPremiumFacility(facilityName);
		common.renewMembershipPopup().tapRenewButton(renewPlan);
		wait.until(ExpectedConditions.visibilityOf(common.paymentOptionsPage().getNetBankButton()));
		wait.until(ExpectedConditions.elementToBeClickable(common.paymentOptionsPage().getNetBankButton())).click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.premiumPopup().isPopupVisible();
		common.adminDashboard().navigateToFacilityByName(facilityName);

		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		Assert.assertEquals(common.adminDashboard().getTaskAuditText().isDisplayed(), true);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}
}
