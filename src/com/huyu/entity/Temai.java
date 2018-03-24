package com.huyu.entity;

public class Temai {
	private int id;
	private int goods_id;
	private String activity_time;
	private double real_price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getActivity_time() {
		return activity_time;
	}
	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public Temai(int id, int goods_id, String activity_time, double real_price) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.activity_time = activity_time;
		this.real_price = real_price;
	}
	public Temai(int goods_id, String activity_time, double real_price) {
		super();
		this.goods_id = goods_id;
		this.activity_time = activity_time;
		this.real_price = real_price;
	}
	public Temai() {
		super();
	}
	

}
