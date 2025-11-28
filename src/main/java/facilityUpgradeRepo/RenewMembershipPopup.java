package facilityUpgradeRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RenewMembershipPopup extends BasePage {

	public RenewMembershipPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Renew Your Membership")
	private WebElement renewText;

	@AndroidFindBy(accessibility = "Renew Membership after the current Membership gets over")
	private WebElement renewAfterRadioButton;

	@AndroidFindBy(accessibility = "Renew Membership immediately")
	private WebElement renewNowRadioButton;

	@AndroidFindBy(accessibility = "Continue")
	private WebElement continueButton;

	@AndroidFindBy(accessibility = "Cancel")
	private WebElement cancelButton;
	
	@AndroidFindBy(accessibility = "Please select Above option to renew membership")
	private WebElement errorMessageText;

	public WebElement getRenewText() {
		return renewText;
	}

	public WebElement getRenewAfterRadioButton() {
		return renewAfterRadioButton;
	}

	public WebElement getRenewNowRadioButton() {
		return renewNowRadioButton;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getErrorMessageText() {
		return errorMessageText;
	}

	public void renewMembershipUIValidation()
	{
		wait.until(ExpectedConditions.visibilityOf(renewText));
		
		Assert.assertEquals(renewAfterRadioButton.isDisplayed(), true);
		Assert.assertEquals(renewNowRadioButton.isDisplayed(), true);
		Assert.assertEquals(continueButton.isDisplayed(), true);
		Assert.assertEquals(cancelButton.isDisplayed(), true);
		
	}
	public void tapRenewButton(String renewPlan)
	{
		wait.until(ExpectedConditions.visibilityOf(renewText));
		
		if(renewPlan.contains("Renew Immediately"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(renewNowRadioButton)).click();
		}
		else if(renewPlan.contains("Renew Later"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(renewAfterRadioButton)).click();
		}
		else
		{
			System.out.println("There is no option matching");
		}
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}
	public void tapContinueButtonWithoutChoosingPlanOPtion()
	{
		wait.until(ExpectedConditions.visibilityOf(renewText));
		
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
		
		Assert.assertTrue(errorMessageText.isDisplayed(), "Error message should be display");
	}
}
