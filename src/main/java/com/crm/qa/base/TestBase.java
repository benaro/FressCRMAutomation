package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;

import com.crm.qa.utilities.LoggerClass;
import com.crm.qa.utilities.SeleniumActions;
import com.crm.qa.utilities.TestUtils;
import com.crm.qa.utilities.WebDriverListener;

public class TestBase {

	protected static WebDriver driver;
	protected static Properties properties;
	protected static SeleniumActions sele_Actions;
	protected static WebDriverListener eventListener;
	protected static EventFiringWebDriver e_driver;
	protected static ChromeOptions chromeOptions;
	protected static Logger log;

	/*
	 * protected ITestResult result; protected ExtentReports extent; protected
	 * ExtentTest extentTest;
	 */
	public TestBase() {

		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/crm/qa/config/config.properties");
			properties.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("io exception");

		}

	}

	protected static void initializaton() {
		startLogger();
		String browserName = properties.getProperty("browser");
		driver = getDriver(browserName);
		log.info(browserName + " is configured");

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebDriverListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get(properties.getProperty("url"));

		sele_Actions = new SeleniumActions();

	}

	private static WebDriver getDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", TestUtils.WORKSAPCE_PATH + "//drivers//chromedriver.exe");
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--window-size=1920,1080");
			return new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", TestUtils.WORKSAPCE_PATH + "//drivers//geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("ScreenResolution", "1920x1080");
			return new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", TestUtils.WORKSAPCE_PATH + "//drivers//IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
		return null;
	}

	private static void startLogger() {
		log = Logger.getLogger(LoggerClass.class);
		PropertyConfigurator.configure(TestUtils.WORKSAPCE_PATH + "\\src\\main\\resources\\log4j.properties");

	}

	public void tearDownMain() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
	/** Open page with given URL */
	protected void openUrl(String url) {
		driver.get(url);
	}

	/** Find element using given locator */
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}
	
	/** Find list of elements using given locator */
	protected List<WebElement> finds(By locator) {
		return driver.findElements(locator);
	}	

	/** Click on element with given locator when its visible */
	protected void click(By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).click();
	}

	/** Type given text into element with given locator */
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(text);
	}

	/** Get URL of current page from browser */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	
	/*
	 * public void setExtend() { extent = new
	 * ExtentReports(System.getProperty("user.dir") +
	 * "//test-output//+NewExtentReport.html", true); Map<String, String> info = new
	 * HashMap<String, String>(); info.put("host name", "Krishna Windows");
	 * info.put("user name", "Krishna"); info.put("Environment", "QA");
	 * 
	 * extent.addSystemInfo(info);
	 * 
	 * }
	 * 
	 * public void FormatResult() { if (result.getStatus() == ITestResult.FAILURE) {
	 * extentTest.log(LogStatus.FAIL, "Failed test case is ::" + result.getName());
	 * extentTest.log(LogStatus.FAIL, "Failed test case is ::" +
	 * result.getThrowable()); TestUtils.takeScreenShot(driver);
	 * extentTest.log(LogStatus.FAIL,
	 * extentTest.addScreenCapture(TestUtils.SCREENSHOT_PATH)); } else if
	 * (result.getStatus() == ITestResult.SKIP) { extentTest.log(LogStatus.SKIP,
	 * "Skipped test case is ::" + result.getName()); } else if (result.getStatus()
	 * == ITestResult.SUCCESS) { extentTest.log(LogStatus.PASS,
	 * "Passed testc case is ::" + result.getName()); }
	 * 
	 * extent.endTest(extentTest);
	 * 
	 * }
	 */}
