package Utilities;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class BasePage {
	protected static final Duration DEFAULT_WAIT = Duration.ofSeconds(20);

	protected final AppiumDriver driver;
	protected final WebDriverWait wait;
	protected final GenericUtility gUtil;

	protected BasePage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
		this.gUtil = new GenericUtility();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	protected WebDriverWait waitWithTimeout(Duration timeout) {
		return new WebDriverWait(driver, timeout);
	}

	protected void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForClickability(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}

