package com.wlq.pojo;


public class OrderingSet {
	private int id;
	private String name;
	private String time;
	private String address;
	private double prices;
	private String status;
	
	public int getId() {
		return id;
	}
	public OrderingSet(int id, String name, String time, String address, double prices, String status) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.address = address;
		this.prices = prices;
		this.status = status;
	}
	public OrderingSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return  id + "\t\t" + name + "\t\t" + time +"\t\t"+ address + "\t\t"
				+ prices + "\t\t" + status+"\n" ;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return time;
	}
	public void setDate(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPrices() {
		return  prices;
	}
	public void setPrices(double  prices) {
		this. prices =  prices;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
