package taskBuddyDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskPhotosPage {

	public AppiumDriver driver;

	public TaskPhotosPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;
	
	@AndroidFindBy(accessibility = "Add Photos of loo")
	private WebElement looText;
	
	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Add Photo\"])[1]")
	private WebElement addPhotoImage1;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[1]/following-sibling::android.view.View[1]")
	private WebElement deleteImage;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement taskImage;
	
	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement remarksTextfield;
	
	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"PHOTO\"]/ancestor::android.view.View/following-sibling::android.view.View[2]")
	private WebElement cameraTapButton;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getLooText() {
		return looText;
	}

	public WebElement getAddPhotoImage1() {
		return addPhotoImage1;
	}

	public WebElement getDeleteImage() {
		return deleteImage;
	}

	public WebElement getRemarksTextfield() {
		return remarksTextfield;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public WebElement getCameraTapButton() {
		return cameraTapButton;
	}

	public WebElement getTaskImage() {
		return taskImage;
	}

	public void completedTaskImage()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(looText));
		

		try {
			if(addPhotoImage1.isDisplayed())
			{
				wait.until(ExpectedConditions.elementToBeClickable(addPhotoImage1)).click();
				wait.until(ExpectedConditions.elementToBeClickable(cameraTapButton)).click();
				wait.until(ExpectedConditions.visibilityOf(taskImage));
			}
		}catch (Exception ex) {
            System.out.println("Image is not displayed " + ex.getMessage());
        }
		
		wait.until(ExpectedConditions.visibilityOf(remarksTextfield));
		remarksTextfield.click();
		remarksTextfield.clear();
		remarksTextfield.sendKeys("Cleaning done");
		try {
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        } catch (Exception ex) {
            System.out.println("BACK key also failed: " + ex.getMessage());
        }
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		ExtentReportManager.logPass("Buddy Uploaded the Task Image and remarks");
	}
}
