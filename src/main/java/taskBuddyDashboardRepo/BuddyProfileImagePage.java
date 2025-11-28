package taskBuddyDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BuddyProfileImagePage {

	public AppiumDriver driver;

	public BuddyProfileImagePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button")
	private WebElement imageButton;

	@AndroidFindBy(accessibility = "Upload A Profile Image")
	private WebElement uploadImageText;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"PHOTO\"]/ancestor::android.view.View/following-sibling::android.view.View[2]")
	private WebElement cameraTapButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Submit\"]/preceding-sibling::android.widget.ImageView")
	private WebElement imageView;

	public WebElement getImageButton() {
		return imageButton;
	}

	public WebElement getUploadImageText() {
		return uploadImageText;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public WebElement getCameraTapButton() {
		return cameraTapButton;
	}

	public WebElement getImageView() {
		return imageView;
	}

	public void profileUIValidation()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(uploadImageText));

		SoftAssertValidationUtility.verifyElementVisible(imageButton,"Image icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(submitButton,"Submit button should be visible");
		ExtentReportManager.logPass("Buddy Profile Image UI validated");
	}

	public void editProfileImage()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(uploadImageText));
		imageButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(cameraTapButton)).click();
		wait.until(ExpectedConditions.visibilityOf(imageView));
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		ExtentReportManager.logPass("Profile Image uploaded successfully", driver, false);
	}
}
