package com.huyu.entity;

public class Order_details {
	private int id;
	private int goods_id;//商品ID
	private int amount;//商品数量
	private int order_id;//订单号
	private double real_price;//实际价格
	private int state;//是否使用抵现金 1是  0否
	private String color_norms;//颜色和规格
	private double integral;//使用积分
	private int type;//1,2,3 积分使用、vip商城、vip券
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getColor_norms() {
		return color_norms;
	}
	public void setColor_norms(String color_norms) {
		this.color_norms = color_norms;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Order_details(int id, int goods_id, int amount, int order_id, double real_price, int state, String color_norms, double integral, int type) {
		this.id = id;
		this.goods_id = goods_id;
		this.amount = amount;
		this.order_id = order_id;
		this.real_price = real_price;
		this.state = state;
		this.color_norms = color_norms;
		this.integral = integral;
		this.type = type;
	}

	public Order_details(int goods_id, int amount, int order_id, double real_price, int state, String color_norms, double integral, int type) {
		this.goods_id = goods_id;
		this.amount = amount;
		this.order_id = order_id;
		this.real_price = real_price;
		this.state = state;
		this.color_norms = color_norms;
		this.integral = integral;
		this.type = type;
	}

	public Order_details() {
		super();
	}
	

}
