package model.entity;

import java.sql.Date;

public class SalesData {
	 private String  date;
     private int volume;
     
     
	public SalesData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SalesData(String date, int volume) {
		super();
		this.date = date;
		this.volume = volume;
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

	
	
}