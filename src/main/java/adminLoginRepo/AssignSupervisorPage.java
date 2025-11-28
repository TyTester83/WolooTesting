package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AssignSupervisorPage {

	public AppiumDriver driver;

	public AssignSupervisorPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Assign\") and contains(@content-desc,\"Supervisor\")]")
	private WebElement assignSupervisorLayout;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Supervisor\")]/following-sibling::android.widget.EditText[1]")
	private WebElement supervisorNameTextfield;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Supervisor\")]/following-sibling::android.widget.EditText[2]")
	private WebElement mobileNumTextfield;

	@AndroidFindBy(accessibility = "Next")
	private WebElement nextButton;

	@AndroidFindBy(accessibility = "Name is required")
	private WebElement nameErrorMessage;

	@AndroidFindBy(accessibility = "Mobile number is required")
	private WebElement mobileNumErrorMessage;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement myselfSuperNameTextfield;

	@AndroidFindBy(xpath = "//android.view.View[@index=3]")
	private WebElement myselfMobNotextfield;

	public WebElement getAssignSupervisorLayout() {
		return assignSupervisorLayout;
	}

	public WebElement getSupervisorNameTextfield() {
		return supervisorNameTextfield;
	}

	public WebElement getMobileNumTextfield() {
		return mobileNumTextfield;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public WebElement getNameErrorMessage() {
		return nameErrorMessage;
	}

	public WebElement getMobileNumErrorMessage() {
		return mobileNumErrorMessage;
	}

	public WebElement getMyselfSuperNameTextfield() {
		return myselfSuperNameTextfield;
	}

	public WebElement getMyselfMobNotextfield() {
		return myselfMobNotextfield;
	}

	public void supervisorUIValidation(String admin) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    ExtentReportManager.logInfo("Validating Assign Supervisor screen for admin type: " + admin);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc,\"Assign\") and contains(@content-desc,\"Supervisor\")]")));
	    
	    if (admin.equalsIgnoreCase("Myself")) {
	    	SoftAssertValidationUtility.verifyElementVisible(myselfSuperNameTextfield, "Myself Supervisor Name TextField not displayed");
//	    	ExtentReportManager.logInfo("Verified Myself supervisor name field visibility");
	    	SoftAssertValidationUtility.verifyElementVisible(myselfMobNotextfield, "Myself Mobile Number TextField not displayed");
//	    	ExtentReportManager.logInfo("Verified Myself mobile number field visibility");
	    } else {
	    	SoftAssertValidationUtility.verifyElementVisible(supervisorNameTextfield, "Supervisor Name TextField not displayed");
//	    	ExtentReportManager.logInfo("Verified supervisor name field visibility");
	    	SoftAssertValidationUtility.verifyElementVisible(mobileNumTextfield, "Supervisor Mobile Number TextField not displayed");
//	    	ExtentReportManager.logInfo("Verified supervisor mobile number field visibility");
	    }

	    SoftAssertValidationUtility.verifyElementVisible(nextButton, "Next button is not visible");
	    ExtentReportManager.logPass("Assign Supervisor screen validation completed for " + admin, driver, false);
	}

	public void supervisorDetails(String admin, String superName, String mobNo) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    ExtentReportManager.logInfo("Entering supervisor details for admin type: " + admin);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc,\"Assign\") and contains(@content-desc,\"Supervisor\")]")));
	    try {
	        if (admin.equalsIgnoreCase("Myself")) {
	        	ExtentReportManager.logInfo("Entering 'Myself' supervisor name: " + superName);
	        	 wait.until(ExpectedConditions.visibilityOf(myselfSuperNameTextfield));
	        	wait.until(ExpectedConditions.elementToBeClickable(myselfSuperNameTextfield)).click();
	            myselfSuperNameTextfield.clear();
	            myselfSuperNameTextfield.sendKeys(superName);
	            ExtentReportManager.logPass("Entered supervisor name successfully");
	        } else {
//	        	ExtentReportManager.logInfo("Entering supervisor name: " + superName);
	        	GenericUtility gUtil = new GenericUtility();
	        	gUtil.safeSendKeysWithOutBackKey(supervisorNameTextfield, superName, 2, driver);
	        	ExtentReportManager.logPass("Entered supervisor name");
	        	gUtil.pressBackKey(driver);
//	        	ExtentReportManager.logInfo("Entering mobile number: " + mobNo);
	    		gUtil.safeSendKeysWithOutBackKey(mobileNumTextfield, mobNo, 2, driver);
	    		ExtentReportManager.logPass("Entered mobile number");
	  
	        }

	        // Hide keyboard if present
	        try {
	            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
	            ExtentReportManager.logInfo("Keyboard hidden");
	        } catch (Exception ex) {
	            System.out.println("BACK key press failed: " + ex.getMessage());
	        }
	    } catch (Exception e) {
	        ExtentReportManager.logFail("Failed to enter supervisor details: " + e.getMessage(), driver);
	        throw new RuntimeException("Failed to enter supervisor details: " + e.getMessage(), e);
	    }
	    // pre-check next visible
	    if (!new GenericUtility().isElementVisible(nextButton, driver)) {
	    	ExtentReportManager.logFail("Next button not visible before clicking", driver);
	    	throw new IllegalStateException("Next button not visible");
	    }
	    wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	    ExtentReportManager.logPass("Supervisor details entered and proceeded to next screen", driver, false);
	}

	public void supervisorErrorMessageValidation(String admin) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		ExtentReportManager.logInfo("Validating error messages for empty fields - admin type: " + admin);
		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
		ExtentReportManager.logInfo("Clicked Next without entering supervisor details");

	    if (admin.equalsIgnoreCase("Myself")) {
	        Assert.assertTrue(nameErrorMessage.isDisplayed(), "'Name' error message not displayed for Myself");
	        ExtentReportManager.logPass("Name error message validated for Myself", driver, false);
	    } else {
	        Assert.assertTrue(nameErrorMessage.isDisplayed(), "'Name' error message not displayed for Supervisor");
	        ExtentReportManager.logPass("Name error message validated for Supervisor", driver, false);
	        Assert.assertTrue(mobileNumErrorMessage.isDisplayed(), "'Mobile Number' error message not displayed for Supervisor");
	        ExtentReportManager.logPass("Mobile Number error message validated", driver, false);
	    }
	}
}
