package com.automation.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class CaptureScreenshot {
	
	
	public static void takeScreenshot(WebDriver driver, String imageName) {	
		String filePath = "";
		try {		
	        File theDir = new File("./Screenshots/");
	        if(!theDir.exists()) {theDir.mkdirs();}
	        
	        BufferedImage source = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        
	        // Sử dụng currentDateTime để tránh bị lưu trùng tên file và để kiểm soát file theo thời gian
	        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        filePath = "./Screenshots/" +currentDateTime+"_"+imageName+ ".jpg";
	        File destiny = new File(filePath);
	        ImageIO.write(source, "jpg", destiny);
	        System.out.println("Đã chụp ảnh màn hình, FilePath: " + filePath);
		} 
		catch (Exception ex) {
			 System.out.println("Đã xảy ra lỗi khi chụp màn hình!");
	         ex.printStackTrace();
		}
		attactScreenShot(filePath);
	}
	
	public static void attactScreenShot(String filePath) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			File file = new File(filePath);
			Reporter.log("<br> <a title = 'ScreenShot' href='" + file.getAbsolutePath() + "'> <img src='" + file+"' alt='"+file.getName()+"' width='418' height='240'/></a></br>");
		} catch(Exception e) {e.printStackTrace();}
	}
	
}



