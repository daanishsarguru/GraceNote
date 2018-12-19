package com.gracenote.logic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gracenote.bean.SalesDataBean;
import com.gracenote.constants.CsvConstants;
import com.gracenote.logic.implementations.Calculations;
import com.gracenote.logic.implementations.ParseStringArrayToList;
import com.gracenote.logic.implementations.ReadCsvToStringArrayList;

public class CalculationsTest {

	// Testing using sample test file which contains only first three rows from the original file
	List<SalesDataBean> salesDataList;
	ReadInterface readObj;
	CalculationsInterface calculations;
	ParseInterface parseObj;

	@Before
	public void initializeData() {

		// Initialize objects
		readObj = new ReadCsvToStringArrayList();
		calculations = new Calculations();
		parseObj = new ParseStringArrayToList();
		
		// prepare the data to test our case
		salesDataList = (List<SalesDataBean>) parseObj.parseStringArrayListToObjectList(readObj.readToStringArrayList(CsvConstants.TEST_INPUT_FILE_PATH));
	}

	@Test
	public void testCalculateAccountAge() {
		// Checking age of first data record to verify the logic used
		calculations.calculateAccountAge(salesDataList);
		assertEquals("9 Years, 11 Months, 17 Days", salesDataList.get(1).getAccountAge());
	}

	@Test
	public void testCalculateSales() {
		// Checking total sales for the test file
		assertEquals(3600, calculations.calculateSales(salesDataList, 2018, "before", true));
	}

}
