package com.gracenote.bean;

import java.util.List;

public class ResponseBean {
	private List<SalesDataBean> salesDataList;
	private long sales;
	public List<SalesDataBean> getSalesDataList() {
		return salesDataList;
	}
	public void setSalesDataList(List<SalesDataBean> salesDataList) {
		this.salesDataList = salesDataList;
	}
	public long getSales() {
		return sales;
	}
	public void setSales(long sales) {
		this.sales = sales;
	}
}
