package iotOnboardingRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MonitorStinkLevelPage extends BasePage {

	public MonitorStinkLevelPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(className = "android.widget.ImageView")
	private WebElement monitorText;

	@AndroidFindBy(accessibility = "Skip")
	private WebElement skipButton;

	@AndroidFindBy(accessibility = "Next")
	private WebElement nextButton;

	public WebElement getMonitorText() {
		return monitorText;
	}

	public WebElement getSkipButton() {
		return skipButton;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public void stinkLevelsUIValidate()
	{
		wait.until(ExpectedConditions.visibilityOf(skipButton)); 
		
		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	}
}
