package adminDashboardRepo;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChooseExistingTaskbuddyPopup extends BasePage {

	public ChooseExistingTaskbuddyPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Choose an Existing Task Buddy")
	private WebElement existingTaskbuddyText;

	@AndroidFindBy(accessibility = "Okay")
	private WebElement okButton;

	@AndroidFindBy(accessibility = "Please select task buddy")
	private WebElement errorMsgText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Choose an Existing Task Buddy\"]/following-sibling::android.view.View[1]/android.view.View")
	private WebElement optionScrollingLayout;

	public WebElement getExistingTaskbuddyText() {
		return existingTaskbuddyText;
	}

	public WebElement getOkButton() {
		return okButton;
	}

	public WebElement getErrorMsgText() {
		return errorMsgText;
	}

	public WebElement getOptionScrollingLayout() {
		return optionScrollingLayout;
	}

	public void existingTaskBuddyUIValidation() {
		ExtentReportManager.logInfo("Validating Choose Existing Task Buddy popup UI elements");
		SoftAssertValidationUtility.verifyElementVisible(existingTaskbuddyText,existingTaskbuddyText+"Element to be visible");
//		ExtentReportManager.logPass("Existing Task Buddy text is visible");
		SoftAssertValidationUtility.verifyElementVisible(okButton,okButton+"Element to be visible");
		ExtentReportManager.logPass("OK button is visible");
	}

	public void tapOkButtonWithoutChoosingTaskbuddy() {
		ExtentReportManager.logInfo("Clicking OK button without selecting task buddy to validate error message");
		wait.until(ExpectedConditions.visibilityOf(existingTaskbuddyText));
		okButton.click();
		ExtentReportManager.logPass("OK button clicked");
		if (errorMsgText.isDisplayed()) {
			ExtentReportManager.logPass("Error message displayed as expected");
		} else {
			ExtentReportManager.logFail("Error message not displayed when expected", driver);
		}
		Assert.assertEquals(true, errorMsgText.isDisplayed());
	}
	public void chooseTaskbuddy(String buddyName)
	{
	    ExtentReportManager.logInfo("Selecting task buddy: " + buddyName);
	    wait.until(ExpectedConditions.visibilityOf(existingTaskbuddyText));

	    String xpath = "//android.widget.RadioButton[contains(@content-desc,'" + buddyName + "')]";

	    boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, optionScrollingLayout, "up", 5);

	    if (found) {
	        WebElement taskbuddyOption = driver.findElement(AppiumBy.xpath(xpath));
	        ExtentReportManager.logPass("Task buddy '" + buddyName + "' found after scrolling");
	        wait.until(ExpectedConditions.elementToBeClickable(taskbuddyOption));
	        taskbuddyOption.click();
	        ExtentReportManager.logPass("Task buddy '" + buddyName + "' selected");
	    } else {
	        ExtentReportManager.logFail("Task buddy '" + buddyName + "' not found after scrolling", driver);
	        throw new NoSuchElementException(" Taskbuddy '" + buddyName + "' not found after scrolling.");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(okButton));
	    okButton.click();
	    ExtentReportManager.logPass("OK button clicked to confirm task buddy selection");
	}
}
