package taskBuddyDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskbuddyRegularTaskTab {
	public AppiumDriver driver;

	public TaskbuddyRegularTaskTab(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "My Dashboard")
	private WebElement dashboardText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"My Dashboard\"]/following-sibling::android.widget.ImageView[1]")
	private WebElement languageImage;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"My Dashboard\"]/following-sibling::android.widget.ImageView[1]")
	private WebElement profileIconImage;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Present\"]/preceding-sibling::android.view.View[1]")
	private WebElement buddyNameText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Present\"]/preceding-sibling::android.view.View[2]")
	private WebElement locationText;

	@AndroidFindBy(accessibility = "Present")
	private WebElement presentText;

	@AndroidFindBy(accessibility = "IN")
	private WebElement shiftInButton;
	
	@AndroidFindBy(accessibility = "OUT")
	private WebElement shiftOutButton;
	
	@AndroidFindBy(accessibility = "Earn10 points every day you log in! ")
	private WebElement loginEarnPtsText;
	
	@AndroidFindBy(xpath  = "//android.view.View[@content-desc=\"Earn10 points every day you log in! \"]/preceding-sibling::android.widget.ImageView[1]")
	private WebElement CoinsLogoImage;

	@AndroidFindBy(accessibility = "Please Clock-In to see the list of task")
	private WebElement listTaskText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"DashBoard\") and contains(@content-desc,\"Tab\")]")
	private WebElement dashboardTab;
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Regular Task\")]")
	private WebElement regularTaskTab;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"IOT Task\")]")
	private WebElement iotTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"IN\"]/following-sibling::android.view.View[1]")
	private WebElement shiftInTimeText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"IN\"]/following-sibling::android.view.View[4]")
	private WebElement shiftInDateText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Pending task\")]")
	private WebElement pendingTaskTab;

	public WebElement getDashboardText() {
		return dashboardText;
	}

	public WebElement getLanguageImage() {
		return languageImage;
	}

	public WebElement getProfileIconImage() {
		return profileIconImage;
	}

	public WebElement getBuddyNameText() {
		return buddyNameText;
	}

	public WebElement getLocationText() {
		return locationText;
	}

	public WebElement getPresentText() {
		return presentText;
	}

	public WebElement getShiftInButton() {
		return shiftInButton;
	}

	public WebElement getShiftOutButton() {
		return shiftOutButton;
	}

	public WebElement getLoginEarnPtsText() {
		return loginEarnPtsText;
	}

	public WebElement getCoinsLogoImage() {
		return CoinsLogoImage;
	}

	public WebElement getListTaskText() {
		return listTaskText;
	}

	public WebElement getDashboardTab() {
		return dashboardTab;
	}

	public WebElement getRegularTaskTab() {
		return regularTaskTab;
	}

	public WebElement getIotTaskTab() {
		return iotTaskTab;
	}

	public WebElement getShiftInTimeText() {
		return shiftInTimeText;
	}

	public WebElement getShiftInDateText() {
		return shiftInDateText;
	}

	public WebElement getPendingTaskTab() {
		return pendingTaskTab;
	}
	

}
