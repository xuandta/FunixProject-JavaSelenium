package com.automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.PropertiesFileUtils;

public class LoginPage {
    private WebDriverWait wait;
    public LoginPage(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
    }

    public void enterEmail(String email) throws InterruptedException {
	   String elementIdentified = PropertiesFileUtils.getProperty("Login_email");
	   WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
	   element.sendKeys(email);
	   Thread.sleep(2000);
    }
    public void enterPassword(String password) throws InterruptedException {
	   String elementIdentified = PropertiesFileUtils.getProperty("Login_password");
	   WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
	   element.sendKeys(password);
	   Thread.sleep(2000);
    }
    public void clickSignIn() throws InterruptedException {
 	   String elementIdentified = PropertiesFileUtils.getProperty("Login_button");
 	   WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
 	   element.click();
 	   Thread.sleep(2000);
	}
    
 // isLogOut = true  => Đã log out; isLogOut = false  => chưa log out 
    public boolean isLogOut() { 
    	String elementIdentified = PropertiesFileUtils.getProperty("LoginOrLogout");
    	WebElement LoginOrLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
    	if(LoginOrLogout.getAttribute("href").equals("https://automationexercise.com/login")) {return true;}
    	else {return false;}
	}

}
