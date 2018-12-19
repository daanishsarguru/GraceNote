package com.gracenote.logic;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gracenote.bean.SalesDataBean;
import com.gracenote.constants.CsvConstants;
import com.gracenote.logic.implementations.ParseStringArrayToList;
import com.gracenote.logic.implementations.ReadCsvToStringArrayList;

public class ParseStringArrayToListTest {

	// Testing using sample test file which contains only first three rows from the original file
	List<String[]> salesDataArray;
	List<SalesDataBean> salesDataList;
	ReadInterface readObj;
	ParseInterface parseObj;

	@Before
	public void initializeData() {

		String dateFormat = CsvConstants.DATE_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		// Initialize objects
		readObj = new ReadCsvToStringArrayList();
		parseObj = new ParseStringArrayToList();

		// Prepare the data for our test scenario
		salesDataArray = readObj.readToStringArrayList(CsvConstants.TEST_INPUT_FILE_PATH);
		salesDataList = new ArrayList<SalesDataBean>();
		try {

			// Sample data, same as the data in test file
			salesDataList.add(new SalesDataBean(formatter.parse("1/2/09 6:17"), "Product1", 1200, "Mastercard",
					"carolina", "Basildon", "England", "United Kingdom", formatter.parse("1/2/09 6:00"),
					formatter.parse("1/2/09 6:08"), 51.5, -1.1166667));
			salesDataList.add(new SalesDataBean(formatter.parse("1/2/09 4:53"), "Product1", 1200, "Visa", "Betina",
					"Parkville", "MO", "United States", formatter.parse("1/2/09 4:42"), formatter.parse("1/2/09 7:49"),
					39.195, -94.68194));
			salesDataList.add(new SalesDataBean(formatter.parse("1/2/09 13:08"), "Product1", 1200, "Mastercard",
					"Federica e Andrea", "Astoria", "OR", "United States", formatter.parse("1/1/09 16:21"),
					formatter.parse("1/3/09 12:32"), 46.18806, -123.83));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testParseStringArrayListToObjectArrayList() {
		// Test whether our data and the parsed data are equal
		assertEquals(salesDataList, parseObj.parseStringArrayListToObjectList(salesDataArray));

	}

}
