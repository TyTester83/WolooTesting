package taskBuddyDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BuddyProfilePage {

	public AppiumDriver driver;

	public BuddyProfilePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;
	
	@AndroidFindBy(accessibility = "My Profile")
	private WebElement profileText;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"My Profile\")]/following-sibling::android.widget.ImageView[2]")
	private WebElement imageEditIcon;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"My Profile\")]/following-sibling::android.widget.ImageView[1]")
	private WebElement profileImage;
	
	@AndroidFindBy(accessibility = "Attendance history")
	private WebElement attendanceImage;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"woloo points\")]")
	private WebElement buddyWolooPtsText;
	
	@AndroidFindBy(accessibility = "Log Out")
	private WebElement logoutImage;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getProfileText() {
		return profileText;
	}

	public WebElement getImageEditIcon() {
		return imageEditIcon;
	}

	public WebElement getProfileImage() {
		return profileImage;
	}

	public WebElement getAttendanceImage() {
		return attendanceImage;
	}

	public WebElement getLogoutImage() {
		return logoutImage;
	}
	
	public WebElement getBuddyWolooPtsText() {
		return buddyWolooPtsText;
	}

	public void buddyProfileUIValidation(String mobNO, String buddyName) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(profileText));
		Thread.sleep(3000);
		SoftAssertValidationUtility.verifyElementVisible(imageEditIcon,imageEditIcon+"Image edit icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(logoutImage, logoutImage+"Logout button should be visible");
		
		WebElement actualMobileNo=driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+mobNO+"')]"));
		SoftAssertValidationUtility.verifyElementVisible(actualMobileNo, "Valid Taskbuddy mobile number should be displayed");
		ExtentReportManager.logPass("Buddy Profile UI validated");
	
	}

	public void tapLogout()
	{
//		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(profileText));
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(logoutImage));
//		element.click();
		GenericUtility gUtil = new GenericUtility();
		gUtil.safeClick(logoutImage, 3,driver);
		ExtentReportManager.logPass("Buddy Logout Button Clicked successfully");
	}
	
	public void wolooPointValidation(String wolooPts)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(profileText));
		
		boolean actualPts=buddyWolooPtsText.getAttribute("content-desc").contains(wolooPts);
		Assert.assertTrue(actualPts, "Not a valid points");
		ExtentReportManager.logPass("Buddy Woloo points validated");
	}
}
