package supervisorDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProfileImagePage extends BasePage {

	public ProfileImagePage(AppiumDriver driver) {
		super(driver);
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
		wait.until(ExpectedConditions.visibilityOf(uploadImageText));

		SoftAssertValidationUtility.verifyElementVisible(imageButton,"Image icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(submitButton,"Submit button should be visible");
		ExtentReportManager.logPass("Supervisor Profile UI validated");
	}

	public void editProfileImage()
	{
		wait.until(ExpectedConditions.visibilityOf(uploadImageText));
		imageButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(cameraTapButton)).click();
		wait.until(ExpectedConditions.visibilityOf(imageView));
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		ExtentReportManager.logPass("Supervisor Profile Image Uploaded");
	}
}
