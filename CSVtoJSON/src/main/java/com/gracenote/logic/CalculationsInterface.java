package com.gracenote.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.gracenote.bean.SalesDataBean;

public interface CalculationsInterface {
	public void calculateAccountAge(List<SalesDataBean> salesDataList);
	
	public long calculateSales(List<SalesDataBean> salesDataList, int year, 
			String beforeAfter, boolean includeYear);
}
