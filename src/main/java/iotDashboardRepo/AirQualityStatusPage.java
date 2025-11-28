package iotDashboardRepo;

import org.openqa.selenium.WebElement;

import Utilities.BasePage;
import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AirQualityStatusPage extends BasePage {

	public AirQualityStatusPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "AI Generated Summary\r\n" + "Close")
	private WebElement aiSummaryLayout;

	@AndroidFindBy(xpath = "//android.widget.Button")
	private WebElement closeButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Air Quality Alert Analysis\")]")
	private WebElement analysisText;

	@AndroidFindBy(accessibility = "Summary generated successfully")
	private WebElement generatedSummaryText;

	public WebElement getAiSummaryLayout() {
		return aiSummaryLayout;
	}

	public WebElement getCloseButton() {
		return closeButton;
	}

	public WebElement getAnalysisText() {
		return analysisText;
	}

	public WebElement getGeneratedSummaryText() {
		return generatedSummaryText;
	}

}
