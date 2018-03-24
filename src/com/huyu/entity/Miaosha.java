package com.huyu.entity;

public class Miaosha {
	private int id;
	private String start_time;//开始时间
	private String end_time;//介绍时间
	private double real_price;//秒杀价格
	private int goods_id;//商品ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public Miaosha(int id, String start_time, String end_time,
			double real_price, int goods_id) {
		super();
		this.id = id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.real_price = real_price;
		this.goods_id = goods_id;
	}
	public Miaosha(String start_time, String end_time, double real_price,
			int goods_id) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.real_price = real_price;
		this.goods_id = goods_id;
	}
	public Miaosha() {
		super();
	}
	@Override
	public String toString() {
		return "Miaosha [id=" + id + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", real_price=" + real_price
				+ ", goods_id=" + goods_id + "]";
	}
	
}
