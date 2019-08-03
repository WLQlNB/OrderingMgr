package com.wlq.pojo;

public class Dish {
	private int id;
	private String name;
	private String prices;
	private int likeNumber;
	
	public Dish() {
		
	}
	public Dish(int id, String name, String prices, int likeNumber) {
		this();
		this.id = id;
		this.name = name;
		this.prices = prices;
		this.likeNumber = likeNumber;
	}
	
	@Override
	public String toString() {
		return  id + "\t\t" + name + "\t" + prices + "\t\t" + likeNumber+"\n" ;
	}
	public int getId() {
		return id;
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
	public String getPrices() {
		return prices;
	}
	public void setPrices(String prices) {
		this.prices = prices;
	}
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	
}
