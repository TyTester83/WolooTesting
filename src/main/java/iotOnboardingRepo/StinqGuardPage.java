package iotOnboardingRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StinqGuardPage extends BasePage {
	
	public StinqGuardPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(className = "android.widget.ImageView")
	private WebElement stinqGuardText;

	@AndroidFindBy(accessibility = "Skip")
	private WebElement skipButton;

	@AndroidFindBy(accessibility = "Next")
	private WebElement nextButton;

	public WebElement getStinqGuardText() {
		return stinqGuardText;
	}

	public WebElement getSkipButton() {
		return skipButton;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public void stinqGuardUIValidate() {
		wait.until(ExpectedConditions.visibilityOf(skipButton));

		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	}
	public void onboardingScreen()
	{
		try
		{
			MonitorStinkLevelPage stinkPage=new MonitorStinkLevelPage(driver);
			MonitorWalkInPage walkInPage=new MonitorWalkInPage(driver);
			
			if(stinkPage.getSkipButton().isDisplayed())
			{
				stinkPage.stinkLevelsUIValidate();
				walkInPage.walkInsUIValidate();
				stinqGuardUIValidate();
			}
		}
		catch (Exception e) {
			System.out.println("There is no onboarding screen");
		}
	}
}
