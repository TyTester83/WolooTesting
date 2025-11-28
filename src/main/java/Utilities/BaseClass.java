package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ObjectUtility.CommonUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {

	public AppiumDriverLocalService service;
	public static Properties property;
	public static AppiumDriver driver;
	public CommonUtility common;
	public FileUtility fUtil = new FileUtility();
	public ExcelUtility exUtil = new ExcelUtility();
	public MobileUtility utility;
	public static GestureUtility gestureUtility;
	public GenericUtility gUtil;

	@BeforeSuite(alwaysRun = true)
	public void loadConfigAndStartServer() {
		try {
			ExtentReportManager.initReport();

			File appiumJS = new File(
					"C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
			service = new AppiumServiceBuilder().withAppiumJS(appiumJS).withIPAddress("127.0.0.1").usingPort(4723)
					.withTimeout(Duration.ofSeconds(300)).withLogFile(new File("./logs/appium.log")).build();
			service.start();

			property = new Properties();
			property.load(new FileInputStream("./src/main/resources/config.properties"));
			System.out.println("[INFO] Config file loaded.");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to start Appium server or load config: " + e.getMessage());
		}
	}

	@Parameters("platformName")
	@BeforeClass(alwaysRun = true)
	public void initDriver(@Optional("Android") String platformName) {
		try {
			BaseOptions<?> options;

			if (platformName.equalsIgnoreCase("android")) {
				UiAutomator2Options androidOptions = new UiAutomator2Options();
				androidOptions.setPlatformName("Android");
				androidOptions.setDeviceName(property.getProperty("android.deviceName"));
				androidOptions.setPlatformVersion(property.getProperty("android.platformVersion"));
				androidOptions.setApp(property.getProperty("android.app.path"));
				androidOptions.setAutomationName("UiAutomator2");
				androidOptions.setAutoGrantPermissions(true);
				androidOptions.setAppPackage(property.getProperty("appPackage"));
				androidOptions.setAppActivity(property.getProperty("appActivity"));
				androidOptions.setNoReset(false);
				androidOptions.setNewCommandTimeout(Duration.ofSeconds(120));
				options = androidOptions;
				driver = new AndroidDriver(new URI(property.getProperty("appium.server.url")).toURL(), options);
				utility = new AndroidUtility(driver);
				// make driver available to listeners/utilities that rely on ThreadLocal
				try { UtilityClassObject.setDriver(driver); } catch (Throwable t) { /* ignore */ }

			} else {
				XCUITestOptions iosOptions = new XCUITestOptions();
				iosOptions.setPlatformName("ios");
				iosOptions.setDeviceName(property.getProperty("ios.deviceName"));
				iosOptions.setPlatformVersion(property.getProperty("ios.platformVersion"));
				iosOptions.setApp(property.getProperty("ios.app.path"));
				iosOptions.setAutomationName("XCUITest");
				iosOptions.setNoReset(false);
				iosOptions.setAutoAcceptAlerts(true);
				iosOptions.setNewCommandTimeout(Duration.ofSeconds(120));

				options = iosOptions;
				driver = new IOSDriver(new URI(property.getProperty("appium.server.url")).toURL(), options);
				utility = new IOSUtility(driver);
				// make driver available to listeners/utilities that rely on ThreadLocal
				try { UtilityClassObject.setDriver(driver); } catch (Throwable t) { /* ignore */ }
			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			common = new CommonUtility(driver);
			gestureUtility = new GestureUtility(driver);
			gUtil= new GenericUtility();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Driver initialization failed: " + e.getMessage());
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void resetAppBeforeTest() {
		try {
			SoftAssertValidationUtility.initSoftAssert();
			String appPackage = property.getProperty("appPackage");

			if (driver != null) {
//                utility.closingapp();
//                Thread.sleep(1500);
//                utility.launchingapp();
//                Thread.sleep(2000);
//                utility.resetingapp();
//                Thread.sleep(3000);
				((InteractsWithApps) driver).terminateApp(appPackage);
				Thread.sleep(1500);

				clearAppData(appPackage); // << forces reset to Login
				Thread.sleep(1000);

				utility.activateApp(appPackage);
				Thread.sleep(3000);
				handlePermissions();
//				autoAcceptPermissions();
				ExtentReportManager.logPass("Application launched successfully", driver, false);
			} else {
				recreateDriver();
			}

		} catch (Exception e) {
			recreateDriver();
		}
	}

	private void clearAppData(String appPackage) {
		// TODO Auto-generated method stub
		String cmd = "adb shell pm clear " + appPackage;
		try {
			Runtime.getRuntime().exec(cmd).waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void handlePermissions() {
		if (driver instanceof AndroidDriver) {
			String[] allowButtons = { "com.android.permissioncontroller:id/permission_allow_button",
					"android:id/button1",
					"com.android.permissioncontroller:id/permission_allow_foreground_only_button" };
			
			for (String id : allowButtons) {
				try {
					WebElement btn = driver.findElement(AppiumBy.id(id));
					if (btn.isDisplayed()) {
						btn.click();
						Thread.sleep(500);
					}
				} catch (Exception ignored) {
				}
			}
		}
	}
//	public void autoAcceptPermissions() {
//	    if (!(driver instanceof AndroidDriver)) return; // Only for Android
//
//	    String[] allowBtns = {
//	        "com.android.permissioncontroller:id/permission_allow_button",
//	        "com.android.permissioncontroller:id/permission_allow_foreground_only_button",
//	        "com.android.permissioncontroller:id/permission_allow_one_time_button",
//	        "android:id/button1"
//	    };
//
//	    boolean popupFound = true;
//	    int loopCounter = 0; // safety to prevent infinite loop
//	    int maxLoops = 10;
//
//	    while (popupFound && loopCounter < maxLoops) {
//	        popupFound = false;
//	        loopCounter++;
//
//	        for (String id : allowBtns) {
//	            try {
//	                List<WebElement> elements = driver.findElements(AppiumBy.id(id));
//	                if (!elements.isEmpty()) {
//	                    elements.get(0).click();
//	                    Thread.sleep(500);  // small wait for UI update
//	                    popupFound = true;  // continue loop if more popups
//	                    System.out.println("Auto-accepted permission: " + id);
//	                    break; // break to recheck all buttons again
//	                }
//	            } catch (Exception ignored) { }
//	        }
//	    }
//
//	    if (loopCounter >= maxLoops) {
//	        System.out.println("[WARNING] Reached max permission loops, some popups may remain.");
//	    }
//	}

	private void recreateDriver() {
		try {
			if (driver != null)
				driver.quit();
			initDriver(property.getProperty("platformName"));
			handlePermissions();
//			 autoAcceptPermissions();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to recreate driver: " + e.getMessage());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {
		try {
			SoftAssertValidationUtility.assertAll();
			 ExtentReportManager.logPass("Application closed successfully");
			// Screenshot fallback on test failure - captures at the moment of failure with driver available
				if (result != null && result.getStatus() == ITestResult.FAILURE) {
					String methodName = result.getMethod().getMethodName();
					ExtentReportManager.logWarning("Test failed: " + methodName + " - capturing screenshot fallback");
					try {
						if (driver != null) {
							ExtentReportManager.captureScreenshot(driver, "FAILURE_FALLBACK_" + methodName);
							ExtentReportManager.logPass("Screenshot captured as fallback for failure");
						} else {
							ExtentReportManager.logWarning("Driver unavailable; cannot capture failure screenshot");
						}
					} catch (Exception e) {
						ExtentReportManager.logWarning("Error capturing failure screenshot: " + e.getMessage());
					}
				}

				ExtentReportManager.removeTest();
			 
		} catch (Exception ignored) {
		}
	}

	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		try {
			if (driver != null) {
				driver.quit();
				try { UtilityClassObject.setDriver(null); } catch (Throwable t) { /* ignore */ }
			}
		} catch (Exception ignored) {
		}
	}

	@AfterSuite(alwaysRun = true)
	public void stopServer() {
		try {
			if (service != null && service.isRunning())
				service.stop();
			ExtentReportManager.logPass("Appium server stopped successfully");
			ExtentReportManager.flushReport();
		} catch (Exception ignored) {
		}
	}
}
