package com.gracenote.logic.implementations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.gracenote.logic.ReadInterface;
import com.gracenote.utility.LoggerClass;
import com.opencsv.CSVReader;

public class ReadCsvToStringArrayList implements ReadInterface {

	@Override
	public List<String[]> readToStringArrayList(String filePath) {
		List<String[]> allRecords = null;
		FileReader fileReader = null;
		CSVReader csvReader = null;
		try {
			
			// Using the Open CSV jars to read our CSV file
			// Read all recors in a single take
			
			fileReader = new FileReader(filePath);
			csvReader = new CSVReader(fileReader); 
			allRecords = csvReader.readAll();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Invalid file path. Please provide correct path.");
			LoggerClass.logErrorMessages(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerClass.logErrorMessages(e.getMessage());
		} finally {
			if(fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
				}
			if(csvReader != null)
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
				}
		}
		return allRecords;
	}

}
