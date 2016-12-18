package com.clearTrip.flights.tests;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;

	@BeforeSuite
	public WebDriver init() {
		if (driver == null) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

	@AfterSuite
	public void close() {
		quit();
	}

	public void quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public void baseOpenURL(String url) throws Exception {
		driver.get(url);
	}

	public void sendkeysbyxpath(String locator, String value) throws Exception {
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}

	public void sendkeysbId(String locator, String value) throws Exception {
		driver.findElement(By.id(locator)).sendKeys(value);
	}

	public void elementByXpathSubmit(String locator) throws Exception {
		driver.findElement(By.xpath(locator)).click();
	}

	public void elementByIdSubmit(String locator) throws Exception {
		driver.findElement(By.id(locator)).click();
	}

	public void waitForelementPresentXpath(String locator) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	public void waitForelementPresentId(String locator) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
	}

	public WebElement elementByXpathWebElement(String locator) throws Exception {
		return driver.findElement(By.xpath(locator));
	}

	public WebElement elementByIdWebElement(String locator) throws Exception {
		return driver.findElement(By.id(locator));
	}

	public String getTextValue(String locator) throws Exception {
		return driver.findElement(By.xpath(locator)).getText();
	}

	public String getValue(String locator) throws Exception {
		return driver.findElement(By.xpath(locator)).getAttribute("value");
	}

	public List<WebElement> findrowsintable(String locator) throws Exception {
		List<WebElement> rows = driver.findElements(By.xpath(locator));
		return rows;
	}

	public void sendKeyBoardkeysbyxpath(String locator, Keys value) throws Exception {
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert().accept();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

}
