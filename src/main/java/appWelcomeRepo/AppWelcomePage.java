package appWelcomeRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import adminDashboardRepo.AdminDashboardPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppWelcomePage extends BasePage {

	public AppWelcomePage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement logoImage;

	@AndroidFindBy(accessibility = "Login As")
	private WebElement loginAsDropdown;

	@AndroidFindBy(accessibility = "Admin")
	private WebElement adminButton;

	@AndroidFindBy(accessibility = "Task Buddy")
	private WebElement taskbuddyButton;

	@AndroidFindBy(accessibility = "Supervisor")
	private WebElement supervisorButton;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@index=0]")
	private WebElement loginOptionDropdown;

	public WebElement getLogoImage() {
		return logoImage;
	}

	public WebElement getLoginAsDropdown() {
		return loginAsDropdown;
	}

	public WebElement getAdminButton() {
		return adminButton;
	}

	public WebElement getTaskbuddyButton() {
		return taskbuddyButton;
	}

	public WebElement getSupervisorButton() {
		return supervisorButton;
	}

	public WebElement getLoginOptionDropdown() {
		return loginOptionDropdown;
	}

	private void openLoginDropdown() {
		ExtentReportManager.logInfo("Opening Login Role dropdown");
		try {
			if (!gUtil.isElementVisible(loginAsDropdown, driver)) {
				ExtentReportManager.logFail("Login dropdown not visible", driver);
				throw new IllegalStateException("Login dropdown not visible");
			}
			String beforeSource = driver.getPageSource();
			gUtil.safeClick(loginAsDropdown, 2, driver);
			boolean clickTookEffect = false;
			try {
				for (int i = 0; i < 5; i++) {
					try {
						if (!loginAsDropdown.isDisplayed()) {
							clickTookEffect = true;
							break;
						}
					} catch (org.openqa.selenium.StaleElementReferenceException sere) {
						clickTookEffect = true;
						break;
					}
					String afterSource = driver.getPageSource();
					if (!afterSource.equals(beforeSource)) {
						clickTookEffect = true;
						break;
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException ie) {}
			if (!clickTookEffect) {
				ExtentReportManager.logFail("Dropdown click did not take effect", driver);
				throw new IllegalStateException("Dropdown click had no effect");
			}
			ExtentReportManager.logPass("Opened Login Role dropdown", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to open Login dropdown", e, driver);
			throw e;
		}
	}

	private void pickRoleButton(WebElement roleButton) {
		ExtentReportManager.logInfo("Selecting login role");
		try {
			if (!gUtil.isElementVisible(roleButton, driver)) {
				ExtentReportManager.logFail("Role button not visible", driver);
				throw new IllegalStateException("Role button not visible");
			}
			String beforeSource = driver.getPageSource();
			gUtil.safeClick(roleButton, 2, driver);
			boolean clickTookEffect = false;
			try {
				for (int i = 0; i < 5; i++) {
					try {
						if (!roleButton.isDisplayed()) {
							clickTookEffect = true;
							break;
						}
					} catch (org.openqa.selenium.StaleElementReferenceException sere) {
						clickTookEffect = true;
						break;
					}
					String afterSource = driver.getPageSource();
					if (!afterSource.equals(beforeSource)) {
						clickTookEffect = true;
						break;
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException ie) {}
			if (!clickTookEffect) {
				ExtentReportManager.logFail("Role button click did not take effect", driver);
				throw new IllegalStateException("Role button click had no effect");
			}
			ExtentReportManager.logPass("Selected login role", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to select role button", e, driver);
			throw e;
		}
	}

	private WebElement resolveRoleButton(LoginRole role) {
		switch (role) {
		case ADMIN:
			return adminButton;
		case TASK_BUDDY:
			return taskbuddyButton;
		case SUPERVISOR:
			return supervisorButton;
		default:
			throw new IllegalStateException("Unhandled login role: " + role);
		}
	}

	public void selectLoginRole(LoginRole role) {
		openLoginDropdown();
		pickRoleButton(resolveRoleButton(role));
	}

	public void welcomeScreenUIvalidation() {
		// Wait until LoginAs dropdown is clickable
		wait.until(ExpectedConditions.visibilityOf(loginAsDropdown));
		ExtentReportManager.logInfo("Validating App Welcome screen UI elements");
		// Assert logo is visible
		Assert.assertTrue(logoImage.isDisplayed(), "Logo image should be visible on the Welcome screen.");
		ExtentReportManager.logPass("Logo image is visible", driver, false);

		// Assert loginAsDropdown is visible and clickable
		Assert.assertTrue(loginAsDropdown.isDisplayed(), "Login As dropdown should be visible.");
		ExtentReportManager.logPass("Login As dropdown is visible", driver, false);
		openLoginDropdown();

		// After dropdown is expanded, validate role options
		Assert.assertTrue(adminButton.isDisplayed(), "Admin option should be visible in the dropdown.");
		ExtentReportManager.logPass("Admin option is visible", driver, false);
		Assert.assertTrue(taskbuddyButton.isDisplayed(), "TaskBuddy option should be visible in the dropdown.");
		ExtentReportManager.logPass("TaskBuddy option is visible", driver, false);
		Assert.assertTrue(supervisorButton.isDisplayed(), "Supervisor option should be visible in the dropdown.");
		ExtentReportManager.logPass("Supervisor option is visible", driver, false);
	}

	public void chooseAdminFromDropdown() {
		selectLoginRole(LoginRole.ADMIN);
	}

	public void chooseSupervisorFromDropdown() {
		selectLoginRole(LoginRole.SUPERVISOR);
	}

	public void chooseTaskbuddyFromDropdown() {
		selectLoginRole(LoginRole.TASK_BUDDY);
	}

	public void newUserLoginValidation(String adminMobNo) {
		try {
			boolean isLoginVisible = gUtil.isElementVisible(driver, loginAsDropdown, 5);
			if (!isLoginVisible) {
				System.out.println("Login screen not displayed. Skipping login.");
				return;
			}

			System.out.println("Login screen is displayed. Running login flow...");
			selectLoginRole(LoginRole.ADMIN);

			AdminDashboardPage dashboardPage = new AdminDashboardPage(driver);
			dashboardPage.adminLogin(adminMobNo);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Login flow interrupted: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Login screen not found or already logged in. Proceeding to Explore.");
		}
	}

	public enum LoginRole {
		ADMIN, TASK_BUDDY, SUPERVISOR
	}
}
