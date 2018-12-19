package com.gracenote.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
	private static final Logger logger = LogManager.getLogger(LoggerClass.class);
	
	public static void logInfoMessages(String message) {
		logger.info(message);
	}
	
	public static void logErrorMessages(String message) {
		logger.error(message);
	}
}
