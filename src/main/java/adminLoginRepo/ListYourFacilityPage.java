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

public class ListYourFacilityPage {

	public AppiumDriver driver;

	public ListYourFacilityPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"List Your\")]/parent::android.view.View")
	private WebElement listYourFacilityScreen;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"List Your\")]")
	private WebElement listYourFacilityTextview;

	@AndroidFindBy(xpath = "//android.widget.EditText[1]")
	private WebElement facilityNameTextfield;

	@AndroidFindBy(xpath = "//android.widget.EditText[2]")
	private WebElement locationTextfield;

	@AndroidFindBy(accessibility = "Home")
	private WebElement homeFacilityImage;

	@AndroidFindBy(accessibility = "Office")
	private WebElement officeFacilityImage;

	@AndroidFindBy(accessibility = "Restaurant")
	private WebElement restrauntFacilityImage;

	@AndroidFindBy(accessibility = "Others")
	private WebElement othersFacilityImage;

	@AndroidFindBy(xpath = "//android.widget.EditText[3]")
	private WebElement othersFacilityTextfield;

	@AndroidFindBy(accessibility = "Next")
	private WebElement nextButton;

	@AndroidFindBy(accessibility = "Facility Name is required")
	private WebElement facilityNameErrorMessage;

	@AndroidFindBy(accessibility = "Please mention location")
	private WebElement locationErrorMessage;

	@AndroidFindBy(accessibility = "Please select a facility")
	private WebElement facilityTypeErrorMessage;

	@AndroidFindBy(accessibility = "Please mention other type")
	private WebElement otherTypeErrorMessage;

	@AndroidFindBy(xpath = "//android.widget.Button")
	private WebElement locationCancelButton;

	@AndroidFindBy(accessibility = "Your Facility Name *")
	private WebElement existingFacilityDropdown;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]/android.view.View")
	private WebElement locationsuggestDropdown;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Bengaluru, Karnataka, India\"])[4]")
	private WebElement locationText;

	public WebElement getListYourFacilityScreen() {
		return listYourFacilityScreen;
	}

	public WebElement getLocationText() {
		return locationText;
	}

	public WebElement getListYourFacilityTextview() {
		return listYourFacilityTextview;
	}

	public WebElement getFacilityNameTextfield() {
		return facilityNameTextfield;
	}

	public WebElement getLocationTextfield() {
		return locationTextfield;
	}

	public WebElement getHomeFacilityImage() {
		return homeFacilityImage;
	}

	public WebElement getOfficeFacilityImage() {
		return officeFacilityImage;
	}

	public WebElement getRestrauntFacilityImage() {
		return restrauntFacilityImage;
	}

	public WebElement getOthersFacilityImage() {
		return othersFacilityImage;
	}

	public WebElement getOthersFacilityTextfield() {
		return othersFacilityTextfield;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public WebElement getFacilityNameErrorMessage() {
		return facilityNameErrorMessage;
	}

	public WebElement getLocationErrorMessage() {
		return locationErrorMessage;
	}

	public WebElement getFacilityTypeErrorMessage() {
		return facilityTypeErrorMessage;
	}

	public WebElement getOtherTypeErrorMessage() {
		return otherTypeErrorMessage;
	}

	public WebElement getLocationCancelButton() {
		return locationCancelButton;
	}

	public WebElement getExistingFacilityDropdown() {
		return existingFacilityDropdown;
	}

	public WebElement getLocationsuggestDropdown() {
		return locationsuggestDropdown;
	}

	public void facilityUIvalidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(listYourFacilityTextview));

		ExtentReportManager.logInfo("Validating List Your Facility screen elements");
		SoftAssertValidationUtility.verifyElementVisible(listYourFacilityTextview,
				"List Your Facility heading should be visible.");
//		ExtentReportManager.logInfo("Verified heading visibility");

		SoftAssertValidationUtility.verifyElementVisible(facilityNameTextfield,
				"Facility Name field should be visible.");
//		ExtentReportManager.logInfo("Verified facility name field visibility");

		SoftAssertValidationUtility.verifyElementVisible(locationTextfield, "Location field should be visible.");
//		ExtentReportManager.logInfo("Verified location field visibility");

		SoftAssertValidationUtility.verifyElementVisible(homeFacilityImage, "Home facility option should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(officeFacilityImage,
				"Office facility option should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(restrauntFacilityImage,
				"Restaurant facility option should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(othersFacilityImage,
				"Others facility option should be visible.");
//		ExtentReportManager.logInfo("Verified facility type options visibility");

		SoftAssertValidationUtility.verifyElementVisible(nextButton, "Next button should be visible.");
		ExtentReportManager.logPass("List Your Facility screen validation completed", driver, false);

	}

	public void facilityDetailsForm(String facilityName, String location, String facilityType, String otherName)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(facilityNameTextfield));
		GenericUtility gUtil = new GenericUtility();
		ExtentReportManager.logInfo("Filling facility form: name='" + facilityName + "', location='" + location + "'");
		gUtil.safeSendKeysWithOutBackKey(facilityNameTextfield, facilityName, 3, driver);
		ExtentReportManager.logPass("Facility Name Entered Successfully");
		gUtil.safeSendKeysWithOutBackKey(locationTextfield, location, 3, driver);
		ExtentReportManager.logPass("Location Entered Successfully");

		WebElement suggestion = driver.findElement(
				AppiumBy.xpath("//android.view.View[@clickable='true' and @content-desc='" + location + "']"));
		// pre-check
		if (!gUtil.isElementVisible(suggestion, driver)) {
			ExtentReportManager.logFail("Location suggestion not visible: " + location, driver);
			throw new IllegalStateException("Location suggestion not visible");
		}
		gUtil.safeClick(suggestion, 3, driver);
		ExtentReportManager.logPass("Selected the suggested location", driver, false);

		pressBackKey();
		wait.until(ExpectedConditions.visibilityOf(homeFacilityImage));
//		ExtentReportManager.logInfo("Selecting facility type: " + facilityType);
		chooseFacilityType(facilityType, otherName);

		// Tick icon confirmation
		WebElement tickIcon = driver.findElement(AppiumBy.xpath(
				"//android.view.View[contains(@content-desc,'" + facilityType + "')]/android.widget.ImageView[2]"));
		wait.until(ExpectedConditions.visibilityOf(tickIcon));
		Assert.assertTrue(tickIcon.isDisplayed(), "Tick icon should be visible for selected facility type.");
		ExtentReportManager.logPass("Facility type tick visible");
		try {
			WebElement nextElement = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Next\"]"));
			if (nextElement.isDisplayed()) {
				gUtil.safeClick(nextElement, 2, driver);
				ExtentReportManager.logPass("Clicked Next button", driver, false);
			}

		} catch (Exception e) {
			String nextElementXpath = "//android.view.View[@content-desc=\"Next\"]";
			boolean found = GenericUtility.swipeAndFindElementByDirection(driver, nextElementXpath,
					listYourFacilityScreen, "up", 3);
			if (!found) {
				ExtentReportManager.logFail("Next button not found after swiping.", driver);
				Assert.fail("Element not found after swiping.");
			} else {

				WebElement nextElement = driver
						.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Next\"]"));
				gUtil.safeClick(nextElement, 2, driver);
				ExtentReportManager.logPass("Clicked Next butten after swipe", driver, false);
			}
		}

	}

	public void facilityEmptyFieldErrorMessageValidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(nextButton)).click();

		Assert.assertTrue(facilityNameErrorMessage.isDisplayed(), "Facility Name error message should be shown.");
		Assert.assertTrue(locationErrorMessage.isDisplayed(), "Location error message should be shown.");
		Assert.assertTrue(facilityTypeErrorMessage.isDisplayed(), "Facility Type error message should be shown.");
	}

	public void tapNextButtonWithoutLocation(String facilityName, String facilityType, String otherName)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		GenericUtility gUtil = new GenericUtility();
		gUtil.safeSendKeysWithOutBackKey(facilityNameTextfield, facilityName, 3, driver);
		ExtentReportManager.logPass("Facility Name Entered Successfully");
		pressBackKey();
		wait.until(ExpectedConditions.elementToBeClickable(homeFacilityImage));
		chooseFacilityType(facilityType, otherName);
		ExtentReportManager.logPass("Facility Type selected Successfully");
		WebElement tickIcon = driver.findElement(AppiumBy
				.xpath("//android.view.View[@content-desc='" + facilityType + "']/android.widget.ImageView[2]"));
		wait.until(ExpectedConditions.visibilityOf(tickIcon));
		Assert.assertTrue(tickIcon.isDisplayed(), "Tick icon should be visible for selected facility type.");
		ExtentReportManager.logPass("Selected Facility Type Tick Icon validated Successfully");

		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
		Assert.assertTrue(locationErrorMessage.isDisplayed(), "Location error message should be shown.");
	}

	public void chooseFacilityfromDropdown(String facilityName, String location, String facilityType) {
		GenericUtility gUtil = new GenericUtility();
		ExtentReportManager.logInfo("Opening existing facility dropdown");
		try {
			// pre-check dropdown visible
			if (!gUtil.isElementVisible(existingFacilityDropdown, driver)) {
				ExtentReportManager.logFail("Existing facility dropdown not visible", driver);
				throw new IllegalStateException("Existing facility dropdown not visible");
			}
			gUtil.safeClick(existingFacilityDropdown, 2, driver);
			ExtentReportManager.logPass("Opened existing facility dropdown");

//			ExtentReportManager.logInfo("Selecting facility: " + facilityName);
			WebElement facility = driver.findElement(
					AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + facilityName + "')]"));
			if (!gUtil.isElementVisible(facility, driver)) {
				ExtentReportManager.logFail("Facility option not visible: " + facilityName, driver);
				throw new IllegalStateException("Facility option not visible: " + facilityName);
			}
			gUtil.safeClick(facility, 2, driver);
			ExtentReportManager.logPass("Selected facility: " + facilityName);

			// Click Next and verify navigation
			if (!gUtil.isElementVisible(nextButton, driver)) {
				ExtentReportManager.logFail("Next button not visible before clicking", driver);
				throw new IllegalStateException("Next button not visible");
			}

			gUtil.safeClick(nextButton, 3, driver);
			ExtentReportManager.logPass("Facility selected and navigated forward: " + facilityName, driver, false);

		} catch (Exception e) {
			ExtentReportManager.logFail("chooseFacilityfromDropdown failed: " + e.getMessage(), driver);
		}
	}

	private void pressBackKey() {
		try {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		} catch (Exception ex) {
			System.out.println("BACK key failed: " + ex.getMessage());
		}
	}

	private void chooseFacilityType(String facilityType, String otherName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		switch (facilityType.toLowerCase()) {
		case "home":
			wait.until(ExpectedConditions.elementToBeClickable(homeFacilityImage)).click();
			break;
		case "office":
			wait.until(ExpectedConditions.elementToBeClickable(officeFacilityImage)).click();
			break;
		case "restaurant":
			wait.until(ExpectedConditions.elementToBeClickable(restrauntFacilityImage)).click();
			break;
		default:
			wait.until(ExpectedConditions.elementToBeClickable(othersFacilityImage)).click();
			wait.until(ExpectedConditions.visibilityOf(othersFacilityTextfield)).click();
			othersFacilityTextfield.sendKeys(otherName);
			pressBackKey();
			break;
		}
	}
}
