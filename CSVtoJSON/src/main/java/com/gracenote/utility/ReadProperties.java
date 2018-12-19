package com.gracenote.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	static Properties properties;
	
	public ReadProperties() {
		properties = new Properties();
		
		try {
			properties.load(new FileInputStream("./sample.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			LoggerClass.logErrorMessages(e.getMessage());
		}
	}
	
	public String getValueFromKey(String key) {
		LoggerClass.logInfoMessages("key: "+key);
		LoggerClass.logInfoMessages(properties.getProperty(key));
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		ReadProperties readProperties = new ReadProperties();
		System.out.println(readProperties.getValueFromKey("csvFilePath"));
	}
}
