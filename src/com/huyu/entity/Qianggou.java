package com.huyu.entity;

public class Qianggou {
	private int id;
	private int goods_id;//商品ID
	private double real_price;//抢购价格
	private String activity_time;//抢购日期
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
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public String getActivity_time() {
		return activity_time;
	}
	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
	}
	public Qianggou(int id, int goods_id, double real_price,
			String activity_time) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.real_price = real_price;
		this.activity_time = activity_time;
	}
	public Qianggou(int goods_id, double real_price, String activity_time) {
		super();
		this.goods_id = goods_id;
		this.real_price = real_price;
		this.activity_time = activity_time;
	}
	public Qianggou() {
		super();
	}
	

}
