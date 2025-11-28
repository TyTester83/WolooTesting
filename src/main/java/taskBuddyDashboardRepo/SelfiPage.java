package taskBuddyDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SelfiPage {

	public AppiumDriver driver;

	public SelfiPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button")
	private WebElement imageButton;

	@AndroidFindBy(accessibility = "Take a selfie")
	private WebElement selfiText;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"PHOTO\"]/ancestor::android.view.View/following-sibling::android.view.View[2]")
	private WebElement cameraTapButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Submit\"]/preceding-sibling::android.widget.ImageView")
	private WebElement imageView;

	public WebElement getImageButton() {
		return imageButton;
	}

	public WebElement getSelfiText() {
		return selfiText;
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
	public void taskImageUIValidation()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(selfiText));

		SoftAssertValidationUtility.verifyElementVisible(imageButton,"Image icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(submitButton,"Submit button should be visible");
		ExtentReportManager.logPass("Buddy Task Selfi Image UI validated");
	}

	public void takeImageBeforeCleaning()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(selfiText));
		wait.until(ExpectedConditions.elementToBeClickable(imageButton)).click();
		
		try {
			WebElement ele=driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_message"));
			if(ele.isDisplayed())
			{
				handlePermissions();
			}
		} catch (Exception e) {
			System.out.println("There is no permission");
		}
		wait.until(ExpectedConditions.elementToBeClickable(cameraTapButton)).click();
		wait.until(ExpectedConditions.visibilityOf(imageView));
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		ExtentReportManager.logPass("Buddy Task Selfi Image Submitted");
	}
	private void handlePermissions() {
		if (driver instanceof AndroidDriver) {
			String[] allowButtons = { "com.android.permissioncontroller:id/permission_allow_button",
					"android:id/button1",
					"com.android.permissioncontroller:id/permission_allow_foreground_only_button" };
			
			for (String id : allowButtons) {
				try {
					WebElement btn = driver.findElement(AppiumBy.id(id));
					if (btn.isDisplayed()) {
						btn.click();
						Thread.sleep(500);
					}
				} catch (Exception ignored) {
				}
			}
		}
	}
}
