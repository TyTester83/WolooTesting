package supervisorDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class JanitorListPage extends BasePage {

	public JanitorListPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement searchTextfield;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Janitors\"]")
	private WebElement janitorText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@index=0 and contains(@content-desc,\"CLUST\")]")
	private WebElement janitorsclusterTemplate;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"CLUST\")]")
	private WebElement janitorsTemplate;

	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement scrollLayout;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getSearchTextfield() {
		return searchTextfield;
	}

	public WebElement getJanitorText() {
		return janitorText;
	}

	public WebElement getJanitorsclusterTemplate() {
		return janitorsclusterTemplate;
	}

	public WebElement getJanitorsTemplate() {
		return janitorsTemplate;
	}

	public void janitorsClusterValidation(String taskBuddyName, String taskbuddyMobNO, String attendance)
			throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(janitorText));
//		Thread.sleep(1000);
//		wait.until(ExpectedConditions.elementToBeClickable(searchTextfield)).click();
//		searchTextfield.clear();
//		searchTextfield.sendKeys(taskBuddyName);
		String xpath = "//android.widget.ImageView[contains(@content-desc,'" + taskBuddyName
				+ "') and contains(@content-desc,'" + taskbuddyMobNO + "')]";

		try {
			WebElement janitorElement = driver.findElement(AppiumBy.xpath(xpath));
			wait.until(ExpectedConditions.visibilityOf(janitorElement));

			Assert.assertTrue(janitorElement.isDisplayed(), "Janitor should be visible on initial screen");

			String janitorData = janitorElement.getAttribute("content-desc");
			Assert.assertEquals(janitorData.contains(attendance), true);
			janitorElement.click();
			ExtentReportManager.logPass("Janitor Cluster validated successfully");

		} catch (Exception e) {

			boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, scrollLayout, "up", 3);
			if (found) {
				WebElement janitorElement = driver.findElement(AppiumBy.xpath(xpath));
				wait.until(ExpectedConditions.visibilityOf(janitorElement));

				Assert.assertTrue(janitorElement.isDisplayed(), "Janitor should be visible after swipe");

				String janitorData = janitorElement.getAttribute("content-desc");
				Assert.assertEquals(janitorData.contains(attendance), true);
				janitorElement.click();
				ExtentReportManager.logPass("Janitor Cluster validated successfully");
			} else {
				Assert.fail("Task buddy [" + taskBuddyName + "] not found even after swiping");
			}
		}

//		wait.until(ExpectedConditions.elementToBeClickable(janitorsclusterTemplate)).click();
	}

	public void janitorsListValidation(String taskBuddyName, String taskbuddyMobNO, String gender) {
		WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(10));
		localWait.until(ExpectedConditions.visibilityOf(janitorText));
//		wait.until(ExpectedConditions.elementToBeClickable(searchTextfield)).click();
//		searchTextfield.clear();
//		searchTextfield.sendKeys(taskBuddyName);
		String xpath = "//android.view.View[contains(@content-desc,'" + taskBuddyName
				+ "') and contains(@content-desc,'" + taskbuddyMobNO + "')]";

		try {
			WebElement janitorElement = driver.findElement(AppiumBy.xpath(xpath));
			localWait.until(ExpectedConditions.visibilityOf(janitorElement));

			Assert.assertTrue(janitorElement.isDisplayed(), "Janitor should be visible on initial screen");

			String janitorData = janitorElement.getAttribute("content-desc");
			Assert.assertTrue(janitorData.contains(gender),
					"Expected gender [" + gender + "] not found in: " + janitorData);
			janitorElement.click();
			ExtentReportManager.logPass("validated Janitor present in the List");

		} catch (Exception e) {

			boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, scrollLayout, "up", 3);
			if (found) {
				WebElement janitorElement = driver.findElement(AppiumBy.xpath(xpath));
				localWait.until(ExpectedConditions.visibilityOf(janitorElement));

				Assert.assertTrue(janitorElement.isDisplayed(), "Janitor should be visible after swipe");

				String janitorData = janitorElement.getAttribute("content-desc");
				Assert.assertTrue(janitorData.contains(gender),
						"Expected gender [" + gender + "] not found in: " + janitorData);
				janitorElement.click();
				ExtentReportManager.logPass("validated Janitor present in the List");
			} else {
				Assert.fail("Task buddy [" + taskBuddyName + "] not found even after swiping");
			}
		}

//		try {
//			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
//		} catch (Exception ex) {
//			System.out.println("BACK key failed too: " + ex.getMessage());
//		}
	}
}
