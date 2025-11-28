package supervisorDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AccountPage extends BasePage {

	public AccountPage(AppiumDriver driver) {
		super(driver);
	}
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"woloo points\")]")
	private WebElement pointsText;
	
	@AndroidFindBy(accessibility = "My Account")
	private WebElement myaccountText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Mob :\"]/preceding-sibling::android.widget.ImageView")
	private WebElement editIconImage;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Log Out\"]")
	private WebElement logoutButton;

	public WebElement getMyaccountText() {
		return myaccountText;
	}

	public WebElement getEditIconImage() {
		return editIconImage;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}
	
	public void supervisorAccountUIValidation(String mobNO, String supervisorName)
	{
		wait.until(ExpectedConditions.visibilityOf(myaccountText));
		
		SoftAssertValidationUtility.verifyElementVisible(editIconImage,editIconImage+"Image edit icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(logoutButton, logoutButton+"Logout button should be visible");
		
//		WebElement actualMobileNo=driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+mobNO+"')]"));
//		Assert.assertTrue(actualMobileNo.isDisplayed(), "Valid supervisor mobile number should be displayed");
		
		WebElement actualSupervisorName=driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+supervisorName+"')]"));
		SoftAssertValidationUtility.verifyElementVisible(actualSupervisorName, actualSupervisorName+"Valid supervisor Name should be displayed");
		ExtentReportManager.logPass("Supervisor accounts UI validated", driver, false);
	}

	public void tapLogout()
	{
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		logoutButton.click();
		ExtentReportManager.logPass("Supervisor Logout Successfully", driver, false);
	}
	
	public void validateSupervisorWolooPoints(String expectedPts) throws InterruptedException {
		WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(10));
		localWait.until(ExpectedConditions.visibilityOf(logoutButton));
		localWait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.xpath("//android.view.View[contains(@content-desc,\"woloo points\")]")));
		Thread.sleep(3000);
		String actualPoints = pointsText.getAttribute("Content-desc");
		ExtentReportManager.logInfo("Actual Woloo points: " + actualPoints);
		boolean validatePoints = actualPoints.contains(expectedPts);
		if (validatePoints) {
			ExtentReportManager
					.logPass("Woloo points validation passed. Expected: " + expectedPts + ", Found: " + actualPoints);
		} else {
			ExtentReportManager.logFail(
					"Woloo points validation failed. Expected: " + expectedPts + ", Found: " + actualPoints, driver);
		}
		Assert.assertTrue(validatePoints, "Not a valid woloo points");

	}
}
