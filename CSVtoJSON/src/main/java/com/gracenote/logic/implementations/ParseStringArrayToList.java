package com.gracenote.logic.implementations;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.gracenote.bean.SalesDataBean;
import com.gracenote.constants.CsvConstants;
import com.gracenote.logic.ParseInterface;
import com.gracenote.utility.LoggerClass;

public class ParseStringArrayToList implements ParseInterface {

	@Override
	public List<SalesDataBean> parseStringArrayListToObjectList(List<String[]> salesDataStringArrayList) {
		String dateFormat = CsvConstants.DATE_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);  
		List<SalesDataBean> salesDataList = new ArrayList<SalesDataBean>();
		
		// Start at 1 as first row is header row
		// For each row, check the property datatype and set them accordingly
		// Any exception during this flow signifies that the input from the file is in an invalid format
		// In case the date format is to be changed, it can be changed from the CsvConstants.java file
		for(int i=1; i<salesDataStringArrayList.size(); i++) {
			SalesDataBean currentRowBean = new SalesDataBean();
			for(int j=0; j<CsvConstants.CSV_HEADER.length; j++) {
				try {
					if(PropertyUtils.getPropertyType(currentRowBean, CsvConstants.CSV_HEADER[j]).equals(Date.class)) {
						// For Date values
						try {
							PropertyUtils.setProperty(currentRowBean, CsvConstants.CSV_HEADER[j], formatter.parse(salesDataStringArrayList.get(i)[j]));
						} catch (ParseException e) {
							e.printStackTrace();
							LoggerClass.logErrorMessages(e.getMessage());
						}
					} else if(PropertyUtils.getPropertyType(currentRowBean, CsvConstants.CSV_HEADER[j]).equals(int.class)) {
						// For Integers values
						PropertyUtils.setProperty(currentRowBean, CsvConstants.CSV_HEADER[j], Integer.parseInt(salesDataStringArrayList.get(i)[j].replace(",", "")));
					} else if(PropertyUtils.getPropertyType(currentRowBean, CsvConstants.CSV_HEADER[j]).equals(double.class)) {
						// For Double values
						PropertyUtils.setProperty(currentRowBean, CsvConstants.CSV_HEADER[j], Double.parseDouble(salesDataStringArrayList.get(i)[j].replace(",", "")));
					} else {
						// For normal Strings
						PropertyUtils.setProperty(currentRowBean, CsvConstants.CSV_HEADER[j], salesDataStringArrayList.get(i)[j].trim());
					}
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					LoggerClass.logErrorMessages(e.getMessage());
				}
			}
			salesDataList.add(currentRowBean);
		}
		
		return salesDataList;
	}

}
