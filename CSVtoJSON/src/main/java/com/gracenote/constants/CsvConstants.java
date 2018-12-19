package com.gracenote.constants;

public class CsvConstants {
	
	// If header format of file changes, simply make changes to this array
	public static final String[] CSV_HEADER = {
			"transactionDate",
			"product",
			"price",
			"paymentType",
			"name",
			"city",
			"state",
			"country",
			"accountCreated",
			"lastLogin",
			"latitude",
			"longitude"
	};
	
	public static final String TEST_INPUT_FILE_PATH = "C:/Users/Daanish/Downloads/Home_Java_Interview_Coding_Test/SalesTest.csv";
	public static final String TEST_OUTPUT_FILE_PATH = "C:/Users/Daanish/Downloads/Home_Java_Interview_Coding_Test/";
	
	public static final String DATE_FORMAT = "MM/dd/yy HH:mm";
}
