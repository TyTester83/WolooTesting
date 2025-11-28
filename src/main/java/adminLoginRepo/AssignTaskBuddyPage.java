
package adminLoginRepo;

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

public class AssignTaskBuddyPage {

	public AppiumDriver driver;

	public AssignTaskBuddyPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Assign\")]/parent::android.view.View")
	private WebElement taskBuddyLayout;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Assign\")]")
	private WebElement taskBuddyText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Assign\")]/following-sibling::android.widget.EditText[1]")
	private WebElement taskBuddyNameTextfield;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Assign\")]/following-sibling::android.widget.EditText[2]")
	private WebElement mobileNumTextfield;

	@AndroidFindBy(accessibility = "Gender *")
	private WebElement genderText;

	@AndroidFindBy(accessibility = "Female")
	private WebElement femaleImageview;

	@AndroidFindBy(accessibility = "Male")
	private WebElement maleImageview;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Female\"]/android.widget.ImageView[2]")
	private WebElement femaleTickImage;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Male\"]/android.widget.ImageView[2]")
	private WebElement maleTickImage;

	@AndroidFindBy(accessibility = "Name is required")
	private WebElement nameErrorMsgText;

	@AndroidFindBy(accessibility = "Enter a valid 10-digit number")
	private WebElement mobileNumErrorMsgText;

	@AndroidFindBy(accessibility = "Please select gender")
	private WebElement genderErrorMsgText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Female\"]/android.widget.ImageView[1]")
	private WebElement femaleImage;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Male\"]/android.widget.ImageView[1]")
	private WebElement maleImage;

	public WebElement getTaskBuddyLayout() {
		return taskBuddyLayout;
	}

	public WebElement getTaskBuddyText() {
		return taskBuddyText;
	}

	public WebElement getTaskBuddyNameTextfield() {
		return taskBuddyNameTextfield;
	}

	public WebElement getMobileNumTextfield() {
		return mobileNumTextfield;
	}

	public WebElement getGenderText() {
		return genderText;
	}

	public WebElement getFemaleImageview() {
		return femaleImageview;
	}

	public WebElement getMaleImageview() {
		return maleImageview;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getFemaleTickImage() {
		return femaleTickImage;
	}

	public WebElement getMaleTickImage() {
		return maleTickImage;
	}

	public WebElement getNameErrorMsgText() {
		return nameErrorMsgText;
	}

	public WebElement getMobileNumErrorMsgText() {
		return mobileNumErrorMsgText;
	}

	public WebElement getGenderErrorMsgText() {
		return genderErrorMsgText;
	}

	public WebElement getFemaleImage() {
		return femaleImage;
	}

	public WebElement getMaleImage() {
		return maleImage;
	}

	public void taskBuddyUIValidation() {

		       ExtentReportManager.logInfo("Validating Task Buddy UI elements");
		       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		       wait.until(ExpectedConditions.visibilityOf(genderText));
//		       ExtentReportManager.logInfo("Gender text is visible");

		       SoftAssertValidationUtility.verifyElementVisible(taskBuddyNameTextfield, "Task Buddy Name field is not visible.");
//		       ExtentReportManager.logInfo("Task Buddy Name field is visible");
		       SoftAssertValidationUtility.verifyElementVisible(mobileNumTextfield, "Mobile Number field is not visible.");
//		       ExtentReportManager.logInfo("Mobile Number field is visible");
		       SoftAssertValidationUtility.verifyElementVisible(femaleImageview, "Female option image is not visible.");
//		       ExtentReportManager.logInfo("Female option image is visible");
		       SoftAssertValidationUtility.verifyElementVisible(maleImageview, "Male option image is not visible.");
//		       ExtentReportManager.logInfo("Male option image is visible");
//		       SoftAssertValidationUtility.verifyElementVisible(submitButton, "Submit button is not visible.");
//		       ExtentReportManager.logInfo("Submit button is visible");
		       ExtentReportManager.logPass("Task Buddy UI elements are validated successfully");

	}

	public void taskBuddyDetailsFill(String buddyName, String mobNo, String gender) throws InterruptedException {

		   ExtentReportManager.logInfo("Filling Task Buddy details: Name, Mobile, Gender");
		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		   GenericUtility gUtil=new GenericUtility();
		   wait.until(ExpectedConditions.visibilityOf(taskBuddyNameTextfield));
		   ExtentReportManager.logPass("Task Buddy Name textfield is visible");
		   gUtil.safeSendKeysWithOutBackKey(taskBuddyNameTextfield, buddyName, 3, driver);
		   ExtentReportManager.logPass("Entered Task Buddy Name: " + buddyName);
		   gUtil.pressBackKey(driver);
		   gUtil.safeSendKeysWithOutBackKey(mobileNumTextfield, mobNo, 3, driver);
		   ExtentReportManager.logPass("Entered Mobile Number: " + mobNo);
		   gUtil.pressBackKey(driver);
		   wait.until(ExpectedConditions.visibilityOf(femaleImageview));
		   ExtentReportManager.logPass("Gender icons are visible");

		   if (gender.equalsIgnoreCase("F")) {
			   gUtil.safeClick(femaleImageview, 2, driver);
			   ExtentReportManager.logPass("Selected Female gender");
			   Assert.assertTrue(femaleTickImage.isDisplayed(), "Female tick mark not displayed after selection.");
			   ExtentReportManager.logPass("Female tick mark displayed");
		   } else {
			   gUtil.safeClick(maleImageview, 2, driver);
			   ExtentReportManager.logPass("Selected Male gender");
			   Assert.assertTrue(maleTickImage.isDisplayed(), "Male tick mark not displayed after selection.");
			   ExtentReportManager.logPass("Male tick mark displayed");
		   }

		   try {
			   WebElement submitElement = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Submit\"]"));
			   if (submitElement.isDisplayed()) {
				   gUtil.safeClick(submitElement, 2, driver);
				   ExtentReportManager.logPass("Clicked Submit button");
			   }
		   } catch (Exception e) {
			   String xpath = "//android.view.View[@content-desc=\"Submit\"]";
			   boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, taskBuddyLayout, "up", 3);
			   if (!found) {
				   ExtentReportManager.logFail("Submit button not found after swiping", driver);
				   Assert.fail("Element not found after swiping.");
			   } else {
				   WebElement submitElement = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Submit\"]"));
				   gUtil.safeClick(submitElement, 2, driver);
				   ExtentReportManager.logPass("Clicked Submit button after swiping");
			   }
		   }

	}

	public void taskBuddyErrorMessageValidation() {

		ExtentReportManager.logInfo("Clicking Submit button for error validation");
		submitButton.click();
		ExtentReportManager.logPass("Clicked Submit button without enter taskbuddy details");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(nameErrorMsgText));
		ExtentReportManager.logPass("Name error message is visible");

		Assert.assertTrue(nameErrorMsgText.isDisplayed(), "Name error message is not displayed.");
		ExtentReportManager.logPass("Name error message is displayed");
		Assert.assertTrue(mobileNumErrorMsgText.isDisplayed(), "Mobile number error message is not displayed.");
		ExtentReportManager.logPass("Mobile number error message is displayed");
		Assert.assertTrue(genderErrorMsgText.isDisplayed(), "Gender error message is not displayed.");
		ExtentReportManager.logPass("Gender error message is displayed");
	}

	public void existingTaskBuddyMobileNoValidation(String buddyName, String oldUserMobileNo, String gender,
			String newUserMobileNo) throws InterruptedException {

		ExtentReportManager.logInfo("Validating existing Task Buddy mobile number");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		GenericUtility gUtil=new GenericUtility();
		taskBuddyDetailsFill(buddyName, oldUserMobileNo, gender);
		ExtentReportManager.logPass("Filled Task Buddy details with old mobile number");
		wait.until(ExpectedConditions.visibilityOf(mobileNumTextfield));
		gUtil.safeSendKeysWithOutBackKey(mobileNumTextfield, newUserMobileNo, 3, driver);
		ExtentReportManager.logPass("Entered new mobile number: " + newUserMobileNo);
		gUtil.pressBackKey(driver);
		gUtil.safeClick(submitButton, 2, driver);
		ExtentReportManager.logPass("Clicked Submit button for mobile number validation");
	}
}
