package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {

    private static String CONFIG_PATH = "./configuration/configs.properties";
    public static String getProperty(String key) {
    	Properties properties= new Properties();
        String value = null;
        FileInputStream fileInputStream = null;
        try {
        	fileInputStream = new FileInputStream(CONFIG_PATH);
            properties.load(fileInputStream);
            value = properties.getProperty(key);
            return value;
        } catch (Exception ex) {
            System.out.println("Xảy ra lỗi khi đọc giá trị của  " + key);
            ex.printStackTrace();
        } finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }

        return value;
    }
    
}

