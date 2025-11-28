package iotOnboardingRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MonitorWalkInPage extends BasePage {

	public MonitorWalkInPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(className = "android.widget.ImageView")
	private WebElement monitorWalkinText;

	@AndroidFindBy(accessibility = "Skip")
	private WebElement skipButton;

	@AndroidFindBy(accessibility = "Next")
	private WebElement nextButton;

	public WebElement getMonitorWalkinText() {
		return monitorWalkinText;
	}

	public WebElement getSkipButton() {
		return skipButton;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public void walkInsUIValidate() {
		wait.until(ExpectedConditions.visibilityOf(skipButton));

		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	}
}
