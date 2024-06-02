package model.entity;

import java.sql.Date;

public class SalesData {
	 private String  date;
     private int volume;
     private String productName;
     
	public SalesData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SalesData(String date, int volume , String productName) {
		super();
		this.date = date;
		this.volume = volume;
		this.productName = productName;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	
}