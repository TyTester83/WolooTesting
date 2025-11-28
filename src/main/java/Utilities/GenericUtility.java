package Utilities;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;

public class GenericUtility  {
	
	
	public void screeShotMethod(AppiumDriver driver, String testName, ExtentTest test) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			String base64Image = ts.getScreenshotAs(OutputType.BASE64);

			String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
			String screenshotName = testName + "_" + time;

			test.addScreenCaptureFromBase64String(base64Image, screenshotName);
		} catch (Exception e) {
			test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
		}
	}

	public static boolean swipeAndFindBy(AppiumDriver driver, String contentDesc, int maxSwipes,
			WebElement swipeContainer) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		boolean expected = false;
		for (int i = 0; i < maxSwipes; i++) {
			try {
				List<WebElement> elements = driver.findElements(AppiumBy.xpath(contentDesc));

				if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
					System.out.println(" Element with XPath '" + contentDesc + "' found after " + i + " swipes.");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Reset wait
					elements.get(0).click();
					expected = true;
					break;
				} else {
					swipeHorizontally(driver, swipeContainer);
				}
			} catch (StaleElementReferenceException e) {
				System.out.println(" StaleElementReferenceException caught. Retrying swipe...");
				swipeHorizontally(driver, swipeContainer);
			} catch (ElementNotInteractableException e) {
				System.out.println(" ElementNotInteractableException caught. Skipping click attempt.");
				swipeHorizontally(driver, swipeContainer);
			} catch (Exception e) {
				System.out.println(" Unexpected exception: " + e.getMessage());
				swipeHorizontally(driver, swipeContainer);
			}
		}

		// Reset wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out
				.println(" Element with content-desc '" + contentDesc + "' not found after " + maxSwipes + " swipes.");
		return expected;
	}

	/**
	 * Performs a horizontal swipe within the bounds of the given container element
	 * using W3C Actions (Appium 8+).
	 *
	 * @param driver  AppiumDriver instance
	 * @param element Container element within which the swipe will occur
	 */
	public static void swipeHorizontally(AppiumDriver driver, WebElement element) {
		int elementWidth = element.getSize().getWidth();
		int elementHeight = element.getSize().getHeight();
		int elementStartX = element.getLocation().getX();
		int elementStartY = element.getLocation().getY();

		// Swipe from 80% to 20% of the container width
		int startX = elementStartX + (int) (elementWidth * 0.8);
		int endX = elementStartX + (int) (elementWidth * 0.2);
		int centerY = elementStartY + (elementHeight / 2);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, centerY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}

	public static boolean fastScrollAndFind(AppiumDriver driver, String partialText, int maxScrolls) {
		// Speed up scroll attempts by reducing wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String xpath = "//android.widget.TextView[contains(@text, '" + partialText + "')]";

		for (int i = 0; i < maxScrolls; i++) {
			List<WebElement> elements = driver.findElements(AppiumBy.xpath(xpath));
			if (!elements.isEmpty()) {
				for (WebElement el : elements) {
					if (el.isDisplayed()) {
						System.out.println("Found and clicked: " + el.getText());
						// el.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						return true;
					}
				}
			}
			verticalScroll(driver);
		}

		// Restore default wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Element with text containing '" + partialText + "' not found.");
		return false;
	}

	/**
	 * Performs a smooth vertical scroll using pointer input gesture (Appium 2.x+).
	 */
	public static void verticalScroll(AppiumDriver driver) {
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();

		int startY = (int) (height * 0.7);
		int endY = (int) (height * 0.3);
		int centerX = width / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}

	public static void tapUsingCoordinatePercentage(AppiumDriver driver, WebElement layout, double xPercent,
			double yPercent) {

		Point location = layout.getLocation();
		Dimension size = layout.getSize();

		int x = (int) ((xPercent / 100) * size.getWidth() + location.getX());
		int y = (int) ((yPercent / 100) * size.getHeight() + location.getY());

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(new Pause(finger, Duration.ofMillis(50)));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(tap));
	}

	public void scrollElement(AppiumDriver driver, WebElement element, String direction) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		Point location = element.getLocation();
		int startX = location.getX() + element.getSize().getWidth() / 2;
		int startY = location.getY() + element.getSize().getHeight() / 2;
		int endY = direction.equalsIgnoreCase("up") ? startY - 200 : startY + 200;

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));
	}

	public boolean isElementVisible(AppiumDriver driver, WebElement element, int timeoutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean swipeAndFindElementByDirection(AppiumDriver driver, String xpath, WebElement container,
			String direction, int maxSwipes) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // short wait
		boolean expected = false;

		for (int i = 0; i < maxSwipes; i++) {
			try {
				List<WebElement> elements = driver.findElements(AppiumBy.xpath(xpath));
				if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
					expected = true;
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // reset wait
					break;
				} else {

					swipeInDirection(driver, container, direction);
				}
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {

				swipeInDirection(driver, container, direction);
			} catch (Exception e) {
				System.out.println(" Unexpected exception: " + e.getMessage());
				swipeInDirection(driver, container, direction);
			}
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // reset wait
		System.out.println(" Element not found after " + maxSwipes + " swipes.");
		return expected;
	}

	public static void swipeInDirection(AppiumDriver driver, WebElement container, String direction) {
		// Validate container
		if (container == null || container.getSize().getWidth() == 0 || container.getSize().getHeight() == 0) {
			throw new IllegalArgumentException(" Swipe container is null or has zero size.");
		}

		int width = container.getSize().getWidth();
		int height = container.getSize().getHeight();
		int startX = container.getLocation().getX();
		int startY = container.getLocation().getY();

		int fromX, toX, fromY, toY;

		switch (direction.toLowerCase()) {
		case "up": // Bottom to top
			fromX = startX + width / 2;
			toX = fromX;
			fromY = startY + (int) (height * 0.8);
			toY = startY + (int) (height * 0.2);
			break;
		case "down": // Top to bottom
			fromX = startX + width / 2;
			toX = fromX;
			fromY = startY + (int) (height * 0.2);
			toY = startY + (int) (height * 0.8);
			break;
		case "left": // Right to left
			fromY = startY + height / 2;
			toY = fromY;
			fromX = startX + (int) (width * 0.8);
			toX = startX + (int) (width * 0.2);
			break;
		case "right": // Left to right
			fromY = startY + height / 2;
			toY = fromY;
			fromX = startX + (int) (width * 0.2);
			toX = startX + (int) (width * 0.8);
			break;
		default:
			throw new IllegalArgumentException(" Unsupported swipe direction: " + direction);
		}

		// Perform swipe using W3C actions
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), fromX, fromY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), toX, toY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}

	public static String convertToUnpaddedTime(String time) {
		try {
			// Parse the padded time (e.g., 05:30 PM)
			DateTimeFormatter paddedFormat = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
			LocalTime localTime = LocalTime.parse(time.trim(), paddedFormat);

			// Format to unpadded time (e.g., 5:30 PM)
			DateTimeFormatter unpaddedFormat = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
			return localTime.format(unpaddedFormat);

		} catch (DateTimeParseException e) {
			System.out.println("Invalid time format: " + time);
			return time; // fallback
		}
	}

	public boolean isElementVisible(WebElement element, AppiumDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementClickable(WebElement element, AppiumDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnElement(WebElement locator, AppiumDriver driver) {
		try {
			// Wait for both visibility and click ability
			if (isElementVisible(locator, driver) && isElementClickable(locator, driver)) {
				WebElement element = locator;
				element.click();
				System.out.println("Clicked on element: " + locator.toString());
			} else {
				Assert.fail("Element is not visible and clickable after waiting: " + locator.toString());
			}
		} catch (StaleElementReferenceException staleEx) {
			try {
				WebElement element = locator;
				element.click();
			} catch (Exception retryEx) {
				Assert.fail("Retry failed for stale element: " + locator.toString());
			}
		} catch (Exception e) {
			Assert.fail("Failed to click element: " + locator.toString() + " - " + e.getMessage());
		}
	}

	public void clearAndSendKeys(AppiumDriver driver, WebElement locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			// Wait for visibility and click ability

			if (isElementVisible(locator, driver) && isElementClickable(locator, driver)) {
				WebElement element = locator;

				// Click and clear
				element.click();
				element.clear();

				// Wait until field is empty (useful for Appium)
				wait.until(ExpectedConditions.or(ExpectedConditions.attributeToBe(element, "text", ""),
						ExpectedConditions.attributeToBe(element, "value", "")));

				// Send new text
				element.sendKeys(text);
				System.out.println("Entered text '" + text + "' into element: " + locator.toString());

				// Hide keyboard safely
				hideKeyboardIfVisible(driver);

			} else {
				Assert.fail("Element is not visible and clickable: " + locator.toString());
			}

		} catch (StaleElementReferenceException staleEx) {
			// Retry once if element becomes stale
			try {
				WebElement element = locator;
				element.click();
				element.clear();
				element.sendKeys(text);
				hideKeyboardIfVisible(driver);
			} catch (Exception retryEx) {
				Assert.fail("Retry failed for stale element: " + locator.toString());
			}
		} catch (Exception e) {
			Assert.fail("Failed to clear and send keys to element: " + locator.toString() + " - " + e.getMessage());
		}
	}

	public void waitForPageToBeStable(int maxWaitSeconds, AppiumDriver driver) {
		String lastSource = "";
		int stableCount = 0;

		for (int i = 0; i < maxWaitSeconds; i++) {
			String currentSource = driver.getPageSource();

			if (currentSource.equals(lastSource)) {
				stableCount++;
			} else {
				stableCount = 0;
			}

			// Page stable for 2 consecutive seconds
			if (stableCount >= 2) {
				System.out.println("Page stabilized after " + i + " seconds.");
				return;
			}

			lastSource = currentSource;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Interrupted while waiting for stability");
			}
		}

		System.out.println("Page may still be changing after " + maxWaitSeconds + "s, proceeding...");
	}

	public void safeClick(WebElement element, int waitTime, AppiumDriver driver) {
		waitForPageToBeStable(waitTime, driver); // wait before click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		waitForPageToBeStable(1, driver); // wait after click
	}

	public void safeSendKeys(WebElement element, String text, int waitTime, AppiumDriver driver) {
		waitForPageToBeStable(waitTime, driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.isDisplayed()) {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			element.clear();
			waitForPageToBeStable(2, driver);
			element.sendKeys(text);
			hideKeyboardIfVisible(driver);
		}

		waitForPageToBeStable(1, driver);
	}

	public void hideKeyboardIfVisible(AppiumDriver driver) {
		try {
			((AndroidDriver) driver).hideKeyboard();
		} catch (Exception e) {
			// fallback: try BACK key
			try {
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			} catch (Exception ex) {
				// ignore if keyboard not open
			}
		}
	}

	public void safeSendKeysWithOutBackKey(WebElement element, String text, int waitTime, AppiumDriver driver) {
		waitForPageToBeStable(waitTime, driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		element.clear();
		waitForPageToBeStable(2, driver);
		element.sendKeys(text);
		waitForPageToBeStable(1, driver);
	}

	public void pressBackKey(AppiumDriver driver) {
		try {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		} catch (Exception ex) {
			System.out.println("BACK key failed: " + ex.getMessage());
		}
	}
	public void handlePermissionPopups(AppiumDriver driver) {
	    try {
	        ExtentReportManager.logInfo("Checking for permission popups...");

	        if (driver instanceof AndroidDriver) {
	            handleAndroidPermissions(driver);
	        } else if (driver instanceof IOSDriver) {
	            handleIOSPermissions(driver);
	        }

	    } catch (Exception e) {
	        ExtentReportManager.logWarning("Permission handler error: " + e.getMessage());
	    }
	}
	private void handleAndroidPermissions(AppiumDriver driver) {
	    String[] allowButtons = {
	        "Allow", "ALLOW", "While using the app", "Only this time",
	        "Allow all the time"
	    };

	    for (int i = 0; i < 5; i++) {   // Try multiple times
	        for (String text : allowButtons) {
	            try {
	                WebElement el =  driver.findElement(
	                    AppiumBy.xpath("//*[@text='" + text + "']")
	                );
	                if (el != null) {
	                    el.click();
	                    ExtentReportManager.logInfo("Permission accepted: " + text);
	                    Thread.sleep(500);
	                }
	            } catch (Exception ignored) {}
	        }
	    }
	}
	private void handleIOSPermissions(AppiumDriver driver) {
	    String[] allowButtons = {
	        "Allow", "OK", "Allow While Using App"
	    };

	    for (int i = 0; i < 5; i++) {
	        for (String text : allowButtons) {
	            try {
	            	 WebElement el =  driver.findElement(
	 	                    AppiumBy.xpath("//*[@label='" + text + "' or @name='" + text + "']")
	                );
	                if (el != null) {
	                    el.click();
	                    ExtentReportManager.logInfo("iOS permission accepted: " + text);
	                    Thread.sleep(500);
	                }
	            } catch (Exception ignored) { }
	        }
	    }
	}


}
