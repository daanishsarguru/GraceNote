package com.gracenote.bean;

import java.util.Date;

public class SalesDataBean {
	private Date transactionDate;
	private String product;
	private int price;
	private String paymentType;
	private String name;
	private String city;
	private String state;
	private String country;
	private Date accountCreated;
	private Date lastLogin;
	private double latitude;
	private double longitude;
	private String accountAge;
	
	public SalesDataBean() {
		
	}
	
	public SalesDataBean(Date transactionDate, String product, int price, String paymentType, String name, String city,
			String state, String country, Date accountCreated, Date lastLogin, double latitude, double longitude) {
		super();
		this.transactionDate = transactionDate;
		this.product = product;
		this.price = price;
		this.paymentType = paymentType;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.accountCreated = accountCreated;
		this.lastLogin = lastLogin;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public String getAccountAge() {
		return accountAge;
	}

	public void setAccountAge(String accountAge) {
		this.accountAge = accountAge;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getAccountCreated() {
		return accountCreated;
	}
	public void setAccountCreated(Date accountCreated) {
		this.accountCreated = accountCreated;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "SalesDataBean [transactionDate=" + transactionDate + ", product=" + product + ", price=" + price
				+ ", paymentType=" + paymentType + ", name=" + name + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", accountCreated=" + accountCreated + ", lastLogin=" + lastLogin
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", accountAge=" + accountAge + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountCreated == null) ? 0 : accountCreated.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + price;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesDataBean other = (SalesDataBean) obj;
		if (accountCreated == null) {
			if (other.accountCreated != null)
				return false;
		} else if (!accountCreated.equals(other.accountCreated))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (price != other.price)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}
	
	
}
