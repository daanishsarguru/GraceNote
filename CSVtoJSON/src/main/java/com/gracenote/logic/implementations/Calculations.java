package com.gracenote.logic.implementations;

import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gracenote.bean.SalesDataBean;
import com.gracenote.logic.CalculationsInterface;

public class Calculations implements CalculationsInterface {

	@Override
	public void calculateAccountAge(List<SalesDataBean> salesDataList) {

		// 1. Calculate the account age, based on account created date
		// 2. Find period between today and the account created date
		// 3. Convert to period to years, months, days format

		Date todaysDate = new Date();
		for (SalesDataBean currentSalesRecord : salesDataList) {
			
			Period betweenBetweenDates = Period.between(currentSalesRecord.getAccountCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					todaysDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			
			currentSalesRecord.setAccountAge(betweenBetweenDates.getYears()+" Years, "
					+betweenBetweenDates.getMonths()+ " Months, "+betweenBetweenDates.getDays()+" Days");
		}
	}

	@Override
	public long calculateSales(List<SalesDataBean> salesDataList, int year, String beforeAfter, boolean includeYear) {

		// Calculate sales based on user inputs and the Account Created Date column 
		// For the loop, for each record
		// If transaction year is less than user specified year and user has selected 'before', then add the 'price' to our sales
		// If transaction year is greater than user specified year and user has selected 'after', then add the 'price' to our sales
		// If transaction year is equal to the user specified year and user has selected 'includeYear' as true, then add the 'price' to our sales
		
		long sales = 0;
		for (SalesDataBean currentSalesRecord : salesDataList) {
			Date currentRecordDate = currentSalesRecord.getAccountCreated();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentRecordDate);
			int currentRecordYear = calendar.get(Calendar.YEAR);
			if (currentRecordYear > year && beforeAfter.equals("after"))
				sales += currentSalesRecord.getPrice();
			else if (currentRecordYear < year && beforeAfter.equals("before"))
				sales += currentSalesRecord.getPrice();
			else if (currentRecordYear == year && includeYear)
				sales += currentSalesRecord.getPrice();
		}
		return sales;
	}
}
