package com.gracenote.logic;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gracenote.bean.ResponseBean;
import com.gracenote.bean.SalesDataBean;
import com.gracenote.constants.CsvConstants;
import com.gracenote.logic.implementations.Calculations;
import com.gracenote.logic.implementations.ParseStringArrayToList;
import com.gracenote.logic.implementations.ReadCsvToStringArrayList;
import com.gracenote.logic.implementations.WriteJson;

public class WriteJsonTest {

	// Sample test file which contains only first three rows from the original file
	List<SalesDataBean> salesDataList;
	String responseString;
	ResponseBean responseBean;
	WriteInterface writeObj;
	ReadInterface readObj;
	CalculationsInterface calculations;
	ParseInterface parseObj;
	
	@Before
	public void initializeData() {
		
		// Initialize objects
		readObj = new ReadCsvToStringArrayList();
		writeObj = new WriteJson();
		calculations = new Calculations();
		parseObj = new ParseStringArrayToList();
		
		salesDataList = (List<SalesDataBean>) parseObj.parseStringArrayListToObjectList(readObj.readToStringArrayList(CsvConstants.TEST_INPUT_FILE_PATH));
		calculations.calculateAccountAge(salesDataList);
		long sales = calculations.calculateSales(salesDataList, 2018, "before", true);
		responseBean = new ResponseBean();
		responseBean.setSalesDataList(salesDataList);
		responseBean.setSales(sales);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String responseJson = gson.toJson(responseBean);
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(responseJson);
		responseString = gson.toJson(jsonElement);
		
	}
	
	@Test
	public void testWriteJson() {
		
		// Writing to the file and then reading and checking whether the content written is equal to what we wanted to write
		
		writeObj.writeToFile(responseBean, CsvConstants.TEST_OUTPUT_FILE_PATH);
		
		try {
			String fileContent = new String(Files.readAllBytes(Paths.get(CsvConstants.TEST_OUTPUT_FILE_PATH+"output.json")));
			assertEquals(responseString, fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
