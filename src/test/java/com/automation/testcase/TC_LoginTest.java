package com.automation.testcase;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {
	
    @Test(dataProvider = "Excel")
    public void TC01_LoginFirstAccount(String email, String password) throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	LoginPage loginPage = new LoginPage(driver);
    	PageFactory.initElements(driver, loginPage);
    	
    	String URL = PropertiesFileUtils.getProperty("Base_URL");
    	driver.get(URL);

//    	 Kiểm tra đã logout chưa. Nếu chưa logout thì click iconLogout
    	String iconLogout = PropertiesFileUtils.getProperty("iconLogout");
    	if(!loginPage.isLogOut()) {wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconLogout))).click();}
    	
    	String iconSignin_Xpath = PropertiesFileUtils.getProperty("iconSignin");
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconSignin_Xpath))).click();
    	
    	loginPage.enterEmail(email);
    	loginPage.enterPassword(password);
    	loginPage.clickSignIn();
    	
    	WebElement iconLogout_element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconLogout)));
    	assertEquals(iconLogout_element.isDisplayed(), true);
    	iconLogout_element.click();
    	Thread.sleep(1000);
    }

    

   @DataProvider(name="Excel")
    public Object testDataGenerator() throws IOException{
	   FileInputStream file = new FileInputStream("./data/assignment2_data_test.xlsx");
	   XSSFWorkbook Workbook = new XSSFWorkbook(file);
	   XSSFSheet s1 = Workbook.getSheet("Login");
	   int numberOfRow = s1.getPhysicalNumberOfRows();
	   Object [][] data = new Object[numberOfRow][2];
	   for (int i = 0; i < numberOfRow; i++){
		   data[i][0] = s1.getRow(i).getCell(0).getStringCellValue();
		   data[i][1] = s1.getRow(i).getCell(1).getStringCellValue();
	   }
	   return data;
   }
   
   
    @AfterMethod
    public void CaptureAndAttact(ITestResult result) throws InterruptedException {
    	if (ITestResult.FAILURE == result.getStatus()) {
    		try {
	       		 String email = result.getParameters()[0].toString();    // Lấy tham số (parameters) đầu vào của TC vừa chạy
	       		 int index = email.indexOf("@");
	       		 String accountName = email.substring(0, index);

	       		CaptureScreenshot.takeScreenshot(driver, accountName); //takeScreenShot and attact to report
    		} catch (Exception e) {
    			System.out.println("Lỗi xảy ra screenshot " + e.getMessage());
    		}  
    	}
    }
    
    

}
