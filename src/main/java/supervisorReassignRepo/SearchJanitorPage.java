package supervisorReassignRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchJanitorPage {
	public AppiumDriver driver;

	public SearchJanitorPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;
	
	@AndroidFindBy(accessibility = "Search Janitor")
	private WebElement janitorText;

	@AndroidFindBy(accessibility = "Cluster Name")
	private WebElement clusterDropdown;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Facility\"]")
	private WebElement facilityDropdown;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search\"]")
	private WebElement searchButton;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getJanitorText() {
		return janitorText;
	}

	public WebElement getClusterDropdown() {
		return clusterDropdown;
	}

	public WebElement getFacilityDropdown() {
		return facilityDropdown;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public void tapReassignByName_MobileNo(String adminMobileNo, String facilityName, String buddyName, String buddyMobileNo)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(clusterDropdown));
		
		wait.until(ExpectedConditions.elementToBeClickable(clusterDropdown)).click();
		
		ClusterSearchPage clusterSearchPage=new ClusterSearchPage(driver);
		clusterSearchPage.searchClusterByMobileNo(adminMobileNo);
		
		wait.until(ExpectedConditions.elementToBeClickable(facilityDropdown)).click();
		FacilitySearchPage facilitySearchPage=new FacilitySearchPage(driver);
		facilitySearchPage.searchFacilityByName(facilityName);
		
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		
		try {
			WebElement facilityElement = driver
					.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+buddyName+"') and contains(@content-desc,'"+buddyMobileNo+"')]/android.view.View"));
			if (facilityElement.isDisplayed()) {
				wait.until(ExpectedConditions.elementToBeClickable(facilityElement)).click();
				ExtentReportManager.logPass("Tapped the Reassign button for valid Janitor", driver, false);
			}
		} catch (Exception e) {

			String xpath = "//android.view.View[contains(@content-desc,'"+buddyName+"') and contains(@content-desc,'"+buddyMobileNo+"')]/android.view.View";
			WebElement ScrollElement = driver
					.findElement(AppiumBy.xpath("//android.widget.ScrollView"));
			boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, ScrollElement, "up",
					3);

			if (found) {
				WebElement facilityElement = driver
						.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+buddyName+"') and contains(@content-desc,'"+buddyMobileNo+"')]/android.view.View"));
				wait.until(ExpectedConditions.elementToBeClickable(facilityElement)).click();
				ExtentReportManager.logPass("Tapped the Reassign button for valid Janitor", driver, false);
			} else {
				System.out.println("Buddy Not found-----> " + buddyName);
				
			}
		}
	}
}
