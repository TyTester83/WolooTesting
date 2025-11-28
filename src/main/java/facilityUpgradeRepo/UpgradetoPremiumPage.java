package facilityUpgradeRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class UpgradetoPremiumPage extends BasePage {

	public UpgradetoPremiumPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath =  "//android.widget.ImageView[contains(@content-desc,\"Upgrade to a premium plan\")]")
	private WebElement premiumText;

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;

	@AndroidFindBy(accessibility = "PREMIUM")
	private WebElement premiumPlanImage;

	@AndroidFindBy(accessibility = "CLASSIC")
	private WebElement classicPlanImage;

	@AndroidFindBy(accessibility = "Add your Facility for Premium")
	private WebElement addPremiumText;
	
	@AndroidFindBy(accessibility = "Add your Facility for TasqMaster")
	private WebElement addClassicText;

	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement facilityLayout;

	public WebElement getPremiumText() {
		return premiumText;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getPremiumPlanImage() {
		return premiumPlanImage;
	}

	public WebElement getClassicPlanImage() {
		return classicPlanImage;
	}

	public WebElement getAddPremiumText() {
		return addPremiumText;
	}

	public WebElement getFacilityLayout() {
		return facilityLayout;
	}

	public WebElement getAddClassicText() {
		return addClassicText;
	}

	public void premiumScreenUIValidation()
	{
		wait.until(ExpectedConditions.visibilityOf(premiumText));
		
		Assert.assertEquals(backButton.isDisplayed(), true);
		Assert.assertEquals(premiumPlanImage.isDisplayed(), true);
		Assert.assertEquals(classicPlanImage.isDisplayed(), true);

	}
	public void tapPremiumFacility(String facilityName)
	{
		wait.until(ExpectedConditions.visibilityOf(premiumPlanImage));
		
		wait.until(ExpectedConditions.elementToBeClickable(premiumPlanImage)).click();
		
		wait.until(ExpectedConditions.visibilityOf(addPremiumText));
		isFacilityVisible(facilityName);
	}
	public void isFacilityVisible(String facilityName) {
	    wait.until(ExpectedConditions.visibilityOf(premiumPlanImage));

	    String xpath = "//android.widget.ImageView[contains(@content-desc,'" + facilityName + "')]";

	    try {
	        WebElement facilityEle = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath))
	        );
	        if(facilityEle.isDisplayed())
	        {
	        Assert.assertTrue(facilityEle.isDisplayed(), "Facility element is not displayed directly");
	        GenericUtility.tapUsingCoordinatePercentage(driver, facilityEle, 93.0, 63.0);
	        
	        }
	        
	    } catch (Exception e) {
	        boolean isFound = GenericUtility.swipeAndFindElementByDirection(driver, xpath, facilityLayout, "up", 3);
	        if (isFound) {
	            WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(xpath));
	            Assert.assertTrue(expFacilityEle.isDisplayed(), "Facility found after swipe but not visible");
	            GenericUtility.tapUsingCoordinatePercentage(driver, expFacilityEle, 93.0, 63.0);
	        } else {
	            Assert.fail("Facility not found even after swiping" + facilityName);
	        }
	    }
	}
	public void tapClassicFacility(String facilityName)
	{
		wait.until(ExpectedConditions.visibilityOf(classicPlanImage));
		
		wait.until(ExpectedConditions.elementToBeClickable(classicPlanImage)).click();
		
		wait.until(ExpectedConditions.visibilityOf(addClassicText));
		isFacilityVisible(facilityName);
	}
}
