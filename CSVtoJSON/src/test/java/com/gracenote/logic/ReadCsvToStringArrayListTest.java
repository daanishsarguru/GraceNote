package com.gracenote.logic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gracenote.constants.CsvConstants;
import com.gracenote.logic.implementations.ReadCsvToStringArrayList;

public class ReadCsvToStringArrayListTest {
	
	WriteInterface writeObj;
	ReadInterface readObj;
	
	@Before
	public void initializeData() {
		
		// Initialize objects
		readObj = new ReadCsvToStringArrayList();
		
	}

	@Test
	public void testReadCsvToStringArray_numberOfRecordsRead() {
		// Checking number of records read
		assertEquals(4, readObj.readToStringArrayList(CsvConstants.TEST_INPUT_FILE_PATH).size());
	}
	
	@Test
	public void testReadCsvToStringArray_checkArrayRead() {
		// Reading a sample file of 3 records and checking equality
		
		List<String[]> testRecordArray = new ArrayList<String[]>();
		
		String[] firstRecord = {"1/2/09 6:17","Product1","1200","Mastercard",
				"carolina","Basildon","England","United Kingdom","1/2/09 6:00",
				"1/2/09 6:08","51.5","-1.1166667"};
		testRecordArray.add(firstRecord);
		
		String[] secondRecord  = {"1/2/09 4:53","Product1","1200","Visa",
				"Betina","Parkville","MO","United States","1/2/09 4:42",
				"1/2/09 7:49","39.195","-94.68194"};
		testRecordArray.add(secondRecord);
		
		String[] thirdRecord = {"1/2/09 13:08","Product1","1200","Mastercard",
				"Federica e Andrea","Astoria","OR","United States","1/1/09 16:21",
				"1/3/09 12:32","46.18806","-123.83"};
		testRecordArray.add(thirdRecord);
		
		List<String[]> actualRecordArray = readObj.readToStringArrayList(CsvConstants.TEST_INPUT_FILE_PATH);
		
		for(int i=0; i < testRecordArray.size(); i++)
			assertArrayEquals("Unequal at index: "+i, testRecordArray.get(i), actualRecordArray.get(i+1));
	}

}
