package com.gracenote.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.gracenote.bean.ResponseBean;
import com.gracenote.bean.SalesDataBean;
import com.gracenote.logic.CalculationsInterface;
import com.gracenote.logic.ParseInterface;
import com.gracenote.logic.ReadInterface;
import com.gracenote.logic.WriteInterface;
import com.gracenote.logic.implementations.Calculations;
import com.gracenote.logic.implementations.ParseStringArrayToList;
import com.gracenote.logic.implementations.ReadCsvToStringArrayList;
import com.gracenote.logic.implementations.WriteJson;
import com.gracenote.utility.LoggerClass;
import com.gracenote.utility.ReadProperties;

public class MainClass {
	public static void main(String[] args) {
		
		LoggerClass.logInfoMessages("Program has started, asking user for options");
		
		// Initialize the Scanner object to read user inputs
		Scanner sc = new Scanner(System.in);
		int decision = 0; 
		
		// Ask the user to enter the correct input and only then allow them to proceed, otherwise show error message
		// and keep on asking them again
		while(decision == 0) {
			try {
				// We now ask whether the user wants the program to read from the properties file or he/she 
				// shall provide the inputs during the flow
				System.out.println("Press 1 to read from sample.properties file, 2 to provide your own inputs: ");
				decision = sc.nextInt();
				sc.nextLine();
				LoggerClass.logInfoMessages("User decision: "+decision);
			} catch (InputMismatchException e) {
				e.printStackTrace();
				sc.nextLine();
				LoggerClass.logErrorMessages(e.getMessage());
				System.out.println("Invalid input. Please enter 1 or 2.");
			}
		}
		
		ReadProperties readFromProperties = null;
		String csvFilePath = null;
		List<String[]> salesDataStringArrayList = null;
		
		// If decision if 1, read all inputs from properties file and execute the program in 1 shot
		// If decision if 2, then accept input from user during program flow
		// In both cases, read the data from the file into a List of String array --> List<String[]>

		ReadInterface readObj = new ReadCsvToStringArrayList();
		if(decision == 1) {
			readFromProperties = new ReadProperties();
			csvFilePath = readFromProperties.getValueFromKey("csvFilePath");
			LoggerClass.logInfoMessages("Csv file path: "+csvFilePath);
			if(csvFilePath != null)
				csvFilePath = csvFilePath.replace("\\", "/");
			salesDataStringArrayList = readObj.readToStringArrayList(csvFilePath);
		} else if (decision == 2) {
			while(salesDataStringArrayList == null) {
				System.out.println("Please provide file path: ");
				csvFilePath = sc.nextLine();
				if(csvFilePath != null)
					csvFilePath = csvFilePath.replace("\\", "/");
				LoggerClass.logInfoMessages("Csv file path: "+csvFilePath);
				salesDataStringArrayList = readObj.readToStringArrayList(csvFilePath);
			}
		}
		
		ParseInterface parseObj = new ParseStringArrayToList();
		
		// Now convert List<String[]> to List<Bean>. Here our Bean is SalesDataBean
		List<SalesDataBean> salesDataList = 
				(List<SalesDataBean>) parseObj.parseStringArrayListToObjectList(salesDataStringArrayList);
		
		// If code fails due to file format, we terminate the program
		if(salesDataList == null) {
			System.out.println("Invalid File Format. Please correct the file and try again.");
			LoggerClass.logErrorMessages("Exception occurred during parsing of List String array, exiting");
			System.exit(0);
		}
		
		// Calculating account age in days
		LoggerClass.logInfoMessages("Calculating the additional column of account age");
		CalculationsInterface calculations = new Calculations();
		calculations.calculateAccountAge(salesDataList);
		
		// To calculate the sales, three inputs are required from the user
		// 1. The year
		// 2. Do we need sales before or after that year
		// 3. Should sales from the entered year also be included.
		// Based on these inputs the sales is calculated
		
		int year = 0; String beforeAfter = ""; boolean includeYear = false;
		LoggerClass.logInfoMessages("Calculating the Sales");
		if(decision == 1) {
			year = Integer.parseInt(readFromProperties.getValueFromKey("year"));
			beforeAfter = readFromProperties.getValueFromKey("beforeAfter");
			includeYear = Boolean.parseBoolean(readFromProperties.getValueFromKey("includeYear"));
		} else if (decision == 2) {
			System.out.println("To calculate Sales, ");
			while(year == 0) {
				System.out.println("Please provide the year: ");
				try {
					year = sc.nextInt();
					sc.nextLine();
					LoggerClass.logInfoMessages("Year: "+year);
				} catch (InputMismatchException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
					System.out.println("Invalid input. Please provide a valid numerical year.");
					sc.nextLine();
				}
			}
			
			while(!(beforeAfter.equalsIgnoreCase("before") || beforeAfter.equalsIgnoreCase("after"))) {
				System.out.println("Calculate sales 'before' or 'after' "+year+"? Please enter 'before' or 'after' : ");
				beforeAfter = sc.nextLine();
				LoggerClass.logInfoMessages("beforeAfter: "+beforeAfter);
			}
			
			System.out.println("Should "+year+" be included: 'true' or 'false'");
			try {
				includeYear = sc.nextBoolean();
				LoggerClass.logInfoMessages("includeYear: "+includeYear);
				sc.nextLine();
			} catch (InputMismatchException e) {
				e.printStackTrace();
				LoggerClass.logErrorMessages(e.getMessage());
				System.out.println("Invalid input. Hence proceeding with 'false'.");
				sc.nextLine();
			}
		}
		long sales = calculations.calculateSales(salesDataList, year, beforeAfter, includeYear);
		LoggerClass.logInfoMessages("Calculated Sales: "+sales);
		
		
		// Finally the response is created and written to an 'output.json' file created in the user specified directory
		LoggerClass.logInfoMessages("Create the response and write it to JSON file in JSON format");
		ResponseBean responseBean = new ResponseBean();
		responseBean.setSalesDataList(salesDataList);
		responseBean.setSales(sales);
		
		boolean writeFileResult = false;

		String outputFilePath = null;
		WriteInterface writeObj = new WriteJson();
		if (decision == 1) {
			outputFilePath = readFromProperties.getValueFromKey("outputFilePath");
			if(outputFilePath != null)
				outputFilePath = outputFilePath.replace("\\", "/");
			writeFileResult = writeObj.writeToFile(responseBean, outputFilePath);
		} else if (decision == 2) {
			while (!writeFileResult) {
				System.out.println("Please enter output path: ");
				outputFilePath = sc.nextLine();
				if(outputFilePath != null)
					outputFilePath = outputFilePath.replace("\\", "/");
				LoggerClass.logInfoMessages("outputFilePath: "+outputFilePath);
				writeFileResult = writeObj.writeToFile(responseBean, outputFilePath);
			}
		}
		
		LoggerClass.logInfoMessages("Program ended. Cleaning resources.");
		// Closing resources
		if(sc != null)
			sc.close();
		
	}
}	
