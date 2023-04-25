package com.automation.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class DriverInstance {
	protected WebDriver driver;
	
	@BeforeClass
	public void initDriverInstance() {
		System.setProperty("webdriver.chrome.driver", "./drive/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeDriverInstance() {
		driver.close();
	}

}
